package com.nitro.platform.init;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.nitro.platform.svc.IUserSvc;
import com.nitro.platform.svc.UserSvc;
import com.nitro.platform.vo.User;
import com.nitro.platform.vo.UserResponse;

/**
 * Thread-safe singleton to start the File Processing
 * @author SBiswas
 *
 */
public enum AllEventsInitiator {
	
	INSTANCE;
	
	public static AllEventsInitiator getInstance(){
		return INSTANCE;
	}
		
	// The Blocking Queue used to store FileRequests.
	// The fixed capacity of the BloackingQueue implementation.
	// The thought process here is since we would distribute the processing to multiple nodes,
	// we can have fixed bounds. Hence ArrayBlockingQueue and a little bit of control with 
	// an EventManager for 1 producer 1 consumer-manager
		
	private BlockingQueue<Object> fileProcessingQ = null;
	
	// Set capacity of the Blocking Queue.
	private static final int QUEUE_SIZE = 10000;

	// The Event Manager co-ordinating the consumption of File Events.
	private AllEventsManager m_EventMgr = null;
	
	// EventManagerFuture
	private ScheduledFuture<?> m_EventMgrFuture;
	
	private IUserSvc userSvc;
	private User user;
		

	/**
	 * Overall orchestration of the Async File Processing framework
	 */
	public void schedule(){
		createUser();
		setupFileProcessingQueue();
		scheduleFileEvents();
		scheduleUserEvents();
		// EventManager that manages the worker consumer threads -
		scheduleEventManager();
	}
	
	private void createUser(){
		user = new User("sanb2008@gmail.com", "admin123");
		userSvc = new UserSvc();
		userSvc.create(user);
		
	}
	
	/**
	 * Creates the Blocking Queue implementation 
	 */
	private void setupFileProcessingQueue() {
		fileProcessingQ = new ArrayBlockingQueue<Object>(QUEUE_SIZE);
	}
	
	
	/**
	 * Create and start the Scheduled FileEventProducer thread.
	 * Performs 2 taks - 
	 * (1) Polls for files in a specific directory
	 * (2) Picks up a file and puts it into the File Processing Queue.
	 * 
	 */
	private void scheduleFileEvents(){
		
		FileEventProducer fPoller = new FileEventProducer(fileProcessingQ);
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		ScheduledFuture<?> future = executor.scheduleAtFixedRate(fPoller, 0, 30, TimeUnit.SECONDS);
		System.out.println("Started the FileEventProducer Executor...");
				
	}
	
	
	/**
	 * Create and start the All Event Manager Executor.
	 * Performs task - 
	 * (1) Creates and starts a specified pool of Worker threads.
	 */
	private void scheduleEventManager(){
		
		m_EventMgr = new AllEventsManager(fileProcessingQ, 1);
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		m_EventMgrFuture = executor.scheduleAtFixedRate(m_EventMgr, 0, 1, TimeUnit.HOURS);
		System.out.println("Started the AllEventsManager Executor...");
		
	}
	
	
	private void scheduleUserEvents(){
		
		UserEvent userEvent = new UserEvent(user);
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		ScheduledFuture<?> future = executor.scheduleAtFixedRate(userEvent, 0, 10, TimeUnit.SECONDS);
		System.out.println("Started the UserEvent Executor...");
				
	}
		
}

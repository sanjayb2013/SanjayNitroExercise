package com.nitro.platform.init;

import java.io.File;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.nitro.platform.svc.FileAnalyticsSvc;
import com.nitro.platform.svc.IFileAnalyticsSvc;
import com.nitro.platform.vo.FileAnalyticsRequest;
import com.nitro.platform.vo.QueueFileRequest;


class AllEventsManager implements Runnable {
	
	private final ExecutorService m_exec;
	private final BlockingQueue<Object> fileProcQ;
	private final int m_WorkerPoolSize;
	protected volatile boolean m_bShutdown = false;

	/**
	 * Sets up the Concurrent Queue and a pre-set number of consumer 
	 * executor threads to start reading off it.
	 * @param q
	 * @param poolSize
	 */
	AllEventsManager(BlockingQueue<Object> q, int poolSize) {
		fileProcQ = q;
		m_WorkerPoolSize = poolSize;
		// Create the fixed thread pool of Worker Consumer threads to process thr file requests
		m_exec = Executors.newFixedThreadPool(m_WorkerPoolSize);
	}
	
	@Override
	public void run(){
		// Continuously polls for the QueueFileRequest data on the Queue for Worker Threads to process
		// unless shutdown.
		while (!m_bShutdown){
			
			Object req = fileProcQ.poll(); // Does not wait, returns NULL if queue is empty
			if (req != null) {
				//System.out.println("Dequeuing a File Queue Event...");
				Worker w = new Worker(req);
				Future<?> w_future = m_exec.submit(w); // SUBMIT THE TASK
			}
		}
	}
		
	public void shutdown() {
		m_exec.shutdown();
	}
	
	public void shutdownNow() {
		m_bShutdown = true;
	}

}


/** The runnable implementation that does the work of processing the enqueued File processing */
class Worker implements Runnable {
	private Object m_req = null;
	
	public Worker(Object req) {
		this.m_req = req;
	}

	
	@Override
	public void run() {
		try {
			if ( m_req instanceof QueueFileRequest ){
				QueueFileRequest qReq = (QueueFileRequest) m_req;
				File qFile = qReq.getFileToProcess();
				FileAnalyticsRequest aReq = new FileAnalyticsRequest(qFile);
				//System.out.println("Worker..Invoking the analytics processor for the File Object");
				IFileAnalyticsSvc fileAnalyticsSvc = new FileAnalyticsSvc(); // Ideally get through Spring or some static factory
				fileAnalyticsSvc.applyAnalytics(aReq);
				
			}else{
				System.out.println("Enqueued Object not valid");
			}
		
		 } finally {
			 //System.out.println("Worker Thread run ending...");
		}
	}
	
}

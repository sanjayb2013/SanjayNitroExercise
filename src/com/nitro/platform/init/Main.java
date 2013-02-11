package com.nitro.platform.init;


public class Main {

		
	public static void main(String[] args){
		
		try {
		
		// Start the Executor background threads for file processing and applying analytics
		// Since I did not get a chance to hook up the user file uploads from the Play front-end,
		// I simulated the user interaction with a user thread
		AllEventsInitiator starter = AllEventsInitiator.getInstance();
		starter.schedule();
							
		}catch (Exception ex){
			ex.getMessage();
		}
		
	}
}

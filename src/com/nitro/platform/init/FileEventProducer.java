package com.nitro.platform.init;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import com.nitro.platform.utils.FileHandlingUtils;
import com.nitro.platform.vo.QueueFileRequest;

class FileEventProducer implements Runnable {

	private final BlockingQueue<Object> fileProcQ;
	private volatile boolean m_bShutdown = false; 
		
	/**
	 * Initialize the FileEventProducer 
	 * @param q
	 */
	FileEventProducer(BlockingQueue<Object> q){
		this.fileProcQ = q;
	}
	
	@Override
	public void run() {
		while (!m_bShutdown){
			QueueFileRequest qReq = null;
			try {
				qReq = new QueueFileRequest(FileHandlingUtils.getFile());
				fileProcQ.put(qReq);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(IOException ioex){
				ioex.printStackTrace();
			}
		}
	}
	
}

package com.nitro.platform.vo;

import java.io.File;
import java.io.Serializable;

/**
 * A Wrapper around the File objects in the directory
 * @author SBiswas
 *
 */
public class QueueFileRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private final File fileToProcess;
	
	public QueueFileRequest(File file){
		this.fileToProcess = file;
	}

	public File getFileToProcess() {
		return fileToProcess;
	}
	
	

	
}

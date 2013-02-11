package com.nitro.platform.vo;

import java.io.File;

public class FileAnalyticsRequest {
	
	private final File fileToAnalyze;
	
	
	public FileAnalyticsRequest(File file){
		this.fileToAnalyze = file;
	}

	public File getFileToAnalyze() {
		return fileToAnalyze;
	}
	
	

}

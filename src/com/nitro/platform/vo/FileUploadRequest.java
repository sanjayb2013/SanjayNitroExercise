package com.nitro.platform.vo;

import java.io.Serializable;

public class FileUploadRequest implements Serializable{
	
	private static final long serialVersionUID = 3395820230361343721L;
	
	final private User user;
	final private String fileName;
	final private String text;
			
	public FileUploadRequest(User user, String text){
		this.user = user;
		this.fileName = System.nanoTime()+"_input.txt";
		this.text = text;
	}

	public String getText() {
		return text;
	}


	public User getUser() {
		return user;
	}


	public String getFileName() {
		return fileName;
	}
	
		
}

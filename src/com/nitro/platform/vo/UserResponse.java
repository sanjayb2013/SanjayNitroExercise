package com.nitro.platform.vo;

public class UserResponse {
	
	private FileAnalyticsResponse fileTags;
	private final User user;
	
	public UserResponse(User user){
		this.user = user;
	}

	public FileAnalyticsResponse getFileTags() {
		return fileTags;
	}

	public void setFileTags(FileAnalyticsResponse fileTags) {
		this.fileTags = fileTags;
	}

	public User getUser() {
		return user;
	}
		

}

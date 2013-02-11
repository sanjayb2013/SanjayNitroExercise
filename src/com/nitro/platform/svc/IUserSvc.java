package com.nitro.platform.svc;

import com.nitro.platform.vo.User;
import com.nitro.platform.vo.UserResponse;

public interface IUserSvc {
	
	public UserResponse create(User user);
	
	public UserResponse uploadFile(User user, String content) throws Exception;
	
	public UserResponse getFileTags(User user);
		

}

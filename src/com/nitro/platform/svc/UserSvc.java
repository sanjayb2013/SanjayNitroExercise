package com.nitro.platform.svc;

import com.nitro.platform.vo.User;
import com.nitro.platform.vo.UserResponse;
import static com.nitro.platform.svc.UserSvcHelper.*; 

public class UserSvc implements IUserSvc {
	
	/**
	 * User create 
	 */
	@Override
	public UserResponse create(User user) {
		return createUser(user);
		
	}

	public UserResponse uploadFile(User user, String text) throws Exception{
		return uploadUserFile(user, text);
		
	}

	public UserResponse getFileTags(User user) {
		return retrieveFileTags(user);
	}
	

}

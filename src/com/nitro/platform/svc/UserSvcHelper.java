package com.nitro.platform.svc;

import java.util.HashSet;
import java.util.Set;

import com.nitro.platform.vo.FileAnalyticsResponse;
import com.nitro.platform.vo.FileUploadRequest;
import com.nitro.platform.vo.User;
import com.nitro.platform.vo.UserResponse;

import static com.nitro.platform.svc.FileUploadSvc.FAILED;

/**
 * Implements delegated static helper implementations for faster access
 * Note : scope only default access as only visible within this package
 *  
 */
class UserSvcHelper {
	
	// Storing in memory as could not get up Postgres - so mocking
	public static Set<User> userSet = new HashSet<User>();
		
	static IFileUploader fileUploader;      // We should autowire this with Spring for the implementation
	static IFileAnalyticsSvc fileAnalytivsSvc; // We should autowire this with Spring for the implementation
	
	static UserResponse createUser(User user){
		if ( !userSet.contains(user.getUserID())){
			user.setStatus("Active");
			userSet.add(user);
		}
		return new UserResponse(user);
	}
	
	static UserResponse uploadUserFile(User user, String contents) throws Exception {
		FileUploadRequest fileUploadReq = new FileUploadRequest(user, contents);
		fileUploader = new FileUploadSvc();
		String upldStatus = fileUploader.upload(fileUploadReq);
		if (upldStatus.equalsIgnoreCase(FAILED) ) throw new Exception("File Upload failed");
		return new UserResponse(user);
	}
	
	static UserResponse retrieveFileTags(User user){
		UserResponse uResp = new UserResponse(user);
		fileAnalytivsSvc = new FileAnalyticsSvc();
		FileAnalyticsResponse afResp = fileAnalytivsSvc.getEmailTagsByFile(user);
		uResp.setFileTags(afResp);
		return uResp;
	}
	
}

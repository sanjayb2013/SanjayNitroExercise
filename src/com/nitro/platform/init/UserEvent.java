package com.nitro.platform.init;

import java.io.File;
import java.util.Map;

import com.nitro.platform.svc.IUserSvc;
import com.nitro.platform.svc.UserSvc;

import com.nitro.platform.vo.FileAnalyticsResponse;

import com.nitro.platform.vo.Tag;
import com.nitro.platform.vo.User;
import com.nitro.platform.vo.UserResponse;

class UserEvent implements Runnable {

	private boolean m_bShutdown = false;
	private User user;
			
	/**
	 * Initialize the FileEventProducer 
	 * @param q
	 */
	UserEvent(User user){
		this.user = user;
	}
	
	@Override
	public void run() {
		while (!m_bShutdown){
					
			IUserSvc userSvc = new UserSvc();
			String text = "Uploading file for user sanb2008@gmail.com";
			try {
				userSvc.uploadFile(user, text);
				UserResponse uResp = userSvc.getFileTags(user);
				FileAnalyticsResponse faResp = uResp.getFileTags();
				Map<File, Tag> fileTagMap = faResp.getFileTagsMap();
				Thread.sleep(1000);
				System.out.println("FileTagInfo for user :" +user.getUserID() + "|"+fileTagMap);
				text = "Uploading file for user sanjay_biswas@hotmail.com";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

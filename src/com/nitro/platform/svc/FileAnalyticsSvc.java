package com.nitro.platform.svc;

import com.nitro.platform.vo.FileAnalyticsRequest;
import com.nitro.platform.vo.FileAnalyticsResponse;
import com.nitro.platform.vo.User;

import static com.nitro.platform.svc.FileAnalyticsSvcHelper.*;

public class FileAnalyticsSvc implements IFileAnalyticsSvc {
	
	
	public void applyAnalytics(FileAnalyticsRequest fReq) {
		applyAnalyticsForEmail(fReq);
	}
	
	
	public FileAnalyticsResponse getEmailTagsByFile(User user){
		return retrieveEmailTagsByFile(user);
		
	}

	

}

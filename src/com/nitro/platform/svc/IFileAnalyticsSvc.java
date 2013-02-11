package com.nitro.platform.svc;

import com.nitro.platform.vo.FileAnalyticsRequest;
import com.nitro.platform.vo.FileAnalyticsResponse;
import com.nitro.platform.vo.User;

public interface IFileAnalyticsSvc {
	
	public void applyAnalytics(FileAnalyticsRequest fReq);
	
	public FileAnalyticsResponse getEmailTagsByFile(User user);

}

package com.nitro.platform.svc;

import com.nitro.platform.vo.FileUploadRequest;

public interface IFileUploader {
	
	public String upload(FileUploadRequest reqObj);

}

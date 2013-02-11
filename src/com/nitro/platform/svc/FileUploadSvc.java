package com.nitro.platform.svc;

import java.io.File;

import com.nitro.platform.utils.FileHandlingUtils;
import com.nitro.platform.vo.FileUploadRequest;

public class FileUploadSvc implements IFileUploader {
	
	public static final String FILE_SEP = File.separator;
	public static final String FILE_PATH = "C:/FileStore/input";
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED = "FAILED";

	/**
	 * Actual file upload and DB file path pointer code here.
	 */
	@Override
	public String upload(FileUploadRequest reqObj) {
		return createFile(reqObj);
	}
	
	private String createFile(FileUploadRequest req){
		File newFile = new File(FILE_PATH+FILE_SEP+req.getFileName());
		return FileHandlingUtils.createFile(newFile, req.getText()) ? SUCCESS : FAILED;
	}


}

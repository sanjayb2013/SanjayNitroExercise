package com.nitro.platform.vo;

import java.io.File;
import java.util.Map;

public class FileAnalyticsResponse {
	
	private Map<File, Tag> fileTagsMap;

	public Map<File, Tag> getFileTagsMap() {
		return fileTagsMap;
	}

	public void setFileTagsMap(Map<File, Tag> fileTagsMap) {
		this.fileTagsMap = fileTagsMap;
	}

			

}

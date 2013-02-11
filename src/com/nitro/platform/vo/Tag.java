package com.nitro.platform.vo;

import java.util.List;

public class Tag {
	
	private final List<String> tags;
	private final String tagType;
	
	public List<String> getTags() {
		return tags;
	}

	public String getTagType() {
		return tagType;
	}

	public Tag(List<String> tags, String type){
		this.tags = tags;
		this.tagType = type;
	}
	
	public int getTagCount() {
		int i = 0;
		if (tags != null) 
			i =  tags.size();
		return i;
	}


}

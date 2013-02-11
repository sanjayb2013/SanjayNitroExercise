package com.nitro.platform.cache;

import com.nitro.platform.utils.PlatformRuntimeException;

public enum FileTagCache implements ICache{
	
	INSTANCE;
	
	public FileTagCache FileTagCache(){
		return INSTANCE; 
	}
	
	@Override
	public void refreshCache(CacheName cacheNm) throws PlatformRuntimeException{
		// Make a DB call here to refresh the FILE_PROC cache section
	}

}

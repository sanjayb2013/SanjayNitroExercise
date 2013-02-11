package com.nitro.platform.cache;

import com.nitro.platform.utils.PlatformRuntimeException;

public interface ICache {
	/** Note: Keep the names in the order of their reference usage. */
	public enum CacheName {
		FILE_PROC
	}
	
	public void refreshCache(CacheName cacheNm) throws PlatformRuntimeException;
}

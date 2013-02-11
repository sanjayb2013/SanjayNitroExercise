package com.nitro.platform.svc;

import java.io.File;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nitro.platform.utils.FileHandlingUtils;
import com.nitro.platform.vo.FileAnalyticsRequest;
import com.nitro.platform.vo.FileAnalyticsResponse;
import com.nitro.platform.vo.Tag;
import com.nitro.platform.vo.User;

class FileAnalyticsSvcHelper {
	
	// All of the relationships are cached here due to the fact that I didn't get a chance to hook up
	// Postgres. Ideally these would be looked up from the DB.
	
	public static Map<String, Set<File>> userFileMap 
						= new HashMap<String, Set<File>>();
	
	public static Map<File, Tag> fileTagsMap 
						= new HashMap<File, Tag>();
	
	public static Set<File> fileSet = Collections.synchronizedSet(new HashSet<File>());
	
	/**
	 * Apply analytics rule and persist to DB.
	 * Due to time constraints I'm storing in memory for now.
	 *  
	 * @param fReq
	 */
	static void applyAnalyticsForEmail(FileAnalyticsRequest fReq){
		List<String> tags = analyzeEmailOccurrencesByDomain(fReq.getFileToAnalyze());
		System.out.println("FileAnalyticsSvcHelper : Email tags found :"+tags);
		Tag tag = new Tag(tags, "Email");
		fileSet.add(fReq.getFileToAnalyze());
		fileTagsMap.put(fReq.getFileToAnalyze(), tag);
		// Since I'm not able to hook up a DB row, I'm always storing the user name (email) as the first
		// email in the files uoloaded. This is only for the purpose of moving forward without a Database.
		userFileMap.put(tags.get(0), fileSet);
	}
	
	
	/**
	 * Ideally this should look up the associations from the Postgres DB.
	 * I'm mocking up from the static cached data here.
	 *  
	 * @param user
	 * @param fReq
	 * @return
	 */
	static FileAnalyticsResponse retrieveEmailTagsByFile(User user){
		FileAnalyticsResponse fAResp = new FileAnalyticsResponse();
		Set<File> userFileSet = userFileMap.get(user.getUserID());
		System.out.println("User file Set :"+userFileSet);
		Map<File, Tag> userFileTagMap = new HashMap<File, Tag>();
		
		if ( userFileSet != null ){
		Set<File> tempSet = userFileSet;
		synchronized(tempSet){
			Iterator<File> iter = tempSet.iterator();
			while ( iter.hasNext() ){
				File f = iter.next();
				userFileTagMap.put(f , fileTagsMap.get(f));
			}
		}
		
		fAResp.setFileTagsMap(userFileTagMap);
		}
		return fAResp; 
		
	}
	
	private static List<String> analyzeEmailOccurrencesByDomain(File f){
		return FileHandlingUtils.parseFileForEmails(f);
		
	}
	
}

package com.nitro.platform.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

public class FileHandlingUtils {
	
	public static final String FILE_INPUT_DIR = "C:/FileStore/input";
	public static final String FILE_PROCESSED_DIR = "C:/FileStore/processed";
	public static final String FILE_SEP = File.separator;
	
	
	/**
	 * 
	 * @param f
	 */
	public static boolean createFile(File f, String contents){
		boolean b_created;
		Writer writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(f));
			writer.write(contents);
			b_created = true; 
		} catch (IOException iox){
			b_created = false;
		}finally {
			try {
				writer.close();
			}catch (Exception ex){
				b_created = false;
			}
	    }
		return b_created;
		
	}
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 */
	public static File getFile() throws IOException{
		File srcDir = new File(FILE_INPUT_DIR);
		File destDir = new File(FILE_PROCESSED_DIR);
		File filePicked = null;
		String fileName = null;
		File[] fileList = srcDir.listFiles();
		if ( fileList != null ){
			if (fileList.length > 0 ){
				fileName = fileList[0].getName();
				// Move the file to the processed dir
				try {
					FileUtils.copyFileToDirectory(fileList[0], destDir);
					File[] destFileList = destDir.listFiles();
					for ( File f : destFileList){
						if (f.getName().equals(fileName)){
							filePicked = f;
							break;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
					throw e;
				}
				boolean bstat = fileList[0].delete();
			}
		}
		return filePicked;
	}
	
	/**
	 * 
	 * @param f
	 * @return
	 */
	public static List<String> parseFileForEmails(File f){
		
		Scanner s = null;
		int counter = 0;
		List<String> emailTagList = new ArrayList<String>();
		
		try {
		 
			s = new Scanner(f);   
			Pattern p = Pattern.compile("([\\w+|\\.?]+)\\w+@([\\w+|\\.?]+)\\.(\\w{2,8}\\w?)");    
                   
			String str = null;   
				while ( (str = s.findWithinHorizon(p, 0)) != null ){    
					System.out.println(str);
					emailTagList.add(str);
					counter++;
					//System.out.println(getString(fileName));   
				}
			} catch(FileNotFoundException fex){
				fex.printStackTrace();
			} finally {   
				if (s != null) {   
					s.close();   
				}   
			}
		return emailTagList;
	}

}

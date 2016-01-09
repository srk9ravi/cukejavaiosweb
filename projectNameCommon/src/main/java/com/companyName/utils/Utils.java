package com.companyName.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipOutputStream;

import org.codehaus.plexus.util.FileUtils;

public class Utils {
	/**
	 * To generate the cucumber sandwich reports
	 * @throws Exception
	 */
	public static void ioscucumberSandwichReports() throws Exception{
		String cucumberSandwichJarpath = System.getProperty("user.dir")+ "/src/test/resources/cucumber-sandwich.jar";
		String reportsFolder = System.getProperty("user.dir")+ "/Reports/iOS";
		FileUtils.cleanDirectory(System.getProperty("user.dir")+"/Reports/iOS/cucumber-html-reports"); 
		Runtime.getRuntime().exec("java -jar "+cucumberSandwichJarpath+" -n "+" -f "+ reportsFolder+" -o "+reportsFolder);
		SimpleDateFormat format = new SimpleDateFormat("M:dd:yyyy HH-mm z");
	    String timeStamp =format.format(new Date());
		zipFolder(reportsFolder + "cucumber-html-reports", reportsFolder +"/cucumberiOSReports-"+timeStamp+".zip");
	}
	
	/**
	 * To zip the cucumber sandwich reports
	 * @param srcFolder
	 * @param destZipFile
	 * @throws IOException 
	 * @throws Exception
	 */
	public static void zipFolder(String srcFolder, String destZipFile) throws IOException {
	    ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(destZipFile));
	    zip.flush();
	    zip.close();
	  }
}

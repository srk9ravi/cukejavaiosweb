package com.companyName.utils;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides access to file driven properties.
 */
public class TestProperties {
	private static final Logger logger = LoggerFactory.getLogger(TestProperties.class);
	private static Properties		props;

	/**
	 * Get a property.
	 * @param key property name
	 * @return    property value
	 */
	public static String get(String key) throws Exception {
		if (null == props) {
			props = new Properties();

			InputStream is = ClassLoader.getSystemResourceAsStream("test.properties");
			props.load(is);
			is.close();
		}
		String value;
		if((value=System.getProperty(key))!=null){
			logger.debug("Overriden Property {} := {} ",key,value);
			return System.getProperty(key);
		}else if((value=props.getProperty(key))!=null){
			logger.trace("Property {} := {} ",key,value);
			return value;
		}
		throw new Exception("Property "+key+" Not Found for !!");
	}

	/**
	 * Get a property and decrypt it
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getDecrypted(String key) throws Exception{
		String encryptedVal =get(key);
		String decrypted = new String( Base64.decodeBase64(encryptedVal.getBytes()));
		return decrypted;
	}

	/**
	 * Load the property file
	 * @param propertiesFileName property file name
	 */
	public static void loadProperties(String propertiesFileName) throws Exception {	
		
		props = new Properties();

		InputStream is = ClassLoader.getSystemResourceAsStream(propertiesFileName);
		
		props.load(is);
		is.close();		
	}

	/**
	 * @param key
	 * @return URL stored in properties file
	 * @throws Exception
	 */
	public static String getURL(String key) throws Exception {
		String url =get(key);
		if(url.endsWith("/")){
			return url;
		}else{
			throw new Exception("Invalid URL must end with /"+url);
		}
	}
}

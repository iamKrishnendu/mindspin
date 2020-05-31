package com.test.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author KRISHNENDU
 * @category Handles property file. Read the values stores under config.properties file and return  
 * */

public class PropertyFileHandler {
	
	public Properties getPropertyValues() {
		String filePath = System.getProperty("user.dir")+"/config.properties";
		Properties prop = new Properties();
		
		try {
			FileInputStream fin = new FileInputStream(filePath);
			prop.load(fin);
		}catch(IOException e) {
			
		}
		return prop;
	}

}

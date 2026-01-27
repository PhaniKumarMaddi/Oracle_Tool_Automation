package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {

	Properties prop;

	public PropertiesFile(String filePath) {
		prop = new Properties();
		InputStream configFile = null;
		try {
			configFile = new FileInputStream(filePath);
			prop.load(configFile);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	

	public String getProperty(String key) {
		return prop.getProperty(key);
	}

}

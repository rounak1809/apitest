package com.dictionary.apitest.util.Log4jHelper;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class loggerhelper {

	private static boolean root = false;

	public static Logger getLogger(Class cls) {
		
		if (root) {
			return Logger.getLogger(cls);
		}
		PropertyConfigurator
				.configure(System.getProperty("user.dir") + "/src/main" + "/java/com/dictionary/apitest/config/log4j.properties");
		root = true;
		return Logger.getLogger(cls);
	}

}

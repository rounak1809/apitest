package com.dictionary.apitest.util;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {

		if (extent == null) {
			String location = System.getProperty("user.dir") + "//test-output//"
					+ "OxfordDictionaryAPI_Automation_Test_Report.html";

			return createInstance(location);
		} else {
			return extent;
		}

	}

	public static ExtentReports createInstance(String fileName) {

		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.loadXMLConfig(new File(System.getProperty("user.dir") + "\\extentconfig.xml"));

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		return extent;
	}

}

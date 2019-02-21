package com.dictionary.apitest.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.commons.io.output.WriterOutputStream;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.dictionary.apitest.util.ExtentManager;
import com.dictionary.apitest.util.Log4jHelper.loggerhelper;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static Properties prop;
	public static ExtentTest test;
	public static ExtentReports extent;

	public static StringWriter requestwriter;
	public static PrintStream requestcaputre;

	public static StringWriter responsewriter;
	public static PrintStream responsecaputre;

	private static Logger log = loggerhelper.getLogger(TestBase.class);

	private static String CONFIGPROP_PATH = System.getProperty("user.dir") + "/src/main"
			+ "/java/com/dictionary/apitest/config/config.properties";

	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(CONFIGPROP_PATH);
			prop.load(ip);
			log.info("system properties file loaded successfully.");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.info("system properties file not found exception received.");

		} catch (IOException e) {
			e.printStackTrace();
			log.info("system properties IOException received.");

		}
	}

	@BeforeSuite
	public void beforeSuite() throws Exception {

		extent = ExtentManager.getInstance();
	}

	@BeforeClass
	public void setUprequestSpecification() {

		setProxy();

		RestAssured.baseURI = prop.getProperty("url");
		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.addHeader("app_id", prop.getProperty("app_id"))
				.addHeader("app_key", prop.getProperty("app_key"))
				.addHeader("accept", "application/json").build();

		RestAssured.requestSpecification = requestSpecification;

		log.info("requestSpecification set for API Test.");
		test.log(Status.INFO, "requestSpecification set for API Test.");
	}

	@SuppressWarnings("deprecation")
	@BeforeTest
	public void beforeTest() throws Exception {

		test = extent.createTest(getClass().getSimpleName());

		requestwriter = new StringWriter();
		requestcaputre = new PrintStream(new WriterOutputStream(requestwriter), true);

		responsewriter = new StringWriter();
		responsecaputre = new PrintStream(new WriterOutputStream(responsewriter), true);
	}

	@BeforeMethod
	public void setUp(Method method) {

		test.log(Status.INFO, method.getName() + "  **************test started***************");
		log.info("**************" + method.getName() + "Started***************");

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, result.getName() + " is pass");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(Status.SKIP, result.getThrowable());
		}

		log.info("**************" + result.getName() + "Finished***************");

		extent.flush();
	}

	/** Method to update logs in Extent Report. **/

	public static void logExtentReport(String s1) {

		test.log(Status.INFO, s1);
	}

	/** Method to Set up Proxy details. **/

	private void setProxy() {

		String s1 = prop.getProperty("proxyrequired");

		if (s1.equalsIgnoreCase("true")) {

			System.getProperties().put("http.proxyHost", prop.getProperty("proxyHost"));
			System.getProperties().put("http.proxyPort", prop.getProperty("proxyPort"));
			System.getProperties().put("http.proxyUser", prop.getProperty("proxyUser"));
			System.getProperties().put("http.proxyPassword", prop.getProperty("proxyPassword"));

			System.getProperties().put("https.proxyHost", prop.getProperty("proxyHost"));
			System.getProperties().put("https.proxyPort", prop.getProperty("proxyPort"));
			System.getProperties().put("https.proxyUser", prop.getProperty("proxyUser"));
			System.getProperties().put("https.proxyPassword", prop.getProperty("proxyPort"));

			log.info("https and http proxy settings for system applied.");
			test.log(Status.INFO, "https and http proxy settings for system applied.");
		} else {

			log.info("https and http proxy settings for system not required.");
			test.log(Status.INFO, "https and http proxy settings for system not required.");
		}

	}

}

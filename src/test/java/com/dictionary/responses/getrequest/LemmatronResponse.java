package com.dictionary.responses.getrequest;

import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.dictionary.apitest.base.TestBase;
import com.dictionary.apitest.util.Log4jHelper.loggerhelper;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class LemmatronResponse extends TestBase {

	private String LEMENTRON_REQUEST = "/inflections/{source_lang}/{word_id}";

	private static Logger log = loggerhelper.getLogger(LemmatronResponse.class);

	/**
	 * This method Returns Response class object for Get Request sent for Lemmatron
	 * API of Oxford Dictionaries API
	 **/

	public Response getDictionaryLemmatron(String source_lang, String word_id) {

		Response response = given()
				.filter(new RequestLoggingFilter(requestcaputre))
				.filter(new ResponseLoggingFilter(responsecaputre))
				.when()
				.get(LEMENTRON_REQUEST, source_lang, word_id);

		log.info("getDictionaryLemmatron request sent as below:     " + requestwriter.toString());
		logExtentReport("getDictionaryLemmatron request sent as below:        " + requestwriter.toString());

		log.info("response for getDictionaryLemmatron request received as below :     " + responsewriter.toString());
		logExtentReport(
				"response for getDictionaryLemmatron request received as below :     " + responsewriter.toString());

		return response;
	}

	/**
	 * This method validate error response code and message for Get Request sent for
	 * Lemmatron API with invalid headword.
	 **/

	public void invalidWordErrorResponseCodeAndMessage(int i, String source_lang, String word_id) {

		Response response = getDictionaryLemmatron(source_lang, word_id);
		
		String responseMessage = response.asString();

		response.then().assertThat().statusCode(i);
		
		Assert.assertTrue(responseMessage.contains("No lemmas found matching supplied source_lang and word"));

		log.info("valid status code and error message received for get request");
		logExtentReport("valid status code and error message received for get request");
	}

	/**
	 * This method call getDictionaryLemmatron(String source_lang, String word_id)
	 * check and retrieve linked headword (e.g., 'swim' in case of 'swimming') for a
	 * given inflected word. which can then use in the Entries API call.
	 **/

	public String getHeadWords(String source_lang, String word) {

		Response response = getDictionaryLemmatron(source_lang, word);
		
		response.then().assertThat().statusCode(200);

		String headword = response.path("results[0].lexicalEntries[0].inflectionOf[0].text");

		return headword;
	}

}

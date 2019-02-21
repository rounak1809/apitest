package com.dictionary.responses.getrequest;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;
import com.dictionary.apitest.base.TestBase;
import com.dictionary.apitest.util.Log4jHelper.loggerhelper;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class EntriesResponse extends TestBase {

	private String ENTRIES_REQUEST = "/entries/{source_lang}/{word_id}";

	private static Logger log = loggerhelper.getLogger(EntriesResponse.class);

	private LemmatronResponse lemmatronResponse;

	/**
	 * This method Returns Response class object for Get Request sent for Entries
	 * API of Oxford Dictionaries API
	 **/

	public Response getDictionaryEntries(String source_lang, String word_id) {

		Response response = given()
				.filter(new RequestLoggingFilter(requestcaputre))
				.filter(new ResponseLoggingFilter(responsecaputre))
				.when()
				.get(ENTRIES_REQUEST, source_lang, word_id);

		log.info("getDictionaryEntries request sent as below:     " + requestwriter.toString());
		logExtentReport("getDictionaryEntries request sent as below:        " + requestwriter.toString());

		log.info("response for getDictionaryEntries request received as below request:     "
				+ responsewriter.toString());
		logExtentReport("response for getDictionaryEntries request received as below request:     "
				+ responsewriter.toString());

		return response;
	}

	/**
	 * This Method validate definition from list of definitions matches with one of
	 * definitions received for given dictionary headword.
	 **/
	
	public void validateResponseCodeAndWordDefinations(int status_code, String source_lang, String word,
			List<String> def) {

		lemmatronResponse = new LemmatronResponse();

		String word_id = lemmatronResponse.getHeadWords(source_lang, word);

		String definations = getResponseCodeandReturnWordDefinations(status_code, source_lang, word_id);

		for (int i = 0; i < def.size(); i++) {
			
			Assert.assertEquals(definations.contains(def.get(i)), true);
			
			log.info(def.get(i) + " matched with one of definitions received for " + word_id);
			logExtentReport(def.get(i) + " matched  with one of definitions received for " + word_id);
		}
	}

	/**
	 * This method validate response code for Get Request sent for Entries API. and
	 * Return all definitions received for given dictionary headword.
	 **/

	private String getResponseCodeandReturnWordDefinations(int i, String source_lang, String word_id) {

		Response response = getDictionaryEntries(source_lang, word_id);
		
		response.then().assertThat().statusCode(i);

		log.info("valid status code received for get request");
		logExtentReport("valid status code received for get request");

		ArrayList<String> sensesdef = response.path("results.lexicalEntries.entries.senses.definitions");

		return sensesdef.toString();

	}

}

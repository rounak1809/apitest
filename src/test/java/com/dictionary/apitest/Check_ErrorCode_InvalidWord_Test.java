package com.dictionary.apitest;

import org.testng.annotations.Test;

import com.dictionary.apitest.base.TestBase;
import com.dictionary.apitest.util.TestUtil;
import com.dictionary.responses.getrequest.LemmatronResponse;

public class Check_ErrorCode_InvalidWord_Test extends TestBase {

	private String word = TestUtil.readJsonFile("validateResponseCodeandMessageforInvalidWord", "word");

	private String source_lang = TestUtil.readJsonFile("validateResponseCodeandMessageforInvalidWord", "source_lang");

	private int status_code = Integer
			.parseInt(TestUtil.readJsonFile("validateResponseCodeandMessageforInvalidWord", "status_code"));

	/**
	 * Test to validate error response code received for a request with invalid
	 * dictionary headword with appropriate error message.
	 **/

	private LemmatronResponse lemmatronResponse;

	@Test
	public void validateResponseCodeandMessageforInvalidWord() {

		lemmatronResponse = new LemmatronResponse();

		lemmatronResponse.invalidWordErrorResponseCodeAndMessage(status_code, source_lang, word);

	}

}

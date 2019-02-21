package com.dictionary.apitest;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;
import com.dictionary.apitest.base.TestBase;
import com.dictionary.apitest.util.TestUtil;
import com.dictionary.responses.getrequest.EntriesResponse;

public class Check_StatusCode_WordDefinations_ValidWord_Test extends TestBase {

	private String word = TestUtil.readJsonFile("validateResponseCodeandWordDefinationsforValidWord", "word");

	private String source_lang = TestUtil.readJsonFile("validateResponseCodeandWordDefinationsforValidWord",
			"source_lang");

	private int status_code = Integer
			.parseInt(TestUtil.readJsonFile("validateResponseCodeandWordDefinationsforValidWord", "status_code"));

	private EntriesResponse entriesResponse;

	/**
	 * Test to validate success response code received for a request with valid
	 * dictionary headword. and check from list of definitions definition matches
	 * with one of definitions received for given dictionary headword.
	 **/

	@Test
	public void validateResponseCodeandWordDefinationsforValidWord() {

		entriesResponse = new EntriesResponse();

		entriesResponse.validateResponseCodeAndWordDefinations(status_code, source_lang, word, definationstoMatch());
	}

	/**
	 * This Method Stores definition and return list of definition that needs to be
	 * matched against definitions received for given dictionary headword from get
	 * Request sent for Entries API
	 **/

	private List<String> definationstoMatch() {

		List<String> def = new ArrayList<String>();

		def.add(TestUtil.readJsonFile("validateResponseCodeandWordDefinationsforValidWord", "word_def_match1"));
		def.add(TestUtil.readJsonFile("validateResponseCodeandWordDefinationsforValidWord", "word_def_match2"));
		def.add(TestUtil.readJsonFile("validateResponseCodeandWordDefinationsforValidWord", "word_def_match3"));
		def.add(TestUtil.readJsonFile("validateResponseCodeandWordDefinationsforValidWord", "word_def_match4"));
		def.add(TestUtil.readJsonFile("validateResponseCodeandWordDefinationsforValidWord", "word_def_match5"));

		return def;

	}

}

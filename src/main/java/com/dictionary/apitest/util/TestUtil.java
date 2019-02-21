package com.dictionary.apitest.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.dictionary.apitest.base.TestBase;

public class TestUtil extends TestBase {

	public static String TESTDATA_JSON_PATH = System.getProperty("user.dir") + "/src/main"
			+ "/java/com/dictionary/apitest/testdata/";

	/** Method to return value from JSON. **/

	public static String readJsonFile(String id, String Details) {

		JSONParser parser = new JSONParser();

		String valueToReturn;

		try {

			Object obj = parser.parse(new FileReader(TESTDATA_JSON_PATH + "testdata.json"));

			JSONObject jsonobject = (JSONObject) obj;

			JSONArray jArray = (JSONArray) jsonobject.get(id);
			JSONObject json_data = (JSONObject) jArray.get(0);

			valueToReturn = (String) json_data.get(Details);
			return valueToReturn;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		} catch (ParseException e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}

}

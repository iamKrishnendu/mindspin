package com.test.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * @author KRISHNENDU
 * @category For JSON File parsing. Read and Write into JSON. Stored at target file. /target/cucumber-reports/data.json
 * 
 * */



public class PrepareJSON {
	public String name;
	  public Integer population;
	  public List<String> states;
	@SuppressWarnings({ "unchecked", "resource" })
	public void writeIntoJson(String problemNo) throws IOException {

		String filePath = System.getProperty("user.dir")+"/target/cucumber-reports/data.json";
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("ProblemNo", problemNo);
		
		
		
		FileWriter file = new FileWriter(filePath);
		file.write(jsonObj.toJSONString());
		System.out.println(jsonObj.toJSONString());
		file.flush();
	}
	
	@SuppressWarnings({"unchecked","resource"})
	public void writeMultipleValuesIntoJson(Map<String,Object>writableData) throws IOException {

		String filePath = System.getProperty("user.dir")+"/target/cucumber-reports/data.json";
		JSONObject jsonObj = new JSONObject();
		
		for(Map.Entry<String, Object>values : writableData.entrySet()) {
			jsonObj.put(values.getKey(), values.getValue());
		}
		
		FileWriter file = new FileWriter(filePath);
		file.write(jsonObj.toJSONString());
		System.out.println(jsonObj.toJSONString());
		file.flush();
	}
	
	public String readJson(String key) throws FileNotFoundException, IOException, ParseException {
		String jsonvalue = null;
		String filePath = System.getProperty("user.dir")+"/target/cucumber-reports/data.json";
		JSONParser parser = new JSONParser();
		 Object object = parser.parse(new FileReader(filePath));
		 JSONObject jsonObject = (JSONObject) object;
		 jsonvalue = jsonObject.get(key).toString();
		return jsonvalue;
	}
	
//	public static void main(String arg[]) throws IOException, ParseException {
//		
//		
//		PrepareJSON json = new PrepareJSON();
//		
//		System.out.println(json.readJson("Hotel"));
//		Map<String,Object>val = new HashMap<String,Object>();
//		val.put("Location", "Bengalore");
//		val.put("Price Range", "1000-30000");
//		val.put("User Rating", "4-5 Very Good");
//		
//		json.writeMultipleValuesIntoJson(val);
//		
//
//	}

}

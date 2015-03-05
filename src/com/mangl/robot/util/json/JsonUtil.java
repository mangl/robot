package com.mangl.robot.util.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * �� �� ���� Json������ </br> 
 * ����ʱ�䣺 2015-03-04 09:06 </br>
 */
public class JsonUtil 
{
	/**
	 * ����json�ַ����õ�Map
	 * 
	 * @param json
	 *            {'a':'1','b':'2','c':'3'}
	 * @return Map
	 * @throws Exception
	 */
	public static Map<String, String> getMapFromJson(String json) throws Exception 
	{
		Map<String, String> mp = new HashMap<String, String>();
		if(json != null  &&  "".equals(json))
		{
			JSONObject jsonObj = new JSONObject(json);
			mp = getMapFromJSONObject(jsonObj);
		}
		return mp;
	}
	/**
	 * �� JSONObject ת���� Map
	 * 
	 * @param jsonObj JSONObject
	 * @return Map
	 */
	public static Map<String,String> getMapFromJSONObject(JSONObject jsonObj)
	{
		if (jsonObj==null) 
			return null;
		Map<String,String> dataMap = new HashMap<String,String>();
		Iterator<?> iterator = jsonObj.keys();
		while(iterator.hasNext())
		{
			String key = (String)iterator.next();
			String value;
			try
			{
				value = jsonObj.getString(key);
			}
			catch(Exception e)
			{
				value = "";
			}					
			dataMap.put(key, value);
		}		
		return dataMap;
	}
	/**
	 * ����json�ַ����õ�List
	 * @param json
	 *            {'row1':{'a':'1','b':'2','c':'3'},'row2':{'a':'1','b':'2','c':'
	 *            3 ' } }
	 * @return  List
	 * @throws JSONException
	 * @throws Exception
	 */
	public static List<Map<String, String>> getListFromJsonSortStr(String json)throws Exception 
	{
		JSONObject jsonObj = new JSONObject(json);
		List<Map<String, String>> ls = new ArrayList<Map<String, String>>();
		@SuppressWarnings("unchecked")
		Iterator<String> it = jsonObj.keys();
		while (it.hasNext()) 
		{
			String key = it.next();
			String value = jsonObj.getString(key);
			Map<String, String> mp = JsonUtil.getMapFromJson(value);
			ls.add(mp);
		}
		return ls;
	}
	/**
	 * JSONArray ת���� List (ֻ�ʺ� JSONArray �����г�Ա��Ϊ  JSONObject �����)
	 * 
	 * @param jsonArr JSONArray
	 * @return List
	 */
	public static List<Map<String,String>>getListFromJSONArray(JSONArray jsonArr)
	{
		if (jsonArr==null) return null;
		List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
		int len = jsonArr.length();
		for(int i=0;i<len;i++)
		{
			JSONObject jsonObj; 
			try
			{
				jsonObj = jsonArr.getJSONObject(i);
			}
			catch(Exception e)
			{
				continue;
			}					
			Map<String,String> dataMap = getMapFromJSONObject(jsonObj);
			if (dataMap!=null)
			{
				dataList.add(dataMap);
			}						
		}
		return dataList;
	}
	/**
	 * ����json�ַ����õ�JSON����
	 * 
	 * @param json  {'a':'1','b':'2','c':'3'}
	 * @return
	 * @throws Exception
	 */
	public static JSONObject getJSONObjectFromJsonStr(String json) throws Exception 
	{
		JSONObject jsonObj = new JSONObject(json);
		return jsonObj;
	}
}
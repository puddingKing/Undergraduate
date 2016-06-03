package com.bb.api;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bb.model.Info;
import com.bb.model.Type;
import com.bb.util.Constants;

import edu.self.utils.AppContext;


/**
 * 
 * @author 
 *
 */
public class HttpApiAccessor {

	
	public static String saveYuding( HashMap params  ) {

		String url = Constants.WEB_APP_URL + "type.do?method=savePhone&type=json"  ;
		String result = null ;

		result = BaseAuthenicationHttpClient.doRequest(url, "", "" , params );
		return result ; 
	}

	
	public static String saveOrder( HashMap params  ) {
		String url = Constants.WEB_APP_URL + "orderEdit.do?method=save&type=json"  ;
		String result = null ;

		result = BaseAuthenicationHttpClient.doRequest(url, "", "" , params );

		return result ; 
	}
	
	public static String updateOrder( HashMap params  ) {
		String url = Constants.WEB_APP_URL + "orderEdit.do?method=update&type=json"  ;
		String result = null ;

		result = BaseAuthenicationHttpClient.doRequest(url, "", "" , params );

		return result ; 
	}
	
	
	// 
	public static ArrayList<Info> getSearch(long lastId, int pageNo, String search_name , String search_type , String search_info ) throws Exception{
		String url = Constants.WEB_APP_URL + "foodList.do?method=search&type=json";
		if(search_name != null && !search_name.equals("") ){
			url += "&search_name=" + search_name;
		}
		if(search_type != null && !search_type.equals("") ){
			url += "&search_type=" + search_type;
		}
	 
		return getUpdatesList(url,lastId,pageNo);
//		return getTypeList(url,lastId,pageNo);
	}

//	private static ArrayList<Type> getTypeList(String url,long lastId, int pageNo) throws Exception{
//		url = appendParams(url, lastId, pageNo);
//		String jsonString = BaseAuthenicationHttpClient.doRequest(url, "", "");
//
//		JSONArray jsonArray = new JSONArray(jsonString);		
//		ArrayList<Type> ret = new ArrayList<Type>();
//		for( int i = 0; i != jsonArray.length(); i++){
//			JSONObject json = jsonArray.getJSONObject(i);
//			Type food = jsonToType(json);
//			ret.add(food);
//		}
//		return ret;
//	}
	

//	将json数据解析赋值到类
	private static Type jsonToType(JSONObject json) throws JSONException{
		Type object = new Type(); 
		object.id =  json.getString("id")  ; 
		object.name =  json.getString("name")  ; 
		object.type =  json.getString("state")  ; 
		object.info =  json.getString("content")  ;  
		return object;
	}
	
	//根据flag去获取指定的
	public static ArrayList<Info> getFollowedByName(long lastId, int pageNo, String flag) throws Exception{
		String url = Constants.WEB_APP_URL + "foodList.do?type=json&method=getFollowedByName";
		url += "&name=" +URLEncoder.encode( AppContext.userinfo.getUserName(),"utf-8" ) ;
		return getUpdatesList(url,lastId,pageNo);
	}
		
		
//根据flag去获取指定的
	public static ArrayList<Info> getFollowed(long lastId, int pageNo, String flag) throws Exception{
		String url = Constants.WEB_APP_URL + "foodList.do?type=json";
		if(flag != null && !flag.equals( Constants.FLAG_ALL)){
			url += "&f=" + flag;
		}
		return getUpdatesList(url,lastId,pageNo);
	}

	
//	
	private static ArrayList<Info> getUpdatesList(String url,long lastId, int pageNo) throws Exception{
		url = appendParams(url, lastId, pageNo);
		String jsonString = BaseAuthenicationHttpClient.doRequest(url, "", "");

		JSONArray jsonArray = new JSONArray(jsonString);		
		ArrayList<Info> ret = new ArrayList<Info>();
		for( int i = 0; i != jsonArray.length(); i++){
			JSONObject json = jsonArray.getJSONObject(i);
			Info food = jsonToFood(json);
			ret.add(food);
		}
		return ret;
	}
	
	
//	将json数据解析赋值到类
	private static Info jsonToFood(JSONObject json) throws JSONException{
		Info object = new Info(); 
		object.info_id =  json.getString("info_id")  ;
		object.info_name =  json.getString("info_name")  ;
		object.info_pic =  json.getString("info_pic") ; 
		object.info_description =  json.getString("info_description") ; 
		object.info_people =   json.getString("info_people")  ;
		object.info_pphone = json.getString("info_pphone")  ;
		object.info_flag =json.getString("info_flag")  ;
		object.info_count =  json.getString("info_count") ;
		
		return object;
	}

	
	private static String appendParams(String url, long lastId, int pageNo) {
		if(lastId != -1){
			url = "?last_id=" + lastId;
		}
		if(pageNo != -1){
			url = "&pageNo=" + pageNo;
		}
		return url;
	}

	
	
}




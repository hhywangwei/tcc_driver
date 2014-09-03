package com.ewinsh.etone.server;

import com.alibaba.fastjson.JSONObject;

public class Response {
	private final JSONObject o = new JSONObject();
	
	private Response(int code, boolean rsFlg, String message){
		o.put("code", code);
		o.put("rsFlg", rsFlg);
		o.put("message", message);
		o.put("errorFields", new JSONObject());
		o.put("rs", new JSONObject());
	}
	
	public Response putErrorField(String field, String message){
		JSONObject errors = o.getJSONObject("errorFields");
		errors.put("field", message);
		return this;
	}
	
	public Response putAll(JSONObject o){
		JSONObject c = o.getJSONObject("rs");
		c.putAll(o);
		
		return this;
	}
	
	public Response put(String field, Object v){
		JSONObject c = o.getJSONObject("rs");
		c.put(field, v);
		
		return this;
	}
	
	public String response(){
		return o.toJSONString();
	}
	
	public static Response fail(int code){
		return new Response(code, false, "");
	}
	
	public static Response fail(int code, String message){
		return new Response(code, false, message);
	}
	
	public static Response success(){
		return new Response(0, true, "");
	}

}

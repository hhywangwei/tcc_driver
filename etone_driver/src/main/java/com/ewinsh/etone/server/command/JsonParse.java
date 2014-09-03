package com.ewinsh.etone.server.command;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.server.Response;
import com.ewinsh.etone.server.command.builder.CommandBuilderable;
import com.ewinsh.etone.server.command.builder.CommandBuilders;

public class JsonParse implements Parseable{

	@Override
	public Commandable parse(String c) throws ParseException {
		try{
			JSONObject o = JSON.parseObject(c);
			CommandBuilderable builder = CommandBuilders.builder(getMsg(o));
			if(builder == null){
				throw new ParseException();
			}
			Response response = Response.fail(1);
			if(!builder.validate(o, response)){
				throw new ParseException();
			}
			return builder.build(o);
		}catch(Exception e){
			throw new ParseException();
		}
	}
	
	private String getMsg(JSONObject o){
		return o.getString("msg");
	}

}

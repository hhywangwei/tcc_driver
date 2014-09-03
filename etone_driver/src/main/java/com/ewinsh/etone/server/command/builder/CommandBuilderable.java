package com.ewinsh.etone.server.command.builder;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.server.Response;

public interface CommandBuilderable{
	
	boolean validate(JSONObject o, Response response);
	
	Commandable build(JSONObject o);
	
}
package com.ewinsh.etone.server.command.json;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.LoginCommand;
import com.ewinsh.etone.server.Response;

/**
 * 构建登陆命令对象
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月9日
 * 
 * @see LoginCommand
 */
public class LoginBuilder extends JSONBuilder{
	private static final String OPNUMBER_FIELD = "opNumber";
	private static final String PASSWORD_FIELD = "password";
	private static final String TYPE_FIELD = "type";
	
	@Override
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		if(isBlank(o, OPNUMBER_FIELD)){
			v = false;
			response.putErrorField(OPNUMBER_FIELD, "error.opnumber.notblank");
		}
		if(isBlank(o, PASSWORD_FIELD)){
			v = false;
			response.putErrorField(PASSWORD_FIELD, "error.password.notblank");
		}
		v = v & super.validate(o, response);
		
		return v;
	}

	@Override
	public Commandable build(JSONObject o) {
		String opId = getOpID(o);
		String companyId = getCompanyID(o);
		String opNumber = o.getString(OPNUMBER_FIELD);
		String password = o.getString(PASSWORD_FIELD);
		String type = o.getString(TYPE_FIELD);
		type = (type == null ? "1" : type);
		
		return new LoginCommand(companyId, opId, opNumber, password, type);
	}

}

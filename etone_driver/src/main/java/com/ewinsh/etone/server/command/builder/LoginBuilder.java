package com.ewinsh.etone.server.command.builder;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.LoginCommand;

class LoginBuilder extends BaseCommandBuilder{
	private static final String OPNUMBER_FIELD = "opNumber";
	private static final String PASSWORD_FIELD = "password";
	private static final String TYPE_FIELD = "TYPE_FILED";

	@Override
	public Commandable build(JSONObject o) {
		String opId = getOpID(o);
		String companyId = getCompanyID(o);
		String opNumber = o.getString(OPNUMBER_FIELD);
		String password = o.getString(PASSWORD_FIELD);
		String type = o.getString(TYPE_FIELD);
		
		return new LoginCommand(opId, companyId, opNumber, password, type);
	}

}

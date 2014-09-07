package com.ewinsh.etone.server.command.builder;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.CallHelpCommand;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.server.Response;

public class CallHelpBuilder extends BaseCommandBuilder{
	private final static String WORKID_FIELD = "workID";
	private final static String NUMBER_FIELD = "number";
	
	public boolean validate(JSONObject o, Response r){
		boolean v = true;
		if(isBlank(o, WORKID_FIELD)){
			v = false;
			r.putErrorField(WORKID_FIELD, "error.workid.notblank");
		}
		if(isBlank(o, NUMBER_FIELD)){
			v = false;
			r.putErrorField(NUMBER_FIELD, "error.number.notblank");
		}
		
		return v;
	}

	@Override
	public Commandable build(JSONObject o) {
		String opID = this.getOpID(o);
		String companyID = this.getCompanyID(o);
		String workID = o.getString(WORKID_FIELD);
		String number = o.getString(NUMBER_FIELD);
		return new CallHelpCommand(opID, companyID, workID, number);
	}

}

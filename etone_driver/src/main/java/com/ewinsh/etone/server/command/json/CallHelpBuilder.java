package com.ewinsh.etone.server.command.json;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.CallHelpCommand;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.server.Response;

/**
 * 构建呼叫帮助命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 * 
 * @see CallHelpCommand
 */
public class CallHelpBuilder extends JSONBuilder{
	private final static String WORKID_FIELD = "workID";
	private final static String NUMBER_FIELD = "number";
	
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		if(isBlank(o, WORKID_FIELD)){
			v = false;
			response.putErrorField(WORKID_FIELD, "error.workid.notblank");
		}
		if(isBlank(o, NUMBER_FIELD)){
			v = false;
			response.putErrorField(NUMBER_FIELD, "error.number.notblank");
		}
		
		v = v & super.validate(o,response);
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

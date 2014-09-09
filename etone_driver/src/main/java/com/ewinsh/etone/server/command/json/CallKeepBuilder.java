package com.ewinsh.etone.server.command.json;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.CallKeepCommand;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.server.Response;

/**
 * 构建保持通话命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 *
 * @see CallKeepCommand
 */
public class CallKeepBuilder extends JSONBuilder {
	private final static String CALLFEG_FIELD = "callLeg";
	private final static String KEEP_FIELD = "keep";

	@Override
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		if(isBlank(o, CALLFEG_FIELD)){
			v = false;
			response.putErrorField(CALLFEG_FIELD, "error.calleg.notblank");
		}
		if(isNull(o, KEEP_FIELD)){
			v = false;
			response.putErrorField(KEEP_FIELD, "error.keep.notnull");
		}
		
		v = v & super.validate(o, response);
		
		return v;
	}
	
	@Override
	public Commandable build(JSONObject o) {
		String opID = this.getOpID(o);
		String companyID = this.getCompanyID(o);
		String callLeg = o.getString(CALLFEG_FIELD);
		Boolean keep = o.getBoolean(KEEP_FIELD);
		
		return new CallKeepCommand(opID, companyID, callLeg, keep);
	}

}

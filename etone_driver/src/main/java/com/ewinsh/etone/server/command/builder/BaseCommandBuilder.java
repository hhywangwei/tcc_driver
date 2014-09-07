package com.ewinsh.etone.server.command.builder;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.server.Response;

abstract  class BaseCommandBuilder implements CommandBuilderable{
	private static final String OPID_FIELD = "opID";
	private static final String COMPANYID_FIELD = "companyID";

	@Override
	public boolean validate(JSONObject o, Response response) {
		boolean v = true;
		if(isBlank(o, OPID_FIELD)){
			v = false;
			response.putErrorField(OPID_FIELD, "error.opidl.notblank");
		}
		if(isBlank(o, COMPANYID_FIELD)){
			v = false;
			response.putErrorField(COMPANYID_FIELD, "error.companyid.notblank");
		}
		return v;
	}
	
	protected boolean isBlank(JSONObject o, String field){
		return StringUtils.isNotBlank(o.getString(field));
	}

	protected boolean isNull(JSONObject o, String field){
		return o.containsKey(field);
	}
	
	protected String getOpID(JSONObject o){
		return o.getString(OPID_FIELD);
	}
	
	protected String getCompanyID(JSONObject o){
		return o.getString(COMPANYID_FIELD);
	}
}

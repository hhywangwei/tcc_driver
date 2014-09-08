package com.ewinsh.etone.server.command.builder;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.RestCommand;
import com.ewinsh.etone.server.Response;

/**
 * 构建休息命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 *
 * @see RestCommand 
 */
public class RestBuilder extends BaseCommandBuilder{
	private static final String REST_FIELD = "rest";
	
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		if(isNull(o, REST_FIELD)){
			v = false;
			response.putErrorField(REST_FIELD, "error.rest.notnull");
		}
		v = v & super.validate(o, response);
		
		return v;
	}

	@Override
	public Commandable build(JSONObject o) {
		String companyID = this.getCompanyID(o);
		String opID = this.getOpID(o);
		boolean rest = o.getBooleanValue(REST_FIELD);
		
		return new RestCommand(companyID, opID, rest);
	}

}

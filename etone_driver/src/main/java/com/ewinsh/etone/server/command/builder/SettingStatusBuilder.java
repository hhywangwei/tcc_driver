package com.ewinsh.etone.server.command.builder;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.SettingStatusCommand;
import com.ewinsh.etone.server.Response;

/**
 * 构建设置坐席状态命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 *
 * @see SettingStatusCommand
 */
public class SettingStatusBuilder extends BaseCommandBuilder{
	private static final String WORKID_FIELD = "workID";
	private static final String STATUS_FIELD = "status";
	
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		if(isBlank(o, WORKID_FIELD)){
			v = false;
			response.putErrorField(WORKID_FIELD, "error.workid.notblank");
		}
		if(isBlank(o, STATUS_FIELD)){
			v = false;
			response.putErrorField(STATUS_FIELD, "error.status.notblank");
		}
		v = v & super.validate(o, response);
		
		return v;
	}

	@Override
	public Commandable build(JSONObject o) {
		String companyID = this.getCompanyID(o);
		String opID = this.getOpID(o);
		String workID = o.getString(WORKID_FIELD);
		String status = o.getString(STATUS_FIELD);
				
		return new SettingStatusCommand(companyID, opID, workID, status);
	}

}

package com.ewinsh.etone.server.command.json;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.WorkerCommand;
import com.ewinsh.etone.server.Response;

/**
 * 构建获取坐席信息命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 * 
 * @see WorkerCommand
 */
public class WorkerBuilder extends JSONBuilder{
	private static final String WORKID_FIELD = "workID";
	
	@Override
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		v = v & super.validate(o, response);
		
		return v;
	}

	@Override
	public Commandable build(JSONObject o) {
		String companyID = this.getCompanyID(o);
		String opID = this.getOpID(o);
		String workID = o.getString(WORKID_FIELD);
		workID = workID == null ? "" : workID;

		return new WorkerCommand(companyID, opID, workID);
	}

}

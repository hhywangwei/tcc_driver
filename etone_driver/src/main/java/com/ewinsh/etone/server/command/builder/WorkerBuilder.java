package com.ewinsh.etone.server.command.builder;

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
public class WorkerBuilder extends BaseCommandBuilder{
	private static final String WORKID_FIELD = "workID";
	
	@Override
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		if(isBlank(o, WORKID_FIELD)){
			v = false;
			response.putErrorField(WORKID_FIELD, "error.workid.notblank");
		}
		v = v & super.validate(o, response);
		
		return v;
	}

	@Override
	public Commandable build(JSONObject o) {
		String companyID = this.getCompanyID(o);
		String opID = this.getOpID(o);
		String workID = o.getString(WORKID_FIELD);

		return new WorkerCommand(companyID, opID, workID);
	}

}

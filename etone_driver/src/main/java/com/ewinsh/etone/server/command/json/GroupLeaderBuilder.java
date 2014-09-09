package com.ewinsh.etone.server.command.json;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.GroupLeaderCommand;
import com.ewinsh.etone.server.Response;

/**
 * 构建获得组长命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 *
 * @see GroupLeaderCommand
 */
public class GroupLeaderBuilder extends JSONBuilder{

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
		
		return new GroupLeaderCommand(companyID, opID);
	}

}

package com.ewinsh.etone.server.command.builder;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.GroupCommand;
import com.ewinsh.etone.server.Response;

/**
 * 构建获取用户组信息命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 *
 * @see GroupCommand
 */
public class GroupBuilder extends BaseCommandBuilder{
	private static final String GROUPID_FEILD = "groupID";
	
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
		String groupID = o.getString(GROUPID_FEILD);
		
		return groupID == null ? new GroupCommand(companyID,opID) :
			new GroupCommand(companyID, opID, groupID);
	}

}

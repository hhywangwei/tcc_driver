package com.ewinsh.etone.server.command.json;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.GroupMemberCommand;
import com.ewinsh.etone.server.Response;

/**
 * 构建获得指定组的成员信息
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 *
 * @see GroupMemberCommand
 */
public class GroupMemberBuilder extends JSONBuilder{
	private static final String GROUPID_FIELD = "groupID";
	
	@Override
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		if(isBlank(o, GROUPID_FIELD)){
			v = false;
			response.putErrorField(GROUPID_FIELD, "error.groupid.notblank");
		}
		v = v & super.validate(o, response);
		
		return v;
	}

	@Override
	public Commandable build(JSONObject o) {
		String companyID = this.getCompanyID(o);
		String opID = this.getOpID(o);
		String groupID = o.getString(GROUPID_FIELD);
		
		return new GroupMemberCommand(companyID, opID, groupID);
	}

}

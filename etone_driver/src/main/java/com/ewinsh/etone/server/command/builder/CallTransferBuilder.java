package com.ewinsh.etone.server.command.builder;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;

public class CallTransferBuilder extends BaseCommandBuilder{
	private final static String CALLLEG_FIELD = "callLeg";
	private final static String GROUPID_FIELD = "groupID";
	private final static String WORKID_FIELD = "workID";
	private final static String NUMBER_FIELD = "number";
	private final static String GROUP_FIELD = "group";

	@Override
	public Commandable build(JSONObject o) {
		String opID = this.getOpID(o);
		String companyID = this.getCompanyID(o);
		String callLeg = o.getString(CALLLEG_FIELD);
		String groupID = o.getString(GROUPID_FIELD);
		String workID = o.getString(WORKID_FIELD);
		String number = o.getString(NUMBER_FIELD);
		boolean group = o.getBooleanValue(GROUP_FIELD);
				
		return null;
	}

}

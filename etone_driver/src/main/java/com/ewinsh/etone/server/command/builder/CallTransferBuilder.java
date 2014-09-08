package com.ewinsh.etone.server.command.builder;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.CallTransferCommand;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.server.Response;
/**
 * 构建转接命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 *
 * @see CallTransferBuilder
 */
public class CallTransferBuilder extends BaseCommandBuilder{
	private final static String CALLLEG_FIELD = "callLeg";
	private final static String GROUPID_FIELD = "groupID";
	private final static String WORKID_FIELD = "workID";
	private final static String NUMBER_FIELD = "number";
	private final static String GROUP_FIELD = "group";
	
	@Override
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		if(isBlank(o, CALLLEG_FIELD)){
			v = false;
			response.putErrorField(CALLLEG_FIELD, "error.callleg.notblank");
		}
		if(isNull(o, GROUP_FIELD)){
			v = false;
			response.putErrorField(GROUP_FIELD, "error.group.notnull");
		}
		if(o.getBoolean(GROUP_FIELD)){
			if(isBlank(o, WORKID_FIELD)){
				v = false;
				response.put(WORKID_FIELD, "error.workid.notblank");
			}
			if(isBlank(o, NUMBER_FIELD)){
				v = false;
				response.put(NUMBER_FIELD, "error.number.notblank");
			}
		}else{
			if(isBlank(o, GROUPID_FIELD)){
				v = false;
				response.putErrorField(GROUPID_FIELD, "error.groupid.notblank");
			}
		}
		
		v = v & super.validate(o, response);
		
		return v;
	}

	@Override
	public Commandable build(JSONObject o) {
		String opID = this.getOpID(o);
		String companyID = this.getCompanyID(o);
		String callLeg = o.getString(CALLLEG_FIELD);
		String groupID = o.getString(GROUPID_FIELD);
		String workID = o.getString(WORKID_FIELD);
		String number = o.getString(NUMBER_FIELD);
		boolean group = o.getBooleanValue(GROUP_FIELD);
		
		return group ? new CallTransferCommand(companyID, opID, callLeg, groupID) :
			new CallTransferCommand(companyID, opID, callLeg, workID, number);
	}

}

package com.ewinsh.etone.server.command.builder;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.OutCallCommand;
import com.ewinsh.etone.server.Response;

/**
 * 构建外呼命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 *
 * @see OutCallCommand
 */
public class OutCallBuilder extends BaseCommandBuilder{
	private final static String OPNUMBER_FIELD = "opNumber";
	private final static String PHONE_FIELD = "phone";
	private final static String CALLLEG_FIELD = "callLeg";
	private final static String CANCEL_FIELD = "cancel";
	
	@Override
	public boolean validate(JSONObject o, Response response){
		if(isNull(o, CANCEL_FIELD)){
			response.putErrorField(CANCEL_FIELD, "error.cancel.notblank");
			return false;
		}
		
		boolean v = true;
		boolean cancel = o.getBoolean(CANCEL_FIELD);
		if(cancel){
			if(isBlank(o, CALLLEG_FIELD)){
				v = false;
				response.putErrorField(CALLLEG_FIELD, "error.callleg.notblank");
			}
		}else{
			if(isBlank(o, OPNUMBER_FIELD)){
				v = false;
				response.putErrorField(OPNUMBER_FIELD, "error.opnumber.notblank");
			}
			if(isBlank(o, PHONE_FIELD)){
				v = false;
				response.putErrorField(PHONE_FIELD, "error.phone.notblank");
			}
		}
		
		v = v & super.validate(o, response);
		
		return v;
	}

	@Override
	public Commandable build(JSONObject o) {
		boolean cancel = o.getBoolean(CANCEL_FIELD);
		String companyID = this.getCompanyID(o);
		String opID = this.getOpID(o);
		if(cancel){
			String callLeg = o.getString(CALLLEG_FIELD);
			return new OutCallCommand(companyID, opID, callLeg);
		}else{
			String opNumber = o.getString(OPNUMBER_FIELD);
			String phone = o.getString(PHONE_FIELD);
			return new OutCallCommand(companyID, opID, opNumber, phone);
		}
	}

}

package com.ewinsh.etone.server.command.json;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.MobileCommand;
import com.ewinsh.etone.server.Response;

/**
 * 构建设置移动坐席命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 *
 * @see MobileCommand
 */
public class MobileBuilder extends JSONBuilder{
	private static final String MOBILE_FIELD = "mobile";
	private static final String SETTING_FIELD = "setting";
	
	@Override
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		if(isBlank(o, MOBILE_FIELD)){
			v = false;
			response.putErrorField(MOBILE_FIELD, "error.mobile.notblank");
		}
		if(isNull(o, SETTING_FIELD)){
			v = false;
			response.putErrorField(SETTING_FIELD, "error.setting.notnull");
		}
		v = v & super.validate(o, response);
		
		return v;
	}

	@Override
	public Commandable build(JSONObject o) {
		String companyID = this.getCompanyID(o);
		String opID = this.getOpID(o);
		String mobile = o.getString(MOBILE_FIELD);
		boolean setting = o.getBoolean(SETTING_FIELD);
		return new MobileCommand(companyID, opID, mobile, setting);
	}

}

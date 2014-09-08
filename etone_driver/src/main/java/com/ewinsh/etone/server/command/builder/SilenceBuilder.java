package com.ewinsh.etone.server.command.builder;

import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.driver.command.SilenceCommand;
import com.ewinsh.etone.server.Response;

/**
 * 构建静音命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年9月8日
 * 
 * @see SilenceCommand
 */
public class SilenceBuilder extends BaseCommandBuilder{
	private static final String CALLLEG_FIELD = "callLeg";
	private static final String SILENCE_FIELD = "silence";
	
	public boolean validate(JSONObject o, Response response){
		boolean v = true;
		if(isBlank(o, CALLLEG_FIELD)){
			v = false;
			response.putErrorField(CALLLEG_FIELD, "error.callleg.notblank");
		}
		if(isNull(o, SILENCE_FIELD)){
			v = false;
			response.putErrorField(SILENCE_FIELD, "error.silence.notnull");
		}
		v = v & super.validate(o, response);
		
		return v;
		
	}

	@Override
	public Commandable build(JSONObject o) {
		String companyID = this.getCompanyID(o);
		String opID = this.getOpID(o);
		String callLeg = o.getString(CALLLEG_FIELD);
		boolean silence = o.getBoolean(SILENCE_FIELD);
		
		return new SilenceCommand(companyID, opID, callLeg, silence);
	}

}

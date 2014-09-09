package com.ewinsh.etone.server.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.server.Response;
import com.ewinsh.etone.server.command.json.CallHelpBuilder;
import com.ewinsh.etone.server.command.json.CallKeepBuilder;
import com.ewinsh.etone.server.command.json.CallTransferBuilder;
import com.ewinsh.etone.server.command.json.GroupBuilder;
import com.ewinsh.etone.server.command.json.GroupMemberBuilder;
import com.ewinsh.etone.server.command.json.LoginBuilder;
import com.ewinsh.etone.server.command.json.LogoutBuilder;
import com.ewinsh.etone.server.command.json.MobileBuilder;
import com.ewinsh.etone.server.command.json.OutCallBuilder;
import com.ewinsh.etone.server.command.json.RestBuilder;
import com.ewinsh.etone.server.command.json.StatusBuilder;
import com.ewinsh.etone.server.command.json.SilenceBuilder;
import com.ewinsh.etone.server.command.json.WorkerBuilder;

/**
 * 解析输入的字符字符串为操作命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 */
public class JSONBuilders {
	private static final String MSG_FIELD = "msg";
	private static final Map<String,Builderable<JSONObject>> builders = init();

	/**
	 *  构建命令对象
	 *  
	 * @param c 命令字符串
	 * @return 命令对象
	 * @throws BuilderException 构建命令错误
	 */
	public static Commandable build(String c)throws BuilderException{
		try{
			JSONObject o = JSON.parseObject(c);
			String msg = getMsg(o);
			
			if(StringUtils.isBlank(msg)){
				Response response = Response.fail(2, "Msg is blank ");
				throw new BuilderException("Msg is blank",response);
			}
			
			Builderable<JSONObject> builder = builders.get(msg);
			if(builder == null){
				Response response = Response.fail(3, "Msg not supper " + msg);
				throw new BuilderException("Msg not supper " + msg, response);
			}
			
			Response response = Response.fail(4);
			if(!builder.validate(o, response)){
				throw new BuilderException("Vaildate fail", response);
			}
			
			return builder.build(o);
		}catch(Exception e){
			Response response = Response.fail(1, "Json data error ");
			throw new BuilderException("Json data error", response);
		}
	}
	
	private static String getMsg(JSONObject o){
		return o.getString(MSG_FIELD);
	}
	
	private static Map<String,Builderable<JSONObject>> init(){
		Map<String,Builderable<JSONObject>> map = new HashMap<String,Builderable<JSONObject>>();
		
		map.put("callHelp", new CallHelpBuilder());
		map.put("callKeep",new CallKeepBuilder());
		map.put("callTransfer",new CallTransferBuilder());
		map.put("group", new GroupBuilder());
		map.put("groupMember", new GroupMemberBuilder());
		map.put("login", new LoginBuilder());
		map.put("logout", new LogoutBuilder());
		map.put("mobile", new MobileBuilder());
		map.put("outCall", new OutCallBuilder());
		map.put("rest", new RestBuilder());
		map.put("status", new StatusBuilder());
		map.put("silence", new SilenceBuilder());
		map.put("worker", new WorkerBuilder());
		
		return map;
	}
}

package com.ewinsh.etone.driver.command;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 实现登陆CTI服务器命令
 * 
 * @author <a href="hhywangwei@gmail.com>WangWei</a>
 * @since 2014-08-30
 */
public class LoginCommand extends BaseCommand {
	private static final String BODY_PATTER = COMPANY_OPID_PATTER +
			"<Type>%s</Type><OPNumber>%s</OPNumber><PassWord>%s</PassWord>";

	private final String _companyID;
	private final String _opID;
	private final String _opNumber;
	private final String _password;
	private final String _type;
	
	/**
	 * 实例{@link LoginCommand}
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号
	 * @param opNumber  操作员号
	 * @param password  密码
	 * @param type      用户类型
	 */
	public LoginCommand(String companyID, String opID, String opNumber, String password, String type){
		super("login");
		_companyID = companyID;
		_opID = opID;
		_opNumber = opNumber;
		_password = DigestUtils.md5Hex(password);
		_type = type;
	}
	
	@Override
	protected String buildBody() {
		return String.format(BODY_PATTER, _companyID, _opID, _type, _opNumber, _password);
	}

}

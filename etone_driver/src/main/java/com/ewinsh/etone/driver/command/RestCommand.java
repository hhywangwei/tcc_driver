package com.ewinsh.etone.driver.command;

/**
 * 构建请求休息命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 *
 */
public class RestCommand extends BaseCommand{
	private final String _companyID;
	private final String _opID;

	public RestCommand(String companyID, String opID) {
		super("leave");
		_companyID = companyID;
		_opID = opID;
	}

	@Override
	protected String buildBody() {
		return String.format(COMPANY_OPID_PATTER, _companyID, _opID);
	}

}

package com.ewinsh.etone.driver.command;

/**
 * 构建恢复工作命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 *
 */
public class ResumeCommand extends BaseCommand{
	private final String _companyID;
	private final String _opID;

	public ResumeCommand(String companyID, String opID) {
		super("come_back");
		_companyID = companyID;
		_opID = opID;
	}

	@Override
	protected String buildBody() {
		return String.format(COMPANY_OPID_PATTER, _companyID, _opID);
	}

}

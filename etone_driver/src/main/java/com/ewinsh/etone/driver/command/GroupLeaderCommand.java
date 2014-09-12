package com.ewinsh.etone.driver.command;

/**
 * 构建获取班长信息命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 *
 */
public class GroupLeaderCommand extends BaseCommand {
	private final String _companyID;
	private final String _opID;

	public GroupLeaderCommand(String companyID, String opID) {
		super("monitor_info");
		_companyID = companyID;
		_opID = opID;
	}

	@Override
	protected String buildBody() {
		return String.format(COMPANY_OPID_PATTER, _companyID, _opID);
	}

}

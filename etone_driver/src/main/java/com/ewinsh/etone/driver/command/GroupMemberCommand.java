package com.ewinsh.etone.driver.command;

/**
 * 构建获取组成员命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 *
 */
public class GroupMemberCommand extends BaseCommand{
	private final static String BODY_PATTER = COMPANY_OPID_PATTER + "<GroupID>%s</GroupID>";
	
	private final String _companyID;
	private final String _opID;
	private final String _groupID;

	public GroupMemberCommand(String companyID, String opID, String groupID) {
		super("worker_number_info");
		_companyID = companyID;
		_opID = opID;
		_groupID = groupID;
	}

	@Override
	protected String buildBody() {
		return String.format(BODY_PATTER, _companyID, _opID, _groupID);
	}

}

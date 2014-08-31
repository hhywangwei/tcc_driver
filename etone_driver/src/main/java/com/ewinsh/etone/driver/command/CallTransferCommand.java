package com.ewinsh.etone.driver.command;

/**
 * 构建转接电话命令，可装机到组和个人
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月31日
 *
 */
public class CallTransferCommand extends BaseCommand{
	private static final String GROUP_BODY_PATTER = COMPANY_OPID_PATTER + 
			"<GroupID>%s</GroupID><CallLeg>%s</CallLeg>";
	private static final String WORK_BODY_PATTEr = COMPANY_OPID_PATTER + 
			"<WorkID>%s</WorkID><Number>%s</Number><CallLeg>%s</CallLeg>";
	
	private final String _companyID;
	private final String _opID;
	private final String _callLeg;
	private final String _groupID;
	private final String _workID;
	private final String _number;
	private final boolean group;
	
	/**
	 * 实例{@link CallTransferCommand}
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号
	 * @param callLeg   通话标示
	 * @param groupID   转接组编号
	 */
	public CallTransferCommand(String companyID, String opID, String callLeg, String groupID){
		super("transfer_to_group");
		_companyID = companyID;
		_opID = opID;
		_callLeg = callLeg;
		_groupID = groupID;
		group = true;
		_workID = "_1";
		_number = "-1";
	}

	/**
	 * 实例{@link CallTransferCommand}
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号
	 * @param callLeg   通话标示
	 * @param workID    转接工号
	 * @param number    转接电话
	 */
	public CallTransferCommand(String companyID, String opID, String callLeg, String workID, String number){
		super("transfer_to_one");
		_companyID = companyID;
		_opID = opID;
		_callLeg = callLeg;
		_workID = workID;
		_number = number;
		group = false;
		_groupID = "-1";
	}

	@Override
	protected String buildBody() {
		if(group){
			return String.format(GROUP_BODY_PATTER, _companyID, _opID, _groupID, _callLeg);
		}else{
			return String.format(WORK_BODY_PATTEr, _companyID, _opID, _workID, _number, _callLeg);
		}
	}

}

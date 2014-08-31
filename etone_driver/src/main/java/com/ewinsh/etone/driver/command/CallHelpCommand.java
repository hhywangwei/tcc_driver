package com.ewinsh.etone.driver.command;

/**
 * 构建呼叫帮助命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月31日
 *
 */
public class CallHelpCommand extends BaseCommand{
	private static final String BODY_PATTER = COMPANY_OPID_PATTER + 
			"<CallLeg>%s</CallLeg><TransferWorkID>%s</TransferWorkID><TransferNumber>%s</TransferNumber><Status>0</Status>";

	private final String _companyID;
	private final String _opID;
	private final String _workID;
	private final String _number;
	private final String _status;
	
	/**
	 * 实例{@link CallHelpCommand}
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号
	 * @param workID    求助操作员编号
	 * @param number    求助号码
	 */
	public CallHelpCommand(String companyID, String opID, String workID, String number){
		this(companyID, opID, workID, number, "0");
	}
	
	/**
	 * 实例{@link CallHelpCommand}
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号
	 * @param workID    求助操作员编号
	 * @param number    求助号码
	 * @param status    0:求助，1：三方会议
	 */
	public CallHelpCommand(String companyID, String opID, String workID, String number, String status){
		super("transfer");
		_companyID = companyID;
		_opID = opID;
		_workID = workID;
		_number = number;
		_status = status;
	}

	@Override
	protected String buildBody() {
		return String.format(BODY_PATTER, _companyID, _opID, _workID, _number, _status);
	}
}

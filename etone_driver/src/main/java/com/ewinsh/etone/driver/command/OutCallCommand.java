package com.ewinsh.etone.driver.command;

/**
 * 构建外呼
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 */
public class OutCallCommand extends BaseCommand{
	private static final String CALL_BODY_PATTER = COMPANY_OPID_PATTER + "<Phone1>%s</Phone1><Phone2>%s</Phone2>";
	private static final String CANCEL_BODY_PATTER = COMPANY_OPID_PATTER + "<CallLeg>%s</CallLeg>";
	
	private final String _companyID;
	private final String _opID;
	private final String _opNumber;
	private final String _phone;
	private final String _callLeg;
	private final boolean _cancel;
	
	/**
	 * 实例外呼命令
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号	 
	 * @param opNumber 操作员号
	 * @param phone    电话号
	 */
	public OutCallCommand(String companyID, String opID, String opNumber,String phone){
		super("outcall");
		_companyID = companyID;
		_opID = opID;
		_opNumber = opNumber;
		_phone = phone;
		_callLeg = "";
		_cancel = false;
	}
	
	/**
	 * 实例取消外呼命令
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号 
	 * @param callLeg 呼叫标示
	 */
	public OutCallCommand(String companyID, String opID, String callLeg){
		super("outcallcancel");
		_companyID = companyID;
		_opID = opID;
		_opNumber = "";
		_phone = "";
		_callLeg = callLeg;
		_cancel = true;
	}

	@Override
	protected String buildBody() {
		return _cancel ? String.format(CANCEL_BODY_PATTER, _companyID, _opID, _callLeg) 
				: String.format(CALL_BODY_PATTER, _companyID, _opID, _opNumber, _phone);
	}

}

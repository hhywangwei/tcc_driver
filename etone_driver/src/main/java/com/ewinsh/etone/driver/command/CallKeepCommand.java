package com.ewinsh.etone.driver.command;

/**
 * 构建呼叫保持（用户可听音乐）
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月31日
 *
 */
public class CallKeepCommand extends BaseCommand{
	private static final String BODY_PATTER = COMPANY_OPID_PATTER + "<CallLeg>%s</CallLeg><Flag>%s</Flag>";
	
	private final String _companyID;
	private final String _opID;
	private final String _callLeg;
	private final String _flag;
	
	/**
	 * 实例{@link CallKeepCommand}
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员
	 * @param callLeg   呼叫标示
	 * @param keep      true:保持通话,false:恢复通话
	 */
	public CallKeepCommand(String companyID, String opID, String callLeg, boolean keep){
		super("call_hold");
		_companyID = companyID;
		_opID = opID;
		_callLeg = callLeg;
		_flag = keep ? "1" : "0";
	}
	@Override
	protected String buildBody() {
		return String.format(BODY_PATTER, _companyID, _opID, _callLeg, _flag);
	}

}

package com.ewinsh.etone.driver.command;

/**
 * 构建请求静音命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月31日
 *
 */
public class SilenceCommand extends BaseCommand{
	private static final String BODY_PATTER =COMPANY_OPID_PATTER + "<CallLeg>%s</CallLeg><Flag>%s</Flag>";
	
	private final String _companyID;
	private final String _opID;
	private final String _callLeg;
	private final String _flag;
	
	/**
	 * 实例{@link SilenceCommand}
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号
	 * @param callLeg   呼叫标示
	 * @param silence   true:静音,false:取消静音
	 */
	public SilenceCommand(String companyID, String opID,String callLeg, boolean silence){
		super("silence");
		_companyID = companyID;
		_opID = opID;
		_callLeg = callLeg;
		_flag = silence ? "1" : "0";
	}

	@Override
	protected String buildBody() {
		return String.format(BODY_PATTER, _companyID, _opID, _callLeg, _flag);
	}

}

package com.ewinsh.etone.driver.command;

/**
 * 构建设置座席状态命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月31日
 *
 */
public class SettingStatusCommand extends BaseCommand {
	private static final String BODY_PATTER = COMPANY_OPID_PATTER + "<WorkID>%s</WorkID><Status>%s</Status>";
	private final String _companyID;
	private final String _opID;
	private final String _workID;
	private final String _status;
	
	/**
	 * 实例{@link SettingStatusCommand}
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号
	 * @param workID    工号
	 * @param status    座席状态
	 */
	public SettingStatusCommand(String companyID, String opID, String workID, String status){
		super("set_status");
		_companyID = companyID;
		_opID = opID;
		_workID = workID;
		_status = status;
	}

	@Override
	protected String buildBody() {
		return String.format(BODY_PATTER, _companyID, _opID, _workID, _status);
	}

}

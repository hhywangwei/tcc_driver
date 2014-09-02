package com.ewinsh.etone.driver.command;

/**
 * 构建设置移动座席命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月31日
 *
 */
public class MobileCommand extends BaseCommand{
	private final static String BODY_PATTER = COMPANY_OPID_PATTER + "<MobileNumber>%s</MobileNumber>";
	private final String _companyID;
	private final String _opID;
	private final String _mobile;
	
	/**
	 * 实例{@link MobileCommand}
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号
	 * @param mobile    设置移动电话号码
	 * @param setting   true:设置移动座席,false:取消移动座席
	 */
	public MobileCommand(String companyID, String opID, String mobile, boolean setting){
		super(setting ? "set_mobile_number" : "cancel_mobile_number");
		_companyID = companyID;
		_opID = opID;
		_mobile = mobile;
	}

	@Override
	protected String buildBody() {
		return String.format(BODY_PATTER, _companyID, _opID, _mobile);
	}

}

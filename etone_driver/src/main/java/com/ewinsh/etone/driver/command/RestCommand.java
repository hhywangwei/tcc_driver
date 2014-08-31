package com.ewinsh.etone.driver.command;

/**
 * 构建请求休息命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 *
 */
public class RestCommand extends BaseCommand{
	private final String _companyID;
	private final String _opID;

	/**
	 * 实例{@link RestCommand}
	 * 
	 * @param companyID 公司编号
	 * @param opID      操作员编号
	 * @param rest      true:休息,false:恢复工作
	 */
	public RestCommand(String companyID, String opID, boolean rest) {
		super(rest ? "leave" : "come_back");
		_companyID = companyID;
		_opID = opID;
	}

	@Override
	protected String buildBody() {
		return String.format(COMPANY_OPID_PATTER, _companyID, _opID);
	}

}

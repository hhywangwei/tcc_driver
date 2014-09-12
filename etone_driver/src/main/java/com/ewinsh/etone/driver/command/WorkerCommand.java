package com.ewinsh.etone.driver.command;

/**
 * 构建获取座席信息命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 *
 */
public class WorkerCommand extends BaseCommand{
	private static final String BODY_PATTER = COMPANY_OPID_PATTER + "<WorkID>%s</WorkID>";
	
	private final String _companyID;
	private final String _opID;
	private final String _workerID;

	public WorkerCommand(String companyID, String opID){
		this(companyID, opID, "");
	}
	
	public WorkerCommand(String companyID, String opID, String workerID) {
		super("per_worker_info");
		_companyID = companyID;
		_opID = opID;
		_workerID = workerID;
	}

	@Override
	protected String buildBody() {
		return String.format(BODY_PATTER, _companyID, _opID, _workerID);
	}

}

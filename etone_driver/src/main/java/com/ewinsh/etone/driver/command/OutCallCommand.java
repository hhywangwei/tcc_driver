package com.ewinsh.etone.driver.command;

/**
 * 构建外呼
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 */
public class OutCallCommand extends BaseCommand{
	private static final String BODY_PATTER = "<Phone1>%s</Phone1><Phone2>%s</Phone2>";
	private final String _opNumber;
	private final String _phone;
	
	public OutCallCommand(String opNumber,String phone){
		super("outcall");
		_opNumber = opNumber;
		_phone = phone;
	}

	@Override
	protected String buildBody() {
		return String.format(BODY_PATTER, _opNumber, _phone);
	}

}

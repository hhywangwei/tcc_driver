package com.ewinsh.etone.driver.command;

/**
 * 清除外呼命令
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 *
 */
public class OutCallCancelCommand extends BaseCommand {
	private static final String BODY_PATTER = "<CallLeg>%s</CallLeg>";
	
	private final String _callLeg;
	
	public OutCallCancelCommand(String callLeg){
		super("outcallcancel");
		_callLeg = callLeg;
	}
	

	@Override
	protected String buildBody() {
		return String.format(BODY_PATTER, _callLeg);
	}
}

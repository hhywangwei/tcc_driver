package com.ewinsh.etone.driver.command;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 测试{@link BaseCommand}
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月31日
 *
 */
public class BaseCommandTest {
	
	@Test
	public void testBuild(){
		BaseCommandImpl command = new BaseCommandImpl("test","2");
		String expected = "<head>00044</head><msg>test</msg><seq>2</seq><body>test</body>";
		Assert.assertEquals(expected, command.build());
	}
	

	static class BaseCommandImpl extends BaseCommand{

		public BaseCommandImpl(String t, String seq) {
			super(t, seq);
		}

		@Override
		protected String buildBody() {
			return "<body>test</body>";
		}
		
	}
}

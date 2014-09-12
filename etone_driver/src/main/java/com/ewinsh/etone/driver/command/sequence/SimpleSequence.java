package com.ewinsh.etone.driver.command.sequence;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 实现发送序列号对象，使用{@link AtomicLog}生成序列
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014年8月30日
 */
public class SimpleSequence implements Sequenceable {
	private static final Object monitor = new Object();
	private static SimpleSequence seq;
    private final AtomicLong count;
    
    private SimpleSequence(){
    	count = new AtomicLong(0l);
    }
	
	@Override
	public String next() {
		return String.valueOf(count.incrementAndGet());
	}

	public static SimpleSequence instance(){
		synchronized (monitor) {
			if(seq == null){
				seq = new SimpleSequence();
			}
			return seq;
		}
	}
}

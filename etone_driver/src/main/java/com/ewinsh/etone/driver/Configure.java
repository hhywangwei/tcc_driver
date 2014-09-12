package com.ewinsh.etone.driver;

import java.nio.charset.Charset;

/**
 * 链接CTI服务器配置信息
 * 
 * @author <a href="hhywangwei@gmail.com">WangWei</a>
 * @since 2014-08-26
 */
public class Configure {
	
	public static class Builder{
		private static final int DEFAULT_CONNECT_TIMEOUT = 20;
		private static final int DEFAULT_MAX_MESSAGE_LEN = 1024;
		private static final int DEFAULT_MAX_SERVICE = 1024;
		private static final long DEFAULT_HEARTBEAT_PERIOD = 20 * 1000l;
		private static final Charset DEFAULT_CHARSET = Charset.forName("GBK");
		
		private final String _host;
		private final int _port;
		private int _connectTimeout = DEFAULT_CONNECT_TIMEOUT;
		private int _maxMessageLen = DEFAULT_MAX_MESSAGE_LEN;
		private int _maxService = DEFAULT_MAX_SERVICE;
		private long _heartbeatPeriod = DEFAULT_HEARTBEAT_PERIOD;
		private Charset _charset = DEFAULT_CHARSET;
		
		/**
		 * 实例{@link Builder}
		 * <br/>
		 * 构建Configure不可变对象
		 * 
		 * @param host CTI服务地址
		 * @param port CTI服务端口
		 */
		public Builder(String host,int port){
			_host = host;
			_port = port;
		}
		
		/**
		 * 设置连接服务器超时
		 * 
		 * @param timeout 超时时间，单位为秒（s）
		 */
		public void setConnectTimeout(int timeout){
			_connectTimeout = timeout;
		}
		
		/**
		 * 设置最大服务数
		 * 
		 * @param max 最大服务数
		 */
		public void setMaxService(int max){
			_maxService = max;
		}
		
		/**
		 * 设置消息最大长度，超过该长度消息忽略
		 * 
		 * @param max 最大消息长度
		 */
		public void setMaxMessageLen(int max){
			_maxMessageLen = max;
		}
		
		/**
		 * 设置心跳周期
		 * 
		 * @param period 心跳周期，单位为毫秒（ms）
		 */
		public void setHeartbeatPeriod(long period){
			_heartbeatPeriod = period;
		}
		
		/**
		 * 设置传输字符编码
		 * 
		 * @param charsetName 字符编码名称
		 */
		public void setCharset(String charsetName){
			_charset = Charset.forName(charsetName);
		}
		
		public Configure build(){
			return new Configure(_host, _port, _connectTimeout, 
					_maxService, _maxMessageLen, _heartbeatPeriod, _charset);
		}
	}
	
	private final String _host;
	private final int _port;
	private final int _connectTimeout;
	private final int _maxService;
	private final int _maxMessageLen;
	private final long _heartbeatPeriod;
	private final Charset _charset;
	
	public Configure(String host, int port, int connectTimeout, 
			int maxService, int maxMessageLen, long heartbeatPeriod, Charset charset){
		
		_host = host;
		_port = port;
		_connectTimeout = connectTimeout;
		_maxService = maxService;
		_maxMessageLen = maxMessageLen;
		_heartbeatPeriod = heartbeatPeriod;
		_charset = charset;
	}
	
	/**
	 * 得到CTI服务器地址
	 * 
	 * @return
	 */
	public String getHost(){
		return _host;
	}
	
	/**
	 * 得到CTI服务端口
	 * 
	 * @return
	 */
	public int getPort(){
		return _port;
	}
	
	/**
	 * 得到最大服务数
	 * 
	 * @return
	 */
	public int getMaxService(){
		return _maxService;
	}
	
	/**
	 * 得到连接超时，单位为秒（s）
	 * 
	 * @return
	 */
	public int getConnectTimeout(){
		return _connectTimeout;
	}
	
	/**
	 * 得到最大消息长度
	 * 
	 * @return
	 */
	public int getMaxMessageLen(){
		return _maxMessageLen;
	}
	
	/**
	 * 得到心跳周期，单位为毫秒（ms）
	 * 
	 * @return
	 */
	public long getHeartbeatPeriod(){
		return _heartbeatPeriod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_host == null) ? 0 : _host.hashCode());
		result = prime * result + _port;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configure other = (Configure) obj;
		if (_host == null) {
			if (other._host != null)
				return false;
		} else if (!_host.equals(other._host))
			return false;
		if (_port != other._port)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder2 = new StringBuilder();
		builder2.append("Configure [_host=");
		builder2.append(_host);
		builder2.append(", _port=");
		builder2.append(_port);
		builder2.append(", _connectTimeout=");
		builder2.append(_connectTimeout);
		builder2.append(", _maxService=");
		builder2.append(_maxService);
		builder2.append(", _maxMessageLen=");
		builder2.append(_maxMessageLen);
		builder2.append(", _heartbeatPeriod=");
		builder2.append(_heartbeatPeriod);
		builder2.append(", _charset=");
		builder2.append(_charset);
		builder2.append("]");
		return builder2.toString();
	}
}

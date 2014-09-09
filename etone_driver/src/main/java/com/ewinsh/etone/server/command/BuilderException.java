package com.ewinsh.etone.server.command;

import com.ewinsh.etone.server.Response;

public class BuilderException extends Exception {
	private static final long serialVersionUID = 1L;
	private final Response _response;
	
	public BuilderException(String msg,Throwable t,Response response){
		super(msg,t);
		_response = response;
	}
	
	public BuilderException(String msg, Response response){
		super(msg);
		_response = response;
	}
	
	public Response response(){
		return _response;
	}

}

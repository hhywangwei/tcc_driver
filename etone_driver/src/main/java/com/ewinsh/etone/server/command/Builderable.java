package com.ewinsh.etone.server.command;

import com.ewinsh.etone.driver.command.Commandable;
import com.ewinsh.etone.server.Response;

public interface Builderable<T>{
	
	boolean validate(T o, Response response);
	
	Commandable build(T o);
	
}
package com.ewinsh.etone.server.command.builder;

public class CommandBuilders {
	
	public static CommandBuilderable builder(String type){
		return new LoginBuilder();
	}

}

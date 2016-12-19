package com.sangupta.am.servlet.support;

import java.io.IOException;

import javax.servlet.ServletException;

public abstract class AmForwardOrIncludeHandler {

	public abstract void handleForward(String url) throws ServletException, IOException;
	
	public abstract void handleInclude(String url, boolean flush) throws ServletException, IOException; 
	
}

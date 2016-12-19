package com.sangupta.am.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AmRequestDispatcher implements RequestDispatcher {
	
	protected String path;
	
	public AmRequestDispatcher() {

	}
	
	public AmRequestDispatcher(String path) {
		this.path = path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return path;
	}
	
	// Overridden methods follow

	@Override
	public void forward(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	public void include(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
	}

}

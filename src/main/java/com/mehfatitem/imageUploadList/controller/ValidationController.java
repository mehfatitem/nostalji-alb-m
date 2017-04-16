package com.mehfatitem.imageUploadList.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/validation")
public class ValidationController {
	
	private Pattern pattern;
	private Matcher matcher;
	
	 
	private static final String USERNAME_PATTERN = "^[a-z0-9_-]{5,30}$";
	
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,30})";
	
	@RequestMapping(value="/username" , method = RequestMethod.GET)

	public void username(HttpServletRequest request  , HttpServletResponse response) throws IOException{
		this.pattern = Pattern.compile(USERNAME_PATTERN);
		this.matcher = this.pattern.matcher(request.getParameter("username"));
		System.out.println(request.getParameter("username") + " " + this.matcher.matches());
		response.getWriter().write(String.valueOf(this.matcher.matches()));
	}
	
	@RequestMapping(value="/password" , method = RequestMethod.GET)
	public void password(HttpServletRequest request , HttpServletResponse response) throws IOException{
		this.pattern = Pattern.compile(PASSWORD_PATTERN);
		this.matcher = this.pattern.matcher(request.getParameter("password"));
		System.out.println(request.getParameter("password") + " " + this.matcher.matches());
		response.getWriter().write(String.valueOf(this.matcher.matches()));
	}
}

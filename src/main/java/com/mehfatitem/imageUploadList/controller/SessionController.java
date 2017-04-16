package com.mehfatitem.imageUploadList.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SessionController {

	@RequestMapping(value = "/controlSession", method = RequestMethod.GET)
	public void controlSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Boolean returnResult = false;
		HttpSession session = request.getSession(true);
		if (session.getAttribute("sessUsername") != null || session.getAttribute("sessContact") != null) {
			returnResult = true;
		}
		response.getWriter().write(returnResult.toString());
	}

}

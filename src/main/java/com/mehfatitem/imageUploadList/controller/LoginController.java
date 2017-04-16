package com.mehfatitem.imageUploadList.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mehfatitem.dao.UserDAO;
import com.mehfatitem.imageUploadList.model.Specification;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    String warningMessage = "";
    Specification us = new Specification();
    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(HttpServletRequest request, HttpServletResponse response, Model m) throws IOException, NoSuchAlgorithmException {
        HttpSession session = request.getSession(true);
        String userName = request.getParameter("username").trim();
        String password = us.MD5Hashing(request.getParameter("password").trim());
        if (userDAO.loginControl(userName, password) > 0) {
        	String contact = userDAO.getColumnFromUserTable("where username = '" +userName+"'" , "contact", "String");
 		    int userId = userDAO.getUserIdAsName(userName);
 		    session.setAttribute("sessUserId", userId);
        	session.setAttribute("sessContact", contact);
            session.setAttribute("sessUsername", request.getParameter("username"));
            session.setAttribute("sessPassword", request.getParameter("password"));
            response.getWriter().write(" ");
        } else {
            warningMessage = "Kullanýcý adý veya þifreniz yanlýþ!";
            us.responseAlertText(response, "Kullanýcý adý veya þifreniz yanlýþ!", "warning", "Uyarý!");
        }
    }
}

package com.mehfatitem.imageUploadList.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
import com.mehfatitem.model.User;

@Controller
@RequestMapping(value = "/login")
public class FbLoginController {

	private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	UserDAO userDAO = (UserDAO) context.getBean("userDAO"); // dao nesnemiz
	Specification sp = new Specification();

	@RequestMapping(value = "/fbLogin", method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response, Model m)
			throws IOException, NoSuchAlgorithmException {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("sessContact") == null) {
			int counter = 0;

			String accessToken = request.getParameter("access_token");

			URL url = new URL("https://graph.facebook.com/me?access_token=" + accessToken);
			System.out.println(url);
			URLConnection conn = url.openConnection();

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			while ((reader.readLine()) != null) {
				counter++;
			}
			if (counter > 0) {

				String contact = request.getParameter("contact");
				String email = request.getParameter("email");
				String userName = contact.replace(" ", "_").toLowerCase();
				String password = sp.createRandomPassword();
				String MD5password = sp.MD5Hashing(password);
				Long saveDate = (long) (System.currentTimeMillis() / 1000L);// kayit
																			// zamani

				if (userDAO.isExist(userName, "username") <= 0) {

					User user = new User(0, contact, userName, MD5password, email, saveDate);
					userDAO.insert(user);

				} else {
					response.getWriter().write("Oturum zaten açýk!");
				}

				session.setAttribute("sessContact", contact);
				session.setAttribute("sessUsername", userName);
				session.setAttribute("sessPassword", password);

				int userId = userDAO.getUserIdAsName(userName);

				session.setAttribute("sessUserId", userId);
			}
			reader.close();
		}
	}
}

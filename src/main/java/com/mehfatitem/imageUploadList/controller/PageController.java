package com.mehfatitem.imageUploadList.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mehfatitem.dao.ImageDAO;
import com.mehfatitem.dao.UserDAO;
import com.mehfatitem.imageUploadList.model.Specification;

@Controller
public class PageController {

	private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
	ImageDAO imageDAO = (ImageDAO) context.getBean("imageDAO"); // dao nesnemiz
	UserDAO userDAO = (UserDAO) context.getBean("userDAO"); // dao nesnemiz
	Specification s = new Specification();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model m, HttpServletRequest request) {
		String template = "";
		HttpSession session = request.getSession(true);
		m.addAttribute("username", session.getAttribute("sessUsername"));
		if (session.getAttribute("sessUsername") == null || session.getAttribute("sessUsername").equals("")
				|| session.getAttribute("sessContact") == null) {
			template = s.returnHomePageSlider();
			m.addAttribute("template", template);
		} else {
			int userId = (int) session.getAttribute("sessUserId");
			template = imageDAO.ListImageAsForHomePage(userId);
			m.addAttribute("template", template);
		}
		return "index";
	}

	@RequestMapping(value = "/imageList", method = RequestMethod.GET)
	public String listImage(Model model, HttpServletRequest request, HttpServletResponse response, Model m)
			throws IOException {
		String returnResult = "";
		HttpSession session = request.getSession(true);
		System.out.println(session.getAttribute("sessUsername"));
		if (session.getAttribute("sessUsername") == null || session.getAttribute("sessUsername").equals("")) {
			m.addAttribute("warningMessage", "Resim listeleyebilmek için oturum açmalýsýnýz!");
			returnResult = "sessionWarning";
		} else {
			int userId = (int) session.getAttribute("sessUserId");
			String template = imageDAO.ListImageAsUserId(userId);
			model.addAttribute("template", template);
			returnResult = "imageList";
		}
		return returnResult;
	}

	@RequestMapping(value = "/imageUpload", method = RequestMethod.GET)
	public String uploadImage(HttpServletRequest request, Model m) {
		String returnResult = "";
		m.addAttribute("display", "none");
		HttpSession session = request.getSession(true);
		if (session.getAttribute("sessUsername") == null || session.getAttribute("sessUsername").equals("")) {
			m.addAttribute("warningMessage", "Resim yükleyebilmek için oturum açmalýsýnýz!");
			returnResult = "sessionWarning";
		} else {
			returnResult = "imageUpload";
		}
		return returnResult;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		String returnResult = "";
		HttpSession session = request.getSession(true);
		if (session.getAttribute("sessUsername") != null || session.getAttribute("sessContact") != null) {
			returnResult = "goHome";
		} else {
			returnResult = "login";
		}
		return returnResult;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		session.invalidate();
		return "goLogin";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(HttpServletRequest request) {
		String returnResult = "";
		HttpSession session = request.getSession(true);
		if (session.getAttribute("sessUsername") != null || session.getAttribute("sessContact") != null) {
			returnResult = "goHome";
		} else {
			returnResult = "signup";
		}
		return returnResult;
	}

	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public String forgetPassword() {
		return "forgetPassword";
	}

	@RequestMapping(value = "/settings", method = RequestMethod.GET)
	public String settings(HttpServletRequest request, Model m) {
		String returnResult = "";
		HttpSession session = request.getSession(true);
		if (session.getAttribute("sessUsername") == null || session.getAttribute("sessUsername").equals("")) {
			returnResult = "goHome";
		} else {
			List<String> userInfo = userDAO.getUserAsUserName(session.getAttribute("sessUsername").toString());
			String[] userArray = userInfo.toArray(new String[userInfo.size()]);
			m.addAttribute("contact", userArray[0]);
			m.addAttribute("username", userArray[1]);
			m.addAttribute("email", userArray[2]);
			returnResult = "settings";
		}
		return returnResult;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response, Model m) throws IOException {
		response.getWriter().write(
				userDAO.getColumnFromUserTable("where contact = 'Otokar Ýnsan Kaynaklarý'", "user_id", "Integer"));
	}
	
	@RequestMapping(value = "/xox", method = RequestMethod.GET)
	public String xox() {
		return "xox";
	}

	@RequestMapping("favicon.ico")
	public String favicon() {
		return "forward:/resources/images/favicon-image.ico";
	}
}

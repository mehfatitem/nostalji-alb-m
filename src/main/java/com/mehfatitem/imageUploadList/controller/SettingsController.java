package com.mehfatitem.imageUploadList.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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

import com.mehfatitem.dao.UserDAO;
import com.mehfatitem.imageUploadList.model.Specification;
import com.mehfatitem.imageUploadList.model.Validation;
import com.mehfatitem.model.User;

@Controller
@RequestMapping(value = "/settings")
public class SettingsController {

    String warningMessage = "";

    Specification us = new Specification();
    Validation v = new Validation();

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO"); // dao nesnemiz

    @RequestMapping(value = "/settings", method = RequestMethod.POST)
    public String editUser(HttpServletRequest request, HttpServletResponse response ,  Model m) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	HttpSession session = request.getSession(true);
        String contact = request.getParameter("contact").trim();
        System.out.println(contact);
        String userName = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password");
        String MD5password = us.MD5Hashing(request.getParameter("password")).trim();
        String repassword = request.getParameter("repassword");

        Long saveDate = (long) (System.currentTimeMillis() / 1000L);//kayit zamani

        String returnResult = "error";

        List<String> userInfo = userDAO.getUserAsUserName(session.getAttribute("sessUsername").toString());
        String[] userArray = userInfo.toArray(new String[userInfo.size()]);
        String userId = userArray[4];
        String gettedEmail = userArray[2];
        
        User user = new User(Integer.parseInt(userId) , contact, userName, MD5password, email, saveDate);

        if (contact.length() == 0) {
            this.warningMessage += "Ad ve soyad girmelisiniz!\n";
        }
        if (userName.length() == 0 || userName.length() < 5) {
            this.warningMessage += "Kullanýcý adý zorunlu alandýr ve minimum 5 karakterli olmalýdýr!\n";
        }
        if (!v.validUserName(userName)) {
            this.warningMessage += "Kullanýcý adýnýn Türkçe karakter ve özel karakter içermemesine dikkat ediniz!\n";
        }
        if (!v.validIsExist(email) || !v.validEmail(email)) {
            this.warningMessage += "Email alaný zorunludur ve geçerli formatta olmalýdýr!\n";
        }
        if ((password.length() == 0 && repassword.length() == 0) || (password.length() < 5 && repassword.length() < 5) || !password.equals(repassword)) {
            this.warningMessage += "Þifre alanlarý zorunludur , minimum 5 karakterli olmalýdýr ve þifreler birbiriyle eþleþmelidir!\n";
        }
        if (!v.validPassword(password)) {
            this.warningMessage += "Þifreniz Türkçe karakter içermemeli , bir büyük bir de küçük harf ve rakamlar içermelidir!";
        }
        if (this.warningMessage.length() == 0) {
            if(userDAO.isExist(email, "email")>0 && !gettedEmail.equals(email)){
            	this.warningMessage += "Bu e-posta adresi sistemde mevcuttur!";
                m.addAttribute("warningMessage", this.warningMessage);
                returnResult = "warning";
            }
            if(!v.validIsExist(this.warningMessage)){
                if (userDAO.update(user) > 0) {
                	session.setAttribute("sessContact", user.getContact());
                    returnResult = "success";
                }
            }else{
            	m.addAttribute("warningMessage", this.warningMessage);
                this.warningMessage = "";
                returnResult = "warning";
            }
        } else {
        	m.addAttribute("warningMessage", this.warningMessage);
            this.warningMessage = "";
            returnResult = "warning";
        }
        return returnResult;
    }
}

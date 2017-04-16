package com.mehfatitem.imageUploadList.controller;

import java.security.NoSuchAlgorithmException;
import com.mehfatitem.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mehfatitem.dao.UserDAO;
import com.mehfatitem.imageUploadList.model.Specification;
import com.mehfatitem.imageUploadList.model.Validation;

@Controller
@RequestMapping(value = "/signup")
public class SignupController {

    String warningMessage = "";

    Specification us = new Specification();
    Validation v = new Validation();

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO"); // dao nesnemiz

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String insertUser(HttpServletRequest request, HttpServletResponse response ,  Model m) throws NoSuchAlgorithmException {
        String contact = request.getParameter("contact").trim();
        System.out.println(contact);
        String userName = request.getParameter("username").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password");
        String MD5password = us.MD5Hashing(request.getParameter("password")).trim();
        String repassword = request.getParameter("repassword");

        Long saveDate = (long) (System.currentTimeMillis() / 1000L);//kayit zamani

        String returnResult = "error";

       
        User user = new User(0,contact, userName, MD5password, email, saveDate);

        if (contact.length() == 0) {
            this.warningMessage += "Ad ve soyad girmelisiniz!\n";
        }
        if (userName.length() == 0 || userName.length() < 5) {
            this.warningMessage += "Kullan�c� ad� zorunlu aland�r ve minimum 5 karakterli olmal�d�r!\n";
        }
        if (!v.validUserName(userName)) {
            this.warningMessage += "Kullan�c� ad�n�n T�rk�e karakter ve �zel karakter i�ermemesine dikkat ediniz!\n";
        }
        if (!v.validIsExist(email) || !v.validEmail(email)) {
            this.warningMessage += "Email alan� zorunludur ve ge�erli formatta olmal�d�r!\n";
        }
        if ((password.length() == 0 && repassword.length() == 0) || (password.length() < 5 && repassword.length() < 5) || !password.equals(repassword)) {
            this.warningMessage += "�ifre alanlar� zorunludur , minimum 5 karakterli olmal�d�r ve �ifreler birbiriyle e�le�melidir!\n";
        }
        if (!v.validPassword(password)) {
            this.warningMessage += "�ifreniz T�rk�e karakter i�ermemeli , bir b�y�k bir de k���k harf ve rakamlar i�ermelidir!";
        }
        if (this.warningMessage.length() == 0) {
            if (userDAO.isExist(userName , "username") > 0) {
                this.warningMessage += "\nBu kullan�c� ad� kay�tl�d�r!\n";
                m.addAttribute("warningMessage", this.warningMessage);
                returnResult = "warning";
            }
            if(userDAO.isExist(email, "email")>0){
            	this.warningMessage += "Bu e-posta adresi sistemde mevcuttur!";
                m.addAttribute("warningMessage", this.warningMessage);
                returnResult = "warning";
            }
            if(!v.validIsExist(this.warningMessage)){
                if (userDAO.insert(user) > 0) {
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

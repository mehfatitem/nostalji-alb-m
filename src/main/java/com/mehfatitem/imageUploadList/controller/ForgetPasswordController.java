package com.mehfatitem.imageUploadList.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mehfatitem.dao.UserDAO;
import com.mehfatitem.imageUploadList.model.MailTemplate;
import com.mehfatitem.imageUploadList.model.SendEmail;
import com.mehfatitem.imageUploadList.model.Specification;
import com.mehfatitem.imageUploadList.model.Validation;

@Controller
public class ForgetPasswordController {

    ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    String warningMessage = "";

    Validation v = new Validation();
    Specification us = new Specification();
    SendEmail se = new SendEmail();
    MailTemplate mt = new MailTemplate();
    
    public void sendMail(String contact , String email , String mailTitle , String text , HttpServletResponse response){
    	String mailTemplate = mt.sendMailTemplateForJob(contact, email, "2017", mailTitle, text, "https://www.linkedin.com/in/mehmed-fatih-temiz-3020326a/", "Mehmed Fatih Temiz");
    	if (se.sendMail(email, "temizfatih54@gmail.com", mailTitle, mailTemplate)) {
        	us.responseAlertText(response, "E-postan�z <strong>" + email + "</strong> mail adresinize ba�ar�yla g�nderilmi�tir.", "success", "Ba�ar�l�!");
        } else {
        	us.responseAlertText(response, "E-posta g�nderimi esnas�nda bir hata olu�tu!", "danger", "Hata!");
        }
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public void username(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, NoSuchAlgorithmException {
        String userName = request.getParameter("username").trim();
        if (!v.validIsExist(userName)) {
            this.warningMessage += "L�tfen kullan�c� ad�n�z� bo� b�rakmay�n!";
        }
        if (v.validIsExist(this.warningMessage)) {
        	us.responseAlertText(response, this.warningMessage, "warning", "Uyar�!");
            this.warningMessage = "";
        } else {
            if (userDAO.isExistUser(userName) <= 0) {
            	us.responseAlertText(response, "Girmi� oldu�unuz kullan�c� ad� sistemde mevcut de�ildir!", "warning", "Uyar�!");
            } else {
                String subject = "ImageUploadList � �ifremi unuttum";
                String randomPassword = us.createRandomPassword();
                List<String> userInfo = userDAO.getUserAsUserName(userName);
                String [] userArray = us.convertListToArray(userInfo);
                String contact = userArray[0];
                String email =userArray[2];
                if(email.equals("ik@otokar.com.tr")){
                	String text = "Merhabalar. Size defalarca mail att�m. Yaz�l�m Uzman� pozisyonu i�in gerekli olan kriterlerin t�m� bende mevcut hatta fazlas� da var. Bana bir �ans vermeniz i�in daha ne yapmam gerek. Hay�r s�yleyin de bileyim. Zor durumday�m. 3 aydan beri i�sizim. Yaz�l�m i�in do�mu�um ve mesle�ime a����m ve keza memleketime de. Bu y�zden otokar da �al��may� istiyorum. Teknik bilgim ve �ngilizce dil seviyem gayet iyi. Her t�rl� m�lakata ve g�r��meye haz�r�m ama malesef o kadar iste�imi arzumu dile getirmeme ra�men , talep var ama sizin taraf�n�zdan talebime cevap yok. Cidden ne yapmam gerek. Beni m�lkata �a��rman�z i�in ne yapmam gerek. Size hakl� bir �ekilde sitem ediyorum ve iyi �al��malar diliyorum.";
                	String mailTitle = "Otokar Yaz�l�m Uzman� �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("ik@ford.com.tr")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum.L�tfen beni m�lakat�n�za davet ediniz. Neler yap�p yapamayaca��m� g�rmenizi isterim. Sizinle �al��may� ger�ekten �ok istiyorum. ";
                	String mailTitle = "Ford Otosan Yaz�l�m Uzman� �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("ik@bimsa.com.tr")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum.L�tfen beni m�lakat�n�za davet ediniz. Neler yap�p yapamayaca��m� g�rmenizi isterim. Sizinle �al��may� ger�ekten �ok istiyorum. ";
                	String mailTitle = "Bimsa Java Yaz�l�m Uzman� �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("info@toyota-boshokutr.com")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum.L�tfen beni m�lakat�n�za davet ediniz. Neler yap�p yapamayaca��m� g�rmenizi isterim. Sizinle �al��may� ger�ekten �ok istiyorum. ";
                	String mailTitle = "Toyota Boshoku Bilgisayar M�hendisli�i �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("brisa.info@brisa.com.tr")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum.L�tfen beni m�lakat�n�za davet ediniz. Neler yap�p yapamayaca��m� g�rmenizi isterim. Sizinle �al��may� ger�ekten �ok istiyorum. ";
                	String mailTitle = "Brisa Yaz�l�m Geli�tirme Uzman� �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("info@gbhtelekom.com")){
                	String mailTitle = "Sakarya Hendek �abuk.Net | GBH Telekom �ikayet";
                	String text = "Merhabalar size bir �ikayetim olacak. Sakarya Hendek'te �abuk.net m�sterisiyim. Yaln�z internet s�rekli kopuyor ve yakla��k 6 saatten beri internete giremiyorum. Konuyla alakal� ilgili birimlere ula�t���m�zda bize malesef �ok vasat bir hizmet sunuyorlar hatta hizmet dahi sunmuyorlar. ��nk� muhattap olabileci�im biri dahi yok kar��mda. Ben yaz�l�m m�hendisiyim ve internet benim i�in bir nefes gibi. Ama bu gidi�le �abuk.net ile yollar�m�z ayr�lacak. Gere�inin bir an �nce yap�lmas�n� , ma�duriyetimin giderilmesini bekliyorum.";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.contains("cybersoft.com.tr")){
                	String mailTitle = "Cybersoft M�lakat Sonucu";
                	String text = "Merhabalar. Geri d�n���n�z� merakla bekliyorum. �lgimi maruz g�r�n. 3 g�n i�ersinde bir geri d�n�� alaca��m s�ylendi. Sizden olumlu ya da olumsuz, eksikliklerimi g�rmek ad�na detayl� bir cevap bekliyorum.";
                	this.sendMail("M�ge Toydemir" ,  "muge.toydemir@cybersoft.com.tr" ,  mailTitle ,  text , response);
                	this.sendMail("Ayhan Apayd�n" ,  "ayhan.apaydin@cybersoft.com.tr" ,  mailTitle ,  text , response);
                	this.sendMail("Ay�e Turan Ezberci" ,  "ayse.ezberci@cybersoft.com.tr" ,  mailTitle ,  text , response);
                }else if(email.equals("serhan.tuygun@highproc.com")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum. Neler yap�p yapamayaca��m� g�rmenizi isterim.";
                	String mailTitle = "HighProc Yaz�l�m Geli�tirme Uzman� �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("zuber@editsystems.com")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum. Neler yap�p yapamayaca��m� g�rmenizi isterim.";
                	String mailTitle = "Edit Systems Yaz�l�m Geli�tirme Uzman� �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }
                else if(email.equals("info@okzasoft.com")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum. Neler yap�p yapamayaca��m� g�rmenizi isterim.";
                	String mailTitle = "OKZASOFT Yaz�l�m Geli�tirme Uzman� �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("info@strategia.com.tr")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum. Neler yap�p yapamayaca��m� g�rmenizi isterim.";
                	String mailTitle = "STRATEGIA Yaz�l�m Geli�tirme Uzman� �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("bilgi@argede.com.tr")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum. Neler yap�p yapamayaca��m� g�rmenizi isterim.";
                	String mailTitle = "ARGEDE Yaz�l�m Geli�tirme Uzman� �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("iletisim@caz.com.tr")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum. Neler yap�p yapamayaca��m� g�rmenizi isterim.";
                	String mailTitle = "CAZ Yaz�l�m Geli�tirme Uzman� �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("info@yazkar.com.tr")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum. Neler yap�p yapamayaca��m� g�rmenizi isterim.";
                	String mailTitle = "YAZKAR G�m�l� Yaz�l�m M�hendisi �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("info@bidolubaski.com")){
                	String text = "Merhabalar. Bu maili beni m�lakat g�r��melerine kabul etmeniz i�in g�nderiyorum. Neler yap�p yapamayaca��m� g�rmenizi isterim.";
                	String mailTitle = "Junior Software Developer �� Ba�vurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else{
                	String text = mt.createforgetPasswordTemplate(contact, email, userName ,  randomPassword , "2017");
                    if (se.sendMail(email, "temizfatih54@gmail.com", subject, text) && userDAO.updatePassword(email, us.MD5Hashing(randomPassword)) > 0) {
                    	us.responseAlertText(response, "E-postan�z <strong>" + email + "</strong> mail adresinize ba�ar�yla g�nderilmi�tir.", "success", "Ba�ar�l�!");
                    } else {
                    	us.responseAlertText(response, "E-posta g�nderimi esnas�nda bir hata olu�tu!", "danger", "Hata!");
                    }
                }
            }
        }
    }

}

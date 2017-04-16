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
        	us.responseAlertText(response, "E-postanýz <strong>" + email + "</strong> mail adresinize baþarýyla gönderilmiþtir.", "success", "Baþarýlý!");
        } else {
        	us.responseAlertText(response, "E-posta gönderimi esnasýnda bir hata oluþtu!", "danger", "Hata!");
        }
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public void username(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException, NoSuchAlgorithmException {
        String userName = request.getParameter("username").trim();
        if (!v.validIsExist(userName)) {
            this.warningMessage += "Lütfen kullanýcý adýnýzý boþ býrakmayýn!";
        }
        if (v.validIsExist(this.warningMessage)) {
        	us.responseAlertText(response, this.warningMessage, "warning", "Uyarý!");
            this.warningMessage = "";
        } else {
            if (userDAO.isExistUser(userName) <= 0) {
            	us.responseAlertText(response, "Girmiþ olduðunuz kullanýcý adý sistemde mevcut deðildir!", "warning", "Uyarý!");
            } else {
                String subject = "ImageUploadList ® Þifremi unuttum";
                String randomPassword = us.createRandomPassword();
                List<String> userInfo = userDAO.getUserAsUserName(userName);
                String [] userArray = us.convertListToArray(userInfo);
                String contact = userArray[0];
                String email =userArray[2];
                if(email.equals("ik@otokar.com.tr")){
                	String text = "Merhabalar. Size defalarca mail attým. Yazýlým Uzmaný pozisyonu için gerekli olan kriterlerin tümü bende mevcut hatta fazlasý da var. Bana bir þans vermeniz için daha ne yapmam gerek. Hayýr söyleyin de bileyim. Zor durumdayým. 3 aydan beri iþsizim. Yazýlým için doðmuþum ve mesleðime aþýðým ve keza memleketime de. Bu yüzden otokar da çalýþmayý istiyorum. Teknik bilgim ve Ýngilizce dil seviyem gayet iyi. Her türlü mülakata ve görüþmeye hazýrým ama malesef o kadar isteðimi arzumu dile getirmeme raðmen , talep var ama sizin tarafýnýzdan talebime cevap yok. Cidden ne yapmam gerek. Beni mülkata çaðýrmanýz için ne yapmam gerek. Size haklý bir þekilde sitem ediyorum ve iyi çalýþmalar diliyorum.";
                	String mailTitle = "Otokar Yazýlým Uzmaný Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("ik@ford.com.tr")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum.Lütfen beni mülakatýnýza davet ediniz. Neler yapýp yapamayacaðýmý görmenizi isterim. Sizinle çalýþmayý gerçekten çok istiyorum. ";
                	String mailTitle = "Ford Otosan Yazýlým Uzmaný Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("ik@bimsa.com.tr")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum.Lütfen beni mülakatýnýza davet ediniz. Neler yapýp yapamayacaðýmý görmenizi isterim. Sizinle çalýþmayý gerçekten çok istiyorum. ";
                	String mailTitle = "Bimsa Java Yazýlým Uzmaný Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("info@toyota-boshokutr.com")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum.Lütfen beni mülakatýnýza davet ediniz. Neler yapýp yapamayacaðýmý görmenizi isterim. Sizinle çalýþmayý gerçekten çok istiyorum. ";
                	String mailTitle = "Toyota Boshoku Bilgisayar Mühendisliði Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("brisa.info@brisa.com.tr")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum.Lütfen beni mülakatýnýza davet ediniz. Neler yapýp yapamayacaðýmý görmenizi isterim. Sizinle çalýþmayý gerçekten çok istiyorum. ";
                	String mailTitle = "Brisa Yazýlým Geliþtirme Uzmaný Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("info@gbhtelekom.com")){
                	String mailTitle = "Sakarya Hendek Çabuk.Net | GBH Telekom Þikayet";
                	String text = "Merhabalar size bir þikayetim olacak. Sakarya Hendek'te çabuk.net müsterisiyim. Yalnýz internet sürekli kopuyor ve yaklaþýk 6 saatten beri internete giremiyorum. Konuyla alakalý ilgili birimlere ulaþtýðýmýzda bize malesef çok vasat bir hizmet sunuyorlar hatta hizmet dahi sunmuyorlar. Çünkü muhattap olabileciðim biri dahi yok karþýmda. Ben yazýlým mühendisiyim ve internet benim için bir nefes gibi. Ama bu gidiþle çabuk.net ile yollarýmýz ayrýlacak. Gereðinin bir an önce yapýlmasýný , maðduriyetimin giderilmesini bekliyorum.";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.contains("cybersoft.com.tr")){
                	String mailTitle = "Cybersoft Mülakat Sonucu";
                	String text = "Merhabalar. Geri dönüþünüzü merakla bekliyorum. Ýlgimi maruz görün. 3 gün içersinde bir geri dönüþ alacaðým söylendi. Sizden olumlu ya da olumsuz, eksikliklerimi görmek adýna detaylý bir cevap bekliyorum.";
                	this.sendMail("Müge Toydemir" ,  "muge.toydemir@cybersoft.com.tr" ,  mailTitle ,  text , response);
                	this.sendMail("Ayhan Apaydýn" ,  "ayhan.apaydin@cybersoft.com.tr" ,  mailTitle ,  text , response);
                	this.sendMail("Ayþe Turan Ezberci" ,  "ayse.ezberci@cybersoft.com.tr" ,  mailTitle ,  text , response);
                }else if(email.equals("serhan.tuygun@highproc.com")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum. Neler yapýp yapamayacaðýmý görmenizi isterim.";
                	String mailTitle = "HighProc Yazýlým Geliþtirme Uzmaný Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("zuber@editsystems.com")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum. Neler yapýp yapamayacaðýmý görmenizi isterim.";
                	String mailTitle = "Edit Systems Yazýlým Geliþtirme Uzmaný Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }
                else if(email.equals("info@okzasoft.com")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum. Neler yapýp yapamayacaðýmý görmenizi isterim.";
                	String mailTitle = "OKZASOFT Yazýlým Geliþtirme Uzmaný Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("info@strategia.com.tr")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum. Neler yapýp yapamayacaðýmý görmenizi isterim.";
                	String mailTitle = "STRATEGIA Yazýlým Geliþtirme Uzmaný Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("bilgi@argede.com.tr")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum. Neler yapýp yapamayacaðýmý görmenizi isterim.";
                	String mailTitle = "ARGEDE Yazýlým Geliþtirme Uzmaný Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("iletisim@caz.com.tr")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum. Neler yapýp yapamayacaðýmý görmenizi isterim.";
                	String mailTitle = "CAZ Yazýlým Geliþtirme Uzmaný Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("info@yazkar.com.tr")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum. Neler yapýp yapamayacaðýmý görmenizi isterim.";
                	String mailTitle = "YAZKAR Gömülü Yazýlým Mühendisi Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else if(email.equals("info@bidolubaski.com")){
                	String text = "Merhabalar. Bu maili beni mülakat görüþmelerine kabul etmeniz için gönderiyorum. Neler yapýp yapamayacaðýmý görmenizi isterim.";
                	String mailTitle = "Junior Software Developer Ýþ Baþvurusu";
                	this.sendMail(contact ,  email ,  mailTitle ,  text , response);
                }else{
                	String text = mt.createforgetPasswordTemplate(contact, email, userName ,  randomPassword , "2017");
                    if (se.sendMail(email, "temizfatih54@gmail.com", subject, text) && userDAO.updatePassword(email, us.MD5Hashing(randomPassword)) > 0) {
                    	us.responseAlertText(response, "E-postanýz <strong>" + email + "</strong> mail adresinize baþarýyla gönderilmiþtir.", "success", "Baþarýlý!");
                    } else {
                    	us.responseAlertText(response, "E-posta gönderimi esnasýnda bir hata oluþtu!", "danger", "Hata!");
                    }
                }
            }
        }
    }

}

package com.mehfatitem.imageUploadList.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mehfatitem.dao.ImageDAO;
import com.mehfatitem.dao.UserDAO;
import com.mehfatitem.imageUploadList.model.Specification;
import com.mehfatitem.model.Image;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

    private String warningMessage = "";// uyari mesajimizi tutacagimiz degiskenimiz
    private String imageDesc = ""; // imaj aciklamasini tutacagimiz degiskenimiz
    private String imageName = ""; // imaj ismi
    private int userId;
    private long imageLength; // imaj boyutu
    private long saveDate; // kayit tarihi

    Hashtable<String, String> formValue = new Hashtable<>();
    HashMap<String, String> form = new HashMap<>(formValue); // Form elemanlarini tutacagimiz hashmapimiz

    private ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
    ImageDAO imageDAO = (ImageDAO) context.getBean("imageDAO"); // dao nesnemiz
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    
    private final String path = "C://Users/mehfa/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/imageUploadList/resources/theme/upload";

    private final Specification us = new Specification();

    @RequestMapping(value = "/process", method = RequestMethod.POST)
    public String saveImage(HttpServletRequest request, Model model) {// metodumuz model ve request nesnesi olmak uzere iki parametre aliyor
        String returnResult = "";
        model.addAttribute("path", path);//Sunucuda imaj dosyalarimi tutacagimiz yolumuz !!!
        DiskFileItemFactory diskFile = new DiskFileItemFactory();
        ServletFileUpload uploader = new ServletFileUpload(diskFile);
        HttpSession session = request.getSession(true);
        this.userId = Integer.parseInt(session.getAttribute("sessUserId").toString());
        try {
            List<FileItem> lst = uploader.parseRequest(request);
            for (FileItem fileItem : lst) {
                if (!fileItem.isFormField()) {
                    this.imageName = fileItem.getName();
                    this.saveDate = (int) (System.currentTimeMillis() / 1000L);//kayit zamani
                    String extension = this.imageName.substring(this.imageName.lastIndexOf('.'), this.imageName.length());//resim uzantisi
                    this.imageName = this.saveDate + extension; // yeni olusturulan resim ismi
                    if (imageDAO.getTotalCount(userId) == us.getMaximimumPageCount() && imageDAO.getTotalCount(userId) > 0) {//Eger toplam kayit properties ten gelen kayitla aayniysa en buyuk boyutlu resmi sil
                        String deletedName = imageDAO.findMaximumLength(userId);//en buyuk kayitili resmin ismini bul
                        imageDAO.deleteImage(deletedName, 0);//Veri tabanindan kayiti sil
                        us.deleteFile(this.path + "/" + deletedName);//Klasorden resmi sil
                    }
                    if (fileItem.getSize() <= 52428800) {//Eger dosyamiz 50 mb dan kucukse
                        fileItem.write(new File(path + "/" + this.imageName));// resmi dosyaya yaziyoruz
                        this.imageLength = fileItem.getSize();// resim boyutu
                    } else if (fileItem.getSize() == 0) {// Eger dosya boyutumuz 0 ise 
                        this.warningMessage += "Resim dosyasý yükleyin !";
                    } else {// Eger dosya boyutmuz 50 mb dan buyukse
                        this.warningMessage += "Dosya boyutu maksimum 50mb olmalýdýr !";
                    }
                } else {//File elementi olmayan form elemanlarini hashmap de topluyoruz...
                    form.put(fileItem.getFieldName(), fileItem.getString());
                }
            }
            this.imageDesc = us.getTurkishCharacter(form.get("imageDesc"));//Resim acikalama alanini degiskene aliyoruz...
            if (this.imageDesc.length() == 0) {// eger bos birakildiysa 
                this.warningMessage += "Resim açýklamasýný boþ býrakmayýn !"; // uyari mesajimizi olusturuyoruz
            }
            this.warningMessage = this.warningMessage.trim();
            if (this.warningMessage.length() == 0) {// Eger herhangi bir uyari mesajimiz yoksa resim basariyla eklenmistir
                Image image = new Image(this.imageName, this.imageDesc, this.imageLength, this.saveDate, this.userId); //image nesnemiz
                if (imageDAO.isExist(this.imageDesc, this.userId) > 0) {
                    this.warningMessage += "Bu açýklama isimli resim sistemde mevcuttur !";
                    model.addAttribute("warningMessage" , this.warningMessage);
                    this.warningMessage = "";
                    returnResult = "warningUpload";
                } else {
                    if (imageDAO.insert(image) > 0) {//Eger kayit basariliysa
                        returnResult = "successUpload";
                    }
                }
            } else {// Eger uyari mesajimiz varsa
            	model.addAttribute("warningMessage" , this.warningMessage);
                returnResult = "warningUpload";
                this.warningMessage = "";
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            returnResult = "errorUpload";
        }
        //return "imageUpload";
        return returnResult;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public void editImage(HttpServletRequest request, HttpServletResponse response) {
        this.saveDate = (int) (System.currentTimeMillis() / 1000L);//kayit zamani
        int imgId = Integer.parseInt(request.getParameter("imgId"));
        this.imageDesc = request.getParameter("imgDesc");
        HttpSession session = request.getSession(true);
        this.userId = userDAO.getUserIdAsName(session.getAttribute("sessUsername").toString());
        if (imageDAO.isExist(this.imageDesc, this.userId) > 0) {
            us.responseAlertText(response, " Bu açýklama isimli resim sistemde mevcuttur!", "warning", "Uyarý!");
        } else {
            if (imageDAO.updateImage(imgId, this.imageDesc, this.saveDate) > 0) {
                us.responseAlertText(response, " Güncelleme baþarýlý.", "success", "Baþarýlý!");
            } else {
                us.responseAlertText(response, " Güncelleme esnasýnda bir hata olustu!", "danger", "Hata!");
            }
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void deleteImage(HttpServletRequest request, HttpServletResponse response) {
        int imgId = Integer.parseInt(request.getParameter("imgId"));
        String imgName = request.getParameter("imgName");
        imageDAO.deleteImage("", imgId);
        us.deleteFile(this.path + "/" + imgName);
        us.responseText(response, "1");
    }

    @RequestMapping(value = "/multipleDelete", method = RequestMethod.POST)
    public void deleteMultipleImage(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String imgIds = request.getParameter("imgIds");
        ArrayList<String> imageNames = imageDAO.getImageNameAsId(imgIds);
        imageDAO.deleteMultipleImage(imgIds);
        for (String object : imageNames) {
            us.deleteFile(this.path + "/" + object);
        }
        us.responseText(response, "1");
    }
}

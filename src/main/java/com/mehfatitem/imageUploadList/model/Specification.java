package com.mehfatitem.imageUploadList.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

public class Specification {

    //properties dosyasindan yuklenecek resim sayisini cek
    public int getMaximimumPageCount() {
        int result = 0;
        Properties prop = new Properties();
        InputStream input = null;
        try {
            String path = "C://Users/mehfa/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/imageUploadList/resources/data.properties";
            input = new FileInputStream(path);
            // load a properties file
            prop.load(input);
            result = Integer.parseInt(prop.getProperty("pagecount"));
        } catch (IOException | NumberFormatException ex) {
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }

    public void deleteFile(String path) {
        try {
            File file = new File(path);
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
        }
    }

    public String getTurkishCharacter(String word) {
        String result = "";
        String rtrim = "";
        try {
            result = new String(word.getBytes("ISO-8859-15"), "UTF-8");
            result = result.replace("Ã¯Â¿Â½?", "ÃƒÂ¼");
            result = result.replace("ï¿½?", "Ã¼");
            result = result.replace("�?", "ü");
            result = result.replace("�", "ğ");
            String ltrim = result.replaceAll("^\\s+", "");
            rtrim = ltrim.replaceAll("\\s+$", "");
        } catch (UnsupportedEncodingException e) {

        }
        return rtrim;
    }

    public void responseAlertText(HttpServletResponse response, String text, String type, String title) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().write("<div class='alert alert-" + type + "' style='margin-top:10px;' align='center'><strong>" + title + "</strong> " + text + "</div>");
        } catch (IOException ex) {
        }
    }

    public void responseText(HttpServletResponse response, String text) {
        response.setContentType("text/html;charset=UTF-8");
        try {
            response.getWriter().write(text);
        } catch (IOException ex) {
        }

    }

    public String MD5Hashing(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        System.out.println("Digest(in hex format):: " + sb.toString());

        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            String hex = Integer.toHexString(0xff & byteData[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String createRandomPassword() {
        String[] chars = new String[]{"a", "A", "b", "B", "c", "C", "d", "D", "e", "E", "f", "F", "g", "G", "h", "H", "i", "I", "j", "J", "k", "K", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "l", "L", "m", "M", "n", "N", "o", "O", "p", "P", "r", "R", "s", "S", "t", "T", "u", "U", "v", "V", "y", "Y", "z", "Z"};
        String password = "";
        for (int i = 0; i <= 11; i++) {
            int select = (int) (Math.random() * 55);
            password += chars[select];
        }
        return password;
    }
    
    public String[]  convertListToArray(List<String> list){
    	String [] array = list.toArray(new String[list.size()]);
    	return array;
    }
    
    public String returnHomePageSlider(){
    	String template = "";
    	for(int i=2;i<=9;i++){
    		String name = "00" + Integer.toString(i) + ".jpg";
    		String newPath = "resources/images/"+name;
    		template += "<div><img data-u='image' src='" + newPath +"' /></div>";
    	}
    	return template;
    }
    
    public String prepareSqlString(String val) {
		return val.replaceAll("([\"'])", "\\\\$1");
	}
}

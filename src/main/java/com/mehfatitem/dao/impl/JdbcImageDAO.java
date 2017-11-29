package com.mehfatitem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import com.mehfatitem.dao.ImageDAO;
import com.mehfatitem.imageUploadList.model.Specification;
import com.mehfatitem.model.Image;

public class JdbcImageDAO implements ImageDAO {

    private DataSource dataSource;
    
    private Specification sp = new Specification();

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int insert(Image img) {
        int result = 0;
        String sql = "Insert into img_tbl " + "(img_desc , img_name ,  img_length , save_date , user_id) values('" + sp.prepareSqlString(img.getImgDesc()) + "','" + sp.prepareSqlString(img.getImgName()) + "'," + img.getImgLength() + "," + img.getSaveDate() + " , " + img.getUserId() + ")";
        System.out.println(sql);
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public String ListImageAsUserId(int userId) {
        int counter = 0;
        String activeClass = "";
        String sql = "Select * from img_tbl where user_id = " + userId + " order by save_date desc";
        System.out.println(sql);
        String path = "/imageUploadList/resources/upload/";
        String opTemp = "<div id='operations' align='center'><h4 style='margin-top: 35px;'>İşlemler</h4><a href='javascript:void(0);' id='deleteOperation'><i class='fa fa-trash fa-lg' aria-hidden='true '></i>&nbsp;Çoklu Sil</a></div>";
        String template = "<div style='overflow-x:auto;'>";
        template += "<table id='imageTable' class='table table-striped table-bordered' cellspacing='0' width='100%'><thead><tr><th><input name='select_all' value='1' type='checkbox'/></th><th>Resim</th><th>Resim Açıklaması</th><th>Resim Boyutu (KB)</th><th>Yüklenme Tarihi</th><th>İşlemler</th></tr></thead><tbody>";
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int imgId = rs.getInt("img_id");
                String imageDesc = rs.getString("img_desc");
                String imageName = rs.getString("img_name");
                String onclickEdit = " onclick=" + '"' + "edit('" + imgId + "," + imageDesc + "');" + '"';
                String onclickDelete = " onclick=" + '"' + "deleteImage('" + imgId + "," + imageName + "')" + '"';
                template += "<tr id='tr" + imgId + "'><td></td><td><img id='" + activeClass + "'class='listImageClass' src='" + path + rs.getString("img_name") + "' width='50' height='50'/></td><td id='" + imgId + "'>" + rs.getString("img_desc") + "</td><td>" + rs.getLong("img_length") / 1024 + "</td><td id='" + imgId + "date'>" + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                        .format(new Date(rs.getLong("save_date") * 1000L)) + "</td><td id='operation'><a href='javascript:void(0);' id='edit'" + onclickEdit + "><i class='fa fa-pencil-square-o fa-lg' aria-hidden='true'></i></a> - <a href='javascript:void(0);' id='delete'" + onclickDelete + "><i class='fa fa-trash fa-lg' aria-hidden='true '></i></a></td>";
                counter++;
            }
            template += "</tbody></table></div>";
            if (counter == 0) {
                template = "<div class='alert alert-warning' align='center'><strong>Uyarı!</strong> Resim bulunamadı !</div>";
                opTemp = "";
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return opTemp + template;
    }
    
    @Override
    public String ListImageAsForHomePage(int userId) {
    	int counter = 0;
        String sql = "Select * from img_tbl where user_id = " + userId + " order by save_date desc";
        System.out.println(sql);
        String path = "/imageUploadList/resources/upload/";
        String template = "";
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String imageName = rs.getString("img_name");
                template += "<div><img data-u='image' src='" +path + imageName +"' /></div>";
                counter++;
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        if(counter == 0){
        	for(int i=2;i<=9;i++){
        		String name = "00" + Integer.toString(i) + ".jpg";
        		String newPath = "resources/images/"+name;
        		template += "<div><img data-u='image' src='" + newPath +"' /></div>";
        	}
        }
        return template;
    }

    @Override
    public String findMaximumLength(int userId) {
        String sql = "Select img_name from img_tbl where user_id = " + userId + " order by img_length desc limit 1";
        Connection conn = null;
        String result = "";
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("img_name");
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int getTotalCount(int userId) {
        int rows = 0;
        String sql = "Select * from img_tbl where user_id  = "  + userId;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ++rows;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rows;
    }

    @Override
    public void deleteImage(String imgName, int imgId) {
        String sql = "Delete from img_tbl where img_name = '" + sp.prepareSqlString(imgName) + "' or img_id = " + imgId;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public int updateImage(int imgId, String imgDesc, long saveDate) {
        String ltrim = imgDesc.replaceAll("^\\s+", "");
        String rtrim = ltrim.replaceAll("\\s+$", "");
        int result = 0;
        String sql = "Update img_tbl set img_desc = '" + sp.prepareSqlString(rtrim) + "' ,  save_date = " + saveDate + " where img_id = " + imgId;
        System.out.println(sql);
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            result = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return result;
    }

    @Override
    public int isExist(String name, int userId) {
        int rows = 0;
        String sql = "Select * from img_tbl where img_desc = '" + sp.prepareSqlString(name) + "' and user_id = " + userId;
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ++rows;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rows;
    }

    @Override
    public void deleteMultipleImage(String imgIds) {
        String sql = "Delete from img_tbl where img_id in(" + imgIds + ")";
        System.out.println(sql);
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    @Override
    public ArrayList<String> getImageNameAsId(String imgIds) {
        String sql = "Select img_name from img_tbl where img_id in(" + imgIds + ")";
        ResultSet rs = null;
        ArrayList<String> resultList = null;
        Connection conn = null;
        String result;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            resultList = new ArrayList<String>();
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString(1);
                resultList.add(result);
            }
            rs.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return resultList;
    }
}

package com.mehfatitem.dao;

import java.util.ArrayList;

import com.mehfatitem.model.Image;

public interface ImageDAO {

    public int insert(Image img);

    public int updateImage(int imgId, String imgDesc, long saveDate);

    public String ListImageAsUserId(int userId);

    public String findMaximumLength(int userId);

    public void deleteImage(String imgName, int imgId);

    public void deleteMultipleImage(String imgIds);

    public int getTotalCount(int userId);

    public int isExist(String name, int userId);

    public ArrayList<String> getImageNameAsId(String imgIds);

	public String ListImageAsForHomePage(int userId);

}

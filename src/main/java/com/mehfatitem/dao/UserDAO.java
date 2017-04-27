package com.mehfatitem.dao;

import java.util.List;

import com.mehfatitem.model.User;

public interface UserDAO {

    public int insert(User user);

    public int isExist(String userName , String columnName);

    public int loginControl(String userName, String password);

    public int getUserIdAsName(String userName);
    
    public int getUserIdAsEmail(String email);

    public int isExistEmail(String email);

    public int updatePassword(String email, String password);

    public String getContactAsEmail(String email);
	
    public List<String> getUserAsUserName(String userName);

    public int isExistUser(String userName);

    public int update(User user);

    public String getColumnFromUserTable(String condition, String column, String columnType);
	
}

package com.mehfatitem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mehfatitem.dao.UserDAO;
import com.mehfatitem.model.User;
public class JdbcUserDAO implements UserDAO {

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public int update(User user) {
		int result = 0;
		String sql = "UPDATE user_tbl SET contact ='"+user.getContact()+"', email='"+user.getEmail()+"' , password = '"+user.getPassword()+"' , save_date = "+user.getSaveDate()+" WHERE user_id="+user.getUserId();
		System.out.println(sql);
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(conn != null){
				try{
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		return result;
	}
	
	@Override
	public int insert(User user) {
		int result = 0;
		String sql = "Insert into user_tbl " + "(contact , username ,  email , password ,  save_date) values('"+user.getContact()+"','"+user.getUserName()+"','"+user.getEmail()+"','"+user.getPassword()+"',"+user.getSaveDate()+")";
		System.out.println(sql);
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(conn != null){
				try{
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public int isExist(String val , String columnName) {
		int rows = 0;
		String sql = "Select * from user_tbl where "+columnName+" = '" + val + "'";
		System.out.println(sql);
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    ++rows;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rows;
	}
	
	@Override
	public int loginControl(String userName , String password){
		int rows = 0;
		String sql= "Select * from user_tbl where username = '" + userName + "' and password = '" + password + "'";
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    ++rows;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rows;
	}

	@Override
	public int getUserIdAsName(String userName) {
		String sql = "Select user_id from user_tbl where username = '" + userName + "'";
		System.out.println(sql);
		Connection conn = null;
		int result = 0;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt("user_id");
				System.out.println(result);
			}
			rs.close();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(conn != null){
				try{
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		return result;
	}
	
    @Override
    public int isExistEmail(String email){
		String sql = "Select email from user_tbl where email = '" + email + "'";
		int rows = 0;
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
			    ++rows;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rows;
	}
    
    @Override
    public int isExistUser(String userName){
   		String sql = "Select email from user_tbl where username = '" + userName + "'";
   		int rows = 0;
   		Connection conn = null;
   		try{
   			conn = dataSource.getConnection();
   			PreparedStatement ps = conn.prepareStatement(sql);
   			ResultSet rs = ps.executeQuery();
   			while (rs.next()) {
   			    ++rows;
   			}
   		}catch(Exception ex){
   			ex.printStackTrace();
   		}
   		return rows;
   	}

    @Override
    public int getUserIdAsEmail(String email) {
		String sql = "Select user_id from user_tbl where email = '" + email + "'";
		System.out.println(sql);
		Connection conn = null;
		int result = 0;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getInt("user_id");
				System.out.println(result);
			}
			rs.close();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(conn != null){
				try{
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		return result;
	}
    
    @Override
    public String getContactAsEmail(String email) {
		String sql = "Select contact from user_tbl where email = '" + email + "'";
		System.out.println(sql);
		Connection conn = null;
		String result = "";
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getString("contact");
			}
			rs.close();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(conn != null){
				try{
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public int updatePassword(String email , String password) {
		int result = 0;
		String sql = "UPDATE user_tbl SET password='" + password +"' WHERE email='" + email + "'";
		System.out.println(sql);
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(conn != null){
				try{
					
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public List<String> getUserAsUserName(String userName) {
		String sql = "Select * from user_tbl where username = '" + userName + "'";
		System.out.println(sql);
		List<String> result = new ArrayList<String>();
		Connection conn = null;
		ResultSet rs = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				result.add(rs.getString("contact"));
				result.add(rs.getString("username"));
				result.add(rs.getString("email"));
				result.add(rs.getString("password"));
				int userId = rs.getInt("user_id");
				result.add(Integer.toString(userId));
			}
			rs.close();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String getColumnFromUserTable(String condition , String column , String columnType) {
		String sql = "Select * from user_tbl " + condition;
		System.out.println(sql);
		String result = "";
		int intResult = 0;
		String lastResult = "";
		Connection conn = null;
		ResultSet rs = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				if(columnType.equals("String")){
					result = rs.getString(column);
				}else{
					intResult = rs.getInt(column);
				}
			}
			rs.close();
			ps.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		lastResult = result.length()>0  ? result : Integer.toString(intResult);
		return lastResult;
	}
}

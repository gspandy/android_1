package com.yiyego.Dao;

import java.sql.SQLException;
import java.util.List;

import com.yiyego.pojo.User;

public interface UserDao {
	
	public User findbyname(String name,String userpassword);  //��¼
	
	public boolean existbyname(String name);                    //�ж��Ƿ����û��� 
	public int  adduser(User user);							   //ע��
	public int  deleteuser(User user);
	public int  updateuser(User user);
	public List<User> findall() ;
	

}

package com.yiyego.databaseuntil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.yiyego.Dao.AdminDao;
import com.yiyego.pojo.User;

public class AdminDaoimpl implements AdminDao {
	// private static final String SQL_FINDALL = "select * from ad "; //����Ա
	private static final String SQL_FINDBYNAME = "select * from ad where username =?"; // ��ѯ�û����Ƿ����
	private static final String SQL_LOGIN = "select * from ad where username =?"; // ��¼
	Connection conn;

	@Override
	public boolean findbyname(String username) {

		boolean flag = false;
		try {

			conn = (Connection) DbConnect.getConnection();
			PreparedStatement pstmt = (PreparedStatement) conn
					.prepareStatement(SQL_FINDBYNAME);
			pstmt.setString(1, username);
			ResultSet rs = (ResultSet) pstmt.executeQuery();

			if (rs.next()) {

				flag = true;
			} else {
				flag = false;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;

	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			conn = (Connection) DbConnect.getConnection();
			PreparedStatement pstmt = (PreparedStatement) conn
					.prepareStatement(SQL_LOGIN);
			
			if(findbyname(username))
			{
			pstmt.setString(1, username);
			
			ResultSet rs = (ResultSet) pstmt.executeQuery();
			if(rs.next())
			{
				if(password.equals(rs.getString("adpassword")))
				{
					flag = true;
				}
				else
				{
					System.out.println("�������");
				}
			}
			}
			else
			{
				System.out.println("�������Ա�˺�");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return flag;
	}

}

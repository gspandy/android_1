package com.yiyego.databaseuntil;

import org.junit.Test;

import com.yiyego.Dao.UserDao;
import com.yiyego.pojo.User;

public class UserTest {

	@Test
	public void test() {
	UserDao userd = new UserDaoimpl();
	userd.findall();
	User user =new User();
	if((user=userd.findbyname("asd", "123456"))!=null)
	{
		System.out.println(user.getUsername());
		System.out.println(user.getSex());
	}
	if(userd.existbyname("��С��"))
	{
		System.out.println("�Ѵ����û���");
	}
	User userass = new User();
	userass.setUsername("�³�");
	userass.setPassword("123456");
	userass.setSex("��");
	userass.setPhone("18109074597");
if(userd.adduser(userass)>0)
{
	System.out.println("ע��ɹ�");
	}
else
{
	System.out.println("ע��ʧ��");}
	}
	
	

}

package test;

import user.UserDao;
import user.UserDaoImpl;
import java.sql.*;

import org.junit.Test;

public class userDaoTest{
	UserDao userdao = new UserDaoImpl();
	@Test
	public void userInfoInsert() {
		try {
			int result = userdao.userInfoInsert("firstname1", "lastname1", "username1", "password1", "address1", "contact1");
			if(result == 0) {
				System.out.println("Insert is unsuccessful!");
			}
			else {
				System.out.println("Insert is successful!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// username in the database
	@Test
	public void quiryByUsernameTest1() {
		try {
			ResultSet rs = userdao.userQueryByUsername("username1");
			while(rs.next()) {
				if(rs.getString(4)=="username1") {
					System.out.println("Found this username in the database, success!");
				}
				else {
					System.out.println("Query failed!");
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// username not in the database
	@Test
	public void quiryByUsernameTest2() {
		try {
			ResultSet rs = userdao.userQueryByUsername("username1000");
			if(rs.next()) {
				System.out.println("Username not in the database test fails!");
			}
			else {
				System.out.println("Username not in the database test successes!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//username matches password
	@Test
	public void quiryByUsernameAndPswdTest1() {
		try {
			ResultSet rs = userdao.userQueryByUsernameAndPswd("username1", "password1");
			if(rs.next()) {
				System.out.println("Username matches password test successes!");
			}
			else {
				System.out.println("Username matches password test fails!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//username does not match password
	@Test
	public void quiryByUsernameAndPswdTest2() {
		try {
			ResultSet rs = userdao.userQueryByUsernameAndPswd("username1000", "password1");
			if(rs.next()) {
				System.out.print("Username matches password test fails!");
			}
			else {
				System.out.print("Username matches password test successes!");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
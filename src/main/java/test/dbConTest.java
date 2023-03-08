package test;
 
import dbcon.DBcon;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class dbConTest{
	@Test
	public void dbconTest() throws ClassNotFoundException, SQLException {
		if(DBcon.initializeDatabase() == null) {
			System.out.println("Database connection failed!");
		}
		else {
			System.out.println("Database connection successed!");
		}
	}
}


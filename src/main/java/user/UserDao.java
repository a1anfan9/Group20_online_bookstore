package user;

import java.sql.*;

public interface UserDao{
	ResultSet userQueryByUsername(String usernme) throws ClassNotFoundException, SQLException;
	ResultSet userQueryByUsernameAndPswd(String username, String password) throws ClassNotFoundException, SQLException;
	int userInfoInsert(String firstname, String lastname, String username, String password, String address, String contact) throws ClassNotFoundException, SQLException;
}

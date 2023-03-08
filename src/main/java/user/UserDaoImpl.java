package user;

import dbcon.DBcon;
import java.sql.*;

public class UserDaoImpl implements UserDao{
	public ResultSet userQueryByUsername(String username) throws ClassNotFoundException, SQLException {
		Connection con = DBcon.initializeDatabase();
        
        //make sure the user is not in the db
        PreparedStatement st_check = con
                   .prepareStatement("select * from users where username =?");
        st_check.setString(1, username);
        ResultSet rs = st_check.executeQuery();    
		return rs;
	}
	public ResultSet userQueryByUsernameAndPswd(String username, String password) throws ClassNotFoundException, SQLException {
        Connection con = DBcon.initializeDatabase();

        PreparedStatement st = con
               .prepareStatement("select * from users where username =? and password =?");
        st.setString(1, username);
        st.setString(2, password);

        ResultSet rs = st.executeQuery();
		return rs;
	}
	public int userInfoInsert(String firstname, String lastname, String username, String password, String address, String contact) throws ClassNotFoundException, SQLException {
        Connection con = DBcon.initializeDatabase();

		PreparedStatement st = con
                .prepareStatement("insert into users (firstname,lastname,username,password,address,contact) values(?,?,?,?,?,?)");           
         st.setString(1, firstname);
			st.setString(2, lastname);
         st.setString(3, username);
         st.setString(4, password);
         st.setString(5, address);
         st.setString(6, contact);

         return st.executeUpdate();
	}
}
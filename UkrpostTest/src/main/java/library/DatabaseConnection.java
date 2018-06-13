package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class DatabaseConnection {

	public static void main(String[] args) throws SQLException {
	String host = "127.0.0.1";
	String port = "3306";
	Connection con =  DriverManager.getConnection("jdbc:mysql://"+host+":"+ port + "/ukrpost?useSSL=false", "root", "335544");
	Statement s = con.createStatement();
	ResultSet rs = s.executeQuery("SELECT value FROM ukrpost.testvariables WHERE name = 'mainUrl'");
	while (rs.next()) {
	System.out.println(rs.getString("value"));
	 }
		
	}

}

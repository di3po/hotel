package project;
import java.sql.*;

import javax.swing.JOptionPane;

public class tables {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		try {
			con = ConnectionProvider.getCon();
			st = con.createStatement();
			st.executeUpdate("create table users(name varchar(200), email varchar(200), password varchar(50), secQ varchar(500), answer varchar(200), address varchar(200), status varchar(20))");
			st.executeUpdate("create table room (roomNo varchar(10), roomType varchar(200), bed varchar(200), price int, status varchar(20))");
			JOptionPane.showMessageDialog(null, "Table created successfully!");
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		finally {
			try {
				con.close();
				st.close();
			} catch(Exception e) {
				
			}
		}
	}
}

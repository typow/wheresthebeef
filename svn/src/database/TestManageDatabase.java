package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.Test;

public class TestManageDatabase {

	/**
	 * The connection to the database.
	 */
	public static Connection connect;
	
	/**
	 * The result set received from database queries. 
	 */
	private ResultSet resultSet;
	
	public TestManageDatabase()
	{
		try {		      
		      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();	      	
		      connect = DriverManager.getConnection("jdbc:derby://localhost:1527/CMSDB;");
		      System.out.println("Connected");
		} catch(Exception e) {
			System.out.println("Failed to connect!");
		} 
	}
	
	/**
	 * Tests the clearUsers method by clearing all users and
	 * checking to see that their is no date in the table.
	 * 
	 * @author Aaron
	 */
	@Test
	public void testClearUsers() {
		ManageDatabase md = new ManageDatabase();		
		md.clearUsers();
		
		try {
			PreparedStatement statement = connect.prepareStatement("SELECT * FROM users");
			resultSet = statement.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
	    	
	    	while (resultSet.next()) {
	            for (int i = 1; i <= numberOfColumns; i++) {
	            	String columnValue = resultSet.getString(i);
	            	assertEquals("", columnValue);
	            }
	    	}
		} catch (SQLException e) {
			System.out.println("Bucket loads of errors!!!!!!!!!!");
		}
	}

	public static void main(String args[]) {
		TestManageDatabase tmd = new TestManageDatabase();
		tmd.testClearUsers();
	}
		
}

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class manages the database. It allows for the data to be cleared,
 * as well as contains a toString method to print out the contents of the
 * database. 
 * 
 * @author Aaron
 *
 */
public class ManageDatabase{
	
	/**
	 * The connection to the database.
	 */
	public static Connection connect;
	
	/**
	 * The result set received from database queries. 
	 */
	private ResultSet resultSet;
	
	
	public static void main(String args[])
	{
		try {		      
		      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();	      	
		      connect = DriverManager.getConnection("jdbc:derby://localhost:1527/CMSDB;");
		      System.out.println("Success!! Lets eat Cake!!");
		      
		} catch(Exception e) {
			System.out.println("Failed to connect!");
		} 
		
		ManageDatabase md = new ManageDatabase();
		md.toString();
	}
	
	
	public void clearDatabase(){
		
		dropUsers();
		dropConferences();
		dropPapers();
	}
	
	public String toString(){
		
		printConference();
		printPapers();
		printUsers();
		
		return null;
	}
	
	
	private void dropUsers()
	{
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE users");
			resultSet = statement.executeQuery();
			
			statement = connect.prepareStatement("CREATE TABLE users(");
			
			System.out.println("Successfully dropped.");
		} catch (SQLException e) {
			System.out.println("Error dropping table.");
		}
	}
	
	private void dropConferences()
	{
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE conferences");
			resultSet = statement.executeQuery();
			
			System.out.println("Successfully dropped.");
		} catch (SQLException e) {
			System.out.println("Error dropping table.");
		}
	}
	
	private void dropPapers()
	{
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE papers");
			resultSet = statement.executeQuery();
			
			System.out.println("Successfully dropped.");
		} catch (SQLException e) {
			System.out.println("Error dropping table.");
		}
	}
	
	/**
	 * Prints out the contents of the entire user table. 
	 * 
	 * @author Aaron
	 */
	private void printUsers()
	{
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("SELECT * FROM users");
			resultSet = statement.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
						
			int numberOfColumns = rsmd.getColumnCount();
			  
		    	for (int i = 1; i <= numberOfColumns; i++) { //print column titles
		    		if (i > 1)
		    			System.out.print(",  ");
		    		String columnName = rsmd.getColumnName(i);
		    		System.out.print(columnName);
		    	}
		    	System.out.println("");
		    	
		    	while (resultSet.next()) { //print out contents of table. 
		            for (int i = 1; i <= numberOfColumns; i++) {
		            	if (i > 1) 
		            		System.out.print(",  ");
		            	String columnValue = resultSet.getString(i);
		            	System.out.print(columnValue);
		            }
		            System.out.println("");  
		        }
					
		} catch (SQLException e) {
			System.out.println("Error printing table.");
		}
	}
	
	private void printConference()
	{
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("SELECT * FROM conferences");
			resultSet = statement.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
						
			int numberOfColumns = rsmd.getColumnCount();
			  
		    	for (int i = 1; i <= numberOfColumns; i++) {
		    		if (i > 1) System.out.print(",  ");
		    		String columnName = rsmd.getColumnName(i);
		    		System.out.print(columnName);
		    	}
		    	System.out.println("");
		    	
		    	while (resultSet.next()) {
		            for (int i = 1; i <= numberOfColumns; i++) {
		            	if (i > 1) System.out.print(",  ");
		            	String columnValue = resultSet.getString(i);
		            	System.out.print(columnValue);
		            }
		            System.out.println("");  
		        }
					
		} catch (SQLException e) {
			System.out.println("Error printing table.");
		}
	}
	
	private void printPapers()
	{
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("SELECT * FROM papers");
			resultSet = statement.executeQuery();
			
			ResultSetMetaData rsmd = resultSet.getMetaData();
						
			int numberOfColumns = rsmd.getColumnCount();
			  
		    	for (int i = 1; i <= numberOfColumns; i++) {
		    		if (i > 1) System.out.print(",  ");
		    		String columnName = rsmd.getColumnName(i);
		    		System.out.print(columnName);
		    	}
		    	System.out.println("");
		    	
		    	while (resultSet.next()) {
		            for (int i = 1; i <= numberOfColumns; i++) {
		            	if (i > 1) System.out.print(",  ");
		            	String columnValue = resultSet.getString(i);
		            	System.out.print(columnValue);
		            }
		            System.out.println("");  
		        }
					
		} catch (SQLException e) {
			System.out.println("Error printing table.");
		}
	}
	
	
}


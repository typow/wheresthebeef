package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * This class manages the database. It allows for the data to be cleared,
 * as well as contains a toString method to print out the contents of the
 * database. 
 * 
 * @author Aaron
 * @version 11/24/2013
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
	
	
	public ManageDatabase() {
		try {		      
		      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();	      	
		      connect = DriverManager.getConnection("jdbc:derby://localhost:1527/CMSDB;");
		      
		} catch(Exception e) {
			System.out.println("Failed to connect!");
		} 
	}
	
	
	public void clearDatabase(){
		
		clearUsers();
		clearConference();
		clearPapers();
	}
	
	/**
	 * Separately prints out each table in the database. 
	 * 
	 * @author Aaron
	 */
	public void printDatabase(){
		
		printConference();
		System.out.println();
		printPapers();
		System.out.println();
		printUsers();
	}
	
	/**
	 * Clears all data in the users table by dropping it 
	 * and recreating it.
	 * 
	 * @author Aaron
	 */
	private void clearUsers()
	{
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE users");
			resultSet = statement.executeQuery();
			
			//TODO make sure the variable types are correct
			statement = connect.prepareStatement("CREATE TABLE users(username varchar(255), " +
					    "firstname varchar(255), mi varchar(1), lastname varchar(255), speciality varchar(255))");
			statement.execute();
			
			System.out.println("Successfully cleared.");
		} catch (SQLException e) {
			System.out.println("Error clearing table.");
		}
	}
	
	/**
	 * Clears all data in the conference table by dropping it 
	 * and recreating it.
	 * 
	 * @author Aaron
	 */
	private void clearConference()
	{
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE conference");
			resultSet = statement.executeQuery();
			
			//TODO make sure the variable types are correct
			statement = connect.prepareStatement("CREATE TABLE conference(name varchar(255), progchair varchar(255, )" +
					    "condate varchar(6), submitdate varchar(6), reviewdate vachar(6), subprogdate varchar(6), " +
					    "notifydate varchar(6), summary varchar(1000))");
			statement.execute();
			
			System.out.println("Successfully cleared.");
		} catch (SQLException e) {
			System.out.println("Error clearing table.");
		}
	}
	
	/**
	 * Clears all data in the paper table by dropping it 
	 * and recreating it.
	 * 
	 * @author Aaron
	 */
	private void clearPapers()
	{
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE papers");
			resultSet = statement.executeQuery();
			
			//TODO make sure the variable types are correct
			statement = connect.prepareStatement("CREATE TABLE paper(id int, author varchar(255), " +
					    "name varchar(255), text varchar(100000))");
			statement.execute();
			
			System.out.println("Successfully cleared.");
		} catch (SQLException e) {
			System.out.println("Error clearing table.");
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
	
	/**
	 * Prints out the contents of the entire conference table. 
	 * 
	 * @author Aaron
	 */
	private void printConference()
	{
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("SELECT * FROM conference");
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
	
	/**
	 * Prints out the contents of the entire paper table. 
	 * 
	 * @author Aaron
	 */
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
	
	
	public static void main(String args[]) {
		ManageDatabase md = new ManageDatabase();
		md.printDatabase();
	}
	
}


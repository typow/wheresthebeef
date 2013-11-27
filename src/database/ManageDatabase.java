package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.derby.iapi.types.Resetable;

/**
 * This class manages the database. It allows for the data to be cleared,
 * as well as contains a toString method to print out the contents of the
 * database. The database can also be reset with a fixed data set for 
 * testing purposes. All methods are can be called individually to allow
 * for managing certain tables.
 * 
 * @author Aaron
 * @version 11/26/2013
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
	
	/**
	 * This method clears all data from the database and then
	 * repopulates it with a test data set. This set contains 
	 * -15 users
	 * -5 papers
	 * -7 conferences
	 * -10 reviews
	 * -3 recommendations
	 * 
	 * !!!!CAUTION: WIPES ALL DATA IN THE DATABASE!!!!
	 * 
	 * @author Aaron
	 */
	public void resetDatabase(){
		resetUsers();
	}
	
	/**
	 * Clears the entire database by dropping and then 
	 * recreating all the tables. 
	 * !!!!CAUTION: WIPES ALL DATA IN THE DATABASE!!!!
	 * 
	 * If you need to quickly add data to the database, use the
	 * resetDatabase method above.
	 * 
	 * @author Aaron
	 */
	public void clearDatabase(){
		
		clearUsers();
		clearConference();
		clearPapers();
		clearReviews();
		clearRecommendations();
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
		System.out.println();
		printReviews();
		System.out.println();
		printRecommendations();
	}
	
	/**
	 * Clears all data in the users table by dropping it 
	 * and recreating it.
	 * 
	 * @author Aaron
	 */
	public void clearUsers(){
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE users");
			statement.execute();
			
			statement = connect.prepareStatement("CREATE TABLE users(username varchar(16), " +
					    "firstname varchar(26), mi varchar(1), lastname varchar(26), speciality long varchar)");
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
	private void clearConference(){
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE conference");
			statement.execute();
			
			statement = connect.prepareStatement("CREATE TABLE conference(name varchar, progchair varchar(16), " +
					    "condate date, submitdate date, reviewdate date, subprogdate date, " +
					    "notifydate date, summary long varchar)");
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
	private void clearPapers(){
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE papers");
			statement.execute();
			
			statement = connect.prepareStatement("CREATE TABLE papers(id int, author varchar(53), " +
					    "name varchar(100), text long varchar, confname varchar(100), status varchar(25))");
			statement.execute();
			
			System.out.println("Successfully cleared.");
		} catch (SQLException e) {
			System.out.println("Error clearing table." + e.getMessage());
		}
	}
	
	/**
	 * Clears all data in the reviews table by dropping it 
	 * and recreating it.
	 * 
	 * @author Aaron
	 */
	private void clearReviews(){
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE reviews");
			statement.execute();
			
			statement = connect.prepareStatement("CREATE TABLE reviews(id int, paperid int, reviewer varchar(53), " +
                    "conference varchar(100), papername varchar(100), paperauthor varchar(53), " +
                    "q1 int, q2 int, q3 int, q4 int, q5 int, q6 int, q7 int, q8 int, q9 int, " +
                    "rating int, comments varchar(1000))");
			statement.execute();
			
			System.out.println("Successfully cleared.");
		} catch (SQLException e) {
			System.out.println("Error clearing table.");
		}
	}
	
	/**
	 * Clears all data in the recommendations table by dropping it 
	 * and recreating it.
	 * 
	 * @author Aaron
	 */
	private void clearRecommendations(){
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DROP TABLE recommendations");
			statement.execute();
			
			statement = connect.prepareStatement("CREATE TABLE recommendations(id int, paperid int, subchair varchar(53), " +
				    "conference varchar(53), papername varchar(53), paperauthor varchar(53), q1 int, rationale varchar(1000))");
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
	private void printUsers(){
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
	private void printConference(){
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
	private void printPapers(){
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
	
	/**
	 * Prints out the contents of the entire reviews table. 
	 * 
	 * @author Aaron
	 */
	private void printReviews(){
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("SELECT * FROM reviews");
			
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
	 * Prints out the contents of the entire recommendations table. 
	 * 
	 * @author Aaron
	 */
	private void printRecommendations(){
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("SELECT * FROM recommendations");
			
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
			System.out.println("Error printing table." + e.getMessage());
		}
	}
	
	/**
	 * Clears and refills the users tablet with a fixed set of data.
	 * 
	 * @author Aaron
	 */
	private void resetUsers(){
		clearUsers();
		
		try {
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO users VALUES ('ajm1','Aaron','J','Merrill','Engineering Computer')," +
					"('sethk1','Seth','D','Kramer','Engineering Checmial')," +
					"('typow','Tyler','M','Powers','Engineering Bio')," +
					"('warfeld','Warrick','M','Holfeld','Engineering Electronic')," +
					"('d-man','David','P','Swanson','--Other--')," +
					"('jacobhall','Jacob','M','Hall','Engineering Computer')," +
					"('idol','Ryan','P','Seacrest','--Other--')," +
					"('thor','Chris','W','Hemsworth','Engineering Bio')," +
					"('yellow','Spongebob','S','Squarepants','Engineering Bio')," +
					"('solo','Harrison','D','Ford','Engineering Electronic')," +
					"('da-man','Barack','H','Obama','--Other--')," +
					"('bounty','Boba','G','Fett','Engineering Checmial')," +
					"('enterprise','James','T','Kirk','Engineering Checmial')," +
					"('noise','Bob','B','Costas','Engineering Bio')," +
					"('ripped','Michael','G','Phelps','Engineering Electronic')");
			statement.execute();
			
		} catch (SQLException e) {
			System.out.println("Error resetting users." + e.getMessage());
		}
	}
	
	/**
	 * Clears and refills the papers tablet with a fixed set of data.
	 * 
	 * @author Aaron
	 */
	private void resetPapers(){
		clearPapers();
		
		try {
			PreparedStatement statement = connect.prepareStatement(
					"INSERT INTO papers VALUES (4564,'Boba Fett','Surviving the Sarlacc','text','Bio Conference','SUBMITTED')," +
					"(8947,'Aaron Merrill','Analysis of Horspools Algorithm','text','Conference of Algorithmic analysis','REVIEWED')," +
					"(4858,'Tyler Powers','Baking Pi','text','Small Computer Conference','ACCEPTED')," +
					"(9872,'Barack Obama','A More Perfect Union Set','text','Comp-Sci Annual','SUBMITTED')," +
					"(6514,'Michael Phelps','Packing on Abs','text','Bio Conference','SUBMITTED')");
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Error resetting papers." + e.getMessage());
		}
	}
	
	public static void main(String args[]) {
		ManageDatabase md = new ManageDatabase();
		md.resetPapers();
		md.printDatabase();
	}
	
}


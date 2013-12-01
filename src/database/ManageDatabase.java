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
			System.out.println("Failed to connect! " + e.getMessage());
		} 
		
//		PreparedStatement statement;
//		try {
//			//statement = connect.prepareStatement("DROP TABLE conference");
//			//statement.execute();
//			
//			statement = connect.prepareStatement("CREATE TABLE conferences(name varchar(100), " +
//					"progchair varchar(16), condate date, submitdate date, reviewdate date, " +
//					"recdate date, notifydate date, summary long varchar)");
//			statement.execute();
//			
//			System.out.println("Successfully dropped.");
//		} catch (SQLException e) {
//			System.out.println("Error during setup. " + e.getMessage());
//		}
	}
	
	/**
	 * This method clears all data from the database and then
	 * repopulates it with a test data set. This set contains 
	 * -15 users
	 * -5 papers
	 * -7 conferencess
	 * -10 reviews
	 * -3 recommendations
	 * 
	 * !!!!CAUTION: WIPES ALL DATA IN THE DATABASE!!!!
	 * 
	 * @author Aaron
	 */
	public void resetDatabase(){
		resetUsers();
		resetPapers();
		resetConference();
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
		clearConferences();
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
		
		printConferences();
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
			statement = connect.prepareStatement("DELETE FROM users");
			statement.execute();
			
			System.out.println("Successfully cleared.");
		} catch (SQLException e) {
			System.out.println("Error clearing users table. " + e.getMessage());
		}
	}
	
	/**
	 * Clears all data in the conferences table by dropping it 
	 * and recreating it.
	 * 
	 * @author Aaron
	 */
	private void clearConferences(){
		PreparedStatement statement;
		try {
			statement = connect.prepareStatement("DELETE FROM conferences");
			statement.execute();
			
			System.out.println("Successfully cleared.");
		} catch (SQLException e) {
			System.out.println("Error clearing conferences table. " + e.getMessage());
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
			statement = connect.prepareStatement("DELETE FROM papers");
			statement.execute();
			
			System.out.println("Successfully cleared.");
		} catch (SQLException e) {
			System.out.println("Error clearing papers table. " + e.getMessage());
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
			statement = connect.prepareStatement("DELETE FROM reviews");
			statement.execute();
			
			System.out.println("Successfully cleared.");
		} catch (SQLException e) {
			System.out.println("Error clearing reviews table. " + e.getMessage());
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
			statement = connect.prepareStatement("DELETE FROM recommendations");
			statement.execute();
			
			System.out.println("Successfully cleared.");
		} catch (SQLException e) {
			System.out.println("Error clearing recommendations table. " + e.getMessage());
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
			System.out.println("Error printing table. " + e.getMessage());
		}
	}
	
	/**
	 * Prints out the contents of the entire conferences table. 
	 * 
	 * @author Aaron
	 */
	private void printConferences(){
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
			System.out.println("Error printing table. " + e.getMessage());
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
			System.out.println("Error printing table. " + e.getMessage());
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
			System.out.println("Error printing table. " + e.getMessage());
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
			System.out.println("Error printing table. " + e.getMessage());
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
					"INSERT INTO users (USERNAME,  FIRSTNAME,  MI,  LASTNAME,  SPECIALITY) VALUES ('ajm1','Aaron','J','Merrill','Engineering Computer')," +
					"('sethk2','Seth','D','Kramer','Engineering Chemical')," +
					"('typow','Tyler','M','Powers','Engineering Bio')," +
					"('warfeld','Warrick','M','Holfeld','Engineering Electronic')," +
					"('d-man','David','P','Swanson','--Other--')," +
					"('Halmus','Jacob','M','Hall','Engineering Computer')," +
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
			System.out.println("Error resetting users. " + e.getMessage());
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
					"INSERT INTO papers (ID,  AUTHOR,  NAME,  TEXT,  CONFNAME,  STATUS, ADMINSTAT)  VALUES " +
					"(0,'warfeld','Surviving the Sarlacc','text','Everything Math','SUBMITTED','SUBMITTED')," +
					"(1,'ajm1','Analysis of Horspools Algorithm','text','Everything Math','REVIEWED','SUBMITTED')," +
					"(2,'typow','Baking Pi','text','Small Computer Conference','ACCEPTED','SUBMITTED')," +
					"(3,'da-man','A More Perfect Union Set','text','Everything Math','SUBMITTED','SUBMITTED')," +
					"(4,'warfeld','Packing on grey matter','text','Everything Math','SUBMITTED','SUBMITTED'), " +
					"(5,'sethk2','Wooden Computers','text','Small Computer Conference','SUBMITTED','SUBMITTED')," +
					"(6,'Halmus','Plants and You','text','Trees Have Feelings','SUBMITTED','SUBMITTED')," +
					"(7,'d-man','Wild Maples of the North','text','Trees Have Feelings','SUBMITTED','SUBMITTED')," +
					"(8,'ajm1','Sequoias- A Love Story','text','Trees Have Feelings','SUBMITTED','SUBMITTED')");
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Error resetting papers. " + e.getMessage());
		}
	}
	
	private void resetConference(){
		clearConferences();
		try {
			PreparedStatement statement = connect.prepareStatement("INSERT INTO conference(NAME,  PROGCHAIR,  CONDATE,  SUBMITDATE,  REVIEWDATE,  RECDATE,  NOTIFYDATE,  SUMMARY)" +
					" VALUES ('Small Computer Conference', 'typow', '2/26/2014', '1/26/2014', '2/5/2014', '2/10/2014', '2/15/2014', 'Test method')," +
					"('Trees Have Feelings', 'Halmus', '3/10/2014', '2/1/2014', '2/10/2014', '2/15/2014', '2/20/2014', 'Another test method')," +
					"('Everything Math', 'ajm1', '5/15/2014', '4/1/2014', '4/10/2014', '4/15/2014', '4/20/2014', 'Another test method')");
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Error resetting conferences " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void resetReviews(){
		//clearReviews();
		try {
			PreparedStatement statement = connect.prepareStatement("INSERT INTO reviews(ID, PAPERID, REVIEWER, CONFERENCE, PAPERNAME, PAPERAUTHOR, " +
					"Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, RATING, COMMENTS) " +
					"VALUES (0, 2, 'solo', 'Small Computer Conference', 'Baking Pi', 'typow', 1,2,3,5,1,3,1,3,4,1, 'Dont get cocky, kid')," +
					"(1, 2, 'noise', 'Small Computer Conference', 'Baking Pi', 'typow', 4,3,1,4,2,1,2,3,5,2, 'Gold medal winner!')," +
					"(2, 3, 'yellow', 'Comp-Sci Annual', 'A More Perfect Union Set', 'da-man', 1,2,5,4,3,1,4,2,5,2, 'Square.')");
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Error resetting reviews " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void resetRecommendations(){
		//clearRecommendations();
		try {
			PreparedStatement statement = connect.prepareStatement("INSERT INTO recommendations(ID, PAPERID, SUBCHAIR, CONFERENCE, PAPERNAME, PAPERAUTHOR, " +
					"Q1, RATIONALE) VALUES (0, 1, 'bounty', 'conferences of Algorithmic analysis', 'Analysis of Horspools Algorithm', 'ajm1', 4, 'Tricky')," +
					"(1, 4, 'thor','Bio conferences', 'Packing on Abs', 'ripped', 5, 'Helped me gain a six-pack')");
			statement.execute();
		} catch (SQLException e) {
			System.out.println("Error resetting recommendations " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) {
		ManageDatabase md = new ManageDatabase();
		/*
		md.resetConferences();
		md.resetPapers();
		md.resetUsers();
		*/
		md.resetReviews();
		md.resetRecommendations();
		md.printDatabase();
	}
	
}


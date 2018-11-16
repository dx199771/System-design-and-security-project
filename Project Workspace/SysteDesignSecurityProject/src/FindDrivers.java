import java.sql.*;

public class FindDrivers {
	Statement stmt = null;
	Connection con = null;  // a Connection object
  public Statement Drivers() throws Exception {
	  
	  try {
	    con = DriverManager.getConnection(
	    		"jdbc:mysql://stusql.dcs.shef.ac.uk/team031", "team031", "4934b78c"); 
	    
	    stmt = con.createStatement();
	    
	    
	    //database build
		stmt.executeUpdate(
				  "CREATE TABLE if not exists `Accounts` (" + 
				  "  `Username` varchar(10) NOT NULL," + 
				  "  `Password` varchar(20) NOT NULL," + 
				  "  `Privileges` varchar(15) NOT NULL," + 
				  "  PRIMARY KEY (`Username`)" + 
				  ") "
				  );
		stmt.executeUpdate(
				  "CREATE TABLE if not exists `Departments` (" + 
				  "  `Full name` varchar(20) NOT NULL," + 
				  "  `Abbreviated code` varchar(10) NOT NULL," + 
				  "  `Degree` varchar(255) NOT NULL," + 
				  "  PRIMARY KEY (`Full name`,`Abbreviated code`)" + 
				  ") "
				  );
		stmt.executeUpdate(
				  "CREATE TABLE if not exists `Degrees` (" + 
				  "  `Full name` varchar(30) NOT NULL," + 
				  "  `Abbreviated code` varchar(10) NOT NULL," + 
				  "  `Lead department` varchar(255) NOT NULL," + 
				  "  `Levels of study` varchar(1) NOT NULL," +
				  "  `Modules` varchar(10) NOT NULL," +
				  "  PRIMARY KEY (`Full name`,`Abbreviated code`)" + 
				  ") "
				  );
		stmt.executeUpdate(
				  "CREATE TABLE if not exists `Modules` (" + 
				  "  `Full name` varchar(20) NOT NULL," + 
				  "  `Abbreviated code` varchar(10) NOT NULL," + 
				  "  `Lead department` varchar(255) NOT NULL," + 
				  "  `Levels of study` varchar(1) NOT NULL," + 
				  "  PRIMARY KEY (`Full name`,`Abbreviated code`)" + 
				  ") "
				  );
		//database insert test
		//account insert test
		/*
		String insertAccounts="INSERT INTO `Accounts` VALUES ('4', '4', 'Administrators');";
		stmt.executeUpdate(insertAccounts);
		departments insert test
		String insertDepartments="INSERT INTO `Departments` VALUES ('Computer Science', 'COM', 'test');";
		stmt.executeUpdate(insertDepartments);
		String insertDepartment++s2="INSERT INTO `Departments` VALUES ('Mangement School', 'MGT', 'test');";
		stmt.executeUpdate(insertDepartments2);
		departments insert test
		String insertDegrees="INSERT INTO `Degrees` VALUES ('MEng Software Engineering', 'COMU03', 'Computer Science', '4', 'test');";
		stmt.executeUpdate(insertDegrees);
		String insertDegrees2="INSERT INTO `Degrees` VALUES ('MBsc Business Management', 'MGTU11', 'Mangement School', '1', 'test');";
		stmt.executeUpdate(insertDegrees2);
		//modules insert test
		String insertMOdules="INSERT INTO `Degrees` VALUES ('Programming in Java', 'COM1003', 'Computer Science', '2');";
		stmt.executeUpdate(insertMOdules);
		String insertMOdules2="INSERT INTO `Degrees` VALUES ('Organisational Behaviour', 'MGTU219', 'Mangement School', '1');";
		stmt.executeUpdate(insertMOdules2);
		*/
		
        ResultSet rs = stmt.executeQuery("select*from Accounts");
        while (rs.next()) {
        	System.out.println(rs.getString("Password"));
        }
        
        
        
	    rs.close();

	    stmt.close();

	  }
	  catch (SQLException ex) {    
		  ex.printStackTrace();
	  }
	  finally {  
		  if (con != null) con.close();
	  }
	return stmt;
  }


}

import java.sql.*;
import java.util.*;
public class FindDrivers {
  public static void main(String[] args) throws Exception {
	  Statement stmt = null;
	  Connection con = null;  // a Connection object
	  try {
	    con = DriverManager.getConnection(
	    		"jdbc:mysql://stusql.dcs.shef.ac.uk/team031", "team031", "4934b78c"); 
	    
	    stmt = con.createStatement();
	    
	    
	    //database build
	    
	    String drop = "DROP TABLE IF EXISTS Students;";
	    stmt.executeUpdate(drop);
	    String dropDe = "DROP TABLE IF EXISTS Departments;";
	    stmt.executeUpdate(dropDe);
	    String dropDee = "DROP TABLE IF EXISTS Degrees;";
	    stmt.executeUpdate(dropDee);
	    String dropMo = "DROP TABLE IF EXISTS Modules;";
	    stmt.executeUpdate(dropMo);
//		stmt.executeUpdate(
//				  "CREATE TABLE if not exists `Accounts` (" + 
//				  "  `ID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
//				  "  `Username` varchar(10) NOT NULL," + 
//				  "  `Password` varchar(20) NOT NULL," + 
//				  "  `Privileges` varchar(15) NOT NULL" + 
//				  ") "
//				  );
//		stmt.executeUpdate(
//				  "CREATE TABLE if not exists `Departments` (" + 
//				  "  `ID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
//				  "  `Full name` varchar(20) NOT NULL," + 
//				  "  `Abbreviated code` varchar(10) NOT NULL," + 
//				  "  `Degree` varchar(255) NOT NULL" + 
//				  ") "
//				  );
//		stmt.executeUpdate(
//				  "CREATE TABLE if not exists `Degrees` (" + 
//				  "  `ID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
//				  "  `Full name` varchar(60) NOT NULL," + 
//				  "  `Abbreviated code` varchar(10) NOT NULL," + 
//				  "  `Lead department` varchar(255) NOT NULL," + 
//				  "  `Levels of study` varchar(1) NOT NULL," +
//				  "  `Modules` varchar(10) NOT NULL" +
//				  //" FOREIGN KEY (Modules) REFERENCES Modules(Full name)"+
//				  ") "
//				  );
//		stmt.executeUpdate(
//				  "CREATE TABLE if not exists `Modules` (" + 
//				  "  `ID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
//				  "  `Full name` varchar(20) NOT NULL," + 
//				  "  `Abbreviated code` varchar(10) NOT NULL," + 
//				  "  `Credits` varchar(30) NOT NULL," + 
//				  "  `Teaching season` varchar(10) NOT NULL" + 
//				  ") "
//				  );
//		stmt.executeUpdate(
//				  "CREATE TABLE if not exists `Students` (" + 
//				  "  `Title` varchar(10) NOT NULL," + 
//				  "  `Surname` varchar(20) NOT NULL," + 
//				  "  `Forename` varchar(20) NOT NULL," + 
//				  "  `Degree` varchar(30) NOT NULL," + 
//				  "  `Registration number` int(30) NOT NULL," + 
//				  "  `Email` varchar(30) NOT NULL," + 
//				  "  `Address` varchar(30) NOT NULL," + 
//				  "  `Personal tutor` varchar(30) NOT NULL," + 
//				  "  PRIMARY KEY (`Registration number`)" + 
//				  ") "
//				  );
//		//database insert test
//		//account insert test
	    String password = SecurityHandler.hashPassword("3");
		String insertAccounts="INSERT INTO `Accounts` (Username,Password,Privileges) VALUES ('3', " + password + ", 'Administrators');";
		//stmt.executeUpdate(insertAccounts);
		//departments insert test
		String insertDepartments="INSERT INTO `Departments`(`Full name`,`Abbreviated code`,`Degree`) VALUES ('Computer Science', 'COM', 'test');";
		//stmt.executeUpdate(insertDepartments);

		
		String insertDegrees="INSERT INTO `Degrees` (`Full name`,`Abbreviated code`,`Lead department`,`Levels of study`,`Modules`)VALUES ('MEng Software Engineering', 'COMU03', 'Computer Science', '4', 'test');";
		//stmt.executeUpdate(insertDegrees);
		String insertModule="INSERT INTO `Modules`(`Full name`,`Abbreviated code`,`Credits`,`Teaching season`)  VALUES ('Java programming', 'Com1005', '20', 'all');";
		//stmt.executeUpdate(insertModule);
		String insertStudents="INSERT INTO `Students` VALUES ('Mr.', 'Xu', 'Dong', 'null', '17899999', 'xdong14@sheffield.ac.uk','s14sa','Dr.xxxxxxx');";
		//stmt.executeUpdate(insertStudents);

        DatabaseMetaData md = con.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        while (rs.next()) {
        	System.out.println(rs.getString(3));
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
  }
}
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
		stmt.executeUpdate(
				  "CREATE TABLE IF NOT EXISTS `teacher2` (`teach_id` INT NOT NULL)"
				  );
		PreparedStatement pstmt = con.prepareStatement("INSERT INTO `teacher2` VALUES (1)");
		ResultSet res =stmt.executeQuery("SELECT `teach_id` FROM `teacher2`");
		
		int id = res.getInt("");	
		System.out.print(id);
		res.close();


	  }
	  catch (SQLException ex) {    
		  ex.printStackTrace();
	  }
	  finally {  
		  if (con != null) con.close();
	  }
  }
}

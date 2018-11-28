import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Database {
	Statement stmt = null;
	Connection con = null; 
	String Host = "jdbc:mysql://stusql.dcs.shef.ac.uk/team031";
	String UserName = "team031";
	String PassWord = "4934b78c";
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox getPBox(String role) throws SQLException {
		JComboBox DepBox=new JComboBox();

		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
		stmt = con.createStatement();

		String sql ="SELECT `fullname` from "+role+";";
		ResultSet depRs = stmt.executeQuery(sql);
		if(!(depRs.next()))
		{
		   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			DepBox.addItem(depRs.getString("fullname"));

			while(depRs.next()) {
				DepBox.addItem(depRs.getString("fullname"));
			}	

		}
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
			return DepBox;

	}
	public void removeItem (String compName,int getname) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
		stmt = con.createStatement();
		String removeName = null;

		if(compName == "accounts") {
			removeName= "DELETE FROM "+compName+" WHERE `accountid` = '"+getname+"'"; 
			System.out.print(removeName);
		
		}else if(compName == "department"){
			removeName= "DELETE FROM "+compName+" WHERE `depid` = '"+getname+"'"; 
		System.out.print(removeName);

		}else if(compName == "degree"){
			removeName= "DELETE FROM "+compName+" WHERE `degid` = '"+getname+"'"; 
		System.out.print(removeName);

		}else if(compName == "module"){
			removeName= "DELETE FROM "+compName+" WHERE `modid` = '"+getname+"'"; 
		System.out.print(removeName);

		}
		stmt.executeUpdate(removeName);
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void linkDee(int id ,String level,String lead) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
			stmt = con.createStatement();	
		
			int depId = 0;
			int depId1 = 0;

			String depName = "SELECT `depid` FROM `department` WHERE `fullname` = '"+level+"';";
			ResultSet depNameRs = stmt.executeQuery(depName);
			while(depNameRs.next()) {
				depId= depNameRs.getInt(1);
			}
			String depName1 = "SELECT `depid` FROM `department` WHERE `fullname` = '"+lead+"';";
			ResultSet depNameRs1 = stmt.executeQuery(depName1);
			while(depNameRs1.next()) {
				depId1= depNameRs1.getInt(1);
			}
			String insertDee="INSERT INTO `degreedepartment` (`degid`,`depid`,`leaddepartment`) VALUES ('"+id+"','"+depId+"','"+depId1+"');";
			
			stmt.executeUpdate(insertDee);
		
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void linkMod (int modId, String name,String cre,String term,boolean core) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
			stmt = con.createStatement();	
			int cre1 =  Integer.parseInt(cre);
			int core1 = 0;
			if(core == true)
				core1 = 0;
			else
				core1 = 1;
			String deeName = "SELECT `degid` FROM `degree` WHERE `fullname` = '"+name+"';";
			ResultSet deeNameRs = stmt.executeQuery(deeName);
			int deeId = 0;
			while(deeNameRs.next()) {
				deeId= deeNameRs.getInt(1);
			}

			String insertDee="INSERT INTO `moduledegree` (`modid`,`degid`,`credits`,`term`,`core`) VALUES ('"+modId+"','"+deeId+"','"+cre1+"','"+term+"','"+core1+"');";
			
			stmt.executeUpdate(insertDee);
		
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertDee (String deeName,String abbCode,String level,Boolean box) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
		stmt = con.createStatement();	
		
			int boxInt = box ? 1 : 0;
			

			String insertDee="INSERT INTO `degree` (`fullname`,`abreviatedcode`,`yearinindustry`,`years`) VALUES ('"+deeName+"','"+abbCode+"','"+boxInt+"','"+ level+"');";
	
			stmt.executeUpdate(insertDee);
		
		
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertDepa (String depName,String abbCode) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
		stmt = con.createStatement();		
		String insertDep="INSERT INTO `department` (`fullname`,`abreviatedcode`) VALUES ('"+depName+"','"+abbCode+"');";

		stmt.executeUpdate(insertDep);

		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertAccount (String accName,String password,String privil) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
		stmt = con.createStatement();
		String insertAccounts="INSERT INTO `accounts` (`username`,`password`,`permissions`) VALUES ('"+accName+"','"+password+"','"+privil+"');";

		stmt.executeUpdate(insertAccounts);

	
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertStudent() {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
			stmt = con.createStatement();
			String insertAccounts="INSERT INTO `student` (`title`,`surname`,`forename`,`email`,) VALUES ('"+accName+"','"+password+"','"+privil+"');";

			
			stmt.executeUpdate(insertAccounts);
	
			
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
		
	}
	//display the table in interface
	public JTable displayTable (String role){
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
		stmt = con.createStatement();
	
		String sqlCom = "SELECT * FROM "+role+";";
		
		ResultSet accRs = stmt.executeQuery(sqlCom);
			
		if(!(accRs.next()))
		{
		   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
		}
		ResultSetMetaData rsmd= (ResultSetMetaData) accRs.getMetaData();
		Vector<Vector<String>> rows = new Vector<Vector<String>>();
		Vector<String> columnHeads=new Vector<String>();	
		
		for(int i=1;i<=rsmd.getColumnCount();i++)
		{
		    columnHeads.addElement(rsmd.getColumnName(i));
		}
		do{
		     rows.addElement(getNextRow(accRs,rsmd));
		}while(accRs.next());
		
		JTable table = new JTable(rows,columnHeads);
		

		return table;

		}
		catch (SQLException ex) {    
			ex.printStackTrace();
			JTable table = null;
			return table;
		}

	}
	
	//get next row in database table
	private Vector<String> getNextRow(ResultSet rs,ResultSetMetaData rsmd) throws SQLException{
		Vector<String> currentRow=new Vector<String>();
		for(int i=1;i<=rsmd.getColumnCount();i++){
			currentRow.addElement(rs.getString(i));
		}
		return currentRow;
	}
	
	
	public void create() {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
		   
		    
		    stmt = con.createStatement();
		    
//		    String drop = "DROP TABLE IF EXISTS accounts;";
//		    String dropDe = "DROP TABLE IF EXISTS department;";
//		    String dropDee = "DROP TABLE IF EXISTS degree;";
//		    String dropSt = "DROP TABLE IF EXISTS student;";
//		    String dropMo = "DROP TABLE IF EXISTS module;";
//		    String dropStDe = "DROP TABLE IF EXISTS studentdegree;";
//		    String dropMoDee = "DROP TABLE IF EXISTS moduledegree;";
//		    String dropStuMod = "DROP TABLE IF EXISTS studentmodule;";
//		    stmt.executeUpdate(dropStuMod);
//		    stmt.executeUpdate(dropMoDee);
//		    stmt.executeUpdate(dropStDe);
//		    stmt.executeUpdate(dropMo);
//		    stmt.executeUpdate(drop);
//
//		    stmt.executeUpdate(dropSt);
//		    stmt.executeUpdate(dropDe);
//
//		    stmt.executeUpdate(dropDee);




		    //database create
			String acc = "CREATE TABLE `accounts` (" +
			   " `accountid` int  NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
			   " `username` varchar(20) NOT NULL," +
			   " `password` varchar(20) NOT NULL," +
			   " `permissions` varchar(15) NOT NULL," +
			   " `regid` int," +
			   " FOREIGN KEY (`regid`) REFERENCES student(`regid`)" +
			   ");";
		
			String dep = "CREATE TABLE `department` (" +
			  " `depid` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," +
			  " `fullname` varchar(30) NOT NULL," +
			  " `abreviatedcode` varchar(10) NOT NULL" +
			  ");";
		
			String dee = "CREATE TABLE `degree` (" +
			  " `degid` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			  " `fullname` varchar(30)," +
			  " `abreviatedcode` varchar(10)," +
			  " `yearinindustry` boolean," +
			  " `years` int" +
			  ");";
			
			String deeDep = "CREATE TABLE `degreedepartment` (" +
			  " `degid` int," +
			  " `depid` int," +
			  " `leaddepartment` int," +
			  " primary key (`degid`, `depid`)," +
			  " FOREIGN KEY (`degid`) REFERENCES degree(`degid`)," +
			  " FOREIGN KEY (`depid`) REFERENCES department(`depid`));";
			
			String tutor = "CREATE TABLE `tutor` (" +
			  " `tutorid` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			  " `name` varchar(30)" +
			  ");";
			String stud = "CREATE TABLE `student` (" +
			   " `regid` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			   " `title` varchar(5)," +
			   " `surname` varchar(20)," +
			   " `forename` varchar(20)," +
			   " `email` varchar(30)," +
			   " `tutorid` int," +
			   " `registered` boolean," +
			   " FOREIGN KEY (tutorid) references tutor(tutorid)" +
			   ");";
			
			String mod = "CREATE TABLE module (" +
			  " `modid` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			  " `fullname` varchar(20)," +
			  " `abreviatedcode` varchar(10)" +
			  ");";

			String stuDee = "CREATE TABLE `studentdegree` (" +
			   " `regid` int," +
			   " `degid` int," +
			   " `yearofstudy` varchar(1)," +
			   " `overallgrade` int," +
			   " `startdate` date," +
			   " `enddate` date," +
			   " primary key (`regid`, `degid`)," +
			   " FOREIGN KEY (`regid`) REFERENCES student(`regid`)," +
			   " FOREIGN KEY (`degid`) REFERENCES degree(`degid`));";
		
			String modDee = "CREATE TABLE `moduledegree` ("+
			  " `modid` int," +
			  " `degid` int," +
			  " `credits` int," +
			  " `term` varchar(10)," +
			  " `core` boolean," +
			  " primary key (`modid`, `degid`)," +
			  " FOREIGN KEY (`modid`) REFERENCES module(`modid`)," +
			  " FOREIGN KEY (`degid`) REFERENCES degree(`degid`));";
		
			String stuMod = "CREATE TABLE `studentmodule` ("+
			  " `regid` int,"+
			  " `modid` int,"+
			  " `grade` int,"+
			  " `resitgrade` int,"+
			  " `repeatgrade` int,"+
			  " primary key (`regid`, `modid`),"+
			  " FOREIGN KEY (`regid`) REFERENCES student(`regid`),"+
			  " FOREIGN KEY (`modid`) REFERENCES module(`modid`));";


			stmt.executeUpdate(dep);


			stmt.executeUpdate(tutor);
			stmt.executeUpdate(stud);
			stmt.executeUpdate(acc);
			stmt.executeUpdate(dee);
			stmt.executeUpdate(deeDep);
			stmt.executeUpdate(mod);
			stmt.executeUpdate(stuDee);
			stmt.executeUpdate(modDee);
			stmt.executeUpdate(stuMod);
			
			
			String insertAcc="INSERT INTO `accounts` (`username`,`password`,`permissions`)VALUES ('test', 'test', 'Administrator');";
			stmt.executeUpdate(insertAcc);
			String insertTu="INSERT INTO `tutor` (`name`)VALUES ('Dr.Green');";
			stmt.executeUpdate(insertTu);
			String insertStu="INSERT INTO `student` (`title`,`surname`,`forename`,`email`,`tutorid`,`registered`)VALUES ('Mr.','Dong','Xu','xdong14@sheffield.ac.uk','1','0');";
			stmt.executeUpdate(insertStu);
			
			String insertAcc1="INSERT INTO `accounts` (`username`,`password`,`permissions`,`regid`)VALUES ('s1', 's1', 'student','1');";
			stmt.executeUpdate(insertAcc1);
			
			String insertDep="INSERT INTO `department` (`fullname`,`abreviatedcode`)VALUES ('Computer Science', 'COM');";
			stmt.executeUpdate(insertDep);
			
			String insertDee="INSERT INTO `degree` (`fullname`,`abreviatedcode`,`yearinindustry`,`years`)VALUES ('MEng Software Engineering', 'COMU03','1','3');";
			stmt.executeUpdate(insertDee);
			
			String insertMod="INSERT INTO `module` (`fullname`,`abreviatedcode`)VALUES ('Java Programming', 'Com1005');";
			stmt.executeUpdate(insertMod);
			

			
			String insertStuMod="INSERT INTO `studentmodule` (`regid`,`modid`)VALUES ('1','1');";
			stmt.executeUpdate(insertStuMod);
			
			
			DatabaseMetaData md = con.getMetaData();
		    ResultSet rs = md.getTables(null, null, "%", null);
		    while (rs.next()) {
		    	System.out.println(rs.getString(3));
		    }

	    }
	    catch (SQLException ex) {    
			  ex.printStackTrace();
		}

	}
		
	
	
	
	
	public static void main(String[] args) throws Exception {
		Database db = new Database();
		db.create();
	}
}

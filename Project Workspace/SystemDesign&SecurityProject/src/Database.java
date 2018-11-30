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

		if(role == "Credits") {
			String sql ="SELECT `value` from "+role+";";
			ResultSet depRs = stmt.executeQuery(sql);
			if(!(depRs.next()))
			{
			   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				DepBox.addItem(depRs.getString("value"));

				while(depRs.next()) {
					DepBox.addItem(depRs.getString("value"));
				}	
			}	
		}
		else if(role == "Login_Details") {
			String sql ="SELECT `username` from "+role+";";
			ResultSet depRs = stmt.executeQuery(sql);
			if(!(depRs.next()))
			{
			   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				DepBox.addItem(depRs.getString("username"));

				while(depRs.next()) {
					DepBox.addItem(depRs.getString("username"));
				}	
			}	
		}
		else if(role == "Period_of_Study") {
			String sql ="SELECT `perID` from "+role+";";
			ResultSet depRs = stmt.executeQuery(sql);
			if(!(depRs.next()))
			{
			   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				DepBox.addItem(depRs.getString("perID"));

				while(depRs.next()) {
					DepBox.addItem(depRs.getString("perID"));
				}	
			}	
		}
		else if(role == "Student") {
			String sql ="SELECT `regID` from "+role+";";
			ResultSet depRs = stmt.executeQuery(sql);
			if(!(depRs.next()))
			{
			   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				DepBox.addItem(depRs.getInt("regID"));

				while(depRs.next()) {
					DepBox.addItem(depRs.getInt("regID"));
				}	
			}	
		}
		else if(role == "Teaching_Time") {
			String sql ="SELECT `time` from "+role+";";
			ResultSet depRs = stmt.executeQuery(sql);
			if(!(depRs.next()))
			{
			   JOptionPane.showMessageDialog(null, "No data!", "No data",JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				DepBox.addItem(depRs.getString("time"));

				while(depRs.next()) {
					DepBox.addItem(depRs.getString("time"));
				}	
			}	
		}
		else {

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

		if(compName == "Login_Details") {
			removeName= "DELETE FROM "+compName+" WHERE `accountid` = '"+getname+"'"; 
			System.out.print(removeName);
		
		}else if(compName == "Department"){
			removeName= "DELETE FROM "+compName+" WHERE `depID` = '"+getname+"'"; 
		System.out.print(removeName);

		}else if(compName == "Degree"){
			removeName= "DELETE FROM "+compName+" WHERE `degID` = '"+getname+"'"; 
		System.out.print(removeName);

		}else if(compName == "Module"){
			removeName= "DELETE FROM "+compName+" WHERE `modID` = '"+getname+"'"; 
		System.out.print(removeName);

		}else if(compName == "Student"){
			removeName= "DELETE FROM "+compName+" WHERE `regID` = '"+getname+"'"; 
		System.out.print(removeName);

		}
		stmt.executeUpdate(removeName);
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void linkDee(int id ,String level,boolean lead1) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
			stmt = con.createStatement();	
		
			int depId1 = 0;
			int lead;
			if(lead1 == true)
				lead = 0;
			else
				lead = 1;
			
			String depName1 = "SELECT `depID` FROM `Department` WHERE `fullname` = '"+level+"';";
			ResultSet depNameRs1 = stmt.executeQuery(depName1);
			while(depNameRs1.next()) {
				depId1= depNameRs1.getInt(1);
			}
			
			String insertDee="INSERT INTO `Department_Degree_Link` (`depID`,`degID`,`lead`) VALUES ('"+depId1+"','"+id+"','"+lead+"');";
			
			stmt.executeUpdate(insertDee);
		
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void linkMod(int modId, String name,boolean core) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
			stmt = con.createStatement();	
			int core1 = 0;
			if(core == true)
				core1 = 0;
			else
				core1 = 1;
			String deeName = "SELECT `degid` FROM `Degree` WHERE `fullname` = '"+name+"';";
			ResultSet deeNameRs = stmt.executeQuery(deeName);
			int deeId = 0;
			while(deeNameRs.next()) {
				deeId= deeNameRs.getInt(1);
			}

			String insertDee="INSERT INTO `Module_Degree_Link` (`degID`,`modID`,`core`) VALUES ('"+deeId+"','"+modId+"','"+core1+"');";
			
			if(name.substring(0,3).equals("MSc") && core== false )
				JOptionPane.showMessageDialog(null, "Msc degree should be all core.", "No data",JOptionPane.INFORMATION_MESSAGE);
			else
			stmt.executeUpdate(insertDee);
		
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertDee (String deeName,String abbCode,char entry1,String level) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
			stmt = con.createStatement();	
			String levelId = "SELECT `studyID` FROM `Level_of_Study` WHERE `fullname` = '"+level+"';";
			ResultSet levelIdRs = stmt.executeQuery(levelId);
			int levelId1 = 0;
			while(levelIdRs.next()) {
				levelId1 = levelIdRs.getInt(1);
			}
			
			String checkExistance = "SELECT COUNT(`degID`) as 'total' FROM `Degree` WHERE `fullname` = '"+deeName+"' or 'code' = '" + abbCode + "';";
			ResultSet rs = stmt.executeQuery(checkExistance);
			rs.next();
			
			if(rs.getInt("total") <= 0) {
				String insertDee="INSERT INTO `Degree` (`fullname`,`code`,`entry`,`studyID`) VALUES ('"+deeName+"','"+abbCode+"','"+entry1+"','"+ levelId1+"');";
				stmt.executeUpdate(insertDee);
			}else {
				JOptionPane.showMessageDialog(null, "A degree with that name/code already exists.", "Degree already exists",JOptionPane.WARNING_MESSAGE);
			}
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertDepa (String depName,String abbCode) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
			stmt = con.createStatement();
			
			String checkExistance = "SELECT COUNT(`depID`) as 'total' FROM `Department` WHERE `fullname` = '"+depName+"' or 'code' = '" + abbCode + "';";
			ResultSet rs = stmt.executeQuery(checkExistance);
			rs.next();
			
			if(rs.getInt("total") <= 0) {
				String insertDep="INSERT INTO `Department` (`fullname`,`code`) VALUES ('"+depName+"','"+abbCode+"');";
				stmt.executeUpdate(insertDep);
			}else {
				JOptionPane.showMessageDialog(null, "A department with that name/code already exists.", "Department already exists",JOptionPane.WARNING_MESSAGE);
			}
	
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertAccount (String accName,String password,int privil) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
			stmt = con.createStatement();
		
			String checkExistance = "SELECT COUNT(`accountid`) as 'total' FROM `Login_Details` WHERE `username` = '"+accName+"';";
			ResultSet rs = stmt.executeQuery(checkExistance);
			rs.next();
			
			if(rs.getInt("total") <= 0) {
				String insertAccounts="INSERT INTO `Login_Details` (`username`,`password`,`pivilegeID`) VALUES ('"+accName+"','"+password+"','"+privil+"');";
				stmt.executeUpdate(insertAccounts);
			}else {
				JOptionPane.showMessageDialog(null, "An account with that username already exists.", "Account already exists",JOptionPane.WARNING_MESSAGE);
			}
		
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertGrade(String mod, Integer stu,int perId, int grade) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
			stmt = con.createStatement();
			String modId = "SELECT `modID` FROM `Module` WHERE `fullname` = '"+mod+"';";
			ResultSet modIdRs = stmt.executeQuery(modId);
			int modId1 = 0;
			while(modIdRs.next()) {
				modId1 = modIdRs.getInt(1);
			}
			

			String insertGrade="INSERT INTO `Student_Grades` (`modID`,`regID`,`perId`,`initialGrade`) VALUES ('"+modId1+"','"+stu+"','"+perId+"','"+grade+"');";
			stmt.executeUpdate(insertGrade);

		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void updateGrade(String type, int grade, int regId, int modId) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
			stmt = con.createStatement();
			
			String update = null;
			if(type.equals("Resit Grade"))
				update = "UPDATE `Student_Grades` SET `resitGrade` = '"+grade+"' WHERE `modID` = '"+modId+"' AND `regID` = '"+regId+"';";
			else
				update = "UPDATE `Student_Grades` SET `resitGrade` = '"+grade+"' WHERE `modID` = '"+modId+"' AND `regID` = '"+regId+"';";

			stmt.executeUpdate(update);
			
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertModule (String modName,String abbCode,int cre, String time) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
		stmt = con.createStatement();
		
		String creId = "SELECT `creditID` FROM `Credits` WHERE `value` = '"+cre+"';";
		ResultSet creIdRs = stmt.executeQuery(creId);
		int creIdRs1 = 0;
		while(creIdRs.next()) {
			creIdRs1 = creIdRs.getInt(1);
		}
		String timeId = "SELECT `timeID` FROM `Teaching_Time` WHERE `time` = '"+time+"';";
		ResultSet timeIdRs = stmt.executeQuery(timeId);
		String timeIdRs1 = null;
		while(timeIdRs.next()) {
			timeIdRs1 = timeIdRs.getString(1);
		}
		
		String checkExistance = "SELECT COUNT(`modID`) as 'total' FROM `Degree` WHERE `fullname` = '"+modName+"' or 'code' = '" + abbCode + "';";
		ResultSet rs = stmt.executeQuery(checkExistance);
		rs.next();
		
		if(rs.getInt("total") <= 0) {
			String insertModule="INSERT INTO `Module` (`fullname`,`code`,`creditID`,`timeID`) VALUES ('"+modName+"','"+abbCode+"','"+creIdRs1+"','"+timeIdRs1+"');";
			stmt.executeUpdate(insertModule);
		}else {
			JOptionPane.showMessageDialog(null, "A module with that name/code already exists.", "Module already exists",JOptionPane.WARNING_MESSAGE);
		}
		
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertStudentMod (int mod,int regId) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
		stmt = con.createStatement();
		String insertStudentMod="INSERT INTO `Module_Student_Link` (`modID`,`regID`) VALUES ('"+mod+"','"+regId+"');";			

		stmt.executeUpdate(insertStudentMod);

	
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertStudent(String tit,String sName,String fName,String emai,String tuto,String account,String dee) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
			int tutoId = 0;
			stmt = con.createStatement();

			
			String tuName = "SELECT `tutorID` FROM `Tutor` WHERE `fullname` = '"+tuto+"';";
			ResultSet tuNameRs = stmt.executeQuery(tuName);
			while(tuNameRs.next()) {
				tutoId= tuNameRs.getInt(1);
			}
			
			stmt = con.createStatement();
			String insertStudent="INSERT INTO `Student` (`title`,`forename`,`surname`,`email`,`tutorID`) VALUES ('"+tit+"','"+sName+"','"+fName+"','"+emai+"','"+tutoId+"');";
			System.out.print(insertStudent);
			stmt.executeUpdate(insertStudent);
			//insert into student_degree table
			String regId = "SELECT `regID` FROM `Student` WHERE `email` = '"+emai+"';";
			ResultSet regIdRs = stmt.executeQuery(regId);
			int regIdRs1 = 0;
			while(regIdRs.next()) {
				regIdRs1 = regIdRs.getInt(1);
			}
			String deeId = "SELECT `degID` FROM `Degree` WHERE `fullname` = '"+dee+"';";
			ResultSet deeIdRs = stmt.executeQuery(deeId);
			int deeIdRs1 = 0;
			while(deeIdRs.next()) {
				deeIdRs1 = deeIdRs.getInt(1);
			}
			String insertStudentDee="INSERT INTO `Student_Degree_Link` (`degID`,`regID`) VALUES ('"+deeIdRs1+"','"+regIdRs1+"');";			
			
			stmt.executeUpdate(insertStudentDee);
			
			
			String allMod = "SELECT `modID` FROM `Module_Degree_Link` WHERE `degID` = '"+deeIdRs1+"';";
			ResultSet allModRs = stmt.executeQuery(allMod);

//			//insert into student_grade table
//			String allMod = "SELECT `modID` FROM `Module_Degree_Link` WHERE `degID` = '"+deeIdRs1+"';";
//			ResultSet allModRs = stmt.executeQuery(allMod);

			while (allModRs.next()) {
				insertStudentMod(allModRs.getInt(1),regIdRs1);
			}
				
			
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
		
	}
	public int getRequireCredit(int regId) {
		int reCre=0;
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
			stmt = con.createStatement();
			int deeId = 0;
			String ent = null;
			String dee = "SELECT `degID` FROM `Student_Degree_Link` WHERE `regID` = '"+regId+"';";
			ResultSet deeRs = stmt.executeQuery(dee);
			while(deeRs.next()) {
				deeId = deeRs.getInt(1);

			}
			String entry = "SELECT `entry` FROM `Degree` WHERE `degID` = '"+deeId+"';";
			ResultSet entRs = stmt.executeQuery(entry);
			while(entRs.next()) {
				ent = entRs.getString("entry");

			}
			System.out.print(ent);

			if(ent.equals("P"))
				reCre = 180;
			else
				reCre = 120;
			
		return reCre;
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
			return reCre;

		}
		
	}
	public int getCurrentCredit(int regId) {
		int cr = 0;

		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
			stmt = con.createStatement();

			int modId = 0;
			String mod = "SELECT `modID` FROM `Module_Student_Link` WHERE `regID` = '"+regId+"';";
			ResultSet modRs = stmt.executeQuery(mod);
			while(modRs.next()) {
				modId = modRs.getInt(1);
				int modCre = getModCre(modId);
				cr = modCre+cr;


			}

			return cr;

		}
		catch (SQLException ex) {    
			ex.printStackTrace();
			return cr;

		}
	}
	private int getModCre(int modId) {
		int cre=0;
		int credit = 0;
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
			stmt = con.createStatement();
			String dee = "SELECT `creditID` FROM `Module` WHERE `modId` = '"+modId+"';";
			ResultSet deeRs = stmt.executeQuery(dee);
			while(deeRs.next()) {
				cre = deeRs.getInt(1);
				System.out.print(cre);

			}
			String creit = "SELECT `value` FROM `Credits` WHERE `creditID` = '"+cre+"';";
			ResultSet creditRs = stmt.executeQuery(creit);
			while(creditRs.next()) {
				credit = creditRs.getInt(1);

			}
		return credit;
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
			return cre;

		}
	}
	public void registerStudent(String startDate, String endDate, char label, String level, int degId) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
			stmt = con.createStatement();
			
			String levelId = "SELECT `studyID` FROM `Level_of_Study` WHERE `fullname` = '"+level+"';";
			System.out.print(levelId);

			ResultSet levelIdRs = stmt.executeQuery(levelId);
			int levelId1 = 0;

			while(levelIdRs.next()) {
				levelId1 = levelIdRs.getInt(1);
			}

			String registerStu="INSERT INTO `Period_of_Study` (`label`,`startDate`,`endDate`,`studyID`,`regID`) VALUES ('"+label+"','"+startDate+"','"+endDate+"','"+levelId1+"','"+degId+"');";			
			stmt.executeUpdate(registerStu);
		}	
		catch (SQLException ex) {    
			ex.printStackTrace();

		}
	}
	public void addOptional(int degId, String mod) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
			stmt = con.createStatement();
			String modId = "SELECT `modID` FROM `Module` WHERE `fullname` = '"+mod+"';";
			ResultSet modIdRs = stmt.executeQuery(modId);
			int modId1 = 0;
			while(modIdRs.next()) {
				modId1 = modIdRs.getInt(1);
			}
			
			insertStudentMod(modId1,degId);
		}	
		catch (SQLException ex) {    
			ex.printStackTrace();

		}		
	}
	public void dropOptional(int degId, String mod) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
			stmt = con.createStatement();
			String modId = "SELECT `modID` FROM `Module` WHERE `fullname` = '"+mod+"';";
			ResultSet modIdRs = stmt.executeQuery(modId);
			int modId1 = 0;
			while(modIdRs.next()) {
				modId1 = modIdRs.getInt(1);
			}
			
			deleteStudentMod(modId1,degId);
		}	
		catch (SQLException ex) {    
			ex.printStackTrace();

		}		
	}
	public void deleteStudentMod (int mod,int regId) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
		stmt = con.createStatement();
		String deleteStudentMod="DELETE FROM `Module_Student_Link` WHERE `modID`='"+mod+"'AND `regID`='"+regId+"';";			

		stmt.executeUpdate(deleteStudentMod);

	
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
	public JTable meanGradeTable (int regID,int perId){
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
		stmt = con.createStatement();
	
		String sqlCom = "SELECT `initialGrade` FROM `Student_Grades` WHERE regID == "+regID+" perID == "+perId+"";";
		
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
		    
		    String drop = "DROP TABLE IF EXISTS Tutor;";
		    String dropDe = "DROP TABLE IF EXISTS Department;";
		    String dropDee = "DROP TABLE IF EXISTS Teaching_Time;";
		    String dropSt = "DROP TABLE IF EXISTS Credits;";
		    String dropMo = "DROP TABLE IF EXISTS Level_of_Study;";
		    String dropStDe = "DROP TABLE IF EXISTS Degree_Class;";
		    String dropMoDee = "DROP TABLE IF EXISTS Privileges;";
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
			String acc = "CREATE TABLE IF NOT EXISTS `Login_Details`  (" +
			   " `accountid` int  NOT NULL PRIMARY KEY AUTO_INCREMENT,"+
			   " `username` varchar(20) NOT NULL," +
			   " `password` varchar(128) NOT NULL," +
			   " `pivilegeID` varchar(15) NOT NULL," +
			   " `regid` int," +
			  // " PRIMARY KEY (username)," +
			   " FOREIGN KEY (`regid`) REFERENCES Student(`regid`)" +
			   ");";
		
			String dep = "CREATE TABLE IF NOT EXISTS `Department` (" +
			  " `depID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," +
			  " `fullname` varchar(30) NOT NULL," +
			  " `code` varchar(10) NOT NULL" +
			  ");";
		
			String dee = "CREATE TABLE IF NOT EXISTS `Degree` (" +
			  " `degID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			  " `fullname` varchar(30)," +
			  " `code` varchar(10)," +
			  " `entry` char," +
			  " `studyID` int," +
			  " FOREIGN KEY (`studyID`) REFERENCES Level_of_Study(`studyID`)" +
			  ");";
			
			String deeDep = "CREATE TABLE IF NOT EXISTS `Department_Degree_Link` (" +
			  " `degID` int," +
			  " `depID` int," +
			  " `lead` bool," +
			  " FOREIGN KEY (`depID`) REFERENCES Department(`depID`)," +
			  " FOREIGN KEY (`degID`) REFERENCES Degree(`degID`));";
			
			String deeClass = "CREATE TABLE IF NOT EXISTS `Degree_Class` (" +
			  " `classID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," +
	     	  " `fullname` varchar(30) NOT NULL," +
			  " `minPercent` float," +
			  " `maxPercent` float" +
			  ");";
			
			String stuGrade = "CREATE TABLE IF NOT EXISTS `Student_Grades` (" +
			  " `modID` int NOT NULL," +
			  " `regID` int NOT NULL," +
			  " `perID` int NOT NULL," +
			  " `initialGrade` float," +
			  " `resitGrade` float," +
			  " `repeatGrade` float," +
			  " FOREIGN KEY (`perID`) REFERENCES Period_of_Study(`perID`)," +
			  " FOREIGN KEY (`modID`) REFERENCES Module(`modID`)," +
			  " FOREIGN KEY (`regID`) REFERENCES Student(`regID`));";
			
			String tutor = "CREATE TABLE IF NOT EXISTS `Tutor` (" +
			  " `tutorID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			  " `fullname` varchar(30)" +
			  ");";
			
			String level = "CREATE TABLE IF NOT EXISTS `Level_of_Study` (" +
			  " `studyID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			  " `fullname` varchar(30)," +
			  " `code` char" +
			  ");";
			
			String teachTime = "CREATE TABLE IF NOT EXISTS `Teaching_Time` (" +
			  " `timeID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			  " `time` varchar(30)" +
			  ");";
			String cred = "CREATE TABLE IF NOT EXISTS `Credits` (" +
			  " `creditID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			  " `value` int" +
			  ");";
			String stud = "CREATE TABLE IF NOT EXISTS `Student` (" +
			   " `regID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			   " `title` varchar(5)," +
			   " `forename` varchar(20)," +
			   " `surname` varchar(20)," +
			   " `email` varchar(30)," +
			   " `tutorID` int," +
			   " FOREIGN KEY (tutorID) references Tutor(tutorID)" +
			   ");";
			
			String mod = "CREATE TABLE IF NOT EXISTS `Module` (" +
			  " `modID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
			  " `fullname` varchar(30)," +
			  " `code` varchar(10)," +
			  " `creditID` int," +
			  " `timeID` int," +
			  " FOREIGN KEY (`creditID`) REFERENCES Credits(`creditID`)," +
			  " FOREIGN KEY (`timeID`) REFERENCES Teaching_Time(`timeID`)" +
			  ");";
			String period = "CREATE TABLE IF NOT EXISTS `Period_of_Study` (" +
			  " `perID` int NOT NULL PRIMARY KEY AUTO_INCREMENT," +
			  " `label` char," +
			  " `startDate` date," +
			  " `endDate` date," +
	          " `studyID` int," +
		      " `regID` int," +
		      " `status` varchar(15)," +
			  " FOREIGN KEY (`studyID`) REFERENCES Level_of_Study(`studyID`)," +
		      " FOREIGN KEY (`regID`) REFERENCES Student(`regID`));";
	
			String stuDee = "CREATE TABLE IF NOT EXISTS `Student_Degree_Link` (" +
			   " `degID` int," +
			   " `regID` int," +
			   " FOREIGN KEY (`degID`) REFERENCES Degree(`degID`)," +
			   " FOREIGN KEY (`regID`) REFERENCES Student(`regID`));";
		
			String modDee = "CREATE TABLE IF NOT EXISTS `Module_Degree_Link` ("+
			  " `degID` int NOT NULL," +
			  " `modID` int NOT NULL," +
			  " `core` bool," +
			  " FOREIGN KEY (`degID`) REFERENCES Degree(`degID`)," +
			  " FOREIGN KEY (`modID`) REFERENCES Module(`modID`));";

			String stuClass = "CREATE TABLE IF NOT EXISTS `Student_Degree_Link` (" +
		     " `regID` int NOT NULL," +
		     " `classID` int NOT NULL," +
		     " FOREIGN KEY (`regID`) REFERENCES Student(`regID`)," +
		     " FOREIGN KEY (`classID`) REFERENCES Degree_Class(`classID`));";

			String stuLoginLink = "CREATE TABLE IF NOT EXISTS `Student_Login_link` (" +
	         " `userID` int NOT NULL," +
	         " `regID` int NOT NULL," +
	         " FOREIGN KEY (`userID`) REFERENCES Login_Details(`accountid`)," +
	         " FOREIGN KEY (`regID`) REFERENCES Student(`regID`));";
			
			String priv = "CREATE TABLE IF NOT EXISTS `Privileges` (" +
		     " `privilegeID` int  NOT NULL PRIMARY KEY AUTO_INCREMENT," + 
		     " `fullname` varchar(30)" +
		     ");";
			
			String stuMod = "CREATE TABLE IF NOT EXISTS `Module_Student_Link` ("+
			 " `modID` int NOT NULL," +
			 " `regID` int NOT NULL," +
			 " `core` bool," +
			 " FOREIGN KEY (`modID`) REFERENCES Module(`modID`)," +
			 " FOREIGN KEY (`regID`) REFERENCES Student(`regID`));";
			//no foreign keys
			stmt.executeUpdate(dep);
			stmt.executeUpdate(teachTime);
			stmt.executeUpdate(cred);
			stmt.executeUpdate(level);
			stmt.executeUpdate(tutor);
			stmt.executeUpdate(deeClass);
			stmt.executeUpdate(priv);
			//have foreign keys
			stmt.executeUpdate(stud);

			stmt.executeUpdate(mod);
			stmt.executeUpdate(dee);
			stmt.executeUpdate(acc);

			stmt.executeUpdate(period);
			stmt.executeUpdate(stuGrade);
			stmt.executeUpdate(stuMod);

			stmt.executeUpdate(stuDee);
			stmt.executeUpdate(deeDep);
			stmt.executeUpdate(modDee);
			stmt.executeUpdate(stuClass);
			stmt.executeUpdate(stuLoginLink);

			String insertTut="INSERT INTO `Tutor` (`fullname`)VALUES ('Dr.eg');";
			stmt.executeUpdate(insertTut);
			String insertDep="INSERT INTO `Department` (`fullname`,`code`)VALUES ('Computer Science', 'COM');";
			stmt.executeUpdate(insertDep);
			String insertTu="INSERT INTO `Level_of_Study` (`fullname`,`code`)VALUES ('Bachelors','3');";
			stmt.executeUpdate(insertTu);
			String insertDee="INSERT INTO `Degree` (`fullname`,`code`,`entry`,`studyID`)VALUES ('Computer Science', 'COMU01','U','1');";
			stmt.executeUpdate(insertDee);
			
			String insertCre="INSERT INTO `Credits` (`value`)VALUES ('20');";
			stmt.executeUpdate(insertCre);
			
			String insertTeacTime="INSERT INTO `Teaching_Time` (`time`)VALUES ('Autumn');";
			stmt.executeUpdate(insertTeacTime);
			
			
			
			String insertMod="INSERT INTO `Module` (`fullname`,`code`,`creditID`,`timeID`)VALUES ('How to Write Examples', 'EXA1001','1','1');";
			stmt.executeUpdate(insertMod);
			String insertMod2="INSERT INTO `Module` (`fullname`,`code`,`creditID`,`timeID`)VALUES ('How to Write Advanced Examples', 'EXA2001','1','1');";
			stmt.executeUpdate(insertMod2);

			
			String insertStu="INSERT INTO `Student` (`title`,`forename`,`surname`,`email`,`tutorID`)VALUES ('Mr','eg','eg','eg@sheffield.ac.uk','1');";
			stmt.executeUpdate(insertStu);

			
			String insertStuMod="INSERT INTO `Student_Grades` (`modID`,`regID`,`perID`,`initialGrade`,`resitGrade`,`repeatGrade`)VALUES ('1','1','1','0','20','40');";
			stmt.executeUpdate(insertStuMod);
			
			String insertPer="INSERT INTO `Period_of_Study` (`label`,`startDate`,`endDate`,`studyID`,`regID`)VALUES ('A','2017/01/08','2018/01/04','1','1');";
			stmt.executeUpdate(insertPer);
			
			String insertPri="INSERT INTO `Privileges` (`fullname`)VALUES ('Administrator');";
			stmt.executeUpdate(insertPri);
			
			String insertAcc="INSERT INTO `Login_Details` (`username`,`password`,`pivilegeID`)VALUES ('1','1','1');";
			stmt.executeUpdate(insertAcc);
			String insertAcc1="INSERT INTO `Login_Details` (`username`,`password`,`pivilegeID`)VALUES ('3','3','2');";
			stmt.executeUpdate(insertAcc1);


			
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

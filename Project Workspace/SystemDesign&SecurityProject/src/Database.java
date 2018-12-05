import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Database {
	PreparedStatement  stmt = null;
	Connection con = null; 
	String Host = "jdbc:mysql://stusql.dcs.shef.ac.uk/team031";
	String UserName = "team031";
	String PassWord = "4934b78c";
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JComboBox getPBox(String role) throws SQLException {
		JComboBox DepBox=new JComboBox();

		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){

		if(role == "Credits") {
			String sql ="SELECT `value` from "+role+";";
			stmt = con.prepareStatement(sql);

			ResultSet depRs = stmt.executeQuery();
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
			stmt = con.prepareStatement(sql);

			ResultSet depRs = stmt.executeQuery();
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
			stmt = con.prepareStatement(sql);

			ResultSet depRs = stmt.executeQuery();
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
		else if(role == "Period_of_Study") {
			String sql ="SELECT `perID` from "+role+";";
			stmt = con.prepareStatement(sql);

			ResultSet depRs = stmt.executeQuery();
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
			stmt = con.prepareStatement(sql);

			ResultSet depRs = stmt.executeQuery();
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
			stmt = con.prepareStatement(sql);

			ResultSet depRs = stmt.executeQuery();
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
		stmt = con.prepareStatement(sql);

		ResultSet depRs = stmt.executeQuery();
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
		stmt = con.prepareStatement(removeName);

		stmt.executeUpdate();
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void linkDee(int id ,String level,boolean lead1) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
		
			int depId1 = 0;
			int lead;
			if(lead1 == true)
				lead = 0;
			else
				lead = 1;
			
			String depName1 = "SELECT `depID` FROM `Department` WHERE `fullname` = '"+level+"';";
			stmt = con.prepareStatement(depName1);

			ResultSet depNameRs1 = stmt.executeQuery();
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
			int core1 = 0;
			if(core == true)
				core1 = 0;
			else
				core1 = 1;
			String deeName = "SELECT `degid` FROM `Degree` WHERE `fullname` = '"+name+"';";
			stmt = con.prepareStatement(deeName);

			ResultSet deeNameRs = stmt.executeQuery();
			int deeId = 0;
			while(deeNameRs.next()) {
				deeId= deeNameRs.getInt(1);
			}

			String insertDee="INSERT INTO `Module_Degree_Link` (`degID`,`modID`,`core`) VALUES ('"+deeId+"','"+modId+"','"+core1+"');";
			
			if(name.substring(0,3).equals("MSc") && core== false )
				JOptionPane.showMessageDialog(null, "Msc degree should be all core.", "No data",JOptionPane.INFORMATION_MESSAGE);
			else
			stmt = con.prepareStatement(insertDee);
			stmt.executeUpdate();
		
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertDee (String deeName,String abbCode,char entry1,String level) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
			String levelId = "SELECT `studyID` FROM `Level_of_Study` WHERE `fullname` = '"+level+"';";
			stmt = con.prepareStatement(levelId);

			ResultSet levelIdRs = stmt.executeQuery();
			int levelId1 = 0;
			while(levelIdRs.next()) {
				levelId1 = levelIdRs.getInt(1);
			}

			
			String checkExistance = "SELECT COUNT(`degID`) as 'total' FROM `Degree` WHERE `fullname` = '"+deeName+"' or 'code' = '" + abbCode + "';";
			stmt = con.prepareStatement(checkExistance);

      ResultSet rs = stmt.executeQuery();
			rs.next();
			
			if(rs.getInt("total") <= 0) {
				String insertDee="INSERT INTO `Degree` (`fullname`,`code`,`entry`,`studyID`) VALUES ('"+deeName+"','"+abbCode+"','"+entry1+"','"+ levelId1+"');";
			  stmt = con.prepareStatement(insertDee);
			  stmt.executeUpdate();
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

			
			String checkExistance = "SELECT COUNT(`depID`) as 'total' FROM `Department` WHERE `fullname` = '"+depName+"' or 'code' = '" + abbCode + "';";
      stmt = con.prepareStatement(checkExistance);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			if(rs.getInt("total") <= 0) {
				String insertDep="INSERT INTO `Department` (`fullname`,`code`) VALUES ('"+depName+"','"+abbCode+"');";
		    stmt = con.prepareStatement(insertDep);
        stmt.executeUpdate();
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

		
			String checkExistance = "SELECT COUNT(`accountid`) as 'total' FROM `Login_Details` WHERE `username` = '"+accName+"';";
      stmt = con.prepareStatement(checkExistance);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			if(rs.getInt("total") <= 0) {
				String insertAccounts="INSERT INTO `Login_Details` (`username`,`password`,`pivilegeID`) VALUES ('"+accName+"','"+password+"','"+privil+"');";
    		stmt = con.prepareStatement(insertAccounts);   
				stmt.executeUpdate();
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
			String modId = "SELECT `modID` FROM `Module` WHERE `fullname` = '"+mod+"';";
			stmt = con.prepareStatement(modId);
			ResultSet modIdRs = stmt.executeQuery();
			int modId1 = 0;
			while(modIdRs.next()) {
				modId1 = modIdRs.getInt(1);
			}

			String insertGrade="INSERT INTO `Student_Grades` (`modID`,`regID`,`perId`,`initialGrade`) VALUES ('"+modId1+"','"+stu+"','"+perId+"','"+grade+"');";
			stmt = con.prepareStatement(insertGrade);
			stmt.executeUpdate();

		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	
	public void updateGrade(int regId, String modName, String type, float grade) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
			String modIDCommand = "SELECT `modID` FROM `Module` WHERE `fullname` = '"+modName+"';";
			stmt = con.prepareStatement(modIDCommand);
			ResultSet modIdRs = stmt.executeQuery();
			modIdRs.next();
			int modID = modIdRs.getInt(1);

			String update = null;
			if(type.equals("Resit Grade"))
				update = "UPDATE `Student_Grades` SET `resitGrade` = '"+grade+"' WHERE `modID` = '"+modID+"' AND `regID` = '"+regId+"';";
			else
				update = "UPDATE `Student_Grades` SET `resitGrade` = '"+grade+"' WHERE `modID` = '"+modID+"' AND `regID` = '"+regId+"';";
			stmt = con.prepareStatement(update);
			stmt.executeUpdate();
			
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	
	public void insertModule (String modName,String abbCode,int cre, String time) throws SQLException {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
		
		String creId = "SELECT `creditID` FROM `Credits` WHERE `value` = '"+cre+"';";
		stmt = con.prepareStatement(creId);

		ResultSet creIdRs = stmt.executeQuery();
		int creIdRs1 = 0;
		while(creIdRs.next()) {
			creIdRs1 = creIdRs.getInt(1);
		}
		String timeId = "SELECT `timeID` FROM `Teaching_Time` WHERE `time` = '"+time+"';";
		stmt = con.prepareStatement(timeId);
		ResultSet timeIdRs = stmt.executeQuery();
		String timeIdRs1 = null;
		while(timeIdRs.next()) {
			timeIdRs1 = timeIdRs.getString(1);
		}
		
		String checkExistance = "SELECT COUNT(`modID`) as 'total' FROM `Degree` WHERE `fullname` = '"+modName+"' or 'code' = '" + abbCode + "';";
    stmt = con.prepareStatement(checkExistance);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		
		if(rs.getInt("total") <= 0) {
			String insertModule="INSERT INTO `Module` (`fullname`,`code`,`creditID`,`timeID`) VALUES ('"+modName+"','"+abbCode+"','"+creIdRs1+"','"+timeIdRs1+"');";
		  stmt = con.prepareStatement(insertModule);
			stmt.executeUpdate();
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
		String insertStudentMod="INSERT INTO `Module_Student_Link` (`modID`,`regID`) VALUES ('"+mod+"','"+regId+"');";			
		stmt = con.prepareStatement(insertStudentMod);

		stmt.executeUpdate();

	
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	public void insertStudent(String tit,String sName,String fName,String emai,String tuto,String account,String dee) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){		
			int tutoId = 0;

			
			String tuName = "SELECT `tutorID` FROM `Tutor` WHERE `fullname` = '"+tuto+"';";
			stmt = con.prepareStatement(tuName);

			ResultSet tuNameRs = stmt.executeQuery();
			while(tuNameRs.next()) {
				tutoId= tuNameRs.getInt(1);
			}
			
			String insertStudent="INSERT INTO `Student` (`title`,`forename`,`surname`,`email`,`tutorID`) VALUES ('"+tit+"','"+sName+"','"+fName+"','"+emai+"','"+tutoId+"');";
			System.out.print(insertStudent);
			stmt = con.prepareStatement(insertStudent);

			stmt.executeUpdate();
			//insert into student_degree table
			String regId = "SELECT `regID` FROM `Student` WHERE `email` = '"+emai+"';";
			stmt = con.prepareStatement(regId);

			ResultSet regIdRs = stmt.executeQuery();
			int regIdRs1 = 0;
			while(regIdRs.next()) {
				regIdRs1 = regIdRs.getInt(1);
			}
			String deeId = "SELECT `degID` FROM `Degree` WHERE `fullname` = '"+dee+"';";
			stmt = con.prepareStatement(deeId);
			ResultSet deeIdRs = stmt.executeQuery();
			int deeIdRs1 = 0;
			while(deeIdRs.next()) {
				deeIdRs1 = deeIdRs.getInt(1);
			}
			String insertStudentDee="INSERT INTO `Student_Degree_Link` (`degID`,`regID`) VALUES ('"+deeIdRs1+"','"+regIdRs1+"');";			
			stmt = con.prepareStatement(insertStudentDee);

			stmt.executeUpdate();
			
			
			String allMod = "SELECT `modID` FROM `Module_Degree_Link` WHERE `degID` = '"+deeIdRs1+"';";
			stmt = con.prepareStatement(allMod);

			ResultSet allModRs = stmt.executeQuery();

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
			int deeId = 0;
			String ent = null;
			String dee = "SELECT `degID` FROM `Student_Degree_Link` WHERE `regID` = '"+regId+"';";
			stmt = con.prepareStatement(dee);
			ResultSet deeRs = stmt.executeQuery();
			while(deeRs.next()) {
				deeId = deeRs.getInt(1);

			}
			String entry = "SELECT `entry` FROM `Degree` WHERE `degID` = '"+deeId+"';";
			stmt = con.prepareStatement(entry);
			ResultSet entRs = stmt.executeQuery();
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

			int modId = 0;
			String mod = "SELECT `modID` FROM `Module_Student_Link` WHERE `regID` = '"+regId+"';";
			stmt = con.prepareStatement(mod);

			ResultSet modRs = stmt.executeQuery();
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
			String dee = "SELECT `creditID` FROM `Module` WHERE `modId` = '"+modId+"';";
			stmt = con.prepareStatement(dee);

			ResultSet deeRs = stmt.executeQuery();
			while(deeRs.next()) {
				cre = deeRs.getInt(1);
				System.out.print(cre);

			}
			String creit = "SELECT `value` FROM `Credits` WHERE `creditID` = '"+cre+"';";
			stmt = con.prepareStatement(creit);

			ResultSet creditRs = stmt.executeQuery();
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
	public void graduate(int degId,String classID) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){
			String classID1 = "SELECT `classID` FROM `Degree_Class` WHERE `fullname` = '"+classID+"';";
			stmt = con.prepareStatement(classID1);
			int cre =0;
			ResultSet classId = stmt.executeQuery();
			while(classId.next()) {
				cre = classId.getInt(1);
				System.out.print(cre);

			}
			String insertAccounts="INSERT INTO `Student_Class_Link` (`regID`,`classID`) VALUES ('"+degId+"','"+cre+"');";
    		stmt = con.prepareStatement(insertAccounts);   
    		stmt.executeUpdate();
		}	
		catch (SQLException ex) {    
			ex.printStackTrace();

		}
	}
	public void registerStudent(String startDate, String endDate, char label, String level, int degId,String status) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
			
			String levelId = "SELECT `studyID` FROM `Level_of_Study` WHERE `fullname` = '"+level+"';";
			stmt = con.prepareStatement(levelId);

			System.out.print(levelId);

			ResultSet levelIdRs = stmt.executeQuery();
			int levelId1 = 0;

			while(levelIdRs.next()) {
				levelId1 = levelIdRs.getInt(1);
			}

			String registerStu="INSERT INTO `Period_of_Study` (`label`,`startDate`,`endDate`,`studyID`,`regID`,`status`) VALUES ('"+label+"','"+startDate+"','"+endDate+"','"+levelId1+"','"+degId+"','"+status+"');";			
			stmt = con.prepareStatement(registerStu);

			stmt.executeUpdate();
		}	
		catch (SQLException ex) {    
			ex.printStackTrace();

		}
	}
	public void addOptional(int degId, String mod) {
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
			String modId = "SELECT `modID` FROM `Module` WHERE `fullname` = '"+mod+"';";
			stmt = con.prepareStatement(modId);

			ResultSet modIdRs = stmt.executeQuery();
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
			String modId = "SELECT `modID` FROM `Module` WHERE `fullname` = '"+mod+"';";
			stmt = con.prepareStatement(modId);
			ResultSet modIdRs = stmt.executeQuery();
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
		String deleteStudentMod="DELETE FROM `Module_Student_Link` WHERE `modID`='"+mod+"'AND `regID`='"+regId+"';";			
		stmt = con.prepareStatement(deleteStudentMod);

		stmt.executeUpdate();

	
		}
		catch (SQLException ex) {    
			ex.printStackTrace();
		}
	}
	//display the table in interface
	public JTable displayTable (String role){
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
	
		String sqlCom = "SELECT * FROM "+role+";";
		stmt = con.prepareStatement(sqlCom);

		ResultSet accRs = stmt.executeQuery();
			
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

	public float getAverageGrade(JTable tb) {
		float cGrade = 0;
		Object gradeObj = 0;
		for(int i=0;i<tb.getRowCount();i++) {
			if(tb.getValueAt(i, 4)==null && tb.getValueAt(i, 5)==null)
				gradeObj = tb.getValueAt(i, 3);
				//System.out.print(tb.getValueAt(i, 3));
			else if(tb.getValueAt(i, 5)==null)
				gradeObj = tb.getValueAt(i, 4);
				//System.out.print(tb.getValueAt(i, 4));
			else
				gradeObj = tb.getValueAt(i, 5);
				//System.out.print(tb.getValueAt(i, 5));
			float grade = Float.parseFloat((String)gradeObj);
			cGrade = grade+cGrade;
		}

		return cGrade/(tb.getRowCount());
	}
	
	public JTable meanGradeTable (int regID,int perID){
		try(Connection con =DriverManager.getConnection(
				Host, UserName, PassWord)){	
	
		String sqlCom = "SELECT * FROM `Student_Grades` WHERE `regID` = '"+ regID +"' AND `perID` = '"+ perID +"';";
		stmt = con.prepareStatement(sqlCom);

		ResultSet accRs = stmt.executeQuery();
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
			    System.out.print(getNextRow(accRs,rsmd));

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
	
//	public void addDegreeClass (int regID, float grade) {
//		try(Connection con =DriverManager.getConnection(
//				Host, UserName, PassWord)){	
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
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
			stmt = con.prepareStatement(dep);

			stmt.executeUpdate();
			stmt = con.prepareStatement(teachTime);

			stmt.executeUpdate();
			stmt = con.prepareStatement(cred);

			stmt.executeUpdate();
			stmt = con.prepareStatement(level);
			stmt.executeUpdate();
			stmt = con.prepareStatement(tutor);
			stmt.executeUpdate();
			stmt = con.prepareStatement(deeClass);
			stmt.executeUpdate();
			stmt = con.prepareStatement(priv);
			stmt.executeUpdate();
			stmt = con.prepareStatement(stud);
			//have foreign keys
			stmt.executeUpdate();
			stmt = con.prepareStatement(mod);

			stmt.executeUpdate();
			stmt = con.prepareStatement(dee);
			stmt.executeUpdate();
			stmt = con.prepareStatement(acc);
			stmt.executeUpdate();
			stmt = con.prepareStatement(period);

			stmt.executeUpdate();
			stmt = con.prepareStatement(stuGrade);
			stmt.executeUpdate();
			stmt = con.prepareStatement(stuMod);
			stmt.executeUpdate();
			stmt = con.prepareStatement(stuDee);

			stmt.executeUpdate();
			stmt = con.prepareStatement(deeDep);
			stmt.executeUpdate();
			stmt = con.prepareStatement(modDee);
			stmt.executeUpdate();
			stmt = con.prepareStatement(stuClass);
			stmt.executeUpdate();
			stmt = con.prepareStatement(stuLoginLink);
			stmt.executeUpdate();

			String insertTut="INSERT INTO `Tutor` (`fullname`)VALUES ('Dr.eg');";
			stmt = con.prepareStatement(insertTut);

			stmt.executeUpdate();
			String insertDep="INSERT INTO `Department` (`fullname`,`code`)VALUES ('Computer Science', 'COM');";
			stmt = con.prepareStatement(insertDep);

			stmt.executeUpdate();
			String insertTu="INSERT INTO `Level_of_Study` (`fullname`,`code`)VALUES ('Bachelors','3');";
			stmt = con.prepareStatement(insertTu);

			stmt.executeUpdate();
			String insertDee="INSERT INTO `Degree` (`fullname`,`code`,`entry`,`studyID`)VALUES ('Computer Science', 'COMU01','U','1');";
			stmt = con.prepareStatement(insertDee);

			stmt.executeUpdate();
			
			String insertCre="INSERT INTO `Credits` (`value`)VALUES ('20');";
			stmt = con.prepareStatement(insertCre);

			stmt.executeUpdate();
			
			String insertTeacTime="INSERT INTO `Teaching_Time` (`time`)VALUES ('Autumn');";
			stmt = con.prepareStatement(insertTeacTime);

			stmt.executeUpdate();
			
			
			
			String insertMod="INSERT INTO `Module` (`fullname`,`code`,`creditID`,`timeID`)VALUES ('How to Write Examples', 'EXA1001','1','1');";
			stmt = con.prepareStatement(insertMod);

			stmt.executeUpdate();
			String insertMod2="INSERT INTO `Module` (`fullname`,`code`,`creditID`,`timeID`)VALUES ('How to Write Advanced Examples', 'EXA2001','1','1');";
			stmt = con.prepareStatement(insertMod2);
			stmt.executeUpdate();

			
			String insertStu="INSERT INTO `Student` (`title`,`forename`,`surname`,`email`,`tutorID`)VALUES ('Mr','eg','eg','eg@sheffield.ac.uk','1');";
			stmt = con.prepareStatement(insertStu);
			stmt.executeUpdate();

			
			String insertStuMod="INSERT INTO `Student_Grades` (`modID`,`regID`,`perID`,`initialGrade`,`resitGrade`,`repeatGrade`)VALUES ('1','1','1','0','20','40');";
			stmt = con.prepareStatement(insertStuMod);
			stmt.executeUpdate();
			
			String insertPer="INSERT INTO `Period_of_Study` (`label`,`startDate`,`endDate`,`studyID`,`regID`)VALUES ('A','2017/01/08','2018/01/04','1','1');";
			stmt = con.prepareStatement(insertPer);

			stmt.executeUpdate();
			
			String insertPri="INSERT INTO `Privileges` (`fullname`)VALUES ('Administrator');";
			stmt = con.prepareStatement(insertPri);

			stmt.executeUpdate();
			
		    String password = SecurityHandler.hashPassword("1");
			String insertAcc="INSERT INTO `Login_Details` (`username`,`password`,`pivilegeID`)VALUES ('1','" + password + "','1');";
			stmt = con.prepareStatement(insertAcc);

			stmt.executeUpdate();
			
		    password = SecurityHandler.hashPassword("3");
			String insertAcc1="INSERT INTO `Login_Details` (`username`,`password`,`pivilegeID`)VALUES ('3','" + password + "','2');";
			stmt = con.prepareStatement(insertAcc1);

			stmt.executeUpdate();
			
			//Adding Degree Classes TEMP
			String degreeClassCommand="INSERT INTO `Degree_Class` (`fullname`, `minPercent`, `maxPercent`) VALUES ('First Class', '69.5', '100.0');";
			stmt = con.prepareStatement(degreeClassCommand);
			stmt.executeUpdate();
			
			degreeClassCommand="INSERT INTO `Degree_Class` (`fullname`, `minPercent`, `maxPercent`) VALUES ('Upper Second', '59.5', '69.4');";
			stmt = con.prepareStatement(degreeClassCommand);
			stmt.executeUpdate();
			
			degreeClassCommand="INSERT INTO `Degree_Class` (`fullname`, `minPercent`, `maxPercent`) VALUES ('Lower Second', '49.5', '59.4');";
			stmt = con.prepareStatement(degreeClassCommand);
			stmt.executeUpdate();
			
			degreeClassCommand="INSERT INTO `Degree_Class` (`fullname`, `minPercent`, `maxPercent`) VALUES ('Third Class', '45.5', '49.4');";
			stmt = con.prepareStatement(degreeClassCommand);
			stmt.executeUpdate();
			
			degreeClassCommand="INSERT INTO `Degree_Class` (`fullname`, `minPercent`, `maxPercent`) VALUES ('Pass Non Honours', '39.5', '44.4');";
			stmt = con.prepareStatement(degreeClassCommand);
			stmt.executeUpdate();
			
			degreeClassCommand="INSERT INTO `Degree_Class` (`fullname`, `minPercent`, `maxPercent`) VALUES ('Fail Bachelors', '0.0', '39.4');";
			stmt = con.prepareStatement(degreeClassCommand);
			stmt.executeUpdate();
			
			degreeClassCommand="INSERT INTO `Degree_Class` (`fullname`, `minPercent`, `maxPercent`) VALUES ('Fail Masters', '0.0', '49.4');";
			stmt = con.prepareStatement(degreeClassCommand);
			stmt.executeUpdate();
			
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

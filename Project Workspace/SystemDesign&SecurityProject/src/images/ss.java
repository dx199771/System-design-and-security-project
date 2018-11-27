JLabel registrationNum = new JLabel("Registration number:");
				registrationNum.setBounds(30, 140, 100, 20);
			    JTextField registrationNum1 = new JTextField();
			    registrationNum1.setBounds(140, 140, 100, 20);
				
				JLabel email = new JLabel("Email address:");
				email.setBounds(30, 170, 100, 20);
			    JTextField email1 = new JTextField();
			    email1.setBounds(140, 170, 100, 20);
			    
				JLabel personalTutor = new JLabel("Personal tutor:");
				personalTutor.setBounds(30, 200, 100, 20);
			    JTextField personalTutor1 = new JTextField();
			    personalTutor1.setBounds(140, 200, 100, 20);
			   
			    JButton okbtn = new JButton("Confirm");
			    okbtn.setBounds(30, 230, 100, 20);
			    JButton cancelbtn = new JButton("Cancel");
			    cancelbtn.setBounds(140, 230, 80, 20);

			    addStudentP.add(title);
			    addStudentP.add(title1);
			    addStudentP.add(surname);
			    addStudentP.add(surname1);
			    addStudentP.add(forename);
			    addStudentP.add(forename1);
			    addStudentP.add(degree);
			    addStudentP.add(PrivilegesBox);
			    addStudentP.add(registrationNum);
			    addStudentP.add(registrationNum1);
			    addStudentP.add(email);
			    addStudentP.add(email1);
			    addStudentP.add(personalTutor);
			    addStudentP.add(personalTutor1);
			    
			    addStudentP.add(okbtn);
			    addStudentP.add(cancelbtn);
			    
			    addStudent.setLocation(900,500);
			    addStudent.setSize(290,320);
			    addStudent.setVisible(true);
			    addStudent.add(addStudentP);
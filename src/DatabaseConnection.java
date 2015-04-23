import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


		public class DatabaseConnection {
			final private static String databaseName = "cecs343";
			final private static String dbURL = "jdbc:mysql://mathnat.com:3306/" + databaseName;
			final private static String mUsername = "cecs343";
			final private static String mPassword = "cecs343";
			private static Connection mConnection = null;
			private static Statement mStatement = null;

			
			
			protected static void createConnection(){
				try {
					mConnection = DriverManager.getConnection(dbURL, mUsername, mPassword);
					
				} catch (SQLException e) {
					e.printStackTrace();
					//System.out.println("Database connection failed");
				}
				
			}
			
			
			protected static Professional attemptLogin(String user, String pass){
				
				try {
					createConnection();
					mStatement = mConnection.createStatement();
					ResultSet result = mStatement.executeQuery("SELECT * FROM `professional` WHERE `username`='"+user+"' && `password`='"+pass+"'");
					while(result.next()){
						String userName = result.getString(1);
						String name = result.getString(3);
						String practice = result.getString(4);
						
						boolean doc = true;
						if(Integer.parseInt(result.getString(5)) == 0){
							doc = false;
						}
						Professional prof = new Professional(name, practice, doc, userName);
						return prof;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			
			protected static ArrayList<Patient> getPatients(String user){
				ArrayList<Patient> list = new ArrayList<Patient>();
			
				try {
					createConnection();
					mStatement = mConnection.createStatement();
					ResultSet result = mStatement.executeQuery("SELECT * FROM `patient` WHERE `docusername`='"+user+"'");
					while(result.next()){
						String docusername = result.getString(1);
						String name = result.getString(2);
						String address = result.getString(3);
						String phone = result.getString(4);
						String dob = result.getString(5);
						
						Patient p = new Patient(name, phone, address,dob, docusername);
						
						list.add(p);
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
			}
			
			
			protected static Patient getPatientInfo(String user,String name){
				Patient p = null;
				
				try {
					createConnection();
					mStatement = mConnection.createStatement();
					ResultSet result = mStatement.executeQuery("SELECT * FROM `patient` WHERE `docusername`='"+user+"' "
							+ "&& `name`='"+name+"'");
					while(result.next()){
						String docusername = result.getString(1);
						String n = result.getString(2);
						String address = result.getString(3);
						String phone = result.getString(4);
						String dob = result.getString(5);
						
						p = new Patient(n, phone, address, dob, docusername);
						
						return p;
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return p;
			}
			
			
			protected static ArrayList<Prescription> getPrescriptions(String user, String n){
				ArrayList<Prescription> list = new ArrayList<Prescription>();
				
				try {
					createConnection();
					mStatement = mConnection.createStatement();
					ResultSet result = mStatement.executeQuery("SELECT * FROM `prescription` WHERE `docusername`='"+user+"' && `name`='"+n+"'");
					while(result.next()){
						String docusername = result.getString(1);
						String name = result.getString(2);
						String drugname = result.getString(3);
						String drugdose = result.getString(4);
						String instructions = result.getString(5);
						int ful = Integer.parseInt(result.getString(6));
						boolean fulfilled = false;
						if(ful == 1){
							fulfilled = true;
						}
						int pick = Integer.parseInt(result.getString(7));
						boolean pickedup = false;
						if(pick == 1){
							pickedup = true;
						}
						int refills = Integer.parseInt(result.getString(8));
						
						
						Prescription p = new Prescription(drugname, drugdose,
								instructions,name,fulfilled,pickedup,refills,docusername);
						
						list.add(p);
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
			}
			
			
			
			
			
			protected static ArrayList<Prescription> getAllPrescriptions(){
				ArrayList<Prescription> list = new ArrayList<Prescription>();
				
				try {
					createConnection();
					mStatement = mConnection.createStatement();
					ResultSet result = mStatement.executeQuery("SELECT * FROM `prescription`");
					while(result.next()){
						String docusername = result.getString(1);
						String name = result.getString(2);
						String drugname = result.getString(3);
						String drugdose = result.getString(4);
						String instructions = result.getString(5);
						int ful = Integer.parseInt(result.getString(6));
						boolean fulfilled = false;
						if(ful == 1){
							fulfilled = true;
						}
						int pick = Integer.parseInt(result.getString(7));
						boolean pickedup = false;
						if(pick == 1){
							pickedup = true;
						}
						int refills = Integer.parseInt(result.getString(8));
						
						
						Prescription p = new Prescription(drugname, drugdose,
								instructions,name,fulfilled,pickedup,refills,docusername);
						
						list.add(p);
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return list;
			}
			
			
			
			protected static boolean insertPatient(Patient patient){
				try {
					
					mConnection.setAutoCommit(false);
					mStatement = mConnection.createStatement();
					
						PreparedStatement ps = null;
						
						String sql = "INSERT INTO `patient`(`docusername`, `name`, `address`,"
								+ " `phone`, `dob`) "
								+ "VALUES ('"+patient.getDoctorName()+"','"+patient.getName()
								+"','"+patient.getAddress()+"','"+patient.getPhone()
								+"','"+patient.getDob()+"');";
						
						ps = mConnection.prepareStatement(sql);
						
						ps.executeUpdate();
						
						mConnection.commit();
					
						return true;
						
//					} else {
//						return false;
//					}
					
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			
			
			
			protected static boolean insertPrescription(Prescription prescription){
				try {
					
					mConnection.setAutoCommit(false);
					mStatement = mConnection.createStatement();
					
						PreparedStatement ps = null;
						
						boolean ful = prescription.isFulfilled();
						boolean pick = prescription.isPickedUp();
						int fulfilled = 0;
						int pickedup = 0;
						if(ful){
							fulfilled = 1;
						}
						if(pick){
							pickedup = 1;
						}
						
						String sql = "INSERT INTO `prescription`(`docusername`, `name`, `drugname`,"
								+ " `drugdose`, `instructions`,`fulfilled`,`pickedup`,`refills`) "
								+ "VALUES ('"+prescription.getDocusername()+"','"+prescription.getPatientName()
								+"','"+prescription.getDrugName()+"','"+prescription.getDrugDose()
								+"','"+prescription.getInstructions()+"','"+fulfilled+"','"
								+pickedup+"','"+prescription.getRefill()+"');";
						
						ps = mConnection.prepareStatement(sql);
						
						ps.executeUpdate();
						
						mConnection.commit();
					
						return true;
						
					
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			
			
			
			
			protected static void shutdown(){
				try {
					if(mStatement != null){
						mStatement.close();
					}
					if (mConnection != null){
						DriverManager.getConnection(dbURL, mUsername, mPassword);
						mConnection.close();
					}
					//System.out.println("Connection to database closed.\n");
				} catch (SQLException sqlException){
					sqlException.printStackTrace();
				}
			}

		}




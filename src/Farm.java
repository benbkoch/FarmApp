import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class Farm {
	/**
	 * Group 1:
	 * Travis Thompson
	 * Mana Sugano
	 * Ben Koch
	 * Maki Okawa
	 * 
	 * CECS 343 - Introduction to Software Engineering
	 *
	 * 
	 * 
	 */

	// GUI Fields
	// Set as protected
	protected Shell shlFarmApplicationCecs;

	// All private local member variables
	private ArrayList<Patient> patientsList;
	private ArrayList<Prescription> prescriptions;
	private Text drugNameBox;
	private Text drugDoseBox;
	private Text drugInstructionsBox;
	private Text firstnameText;
	private Text phoneText;
	private Text addressText;
	private Text insuranceText;
	private Text dobText;
	private Text lastnameText;
	private List patientsListBox;
	private List prescriptionsListBox;
	private Table tablePrescriptions;
	private Text searchText;
	private Button buttonRemovePatient;
	private TabItem tbtmPharmacy;
	private List list;
	private static int nextID = 0;
	private Button btnUpdate;
	private Text txtAddNewPrescription;
	private Text refills;
	private Text txtPatientInformationError;
	private Text txtPrescriptionError;
	private Professional User;
	private Text doctorName;
	private Text pharmName;




	/**
	 * This method opens the window for the user to see.
	 * @wbp.parser.entryPoint
	 * @param user Professional
	 */
	public void open(Professional user) {
		User = user;
		Display display = Display.getDefault();
		createContents();
		shlFarmApplicationCecs.open();
		shlFarmApplicationCecs.layout();
		while (!shlFarmApplicationCecs.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window. This is the method that will respond to
	 * the actions of the user.
	 */
	protected void createContents() {
		
		prescriptions = new ArrayList<Prescription>();
		
		// Connect to database to get the professional's patients.
		DatabaseConnection.createConnection();
		patientsList = DatabaseConnection.getPatients(User.getUsername());
		DatabaseConnection.shutdown();
		
		// Create window for GUI.
		shlFarmApplicationCecs = new Shell();
		shlFarmApplicationCecs.setSize(703, 671);
		shlFarmApplicationCecs.setText("Farm Application CECS 343");
		
		// Create tabs for window.
		TabFolder tabFolder = new TabFolder(shlFarmApplicationCecs, SWT.NONE);
		tabFolder.setBounds(0, 0, 685, 624);
		
		
		// If the professional is a doctor, use this tab.
		if (User.isDoctor()) {
			
			// Set doctor tab viewable.
			TabItem tbtmDoctor = new TabItem(tabFolder, SWT.NONE);
			tbtmDoctor.setText("Doctor");
			Composite compositeDoc = new Composite(tabFolder, SWT.NONE);
			tbtmDoctor.setControl(compositeDoc);
			
			// Create label.
			Label label = new Label(compositeDoc, SWT.NONE);
			label.setText("Patients");
			label.setBounds(16, 38, 68, 23);

			// Create button.
			Button buttonAddPatient = new Button(compositeDoc, SWT.NONE);
			// Add button selection listener for insert patient.
			buttonAddPatient.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					
					// Check if required text fields are empty.
					if (firstnameText.getText().length() == 0
							|| lastnameText.getText().length() == 0
							|| phoneText.getText().length() == 0
							|| addressText.getText().length() == 0
							|| insuranceText.getText().length() == 0
							|| dobText.getText().length() == 0) {
						//Failed: Set error text red.
						txtPatientInformationError
								.setForeground(SWTResourceManager
										.getColor(SWT.COLOR_RED));
					} else {
						//Success: add new patient.
						Patient patient = new Patient(firstnameText.getText()
								+" "+lastnameText.getText(), phoneText.getText(),
								addressText.getText(), dobText.getText(),User.getUsername());
						
						// Connect to database and insert the new patient.
						DatabaseConnection.createConnection();
						boolean succ = DatabaseConnection.insertPatient(patient);
						DatabaseConnection.shutdown();
						
						// Connect to database and get new list of patients.
						DatabaseConnection.createConnection();
						patientsList = DatabaseConnection.getPatients(User.getUsername());
						DatabaseConnection.shutdown();
						
						// Update patient list box.
						patientsListBox.removeAll();
						for(Patient p : patientsList){
							patientsListBox.add(p.getName());
						}
						
						//Reset text fields to blank.
						if(succ){
							txtPatientInformationError
									.setForeground(SWTResourceManager
											.getColor(SWT.COLOR_WHITE));
							firstnameText.setText("");
							lastnameText.setText("");
							phoneText.setText("");
							addressText.setText("");
							insuranceText.setText("");
							dobText.setText("");
						} else {
							//If add patient fails, set error text red.
							txtPatientInformationError
							.setForeground(SWTResourceManager
									.getColor(SWT.COLOR_RED));
						}
					}
				}
			});
			buttonAddPatient.setText("Add Patient");
			buttonAddPatient.setBounds(10, 292, 114, 28);

			//Create remove patient button.
			buttonRemovePatient = new Button(compositeDoc, SWT.NONE);
			buttonRemovePatient.setEnabled(false);
			buttonRemovePatient.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int index = patientsListBox.getSelectionIndex();
					patientsListBox.remove(index);
					patientsList.remove(index);
				}
			});
			buttonRemovePatient.setText("Remove Patient");
			buttonRemovePatient.setBounds(141, 292, 133, 28);

			// Create label.
			Label label_1 = new Label(compositeDoc, SWT.NONE);
			label_1.setText("Drug Name:");
			label_1.setBounds(333, 52, 87, 23);

			// Create label.
			Label label_2 = new Label(compositeDoc, SWT.NONE);
			label_2.setText("Drug Dose:");
			label_2.setBounds(333, 81, 76, 28);

			// Create label.
			Label label_3 = new Label(compositeDoc, SWT.NONE);
			label_3.setText("Instructions:");
			label_3.setBounds(333, 147, 87, 28);

			// Create textbox.
			drugNameBox = new Text(compositeDoc, SWT.BORDER);
			drugNameBox.setBounds(426, 52, 114, 23);

			// Create textbox.
			drugDoseBox = new Text(compositeDoc, SWT.BORDER);
			drugDoseBox.setBounds(426, 81, 56, 23);

			// Create textbox.
			drugInstructionsBox = new Text(compositeDoc, SWT.BORDER | SWT.WRAP);
			drugInstructionsBox.setBounds(426, 144, 181, 53);

			// Create label.
			Label label_4 = new Label(compositeDoc, SWT.NONE);
			label_4.setText("Current Prescriptions");
			label_4.setBounds(317, 263, 136, 23);

			// Create label.
			Label label_5 = new Label(compositeDoc, SWT.NONE);
			label_5.setText("First Name:");
			label_5.setBounds(19, 341, 76, 23);

			// Create label.
			Label label_6 = new Label(compositeDoc, SWT.NONE);
			label_6.setText("Phone:");
			label_6.setBounds(19, 399, 49, 19);

			// Create label.
			Label label_7 = new Label(compositeDoc, SWT.NONE);
			label_7.setText("Address:");
			label_7.setBounds(16, 424, 68, 23);

			// Create label.
			Label label_8 = new Label(compositeDoc, SWT.NONE);
			label_8.setText("Insurance:");
			label_8.setBounds(19, 495, 68, 18);

			// Create label.
			Label label_9 = new Label(compositeDoc, SWT.NONE);
			label_9.setText("DOB:");
			label_9.setBounds(19, 519, 49, 23);

			// Create textbox.
			firstnameText = new Text(compositeDoc, SWT.BORDER);
			firstnameText.setBounds(101, 341, 153, 23);

			// Create textbox.
			phoneText = new Text(compositeDoc, SWT.BORDER);
			phoneText.setBounds(101, 399, 153, 23);

			// Create textbox.
			addressText = new Text(compositeDoc, SWT.BORDER | SWT.WRAP);
			addressText.setBounds(101, 428, 181, 53);

			// Create textbox.
			insuranceText = new Text(compositeDoc, SWT.BORDER);
			insuranceText.setBounds(101, 492, 153, 23);

			// Create textbox.
			dobText = new Text(compositeDoc, SWT.BORDER);
			dobText.addMouseListener(new MouseListener() {

				@Override
				public void mouseDoubleClick(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseDown(MouseEvent e) {
					// TODO Auto-generated method stub
					dobText.setText("");
					dobText.setForeground(SWTResourceManager
							.getColor(SWT.COLOR_BLACK));
				}

				@Override
				public void mouseUp(MouseEvent e) {
					// TODO Auto-generated method stub

				}

			});
			dobText.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
			dobText.setText("mm/dd/yyyy");
			dobText.setBounds(101, 521, 98, 28);
			
			// Create patient list.
			patientsListBox = new List(compositeDoc, SWT.BORDER | SWT.H_SCROLL
					| SWT.V_SCROLL);
			patientsListBox.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					// List all patients.
					String name = patientsListBox.getSelection()[0];
					int index = 0;
					
					for (Patient patient : patientsList) {
						if (patient.getName().equals(name)) {
							index = patientsList.indexOf(patient);
							buttonRemovePatient.setEnabled(true);
							break;
						}
					}
					showPrescriptionList(patientsList.get(index));
				}
			});
			patientsListBox.setBounds(13, 67, 261, 219);
			patientsListBox.removeAll();
			for (Patient p : patientsList){
				patientsListBox.add(p.getName());
			}
			
			// Create label.
			Label label_10 = new Label(compositeDoc, SWT.NONE);
			label_10.setText("Last Name:");
			label_10.setBounds(19, 370, 76, 16);

			// Create textbox.
			lastnameText = new Text(compositeDoc, SWT.BORDER);
			lastnameText.setBounds(101, 370, 153, 23);

			// Create add prescription button.
			Button addPrescriptButton = new Button(compositeDoc, SWT.NONE);
			addPrescriptButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int index = patientsListBox.getSelectionIndex();
					try {
						int ref = Integer.parseInt(refills.getText());
						Patient patient = patientsList.get(index);
						// New prescription object.
						Prescription prescription = new Prescription(
								drugNameBox.getText(), drugDoseBox.getText(),
								drugInstructionsBox.getText(),patient.getName(),
								false,false,ref,User.getUsername());
						
						//Connect to database and insert new prescription.
						DatabaseConnection.createConnection();
						boolean succ = DatabaseConnection.insertPrescription(prescription);
						DatabaseConnection.shutdown();
						
						//Update prescription list.
						showPrescriptionList(patient);
						
						//If successful added to database:
						if(succ){
							// Reset text boxes.
							refills.setText("");
							drugNameBox.setText("");
							drugDoseBox.setText("");
							drugInstructionsBox.setText("");
							txtPrescriptionError.setForeground(SWTResourceManager
									.getColor(SWT.COLOR_WHITE));
						} else {
							//Set error message red.
							txtPrescriptionError.setForeground(SWTResourceManager
									.getColor(SWT.COLOR_RED));
						}
					} catch (Exception e1) {
						//Set error message red.
						txtPrescriptionError.setForeground(SWTResourceManager
								.getColor(SWT.COLOR_RED));
					}

				}
			});
			addPrescriptButton.setText("Add Prescription");
			addPrescriptButton.setBounds(426, 203, 129, 28);

			//Create prescription list box.
			prescriptionsListBox = new List(compositeDoc, SWT.BORDER);
			prescriptionsListBox.setBounds(317, 292, 308, 273);

			//Create label.
			Label lblSearch = new Label(compositeDoc, SWT.NONE);
			lblSearch.setBounds(16, 10, 56, 22);
			lblSearch.setText("Search:");

			//Create dynamic search box.
			searchText = new Text(compositeDoc, SWT.BORDER);
			searchText.addModifyListener(new ModifyListener() {
				//Check for changes to implement dynamic search.
				public void modifyText(ModifyEvent e) {
					patientsListBox.removeAll();
					if (searchText.getText().trim().length() == 0) {
						for (Patient patient : patientsList) {
							patientsListBox.add(patient.getName());
						}
					} else {
						for (Patient patient : patientsList) {
							if (patient.getName().contains(
									
									searchText.getText())) {
								patientsListBox.add(patient.getName());
							}
						}
					}
				}
			});
			searchText.setBounds(78, 9, 173, 23);

			//Create text box.
			txtAddNewPrescription = new Text(compositeDoc, SWT.NONE);
			txtAddNewPrescription.setText("Add New Prescription");
			txtAddNewPrescription.setBounds(318, 20, 164, 26);

			//Create text box.
			refills = new Text(compositeDoc, SWT.BORDER);
			refills.setBounds(426, 110, 56, 28);

			//Create label.
			Label lblRefills = new Label(compositeDoc, SWT.NONE);
			lblRefills.setText("Refills:");
			lblRefills.setBounds(371, 115, 49, 28);
			
			//Create refill button to increase the number of refills.
			Button refillButton = new Button(compositeDoc, SWT.NONE);
			refillButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int index = prescriptionsListBox.getSelectionIndex();
					Prescription current = prescriptions.get(index);
					//Update number of refills in database.
					DatabaseConnection.createConnection();
					DatabaseConnection.increaseRefills(current);
					DatabaseConnection.shutdown();
					
					showPrescriptionList(patientsList.get(patientsListBox.getSelectionIndex()));
				}
			});
			refillButton.setText("Refill");
			refillButton.setBounds(531, 259, 76, 28);

			// Create text box.
			txtPatientInformationError = new Text(compositeDoc, SWT.NONE);
			txtPatientInformationError.setFont(SWTResourceManager.getFont(
					"Segoe UI", 7, SWT.NORMAL));
			txtPatientInformationError.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_WHITE));
			txtPatientInformationError
					.setText("Patient information error: could not add patient");
			txtPatientInformationError.setForeground(SWTResourceManager
					.getColor(SWT.COLOR_WHITE));
			txtPatientInformationError.setEditable(false);
			txtPatientInformationError.setBounds(16, 555, 295, 19);

			// Create text box.
			txtPrescriptionError = new Text(compositeDoc, SWT.NONE);
			txtPrescriptionError
					.setText("Prescription error: could not add prescription");
			txtPrescriptionError.setForeground(SWTResourceManager
					.getColor(SWT.COLOR_WHITE));
			txtPrescriptionError.setFont(SWTResourceManager.getFont("Segoe UI",
					7, SWT.NORMAL));
			txtPrescriptionError.setEditable(false);
			txtPrescriptionError.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_WHITE));
			txtPrescriptionError.setBounds(317, 231, 295, 26);

			// Create text box.
			doctorName = new Text(compositeDoc, SWT.RIGHT);
			doctorName.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_WHITE));
			doctorName.setEditable(false);
			doctorName.setFont(SWTResourceManager.getFont("Segoe UI", 8,
					SWT.NORMAL));
			doctorName.setText(User.getName());
			doctorName.setBounds(504, 0, 173, 26);
			compositeDoc.setTabList(new Control[] { patientsListBox,
					firstnameText, lastnameText, phoneText, addressText,
					insuranceText, dobText, buttonAddPatient, drugNameBox,
					drugDoseBox, drugInstructionsBox, addPrescriptButton,
					prescriptionsListBox, buttonRemovePatient });

		} else {
			//Professional is a PHARMACIST.
			
			//Display pharmacist tab.
			TabItem tbtmPharm = new TabItem(tabFolder, SWT.NONE);
			tbtmPharm.setText("Pharmacy");

			//Create pharmacist tab.
			Composite composite = new Composite(tabFolder, SWT.NONE);
			tbtmPharm.setControl(composite);

			//Create prescription table.
			tablePrescriptions = new Table(composite, SWT.BORDER
					| SWT.FULL_SELECTION);
			tablePrescriptions.setBounds(10, 22, 657, 330);
			tablePrescriptions.setHeaderVisible(true);
			tablePrescriptions.setLinesVisible(true);
			//Add selection listener.
			tablePrescriptions.addSelectionListener(new SelectionListener(){
				//Add selection listener.
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					
					int id = 0;
					try{
						id = Integer.parseInt(tablePrescriptions.getSelection()[0]
								.getText())-1;
					} catch (Exception e1){}
					
					Prescription selected = prescriptions.get(id);
					DatabaseConnection.createConnection();
					Patient p = DatabaseConnection.getPatientInfo(selected.getDocusername(),selected.getPatientName());
					DatabaseConnection.shutdown();
					if(selected == null || p == null){
						
					} else {
						fillPatientAndPrescriptionInfo(p,selected);
					}
				}

				//Add selection listener.
				@Override
				public void widgetSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					int id = 0;
					try{
						id = Integer.parseInt(tablePrescriptions.getSelection()[0]
								.getText())-1;
					} catch (Exception e1){}
					
					Prescription selected = prescriptions.get(id);
					DatabaseConnection.createConnection();
					Patient p = DatabaseConnection.getPatientInfo(selected.getDocusername(),selected.getPatientName());
					DatabaseConnection.shutdown();
					if(selected == null || p == null){
						
					} else {
						fillPatientAndPrescriptionInfo(p,selected);
					}
				}
			});
			
			//Create column.
			TableColumn tblclmnId = new TableColumn(tablePrescriptions,
					SWT.NONE);
			tblclmnId.setWidth(35);
			tblclmnId.setText("ID");

			//Create column.
			TableColumn tblclmnName = new TableColumn(tablePrescriptions,
					SWT.NONE);
			tblclmnName.setWidth(254);
			tblclmnName.setText("Name");

			//Create column.
			TableColumn tblclmnDrug = new TableColumn(tablePrescriptions,
					SWT.NONE);
			tblclmnDrug.setWidth(184);
			tblclmnDrug.setText("Drug");

			//Create column.
			TableColumn tblclmnFulfilled = new TableColumn(tablePrescriptions,
					SWT.NONE);
			tblclmnFulfilled.setWidth(92);
			tblclmnFulfilled.setText("Fulfilled");

			//Create column.
			TableColumn tblclmnPickedup = new TableColumn(tablePrescriptions,
					SWT.NONE);
			tblclmnPickedup.setWidth(95);
			tblclmnPickedup.setText("PickedUp");

			//Create label.
			Label lblPrescriptionDetails = new Label(composite, SWT.NONE);
			lblPrescriptionDetails.setBounds(10, 377, 146, 28);
			lblPrescriptionDetails.setText("Prescription Details");

			//Create list.
			list = new List(composite, SWT.BORDER);
			list.setBounds(10, 409, 245, 172);
			
			//Create button to mark a prescription as fulfilled.
			Button btnMarkFulfilled = new Button(composite, SWT.NONE);
			btnMarkFulfilled.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					int id = 0;
					try{
						id = Integer.parseInt(tablePrescriptions.getSelection()[0]
								.getText())-1;
					} catch (Exception e1){}
					//Get selected prescription.
					Prescription selected = prescriptions.get(id);
					//Update database as fulfilled.
					DatabaseConnection.createConnection();
					DatabaseConnection.markFulfilled(selected);
					DatabaseConnection.shutdown();
					//Redisplay prescriptions.
					showAllPrescriptions();
				}

			});
			btnMarkFulfilled.setBounds(385, 358, 102, 28);
			btnMarkFulfilled.setText("Mark Fulfilled");

			//Create a button to mark a prescriptions as picked up.
			Button btnPickedUp = new Button(composite, SWT.NONE);
			btnPickedUp.addSelectionListener(new SelectionAdapter() {
				//Add selection listener.
				@Override
				public void widgetSelected(SelectionEvent e) {
					int id = 0;
					try{
						id = Integer.parseInt(tablePrescriptions.getSelection()[0]
								.getText())-1;
					} catch (Exception e1){}
					//Get selected prescription.
					Prescription selected = prescriptions.get(id);
					//Update database as picked up.
					DatabaseConnection.createConnection();
					DatabaseConnection.markPickedUp(selected);
					DatabaseConnection.shutdown();
					//Redisplay prescriptions.
					showAllPrescriptions();
				}
			});
			btnPickedUp.setBounds(503, 358, 115, 28);
			btnPickedUp.setText("Mark Picked Up");

			//Create a button that updates the list of patients.
			btnUpdate = new Button(composite, SWT.NONE);
			btnUpdate.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					//Retrieve all prescriptions from database.
					DatabaseConnection.createConnection();
					prescriptions = DatabaseConnection.getAllPrescriptions();
					DatabaseConnection.shutdown();
					showAllPrescriptions();
				}
			});
			btnUpdate.setBounds(268, 358, 95, 28);
			btnUpdate.setText("Update");
			
			//Set text box.
			pharmName = new Text(composite, SWT.RIGHT);
			pharmName.setText(User.getName());
			pharmName.setFont(SWTResourceManager.getFont("Segoe UI", 8,
					SWT.NORMAL));
			pharmName.setEditable(false);
			pharmName.setBackground(SWTResourceManager
					.getColor(SWT.COLOR_WHITE));
			pharmName.setBounds(504, 0, 173, 26);

			
		}

	}

	/**
	 * Fill patient/prescription box with patient information.
	 * @param p Patient
	 * @param selected Prescription
	 */
	protected void fillPatientAndPrescriptionInfo(Patient p,
			Prescription selected) {
		list.removeAll();
		String name = "Name: " + p.getName();
		list.add(name);
		String dob = "DOB: " + p.getDob();
		list.add(dob);
		String dname = "Drug: " + selected.getDrugName();
		list.add(dname);
		String dose = "Dose: " + selected.getDrugDose();
		list.add(dose);
		String ref = "Refills: " + selected.getRefill();
		list.add(ref);
		String instruct = "Instructions: " + selected.getInstructions();
		list.add(instruct);
		
	}

	/**
	 * Draw the list of prescriptions of the patient from the database.
	 * @param patient Patient
	 */
	protected void showPrescriptionList(Patient patient) {
		//Retrieve all prescriptions of a patient from the database.
		DatabaseConnection.createConnection();
		prescriptions = DatabaseConnection.getPrescriptions(User.getUsername(), patient.getName());
		DatabaseConnection.shutdown();
		
		//Clear the currently displayed prescriptions.
		prescriptionsListBox.removeAll();
		
		//Draw prescriptions.
		if (prescriptions.size() == 0) {
			prescriptionsListBox.add("No Prescriptions");
		} else {
			for (Prescription prescription : prescriptions) {
				prescriptionsListBox.add(prescription.getDrugName() + "        " + prescription.getDrugDose() 
						+ "        " + prescription.getRefill());
			}
		}
	}

	/**
	 * Display all prescriptions from the database.
	 */
	protected void showAllPrescriptions(){
		//Retrieve all prescriptions from the database.
		DatabaseConnection.createConnection();
		prescriptions = DatabaseConnection.getAllPrescriptions();
		DatabaseConnection.shutdown();
		
		//Clear the table.
		tablePrescriptions.removeAll();
		
		//Display all prescriptions.
		Integer i = 1;
		for (Prescription prescription : prescriptions) {
			
			TableItem item = new TableItem(tablePrescriptions,
					SWT.NONE);
			item.setText(new String[] {i.toString(), 
					prescription.getPatientName(),
					prescription.getDrugName(),
					"" + prescription.isFulfilled(),
					"" + prescription.isPickedUp() });
			i++;
		}
	}
	
}

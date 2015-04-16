import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
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

	// GUI Fields
	protected Shell shlFarmApplicationCecs;

	// My fields
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
	
	private static int nextID = 0;
	private Button btnUpdate;
	private Text txtAddNewPrescription;
	private Text refills;
	private Text txtPatientInformationError;
	private Text txtPrescriptionError;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Farm window = new Farm();
			window.open();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
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
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlFarmApplicationCecs = new Shell();
		shlFarmApplicationCecs.setSize(703, 671);
		shlFarmApplicationCecs.setText("Farm Application CECS 343");

		TabFolder tabFolder = new TabFolder(shlFarmApplicationCecs, SWT.NONE);
		tabFolder.setBounds(0, 0, 685, 624);

		TabItem tbtmPharmacy = new TabItem(tabFolder, SWT.NONE);
		tbtmPharmacy.setText("Doctor");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmPharmacy.setControl(composite_1);

		Label label = new Label(composite_1, SWT.NONE);
		label.setText("Patients");
		label.setBounds(16, 38, 68, 23);

		Button buttonAddPatient = new Button(composite_1, SWT.NONE);
		buttonAddPatient.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if (firstnameText.getText().length() == 0
						|| lastnameText.getText().length() == 0
						|| phoneText.getText().length() == 0
						|| addressText.getText().length() == 0
						|| insuranceText.getText().length() == 0
						|| dobText.getText().length() == 0) {
					MissingInfo missing = new MissingInfo(
							shlFarmApplicationCecs, SWT.DIALOG_TRIM);
					txtPatientInformationError.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				} else {

					Patient patient = new Patient(firstnameText.getText(),
							lastnameText.getText(), phoneText.getText(),
							addressText.getText(), dobText.getText());
					patientsListBox.add(patient.getFullName());
					patientsList.add(patient);
					txtPatientInformationError.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				}
			}
		});
		buttonAddPatient.setText("Add Patient");
		buttonAddPatient.setBounds(10, 292, 114, 28);

		buttonRemovePatient = new Button(composite_1, SWT.NONE);
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

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("Drug Name:");
		label_1.setBounds(333, 52, 87, 23);

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setText("Drug Dose:");
		label_2.setBounds(333, 81, 76, 28);

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setText("Instructions:");
		label_3.setBounds(333, 147, 87, 28);

		drugNameBox = new Text(composite_1, SWT.BORDER);
		drugNameBox.setBounds(426, 52, 114, 23);

		drugDoseBox = new Text(composite_1, SWT.BORDER);
		drugDoseBox.setBounds(426, 81, 56, 23);

		drugInstructionsBox = new Text(composite_1, SWT.BORDER | SWT.WRAP);
		drugInstructionsBox.setBounds(426, 144, 181, 53);

		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setText("Current Prescriptions");
		label_4.setBounds(317, 263, 136, 23);

		Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setText("First Name:");
		label_5.setBounds(19, 341, 76, 23);

		Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setText("Phone:");
		label_6.setBounds(19, 399, 49, 19);

		Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setText("Address:");
		label_7.setBounds(16, 424, 68, 23);

		Label label_8 = new Label(composite_1, SWT.NONE);
		label_8.setText("Insurance:");
		label_8.setBounds(19, 495, 68, 18);

		Label label_9 = new Label(composite_1, SWT.NONE);
		label_9.setText("DOB:");
		label_9.setBounds(19, 519, 49, 23);

		firstnameText = new Text(composite_1, SWT.BORDER);
		firstnameText.setBounds(101, 341, 153, 23);

		phoneText = new Text(composite_1, SWT.BORDER);
		phoneText.setBounds(101, 399, 153, 23);

		addressText = new Text(composite_1, SWT.BORDER | SWT.WRAP);
		addressText.setBounds(101, 428, 181, 53);

		insuranceText = new Text(composite_1, SWT.BORDER);
		insuranceText.setBounds(101, 492, 153, 23);

		dobText = new Text(composite_1, SWT.BORDER);
		dobText.addMouseListener(new MouseListener(){

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				dobText.setText("");
				dobText.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
			}

			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		dobText.setForeground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		dobText.setText("mm/dd/yyyy");
		dobText.setBounds(101, 521, 98, 28);

		patientsListBox = new List(composite_1, SWT.BORDER | SWT.H_SCROLL
				| SWT.V_SCROLL);
		patientsListBox.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = patientsListBox.getSelection()[0];
				int index = 0;
				for(Patient patient : patientsList) {
					if(patient.getFullName().equals(name)) {
						index = patientsList.indexOf(patient);
						buttonRemovePatient.setEnabled(true);
						break;
					}
				}
				showPrescriptionList(patientsList.get(index));
			}
		});
		patientsListBox.setBounds(13, 67, 261, 219);

		Label label_10 = new Label(composite_1, SWT.NONE);
		label_10.setText("Last Name:");
		label_10.setBounds(19, 370, 76, 16);

		lastnameText = new Text(composite_1, SWT.BORDER);
		lastnameText.setBounds(101, 370, 153, 23);

		Button addPrescriptButton = new Button(composite_1, SWT.NONE);
		addPrescriptButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = patientsListBox.getSelectionIndex();
				try{
					int ref = Integer.parseInt(refills.getText());
					Patient patient = patientsList.get(index);
					Prescription prescription = new Prescription(drugNameBox.getText(),
							drugDoseBox.getText(), drugInstructionsBox.getText(), patient, nextID++, ref);
					patient.addPrescription(prescription);
					prescriptions.add(prescription);
					showPrescriptionList(patient);
					refills.setText("");
					drugNameBox.setText("");
					drugDoseBox.setText("");
					drugInstructionsBox.setText("");
					txtPrescriptionError.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
				} catch (Exception e1){
					txtPrescriptionError.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				}
				
			}
		});
		addPrescriptButton.setText("Add Prescription");
		addPrescriptButton.setBounds(426, 203, 129, 28);

		prescriptionsListBox = new List(composite_1, SWT.BORDER);
		prescriptionsListBox.setBounds(317, 292, 308, 273);

		Label lblSearch = new Label(composite_1, SWT.NONE);
		lblSearch.setBounds(16, 10, 56, 22);
		lblSearch.setText("Search:");

		searchText = new Text(composite_1, SWT.BORDER);
		searchText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				patientsListBox.removeAll();
				if (searchText.getText().trim().length() == 0) {
					for (Patient patient : patientsList) {
						patientsListBox.add(patient.getFullName());
					}
				} else {
					for(Patient patient : patientsList) {
						if(patient.getFullName().contains(searchText.getText())) {
							patientsListBox.add(patient.getFullName());
						}
					}
				}
			}
		});
		searchText.setBounds(78, 9, 173, 23);
		
		txtAddNewPrescription = new Text(composite_1, SWT.NONE);
		txtAddNewPrescription.setText("Add New Prescription");
		txtAddNewPrescription.setBounds(318, 20, 164, 26);
		
		refills = new Text(composite_1, SWT.BORDER);
		refills.setBounds(426, 110, 56, 28);
		
		Label lblRefills = new Label(composite_1, SWT.NONE);
		lblRefills.setText("Refills:");
		lblRefills.setBounds(371, 115, 49, 28);
		
		Button refillButton = new Button(composite_1, SWT.NONE);
		refillButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		refillButton.setText("Refill");
		refillButton.setBounds(531, 259, 76, 28);
		
		txtPatientInformationError = new Text(composite_1, SWT.NONE);
		txtPatientInformationError.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		txtPatientInformationError.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtPatientInformationError.setText("Patient information error: could not add patient");
		txtPatientInformationError.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtPatientInformationError.setEditable(false);
		txtPatientInformationError.setBounds(16, 555, 295, 19);
		
		txtPrescriptionError = new Text(composite_1, SWT.NONE);
		txtPrescriptionError.setText("Prescription error: could not add prescription");
		txtPrescriptionError.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtPrescriptionError.setFont(SWTResourceManager.getFont("Segoe UI", 7, SWT.NORMAL));
		txtPrescriptionError.setEditable(false);
		txtPrescriptionError.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtPrescriptionError.setBounds(317, 231, 295, 26);
		composite_1.setTabList(new Control[]{patientsListBox, firstnameText, lastnameText, phoneText, addressText, insuranceText, dobText, buttonAddPatient, drugNameBox, drugDoseBox, drugInstructionsBox, addPrescriptButton, prescriptionsListBox, buttonRemovePatient});

		TabItem tbtmDoctor = new TabItem(tabFolder, SWT.NONE);
		tbtmDoctor.setText("Pharmacy");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmDoctor.setControl(composite);

		tablePrescriptions = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tablePrescriptions.setBounds(10, 22, 657, 330);
		tablePrescriptions.setHeaderVisible(true);
		tablePrescriptions.setLinesVisible(true);
		
		TableColumn tblclmnId = new TableColumn(tablePrescriptions, SWT.NONE);
		tblclmnId.setWidth(35);
		tblclmnId.setText("ID");

		TableColumn tblclmnName = new TableColumn(tablePrescriptions, SWT.NONE);
		tblclmnName.setWidth(254);
		tblclmnName.setText("Name");

		TableColumn tblclmnDrug = new TableColumn(tablePrescriptions, SWT.NONE);
		tblclmnDrug.setWidth(184);
		tblclmnDrug.setText("Drug");

		TableColumn tblclmnFulfilled = new TableColumn(tablePrescriptions, SWT.NONE);
		tblclmnFulfilled.setWidth(92);
		tblclmnFulfilled.setText("Fulfilled?");

		TableColumn tblclmnPickedup = new TableColumn(tablePrescriptions, SWT.NONE);
		tblclmnPickedup.setWidth(166);
		tblclmnPickedup.setText("PickedUp?");

		Label lblPrescriptionDetails = new Label(composite, SWT.NONE);
		lblPrescriptionDetails.setBounds(10, 385, 146, 28);
		lblPrescriptionDetails.setText("Prescription Details");

		List list = new List(composite, SWT.BORDER);
		list.setBounds(10, 409, 245, 172);

		Button btnMarkFulfilled = new Button(composite, SWT.NONE);
		btnMarkFulfilled.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int id = Integer.parseInt(tablePrescriptions.getSelection()[0].getText());
				Prescription selected = null;
				for(Prescription prescription : prescriptions) {
					if(Integer.parseInt(prescription.getID()) == id) {
						selected = prescription;
					}
				}
				if(selected != null) {
					selected.setFulfilled(true);
					btnUpdate.notifyListeners(SWT.Selection, new Event());
				}
				}
			
		});
		btnMarkFulfilled.setBounds(385, 358, 95, 28);
		btnMarkFulfilled.setText("Mark Fulfilled");

		Button btnPickedUp = new Button(composite, SWT.NONE);
		btnPickedUp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int id = Integer.parseInt(tablePrescriptions.getSelection()[0].getText());
				Prescription selected = null;
				for(Prescription prescription : prescriptions) {
					if(Integer.parseInt(prescription.getID()) == id) {
						selected = prescription;
					}
				}
				if(selected != null) {
					selected.setPickedUp(true);
					btnUpdate.notifyListeners(SWT.Selection, new Event());
				}
			}
		});
		btnPickedUp.setBounds(503, 358, 108, 28);
		btnPickedUp.setText("Mark Picked Up");
		
		btnUpdate = new Button(composite, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tablePrescriptions.removeAll();
				for(Prescription prescription : prescriptions) {
					TableItem item = new TableItem(tablePrescriptions, SWT.NONE);
					item.setText(new String[] {prescription.getID(), prescription.getPatientName(), prescription.getDrugName(), "" + prescription.isFulfilled(), "" + prescription.isPickedUp()});
				}
			}
		});
		btnUpdate.setBounds(268, 358, 95, 28);
		btnUpdate.setText("Update");

		// My init;
		patientsList = new ArrayList<Patient>();
		prescriptions = new ArrayList<Prescription>();

	}

	protected void showPrescriptionList(Patient patient) {
		prescriptionsListBox.removeAll();
		ArrayList<Prescription> prescriptions = patient.getPrescriptions();
		if (prescriptions.size() == 0) {
			prescriptionsListBox.add("No Prescriptions");
		} else {
			for (Prescription prescription : prescriptions) {
				prescriptionsListBox.add(prescription.getDrugName());
			}
		}
	}
	
	protected void patientInfoError(){
		txtPatientInformationError.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
	}
}

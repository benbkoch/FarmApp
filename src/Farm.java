import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
	
	private static int nextID = 0;
	private Button btnUpdate;

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
		shlFarmApplicationCecs.setSize(640, 572);
		shlFarmApplicationCecs.setText("Farm Application CECS 343");

		TabFolder tabFolder = new TabFolder(shlFarmApplicationCecs, SWT.NONE);
		tabFolder.setBounds(10, 10, 612, 525);

		TabItem tbtmPharmacy = new TabItem(tabFolder, SWT.NONE);
		tbtmPharmacy.setText("Doctor");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmPharmacy.setControl(composite_1);

		Label label = new Label(composite_1, SWT.NONE);
		label.setText("Patients");
		label.setBounds(16, 48, 49, 13);

		Button button = new Button(composite_1, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
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
					missing.open();
				} else {

					Patient patient = new Patient(firstnameText.getText(),
							lastnameText.getText(), phoneText.getText(),
							addressText.getText(), dobText.getText());
					patientsListBox.add(patient.getFullName());
					patientsList.add(patient);
				}
			}
		});
		button.setText("Add Patient");
		button.setBounds(16, 292, 114, 23);

		Button button_1 = new Button(composite_1, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = patientsListBox.getSelectionIndex();
				patientsListBox.remove(index);
				patientsList.remove(index);
			}
		});
		button_1.setText("Remove Patient");
		button_1.setBounds(128, 292, 148, 23);

		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setText("Drug Name:");
		label_1.setBounds(282, 29, 68, 13);

		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setText("Drug Dose:");
		label_2.setBounds(282, 64, 68, 13);

		Label label_3 = new Label(composite_1, SWT.NONE);
		label_3.setText("Instructions:");
		label_3.setBounds(282, 98, 68, 13);

		drugNameBox = new Text(composite_1, SWT.BORDER);
		drugNameBox.setBounds(356, 29, 76, 19);

		drugDoseBox = new Text(composite_1, SWT.BORDER);
		drugDoseBox.setBounds(356, 61, 76, 19);

		drugInstructionsBox = new Text(composite_1, SWT.BORDER);
		drugInstructionsBox.setBounds(356, 95, 76, 19);

		Label label_4 = new Label(composite_1, SWT.NONE);
		label_4.setText("Current Prescriptions");
		label_4.setBounds(282, 137, 114, 13);

		Label label_5 = new Label(composite_1, SWT.NONE);
		label_5.setText("First Name:");
		label_5.setBounds(16, 331, 68, 13);

		Label label_6 = new Label(composite_1, SWT.NONE);
		label_6.setText("Phone:");
		label_6.setBounds(16, 388, 49, 13);

		Label label_7 = new Label(composite_1, SWT.NONE);
		label_7.setText("Address:");
		label_7.setBounds(16, 407, 49, 13);

		Label label_8 = new Label(composite_1, SWT.NONE);
		label_8.setText("Insurance:");
		label_8.setBounds(16, 426, 68, 13);

		Label label_9 = new Label(composite_1, SWT.NONE);
		label_9.setText("DOB:");
		label_9.setBounds(16, 450, 49, 13);

		firstnameText = new Text(composite_1, SWT.BORDER);
		firstnameText.setBounds(91, 325, 153, 19);

		phoneText = new Text(composite_1, SWT.BORDER);
		phoneText.setBounds(91, 382, 153, 19);

		addressText = new Text(composite_1, SWT.BORDER);
		addressText.setBounds(91, 401, 153, 19);

		insuranceText = new Text(composite_1, SWT.BORDER);
		insuranceText.setBounds(91, 420, 153, 19);

		dobText = new Text(composite_1, SWT.BORDER);
		dobText.setBounds(91, 444, 153, 19);

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
						break;
					}
				}
				showPrescriptionList(patientsList.get(index));
			}
		});
		patientsListBox.setBounds(13, 67, 231, 219);

		Label label_10 = new Label(composite_1, SWT.NONE);
		label_10.setText("Last Name:");
		label_10.setBounds(16, 353, 68, 13);

		lastnameText = new Text(composite_1, SWT.BORDER);
		lastnameText.setBounds(91, 350, 153, 19);

		Button button_2 = new Button(composite_1, SWT.NONE);
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = patientsListBox.getSelectionIndex();
				Patient patient = patientsList.get(index);
				Prescription prescription = new Prescription(drugNameBox.getText(),
						drugDoseBox.getText(), drugInstructionsBox.getText(), patient, nextID++);
				patient.addPrescription(prescription);
				prescriptions.add(prescription);
				showPrescriptionList(patient);
				drugNameBox.setText("");
				drugDoseBox.setText("");
				drugInstructionsBox.setText("");
			}
		});
		button_2.setText("Add Prescription");
		button_2.setBounds(463, 29, 129, 23);

		prescriptionsListBox = new List(composite_1, SWT.BORDER);
		prescriptionsListBox.setBounds(282, 156, 308, 336);

		Label label_11 = new Label(composite_1, SWT.NONE);
		label_11.setText("Ben Koch GUI Design");
		label_11.setBounds(13, 479, 228, 13);

		Label lblSearch = new Label(composite_1, SWT.NONE);
		lblSearch.setBounds(16, 10, 49, 13);
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
		searchText.setBounds(71, 10, 173, 19);
		composite_1.setTabList(new Control[] { button, button_1, firstnameText,
				lastnameText, phoneText, addressText, insuranceText, dobText,
				drugNameBox, drugDoseBox, drugInstructionsBox, patientsListBox,
				button_2, prescriptionsListBox });

		TabItem tbtmDoctor = new TabItem(tabFolder, SWT.NONE);
		tbtmDoctor.setText("Pharmacy");

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmDoctor.setControl(composite);

		tablePrescriptions = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tablePrescriptions.setBounds(10, 22, 584, 330);
		tablePrescriptions.setHeaderVisible(true);
		tablePrescriptions.setLinesVisible(true);
		
		TableColumn tblclmnId = new TableColumn(tablePrescriptions, SWT.NONE);
		tblclmnId.setWidth(35);
		tblclmnId.setText("ID");

		TableColumn tblclmnName = new TableColumn(tablePrescriptions, SWT.NONE);
		tblclmnName.setWidth(279);
		tblclmnName.setText("Name");

		TableColumn tblclmnDrug = new TableColumn(tablePrescriptions, SWT.NONE);
		tblclmnDrug.setWidth(100);
		tblclmnDrug.setText("Drug");

		TableColumn tblclmnFulfilled = new TableColumn(tablePrescriptions, SWT.NONE);
		tblclmnFulfilled.setWidth(100);
		tblclmnFulfilled.setText("Fulfilled?");

		TableColumn tblclmnPickedup = new TableColumn(tablePrescriptions, SWT.NONE);
		tblclmnPickedup.setWidth(100);
		tblclmnPickedup.setText("PickedUp?");

		Label lblPrescriptionDetails = new Label(composite, SWT.NONE);
		lblPrescriptionDetails.setBounds(10, 359, 108, 13);
		lblPrescriptionDetails.setText("Prescription Details");

		List list = new List(composite, SWT.BORDER);
		list.setBounds(10, 378, 245, 111);

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
		btnMarkFulfilled.setBounds(400, 358, 83, 23);
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
		btnPickedUp.setBounds(489, 358, 105, 23);
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
		btnUpdate.setBounds(261, 353, 95, 28);
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
}

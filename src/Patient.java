import java.util.ArrayList;


public class Patient {
	
	private String name, phone, address, dob, doctorName;
	private ArrayList<Prescription> prescriptions;


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	

	public Patient(String n, String phone,
			String address, String dob, String docName) {
		super();
		this.name = n;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
		this.doctorName = docName;
		
		prescriptions = new ArrayList<Prescription>();
	}
	
	public void addPrescription(Prescription prescription) {
		prescriptions.add(prescription);
	}
	
	public ArrayList<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
}

import java.util.ArrayList;


public class Patient {
	
	private String firstName, lastName, phone, address, dob;
	private ArrayList<Prescription> prescriptions;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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
	
	public String getFullName() {
		return firstName + " " + lastName;
	}

	public Patient(String firstName, String lastName, String phone,
			String address, String dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
		
		prescriptions = new ArrayList<Prescription>();
	}
	
	public void addPrescription(Prescription prescription) {
		prescriptions.add(prescription);
	}
	
	public ArrayList<Prescription> getPrescriptions() {
		return prescriptions;
	}
}

import java.util.ArrayList;


public class Patient {
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
	
	private String name, phone, address, dob, doctorName;
	private ArrayList<Prescription> prescriptions;

	/**
	 * Patient constructor.
	 * @param n String Name
	 * @param phone String Phone number
	 * @param address String Address
	 * @param dob String Date of birth
	 * @param docName String Doctor name.
	 */
	public Patient(String n, String phone,
			String address, String dob, String docName) {
		super();
		this.name = n;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
		this.doctorName = docName;
	}

	/**
	 * Return patient's phone number.
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Return the patient's address.
	 * @return String
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Return the patient's date of birth. (mm/dd/yyyy)
	 * @return String
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Return the patient's name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the doctor's name.
	 * @return String
	 */
	public String getDoctorName() {
		return doctorName;
	}

}


public class Professional {
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
	private String Name, PracticeName, Username;
	private boolean Doctor;
	
	/**
	 * Professional constructor. A professional is either a doctor or 
	 * a pharmacist.
	 * @param n String Name
	 * @param p String Practice name
	 * @param d boolean Is doctor
	 * @param user String Username
	 */
	public Professional(String n, String p, boolean d, String user){
		Name = n;
		PracticeName = p;
		Doctor = d;
		Username = user;
	}

	/**
	 * Return the name of the professional.
	 * @return String
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Return the name of the practice.
	 * @return String
	 */
	public String getPracticeName() {
		return PracticeName;
	}

	/**
	 * Return the boolean of whether or not the professional is a doctor.
	 * @return boolean
	 */
	public boolean isDoctor() {
		return Doctor;
	}

	/**
	 * Return the username of the professional.
	 * @return String
	 */
	public String getUsername() {
		return Username;
	}


}

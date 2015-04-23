
public class Professional {
	private String Name, PracticeName, Username;
	private boolean Doctor;
	
	public Professional(String n, String p, boolean d, String user){
		setName(n);
		setPracticeName(p);
		setDoctor(d);
		setUsername(user);
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPracticeName() {
		return PracticeName;
	}

	public void setPracticeName(String practiceName) {
		PracticeName = practiceName;
	}

	public boolean isDoctor() {
		return Doctor;
	}

	public void setDoctor(boolean doctor) {
		Doctor = doctor;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

}

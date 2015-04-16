
public class Prescription {
	private String drugName, drugDose, instructions;
	private boolean isFulfilled, isPickedUp;
	private Patient patient;
	private int id;
	private int refill;
	
	
	public Prescription(String drugName, String drugDose, String instructions, Patient patient, int id, int ref) {
		super();
		this.drugName = drugName;
		this.drugDose = drugDose;
		this.instructions = instructions;
		refill = ref;
		isFulfilled = false;
		isPickedUp = false;
		this.patient = patient;
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}
	
	public String getPatientName() {
		return patient.getFullName();
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public boolean isFulfilled() {
		return isFulfilled;
	}

	public void setFulfilled(boolean isFulfilled) {
		this.isFulfilled = isFulfilled;
	}

	public boolean isPickedUp() {
		return isPickedUp;
	}

	public void setPickedUp(boolean isPickedUp) {
		this.isPickedUp = isPickedUp;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugDose() {
		return drugDose;
	}

	public void setDrugDose(String drugDose) {
		this.drugDose = drugDose;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getID() {
		return "" + id;
	}

	public int getRefill() {
		return refill;
	}

	public void setRefill(int refill) {
		this.refill = refill;
	}
}

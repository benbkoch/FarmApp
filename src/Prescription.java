
public class Prescription {
	private String drugName, drugDose, instructions;
	private boolean isFulfilled, isPickedUp;
	private String patientName, docusername;
	private int id;
	private int refill;
	
	
	public Prescription(String drugName, String drugDose, 
			String instructions, String name, boolean ful, 
			boolean picked, int ref, String docuser) {
		super();
		this.drugName = drugName;
		this.drugDose = drugDose;
		this.instructions = instructions;
		patientName = name;
		refill = ref;
		isFulfilled = ful;
		isPickedUp = picked;
		docusername = docuser;
		
	}
	
	public String getPatientName() {
		return patientName;
	}


	public boolean isFulfilled() {
		return isFulfilled;
	}

	public void setFulfilled(boolean f) {
		this.isFulfilled = f;
	}

	public boolean isPickedUp() {
		return isPickedUp;
	}

	public void setPickedUp(boolean i) {
		this.isPickedUp = i;
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

	public String getDocusername() {
		return docusername;
	}

	public void setDocusername(String docusername) {
		this.docusername = docusername;
	}
}


public class Prescription {
	private String drugName, drugDose, instructions;
	private boolean isFulfilled, isPickedUp;

	public Prescription(String drugName, String drugDose, String instructions) {
		super();
		this.drugName = drugName;
		this.drugDose = drugDose;
		this.instructions = instructions;
		isFulfilled = false;
		isPickedUp = false;
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
}

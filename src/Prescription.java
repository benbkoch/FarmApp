
public class Prescription {
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
	private String drugName, drugDose, instructions;
	private boolean isFulfilled, isPickedUp;
	private String patientName, docusername;
	private int id;
	private int refill;
	
	/**
	 * Prescription constructor.
	 * @param drugName String Drug name
	 * @param drugDose String Drug dose
	 * @param instructions String instructions
	 * @param name String Patient name
	 * @param ful boolean Fulfilled
	 * @param picked boolean PickedUp
	 * @param ref int Number of refills
	 * @param docuser String Doctor's username
	 */
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
	
	/**
	 * Get the patient's name.
	 * @return String Patient name
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * Check if prescription is fulfilled. 
	 * @return boolean Fulfilled
	 */
	public boolean isFulfilled() {
		return isFulfilled;
	}
	/**
	 * Set prescription as boolean.
	 */
	public void setFulfilled(boolean f) {
		this.isFulfilled = f;
	}

	/**
	 * Check if prescription is picked up. 
	 * @return boolean PickedUp
	 */
	public boolean isPickedUp() {
		return isPickedUp;
	}

	/**
	 * Set picked up as boolean.
	 * @param i boolean
	 */
	public void setPickedUp(boolean i) {
		this.isPickedUp = i;
	}

	/**
	 * Get the name of the drug.
	 * @return String Drug name
	 */
	public String getDrugName() {
		return drugName;
	}

	/**
	 * Get the drug dose.
	 * @return String Drug dose.
	 */
	public String getDrugDose() {
		return drugDose;
	}

	/**
	 * Get the instructions for the drug.
	 * @return String Instructions
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * Get the ID.
	 * @return String ID
	 */
	public String getID() {
		return "" + id;
	}

	/**
	 * Get the number of refills.
	 * @return Refills
	 */
	public int getRefill() {
		return refill;
	}

	/**
	 * Get the doctor's username.
	 * @return String Doctor's username
	 */
	public String getDocusername() {
		return docusername;
	}

}

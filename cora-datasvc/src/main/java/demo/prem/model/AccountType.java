package demo.prem.model;

public enum AccountType {
	Hospital("Hospital"),
	PrivatePractice("Private Practice"),
	AmbulatorySurgeryCenter("Ambulatory Surgery Center"),
	IndependentLaboratory("Independent Laboratory");

	private final String displayValue;
	private AccountType(String value) {
		displayValue = value;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}

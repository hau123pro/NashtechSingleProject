package utils.constant;

public enum Status {
	ACTIVE(0), INACTIVE(1);
	private final int shortCode;

	Status(int code) {
		this.shortCode = code;
	}

	public int getValue() {
		return this.shortCode;
	}
}

package utils.constant;

public enum Role {
	ADMIN(0), USER(1);
	private final int shortCode;

	Role(int code) {
		this.shortCode = code;
	}

	public int getValue() {
		return this.shortCode;
	}
}

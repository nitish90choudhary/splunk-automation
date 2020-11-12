package utility.request.enums;

public enum Operator {
	EQUAL("="),
	NOT_EQUAL("!="),
	GREATER_THAN(">");

	private String value;

	Operator(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}

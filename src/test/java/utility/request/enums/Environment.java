package utility.request.enums;

public enum Environment {
	QA("qa", "dsp_qa"), TEST("tests", "dsp_dev"), STAGING("staging", "dsp_staging"),
	// PREPROD,
	// PROD,
	DEV("dev", "dsp_dev");
	private String env;
	private String index;

	Environment(String env, String index) {
		this.env = env;
		this.index = index;
	}

	public String getEnv() {
		return this.env;
	}

	public String getIndex() {
		return this.index;
	}

}

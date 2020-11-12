package utility.request.enums;

public enum DSPApi {

	CONTACTME_V1("contactMe-v1", "/api/contactMe/v1/"),
	SEATMAP_V2("seatMap-v2", "/api/seatMap/v2/");

	private String name;
	private String uri;

	DSPApi(String name, String uri) {
		this.name = name;
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public String getUri() {
		return uri;
	}
}

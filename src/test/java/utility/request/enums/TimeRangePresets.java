package utility.request.enums;

public enum TimeRangePresets {

	//RealTime
	THIRTY_SECOND_WINDOW("30 second window"),
	One_MINUTE_WINDOW("1 minute window"),
	FIVE_MINUTE_WINDOW("5 minute window"),
	THIRTY_MINUTE_WINDOW("30 minute window"),
	ONE_HOUR_WINDOW("1 hour window"),
	ALL_TIME_REAL_TIME("All time (real-time)"),
	TODAY("Today"),
	WEEK_TO_DATE("Week to date"),
	BUSINESS_WEEK_TO_DATE("Business week to date"),
	MONTH_TO_DATE("Month to date"),
	YEAR_TO_DATE("Year to date"),
	YESTERDAY("Yesterday"),
	PREVIOUS_WEEK("Previous week"),
	PREVIOUS_BUSINESS_WEEK("Previous business week"),
	PREVIOUS_MONTH("Previous month"),
	PREVIOUS_YEAR("Previous year"),
	LAST_15_MINUTES("Last 15 minutes"),
	LAST_60_MINUTES("Last 60 minutes"),
	LAST_4_HOUR("Last 4 hours"),
	LAST_24_HOUR("Last 24 hours"),
	LAST_7_DAYS("Last 7 days"),
	LAST_30_DAYS("Last 30 days"),
	ALL_TIME("All time");

	private String name;

	TimeRangePresets(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}


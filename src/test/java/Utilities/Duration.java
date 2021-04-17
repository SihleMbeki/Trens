package Utilities;

public enum Duration {
	twelveMonths("#select_option_15");

	String data;

	private Duration(String xpath) {
		data = xpath;
	}

	public String getPastDuration() {
		return data;
	}
}

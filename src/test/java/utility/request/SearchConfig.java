package utility.request;

import java.util.ArrayList;
import java.util.List;

import utility.request.enums.DSPApi;
import utility.request.enums.Environment;
import utility.request.enums.Operator;
import utility.request.enums.TimeRangePresets;

//index="dsp_qa" | spath environment | search environment=qa | spath application | search application="contactMe-v1"| spath requesturi | search requesturi!="/api/contactMe/v1/healthCheck"
public class SearchConfig {
	private String finalQuery;
	private TimeRangePresets presets;

	private void setFinalQuery(String finalQuery) {
		this.finalQuery = finalQuery;
	}

	public String getSearchQuery() {
		return finalQuery;
	}

	public TimeRangePresets getPresets() {
		return presets;
	}

	public void setPresets(TimeRangePresets presets) {
		this.presets = presets;
	}

	public static class Builder {
		private List<String> searchTokens = new ArrayList<String>();
		private TimeRangePresets presets;
		private String finalQuery = null;

		public Builder withEnvironment(Environment env) {
			searchTokens.add("index =" + "\"" + env.getIndex() + "\"");
			searchTokens.add("spath environment");
			searchTokens.add("search environment=" + env.getEnv());
			return this;
		}

		public Builder withApplication(DSPApi api) {
			searchTokens.add("spath application");
			searchTokens.add("search application=" + "\"" + api.getName() + "\"");
			return this;
		}

		public Builder withRequestUri(String requestUri) {
			searchTokens.add("spath requesturi");
			searchTokens.add("search requesturi" + "=" + "\"" + requestUri + "\"");
			return this;
		}

		// without operator it's always equal
		public Builder withSearch(String searchKey, String searchKeyValue) {
			searchTokens.add("spath " + searchKey);
			searchTokens.add("search " + searchKey + "=" + "\"" + searchKeyValue + "\"");
			return this;
		}

		public Builder withSearch(String searchKey, Operator operator, String searchKeyValue) {
			searchTokens.add("spath " + searchKey);
			searchTokens.add("search " + searchKey + operator.toString() + "\"" + searchKeyValue + "\"");
			return this;
		}

		public Builder withTimeRangePresets(TimeRangePresets presets) {
			this.presets = presets;
			return this;
		}

		public Builder withQuery(String query) {
			this.finalQuery = query;
			return this;
		}

		public SearchConfig build() {
			SearchConfig searchConfig = new SearchConfig();
			if (finalQuery == null)
				searchConfig.setFinalQuery(String.join(" | ", searchTokens));
			else
				searchConfig.setFinalQuery(finalQuery);

			searchConfig.setPresets(presets);
			return searchConfig;
		}
	}

}

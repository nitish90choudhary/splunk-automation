package utility.utils.test;

import static utility.utils.enums.ExcelColumn.API;
import static utility.utils.enums.ExcelColumn.ENVIRONMENT;
import static utility.utils.enums.ExcelColumn.NumOfTxn;
import static utility.utils.enums.ExcelColumn.PassThrough;
import static utility.utils.enums.ExcelColumn.SCENARIO;

import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import utility.request.SearchConfig;
import utility.request.enums.DSPApi;
import utility.request.enums.Environment;
import utility.utils.ExcelUtil;

public class TestDriver extends TestBase {

	ExcelUtil excelUtil = new ExcelUtil();
	List<HashMap<String, String>> excelData;

	@Test
	public void testEntry() {
		excelData = excelUtil.getSheetData();
		if (excelData != null && excelData.size() > 0) {
			for (int row = 0; row < excelData.size(); row++) {

				HashMap<String, String> dataRow = excelData.get(row);
				//construct searchConfig
				System.out.println("Test Entry -- " + row);
				System.out.println(excelData);

				SearchConfig config = new SearchConfig.Builder()
						.withEnvironment(Environment.valueOf(dataRow.get(ENVIRONMENT)))
						.withApplication(DSPApi.valueOf(dataRow.get(API)))
						.build();
				//get search result
				List<JsonNode> results = splunkUtil.getSearchResult(config, Integer.valueOf(dataRow.get(NumOfTxn)));

				// assert based on row configuration & result records

				for (JsonNode resultNode : results) {
					//For Each test result node below conditional assertions

					//Scenario 1:
					if (dataRow.get(SCENARIO).equalsIgnoreCase("200")) {

					}
					//Scenario 2:
					if (dataRow.get(PassThrough).equalsIgnoreCase("yes")) {
						//Write your assertion
						splunkUtil.assertNodePresent(resultNode, "/");
						//or call assertion method
					}
				}
			}
		}
	}
}

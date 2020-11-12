package tests.qa;

import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import utility.request.SearchConfig;
import utility.request.enums.TimeRangePresets;
import utility.utils.test.TestBase;

public class TC01_ContactMe_QA extends TestBase {

//	@Test
//	public void contactMeBackendArrayForQAEnv() {
//		SearchConfig searchConfig = new SearchConfig.Builder()
//				.withEnvironment(Environment.QA)
//				.withApplication(DSPApi.CONTACTME_V1)
//				.withSearch("requesturi", Operator.NOT_EQUAL, "/api/contactMe/v1/healthCheck")
//				.build();
//
//		List<JsonNode> results = splunkUtil.getSearchResult(searchConfig, 3);
//
//		results.stream().forEach(node -> {
//			//write test here
//			//Test for value
//			splunkUtil.assertValue(node, "/BackendArray/DCIRequest/outboundFlightNumber", "AC839");
//
//			//Comparing two nodes in same root
//			splunkUtil.compareNode(node, "/BackendArray/DCIRequest", "/requestpayload");
//		});
//	}

	@Test
	public void contactMeBackendArrayForDevEnv() {
//		SearchConfig searchConfig = new SearchConfig.Builder()
//				.withEnvironment(Environment.DEV)
//				.withApplication(DSPApi.CONTACTME_V1)
//				.withSearch("requesturi", Operator.EQUAL, "/api/contactMe/dev")
//				.withTimeRangePresets(TimeRangePresets.ALL_TIME)
//				.build();

		SearchConfig searchConfig = new SearchConfig.Builder()
				.withQuery("host=My_test")
				.withTimeRangePresets(TimeRangePresets.ALL_TIME)
				.build();

		List<JsonNode> results = splunkUtil.getSearchResult(searchConfig, 3);

//		results.stream().forEach(node -> {
//			//write test here
//			//Test for value
//			splunkUtil.assertValue(node, "/BackendArray/DCIRequest/outboundFlightNumber", "AC839");
//
//			//Comparing two nodes in same root
//			splunkUtil.compareNode(node, "/BackendArray/DCIRequest", "/requestpayload");
//		});
	}

//	@Test
//	public void contactMeBackendArrayForProdEnv() {
//		SearchConfig searchConfig = new SearchConfig.Builder()
//				.withEnvironment(Environment.STAGING)
//				.withApplication(DSPApi.CONTACTME_V1)
//				.withSearch("requesturi", Operator.EQUAL, "/api/contactMe/dev")
//				.withSearch("requesturi", Operator.EQUAL, "/api/contactMe/dev")
//				.build();
//
//		List<JsonNode> results = splunkUtil.getSearchResult(searchConfig, 3);
//
//		results.stream().forEach(node -> {
//			//write test here
//			//Test for value
//			splunkUtil.assertValue(node, "/BackendArray/DCIRequest/outboundFlightNumber", "AC839");
//
//			//Comparing two nodes in same root
//			splunkUtil.compareNode(node, "/BackendArray/DCIRequest", "/requestpayload");
//		});
//	}

}

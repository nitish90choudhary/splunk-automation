package utility.request;

import java.io.IOException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import utility.request.enums.DSPApi;
import utility.request.enums.Environment;
import utility.request.enums.Operator;
import utility.utils.SplunkUtil;

public class Test {
	private static String jsonText = "{\"errormessage\":\"No message available\",\"severity\":\"critical\",\"requestmethod\":\"GET\",\"BackendArray\":{\"DCIResponseReason\":\"Not Found\",\"DCIResponseCode\":404,\"DCIMethod\":\"GET\",\"DCIResponse\":{\"path\":\"/apiService/v1/dci/dci\",\"error\":\"Not Found\",\"message\":\"No message available\",\"timestamp\":\"2020-09-25T12:55:49.856+0000\",\"status\":404},\"isTokenCached\":true,\"totalBackendResponseTime\":32,\"DCIRequestHeaders\":{\"authorization\":\"mnXE4eYdpnScJLZaXJokOPYMHFMB58WwBhOc40Qw1gw97ygSXearNHvUoEROkxQYocWuLYC67bUCooRh6ZExwFbFTMzx7Tk3h1H9musDFv+R3fgEf7APE2DZI2C3E7PeF4ZCzta0FbEXfwF8kc5saWkv8ieDZivACZvWnUq2fyht6M2sjGpoo8Zl4h0vZc03CegkApzopINS31J39qRJ7FyUcOYwXkEvAYxx6hh97NeR5IpDzkRy5Tu9CfDotCLUNUZ6zR1IDg59F/cvS8ireEZjXlrJ+CmG5FXBma3ygyBIQ9wEx8xh2GmPxRJrE7O2hSYzdUOQb5qwjQyMTzacTZ/O1FMf0QXO94oEBH+kYKE8rKqGmHBAFFkM758ut9NzYrC5L1VZ5HpR9LUSjn8/Q3p3Bb0RHKV/k0NpEZCC8YpHMAM+z/sz44hQbHPVRbK8zTdsVsVag2SXmoO5ON6/VwFwvF4BT1i1lLYkOrIwHqvPtgkUxCxhPWrH3Fy9fQYd+W6II6pNU3ALEw/aALqpVloBkJ92CC7xth84sBzbEwI9fU3Ss8I94WdEs3tNH0JJ3d0OfCpnyAn/T5zikx3h6W+Tvkb+LpxDlfd6dgR81nuARZY+XC86L4CP0QkzrPiTEUDTzXUFsyQ4Rjeu6MqHPhHz4UKVixTwPtaL7lGVqyM9cn9PV1RyO3MbK5mftDNEWvh1Opn6oqfncJ/YWGN1p+Z+3SK6Zwbh2YhZRXB0GDt0emUrxTyloX+ux6CMpunr3Gk6hd5Rxo0MAKEfwBingNEx8hgFxlQy7IHPc210XojNiXnzFcbnamF3Wsq+6bhlN0Vl+Rys+hA2SUjpre4EXpSjFCcA7lU2YtlEb4htb6sMslmUMs2OiPU/+zJe7Fs4341iJvPIfMp2tNU53Jjf79Hu6Fz4LYtKu3jDZMquiZpWh+He6+Hbz7nuHfHJFIG+rSXd2AraLm+4WylHB6/krOziH1JZRBWwvazBtEicwGsA7MysBfhXcrKouVAJadPrw5S3R/Qrh+aaGJajiZRnknubpqpNQMRPP6+f7fmvXiS7KcFlHYApjw0wBIufU/yDEvgUv+H+tVakbYXiXxdrGsPrHb8bw6CpNDuyOOg85VS57PqseqjKcXraqROTjD+WAMN9m9LZLYUwmGhQmlySPzdPhxLvcoM07HbhYHRaLdJNvpI4HStXXgeeFutOnm8omlY7yFb82sDlHv2j7CChMbcMr0JYnO3hIIRGuDk2v9GihicBiDRG75a0NcqSFh3WeutHdmHSHEIJeVyrAyNaaOsPb5lHelXbK65FmAIdhTwRsGgLkMZbuc4PkdKezV+GX1WOPS/qZoZSXaiNuj+zIa8JmjvN9+tB0puH8+j+pQFMUCfTlFRqxP/TV5yZCJBG7XFZHj/MTM5wBg3xuBtiWWXMGg3O9qlMLmjcfieuTPh6cVWe+sxOM3yC03AxBMDct10kEu+xnMh02HRR0qgY/trk1FW+9s8Xu0i9g4DnoX0V32icukn5KkbvYsWeARBLIsR9LAiEtXwyINCP7SJ+bPyWg5ttRY9UnwQk1/JQbdCulEx/kV8WqsEiOLEkbqQq++OT0Nl/VoY2gW5PHwRGdOV+2hrRLgM4iZkB7cguTXg=\",\"User-Agent\":\"Apache-HttpClient/4.5.3 (Java/1.8.0_261)\",\"X-Forwarded-Proto\":\"https\",\"X-API-KEY\":\"FeBpKaVY5/yLd3aO7arkChRj5LLbhyzDa3WksFbeL0XRLM3+4P32zyF7rCdNf8IK0/tfAIzZjJhPkJp3S6VRQN2558N+U24w+67sG1deLpqJrLAaw5FBzb9qu7godYt6R5ZL7rXoY5EceEsOMtMcqYJrdTpppa3nv/h4i22OjP0aAXdgn4pXZcYzIEJebkR9iy+FpJgapFlpFRWgLeGwMyyRSxHsF8qykswI5UFgl00Ou5pWkvAUPbcWLX3H9FeI47nsaVDF5PI5v4e197o9jYTiTHYzod+J1HFx/kUZD04kI2qzOdSIFgtTbQh/7aChTxPRCRO7mxH0/3KBInSYq9/1RRiW2lL/p10OLwwDCkbNZF0FEj06ub4DvAvuLytrKchmxHETsORm+6ioFCn7+jB3DOIaPRgq8F0D4quHkLI=\",\"X-Forwarded-For\":\"27.62.211.246\",\"Host\":\"apigw.stag.staralliance.com\",\"cache-control\":\"no-cache\",\"X-Forwarded-Port\":\"443\"},\"DCIRequest\":{\"outboundFlightDate\":\"2019-10-18\",\"callingApplication\":\"LivingMapAA\",\"outboundFlightNumber\":\"AC839\",\"callingAirline\":\"1N\",\"inboundFlightNumber\":\"TK1587\",\"timeZone\":\"L\",\"inboundFlightDate\":\"2019-09-18\"},\"DCIResponseTimeInMilliSecs\":32},\"callingAirline\":\"1N\",\"requesterAirline\":\"1N\",\"sourcehost\":\"27.62.211.246\",\"requesturi\":\"/api/digitalConnectionInfo/v1/dci?callingAirline=1N&callingApplication=LivingMapAA&inboundFlightDate=2019-09-18&inboundFlightNumber=TK1587&outboundFlightDate=2019-10-18&outboundFlightNumber=AC839&timeZone=L\",\"source\":\"apigee\",\"transactionID\":\"1601038549866-rrt-07a91488af1df983e-b-de-10900-10759278-2\",\"statuscode\":404,\"environment\":\"test\",\"errordescription\":\"Not Found\",\"responseTimeInMilliSecs\":46,\"application\":\"digitalConnectionInfo-v1\",\"sourceip\":\"27.62.211.246\",\"loglevel\":\"error\",\"totalBackendResponseTime\":32,\"apirevision\":\"2\",\"severityID\":3,\"loggingAsyncTimeTaken\":1,\"requestpayload\":{\"outboundFlightDate\":\"2019-10-18\",\"callingApplication\":\"LivingMapAA\",\"outboundFlightNumber\":\"AC839\",\"callingAirline\":\"1N\",\"inboundFlightNumber\":\"TK1587\",\"timeZone\":\"L\",\"inboundFlightDate\":\"2019-09-18\"}}";

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SearchConfig search = new SearchConfig.Builder()
				.withEnvironment(Environment.QA)
				.withApplication(DSPApi.CONTACTME_V1)
				.withSearch("requesturi", Operator.NOT_EQUAL, "/api/contactMe/v1/healthCheck")
				.build();

		System.out.println(search.getSearchQuery());

		SplunkUtil util = new SplunkUtil();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		util.compareNode(mapper.readTree(jsonText), "/BackendArray/DCIResponse", "/requestpayload");
	}

}

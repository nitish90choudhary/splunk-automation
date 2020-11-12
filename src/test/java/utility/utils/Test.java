package utility.utils;

import java.util.HashMap;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		ExcelUtil excelUtil = new ExcelUtil();
		List<HashMap<String,String>> data = excelUtil.getSheetData();

		System.out.println(data.get(0).get("Environment"));

	}
}

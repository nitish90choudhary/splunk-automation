package utility.utils;

import static utility.utils.enums.Properties.TEST_DATA_FILE_PATH;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	AppUtil appUtil = new AppUtil();
	Workbook workbook;
	Sheet sheet;
	DataFormatter dataFormatter;
	private String EXCEL_FILE_PATH = null;

	public ExcelUtil() {
		try {

			EXCEL_FILE_PATH = appUtil.getProperty(TEST_DATA_FILE_PATH);

			// Creating a Workbook from an Excel file (.xls or .xlsx)
			workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
			// Getting the Sheet at index zero
			sheet = workbook.getSheetAt(0);
			// Create a DataFormatter to format and get each cell's value as String
			dataFormatter = new DataFormatter();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<Row> getRows() {
		List<Row> rows = new ArrayList<>();

		sheet.forEach(row -> {
			rows.add(row);
		});

		return rows;
	}

	public List<String> getSheets() {
		List<String> sheetNames = new ArrayList<>();
		workbook.forEach(sheet -> sheetNames.add(sheet.getSheetName()));
		return sheetNames;
	}

	public List<HashMap<String, String>> getSheetData(int... sheetIndex) {
		int index = sheetIndex.length != 0 ? sheetIndex[0] : 0;
		return getSheetData(getSheets().get(index));
	}

	public List<HashMap<String, String>> getSheetData(String sheetName) {

		List<HashMap<String, String>> data = new ArrayList<>();

		for (Row row : sheet) {
			if (isTopRow(row) || isEmptyRow(row))
				continue;

			HashMap<String, String> valueMap = new HashMap<>();
			for (Cell cell : row) {
				String cellKey = getColHeaderName(cell.getColumnIndex());
				String cellValue = dataFormatter.formatCellValue(cell);
				valueMap.put(cellKey, cellValue);
				System.out.print(cellValue + "\t");
			}
			System.out.println();
			data.add(valueMap);
		}
		return data;
	}

	public int getSheetCount() {
		return workbook.getNumberOfSheets();
	}

	public boolean isSheetPresent(String name) {
		return workbook.isSheetHidden(workbook.getSheetIndex(name));
	}

	public int getSheetRows() {
		return sheet.getPhysicalNumberOfRows();
	}

	public int getSheetCols() {
		return sheet.getRow(0).getPhysicalNumberOfCells();
	}

	public String getColHeaderName(int cell) {
		return dataFormatter.formatCellValue(sheet.getRow(0).getCell(cell));
	}

	private boolean isTopRow(Row row) {
		return sheet.getTopRow() == row.getRowNum();
	}

	private boolean isEmptyRow(Row row) {
		AtomicBoolean isEmpty = new AtomicBoolean(true);

		row.forEach(cell -> {
			if (!dataFormatter.formatCellValue(row.getCell(cell.getColumnIndex())).equals(""))
				isEmpty.set(false);

		});
		return isEmpty.get();
	}

}

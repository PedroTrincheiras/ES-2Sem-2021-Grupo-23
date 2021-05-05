package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Calculate_Indicators;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.script.ScriptException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javaparser.utils.Pair;

import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.CodeSmell_Editor.CodeSmell_Editor;

public class CalculateIndicators {

	/**
	 * Open the xlsx file passed in the directory and search in the first line for
	 * the column with name equal to rulename, apply the rule to each line of the
	 * file and compare the result with the result inserted manually by the user
	 * 
	 * @param directory
	 * @param rule
	 * @param ruleName
	 * @return list of pairs <String,String>, if the rule doesnt exists in the xlsx
	 *         file return null
	 * @throws IOException
	 * @throws ScriptException
	 */
	public static List<Pair<String, String>> getIndicators(String directory, String rule, String ruleName)
			throws IOException, ScriptException {
		if (!ruleName.toLowerCase().contains("method") && !ruleName.toLowerCase().contains("class")) {
			throw new InputMismatchException();
		}

		List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();

		FileInputStream f = new FileInputStream(new File(directory));
		try (XSSFWorkbook workbook = new XSSFWorkbook(f)) {
			int column = 0;
			for (int j = 1; j < workbook.getSheet("Metrics").getRow(0).getLastCellNum(); j++) {
				if (ruleName.equals(workbook.getSheet("Metrics").getRow(0).getCell(j).toString())) {
					column = j;
				}
			}
			// return null if the rulename dont exist in the xlsx file
			if (column == 0)
				return null;
			if (ruleName.toLowerCase().contains("method")) {
				for (int i = 1; i < workbook.getSheet("Metrics").getLastRowNum(); i++) {
					String methodID = workbook.getSheet("Metrics").getRow(i).getCell(0).toString();

					int NOM_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(4).getNumericCellValue();
					int LOC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(5).getNumericCellValue();
					int WMC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(6).getNumericCellValue();
					int LOC_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(7).getNumericCellValue();
					int CYCLO_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(8).getNumericCellValue();
					// continue for the next iteration if the cell is undefined
					if (workbook.getSheet("Metrics").getRow(i).getCell(column).equals(null))
						continue;
					boolean manualValue = workbook.getSheet("Metrics").getRow(i).getCell(column).getBooleanCellValue();
					boolean autoValue = CodeSmell_Editor.codeSmellIdentifier(rule, LOC_method, CYCLO_method, LOC_class,
							NOM_class, WMC_class);
					if (manualValue && autoValue)
						list.add(new Pair<String, String>(methodID, "VP"));
					else if (!manualValue && autoValue)
						list.add(new Pair<String, String>(methodID, "FP"));
					else if (!manualValue && !autoValue)
						list.add(new Pair<String, String>(methodID, "VN"));
					else if (manualValue && !autoValue)
						list.add(new Pair<String, String>(methodID, "FN"));
				}
			} else {
				for (int i = 1; i < workbook.getSheet("Metrics").getLastRowNum(); i++) {
					String className = workbook.getSheet("Metrics").getRow(i).getCell(2).toString();
					// if the classe already exist in the list continue for the next line
					if (containFirstElement(list, className))
						continue;
					int NOM_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(4).getNumericCellValue();
					int LOC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(5).getNumericCellValue();
					int WMC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(6).getNumericCellValue();
					int LOC_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(7).getNumericCellValue();
					int CYCLO_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(8).getNumericCellValue();
					// continue for the next iteration if the cell is undefined
					if (workbook.getSheet("Metrics").getRow(i).getCell(column).equals(null))
						continue;
					boolean manualValue = workbook.getSheet("Metrics").getRow(i).getCell(column).getBooleanCellValue();
					boolean autoValue = CodeSmell_Editor.codeSmellIdentifier(rule, LOC_method, CYCLO_method, LOC_class,
							NOM_class, WMC_class);
					if (manualValue && autoValue)
						list.add(new Pair<String, String>(className, "VP"));
					else if (!manualValue && autoValue)
						list.add(new Pair<String, String>(className, "FP"));
					else if (!manualValue && !autoValue)
						list.add(new Pair<String, String>(className, "VN"));
					else if (manualValue && !autoValue)
						list.add(new Pair<String, String>(className, "FN"));
				}
			}
		}
		return list;
	}

	/**
	 * Receive a list of pairs and compare the first element of each pair with the
	 * string compare
	 * 
	 * @param list
	 * @param compare
	 * @return true if the string compare exist in the list
	 */
	public static boolean containFirstElement(List<Pair<String, String>> list, String compare) {
		for (Pair<String, String> l : list) {
			if (l.a.equals(compare))
				return true;
		}
		return false;
	}

	/**
	 * Count the presences of each indicator(VP,FP,VN,FN)
	 * 
	 * @param list
	 * @return a list of pairs where the first element of the pair is the indicator
	 *         and second is the number of presences
	 */
	public static List<Pair<String, Integer>> countIndicators(List<Pair<String, String>> list) {
		List<Pair<String, Integer>> result_list = new ArrayList<>();
		int vp = 0, fp = 0, vn = 0, fn = 0;
		for (Pair<String, String> l : list) {
			if (l.b.equals("VP")) {
				vp++;
			} else if (l.b.equals("FP")) {
				fp++;
			} else if (l.b.equals("VN")) {
				vn++;
			} else if (l.b.equals("FN")) {
				fn++;
			}

		}
		result_list.add(new Pair<String, Integer>("VP", vp));
		result_list.add(new Pair<String, Integer>("FP", fp));
		result_list.add(new Pair<String, Integer>("VN", vn));
		result_list.add(new Pair<String, Integer>("FN", fn));
		return result_list;
	}
}

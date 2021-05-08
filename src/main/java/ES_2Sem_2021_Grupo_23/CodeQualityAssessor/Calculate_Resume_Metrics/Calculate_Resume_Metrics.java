package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Calculate_Resume_Metrics;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javaparser.utils.Pair;

/**
* A class where are implemented a method to read a xlsx file and return a resume of the metrics
*/
public class Calculate_Resume_Metrics {

	/**
	 * Receives a string(directory of the xlsx file) and read cell by cell. Count
	 * the number of packages, the number of classes, the number of methods and the
	 * number of lines.
	 * 
	 * @param fromDirectory - directory of the xlsx file
	 * @return a list of pairs with a string(number of packages, the number of
	 *         classes, the number of methods or the number of lines) and a integer
	 *         with the result.
	 * @throws IOException Throws IOException if can't read the file
	 */
	public static List<Pair<String, Integer>> readXLSX(String fromDirectory) throws IOException {
		List<String> classes = new ArrayList<String>();
		List<String> countLinesClasses = new ArrayList<String>();
		List<String> packages = new ArrayList<String>();
		List<String> methods = new ArrayList<String>();
		List<Pair<String, Integer>> metricsCalculate = new ArrayList<Pair<String, Integer>>();
		int numberOfLines = 0;
		FileInputStream f = new FileInputStream(new File(fromDirectory));
		try (XSSFWorkbook workbook = new XSSFWorkbook(f)) {
			for (int i = 1; i < workbook.getSheet("Metrics").getLastRowNum(); i++) {
				for (int j = 1; j < workbook.getSheet("Metrics").getRow(i).getLastCellNum(); j++) {
					String s = workbook.getSheet("Metrics").getRow(i).getCell(j).toString();
					if (j == 1 && !packages.contains(s)) {
						packages.add(s);
					}
					if (j == 2 && !classes.contains(s)) {
						classes.add(s);
					}
					if (j == 3 && !methods.contains(s)) {
						methods.add(s);
					}
					if (j == 5 && !countLinesClasses
							.contains(workbook.getSheet("Metrics").getRow(i).getCell(2).toString())) {
						countLinesClasses.add(workbook.getSheet("Metrics").getRow(i).getCell(2).toString());
						numberOfLines += Double.parseDouble(s);
					}
				}
			}
		}
		metricsCalculate.add(new Pair<String, Integer>("Number Of Packages", packages.size()));
		metricsCalculate.add(new Pair<String, Integer>("Number Of Classes", classes.size()));
		metricsCalculate.add(new Pair<String, Integer>("Number Of Methods", methods.size()));
		metricsCalculate.add(new Pair<String, Integer>("Number Of Lines", numberOfLines));
		return metricsCalculate;
	}
}

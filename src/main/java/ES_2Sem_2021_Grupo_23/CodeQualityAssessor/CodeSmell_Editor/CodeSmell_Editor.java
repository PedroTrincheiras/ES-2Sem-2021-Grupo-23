package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.CodeSmell_Editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javaparser.utils.Pair;

import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Utils.ArrayListAnySize;

public class CodeSmell_Editor {
	
	/**
	 * 
	 * @param rule -> rule inserted by the user
	 * @param ruleName -> rule name
	 * @param fromDirectory -> directory of the xlsx file 
	 * @return list of pairs that contain a string that represents the class name or the methodID (depends on the rule name) and a boolean that
	 * represents if that class or method for that rule is a CodeSmell or not
	 * @throws IOException
	 * @throws ScriptException
	 */
	public static List<Pair<String, Boolean>> getCodeSmellsResults(String rule, String ruleName, String fromDirectory)
			throws IOException, ScriptException {
		List<Pair<String, Boolean>> list = new ArrayList<Pair<String, Boolean>>();
		
		ruleName = ruleName.toLowerCase();

		FileInputStream f = new FileInputStream(new File(fromDirectory));
		try (XSSFWorkbook workbook = new XSSFWorkbook(f)) {
			if (ruleName.contains("method")) {
				for (int i = 1; i < workbook.getSheet("Metrics").getLastRowNum(); i++) {

					String methodID = workbook.getSheet("Metrics").getRow(i).getCell(0).toString();

					int NOM_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(4).getNumericCellValue();
					int LOC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(5).getNumericCellValue();
					int WMC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(6).getNumericCellValue();
					int LOC_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(7).getNumericCellValue();
					int CYCLO_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(8).getNumericCellValue();

					list.add(new Pair<String, Boolean>(methodID,
							codeSmellIdentifier(rule, LOC_method, CYCLO_method, LOC_class, NOM_class, WMC_class)));
				}
			} else {
				for (int i = 1; i < workbook.getSheet("Metrics").getLastRowNum(); i++) {

					String className = workbook.getSheet("Metrics").getRow(i).getCell(2).toString();

					int NOM_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(4).getNumericCellValue();
					int LOC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(5).getNumericCellValue();
					int WMC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(6).getNumericCellValue();
					int LOC_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(7).getNumericCellValue();
					int CYCLO_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(8).getNumericCellValue();

					if (!list.contains(new Pair<String, Boolean>(className,
							codeSmellIdentifier(rule, LOC_method, CYCLO_method, LOC_class, NOM_class, WMC_class))))
						list.add(new Pair<String, Boolean>(className,
								codeSmellIdentifier(rule, LOC_method, CYCLO_method, LOC_class, NOM_class, WMC_class)));
				}
			}

		} catch (NumberFormatException e) {
			System.out.println(e);
		}
		return list;
	}

	/**
	 * 
	 * @param rule         -> rule inserted by the user
	 * @param LOC_method   -> value of the LOC_method
	 * @param CYCLO_method -> value of the CYCLO_method
	 * @param LOC_class    -> value of the LOC_class
	 * @param NOM_class    -> value of the NOM_class
	 * @param WMC_class    -> value of the WMC_class
	 * @return Boolean -> that identifies if the rule met the criteria that the user
	 *         specified
	 * @throws ScriptException
	 */
	public static Boolean codeSmellIdentifier(String rule, int LOC_method, int CYCLO_method, int LOC_class,
			int NOM_class, int WMC_class) throws ScriptException {

		if (!rule_Evaluator(rule)) {
			throw new IllegalArgumentException();

		} else {

			rule = rule.replaceAll("(?i)AND", "&&");
			rule = rule.replaceAll("(?i)OR", "||");
			rule = rule.toLowerCase();
			ScriptEngineManager factory = new ScriptEngineManager();
			ScriptEngine engine = factory.getEngineByName("JavaScript");
			engine.put("loc_method", LOC_method);
			engine.put("cyclo_method", CYCLO_method);
			engine.put("loc_class", LOC_class);
			engine.put("nom_class", NOM_class);
			engine.put("wmc_class", WMC_class);

			return (Boolean) engine.eval(rule);
		}
	}

	/**
	 * 
	 * @param rule -> rule inserted by the user
	 * @return Boolean -> that evaluates if the rule is a valid rule or not
	 */
	public static Boolean rule_Evaluator(String rule) {
		Pattern pattern = Pattern.compile("^[A-Za-z><=\s0-9()_&|]*$");
		return pattern.matcher(rule).matches();
	}
}

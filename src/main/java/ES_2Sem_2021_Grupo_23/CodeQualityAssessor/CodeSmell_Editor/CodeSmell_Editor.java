package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.CodeSmell_Editor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;

import groovy.lang.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javaparser.utils.Pair;

public class CodeSmell_Editor {

	/**
	 * Gives the result of the code smell for that class/methodID based on the rule
	 * that was applied
	 * 
	 * @param rule          -> Rule inserted by the user
	 * @param ruleName      -> Rule name
	 * @param fromDirectory -> Directory of the xlsx file
	 * @return List of pairs that contain a string that represents the class name or
	 *         the methodID (depends on the rule name) and a boolean that represents
	 *         if that class or method for that rule is a CodeSmell or not
	 * @throws IOException
	 * @throws ScriptException
	 */
	public static List<Pair<String, Boolean>> getCodeSmellsResults(String rule, String ruleName, String fromDirectory)
			throws IOException {
		
		if(!ruleName.toLowerCase().contains("class") && !ruleName.toLowerCase().contains("method")) 
			throw new InputMismatchException();
		
		List<Pair<String, Boolean>> list = new ArrayList<Pair<String, Boolean>>();

		FileInputStream f = new FileInputStream(new File(fromDirectory));
		try (XSSFWorkbook workbook = new XSSFWorkbook(f)) {
			if (ruleName.toLowerCase().contains("method")) {
				for (int i = 1; i < workbook.getSheet("Metrics").getLastRowNum(); i++) {
					
					String methodID = workbook.getSheet("Metrics").getRow(i).getCell(0).toString().split("\\.")[0];

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
		}
		return list;
	}

	/**
	 * Identifies if, based on a rule, there is a code smell or not
	 * 
	 * @param rule         -> rule inserted by the user
	 * @param LOC_method   -> value of the LOC_method
	 * @param CYCLO_method -> value of the CYCLO_method
	 * @param LOC_class    -> value of the LOC_class
	 * @param NOM_class    -> value of the NOM_class
	 * @param WMC_class    -> value of the WMC_class
	 * @return Boolean -> represents if there is a code smell or not
	 * @throws ScriptException
	 */
	public static Boolean codeSmellIdentifier(String rule, int LOC_method, int CYCLO_method, int LOC_class,
			int NOM_class, int WMC_class) {

		if (!rule_Evaluator(rule)) {
			throw new IllegalArgumentException();

		} else {

			rule = rule.replaceAll("(?i)AND", "&&");
			rule = rule.replaceAll("(?i)OR", "||");

			rule = rule.toLowerCase();

			Binding metrics = new Binding();
			GroovyShell shell = new GroovyShell(metrics);
			metrics.setProperty("loc_method", LOC_method);
			metrics.setProperty("cyclo_method", CYCLO_method);
			metrics.setProperty("loc_class", LOC_class);
			metrics.setProperty("nom_class", NOM_class);
			metrics.setProperty("wmc_class", WMC_class);

			return (Boolean) shell.evaluate(rule);
		}
	}

	/**
	 * Evaluates if the rule is valid or not
	 * 
	 * @param rule -> rule inserted by the user
	 * @return Boolean -> that evaluates if the rule is a valid rule or not
	 */
	public static Boolean rule_Evaluator(String rule) {
		Pattern pattern = Pattern.compile("^[A-Za-z><=\s0-9()_&|]*$");
		return pattern.matcher(rule).matches();
	}
}

package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Calculate_Indicators;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.github.javaparser.utils.Pair;

import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.CodeSmell_Editor.CodeSmell_Editor;

public class calculateindicators {
	/**
	 * 
	 * @param directory
	 * @param rule
	 * @param ruleName
	 * @return
	 * @throws IOException
	 * @throws ScriptException
	 */

	public static List<Pair<String, String>> function(String directory,String rule,String ruleName) throws IOException, ScriptException {
		List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
		ruleName = ruleName.toLowerCase();

		FileInputStream f = new FileInputStream(new File(directory));
		try (XSSFWorkbook workbook = new XSSFWorkbook(f)) {
			int column=0;
			for(int j=1;j<workbook.getSheet("Metrics").getRow(0).getLastCellNum();j++) {
				if(ruleName.equals(workbook.getSheet("Metrics").getRow(0).getCell(j).toString())){
					column=j;
				}
			}
			if(column==0)return null;
			if (ruleName.contains("method")) {
				for (int i = 1; i < workbook.getSheet("Metrics").getLastRowNum(); i++) {
					String methodID = workbook.getSheet("Metrics").getRow(i).getCell(0).toString();

					int NOM_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(4).getNumericCellValue();
					int LOC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(5).getNumericCellValue();
					int WMC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(6).getNumericCellValue();
					int LOC_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(7).getNumericCellValue();
					int CYCLO_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(8).getNumericCellValue();
					if(workbook.getSheet("Metrics").getRow(i).getCell(column).equals(null)) continue;
					boolean manualValue =  workbook.getSheet("Metrics").getRow(i).getCell(column).getBooleanCellValue();
					boolean autoValue = CodeSmell_Editor.codeSmellIdentifier(rule, LOC_method, CYCLO_method, LOC_class, NOM_class, WMC_class);
					if(manualValue && autoValue) list.add(new Pair<String,String>(methodID, "VP"));
					else if(!manualValue && autoValue) list.add(new Pair<String,String>(methodID, "FP"));
					else if(!manualValue && !autoValue) list.add(new Pair<String,String>(methodID, "VN"));
					else if(manualValue && !autoValue) list.add(new Pair<String,String>(methodID, "FN"));
				}
			}else {
				for (int i = 1; i < workbook.getSheet("Metrics").getLastRowNum(); i++) {
					String className = workbook.getSheet("Metrics").getRow(i).getCell(2).toString();
					if(containFirstElement(list, className)) continue;
					int NOM_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(4).getNumericCellValue();
					int LOC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(5).getNumericCellValue();
					int WMC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(6).getNumericCellValue();
					int LOC_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(7).getNumericCellValue();
					int CYCLO_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(8).getNumericCellValue();
					if(workbook.getSheet("Metrics").getRow(i).getCell(column).equals(null)) continue;
					boolean manualValue =  workbook.getSheet("Metrics").getRow(i).getCell(column).getBooleanCellValue();
					boolean autoValue = CodeSmell_Editor.codeSmellIdentifier(rule, LOC_method, CYCLO_method, LOC_class, NOM_class, WMC_class);
					if(manualValue && autoValue) list.add(new Pair<String,String>(className, "VP"));
					else if(!manualValue && autoValue) list.add(new Pair<String,String>(className, "FP"));
					else if(!manualValue && !autoValue) list.add(new Pair<String,String>(className, "VN"));
					else if(manualValue && !autoValue) list.add(new Pair<String,String>(className, "FN"));
				}
			}
	}
		return list;
	}
	/**
	 * 
	 * @param list
	 * @param compare
	 * @return
	 */
	public static boolean containFirstElement(List<Pair<String, String>> list, String compare) {
		for(Pair<String, String> l : list) {
			if(l.a.equals(compare)) return true;
		}
		return false;
	}

}

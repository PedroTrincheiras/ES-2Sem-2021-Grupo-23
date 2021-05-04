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

	public static List<Pair<String, String>> function(String directory,String rule,String ruleName) throws IOException, ScriptException {
		List<Pair<String, String>> list = new ArrayList<Pair<String, String>>();
		ruleName = rule.toLowerCase();

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
					boolean manualValue =  workbook.getSheet("Metrics").getRow(i).getCell(column).getBooleanCellValue();
					boolean autoValue = CodeSmell_Editor.codeSmellIdentifier(rule, LOC_method, CYCLO_method, LOC_class, NOM_class, WMC_class);
					
					
			}
		}else {
			for (int i = 1; i < workbook.getSheet("Metrics").getLastRowNum(); i++) {
				String className = workbook.getSheet("Metrics").getRow(i).getCell(2).toString();

				int NOM_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(4).getNumericCellValue();
				int LOC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(5).getNumericCellValue();
				int WMC_class = (int) workbook.getSheet("Metrics").getRow(i).getCell(6).getNumericCellValue();
				int LOC_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(7).getNumericCellValue();
				int CYCLO_method = (int) workbook.getSheet("Metrics").getRow(i).getCell(8).getNumericCellValue();
				boolean manualValue =  workbook.getSheet("Metrics").getRow(i).getCell(column).getBooleanCellValue();
				boolean autoValue = CodeSmell_Editor.codeSmellIdentifier(rule, LOC_method, CYCLO_method, LOC_class, NOM_class, WMC_class);
			}
		}
	}
		return list;
	}
	
	public static void main(String[] args) throws IOException {
		function("C:/Users/tomas/Documents/GitHub/ES-2Sem-2021-Grupo-23/jasmlFiles/jasmlFiles_metrics.xlsx","CYCLO_method<3","method");
	}

}

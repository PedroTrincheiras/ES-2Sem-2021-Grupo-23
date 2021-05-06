package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Generate_XLSX_With_Metrics;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

class Generate_XLSX_With_MetricsTest {

	@Test
	void testGenerateXLSX() {
		String folder = ("jasmlFiles\\");
		FileInputStream f;
		Boolean equal=true;
		try {
			XSSFWorkbook toTeste=Generate_XLSX_With_Metrics.generateWorkbook(Generate_XLSX_With_Metrics.loadDirectory(folder));
			f = new FileInputStream(new File("jasmlFiles\\jasmlFiles_metrics.xlsx"));
			@SuppressWarnings("resource")
			XSSFWorkbook expected=new XSSFWorkbook(f);
			for(int i=0;i<toTeste.getSheet("Metrics").getLastRowNum();i++) {
				for(int j=0;j<toTeste.getSheet("Metrics").getRow(i).getLastCellNum();j++) {
					if(!toTeste.getSheet("Metrics").getRow(i).getCell(j).toString().equals(expected.getSheet("Metrics").getRow(i).getCell(j).toString())) {
						equal=false;
					}
				}
				
			}
		} catch (FileNotFoundException e) {} catch (IOException e) {
		}
		
		
		assertTrue(equal);
		
	}
	
	
}

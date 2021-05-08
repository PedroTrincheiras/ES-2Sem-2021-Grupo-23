package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Generate_XLSX_With_Metrics;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.javatuples.Triplet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.utils.Pair;

import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics.CYCLO_method;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics.LOC_Class;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics.LOC_Method;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics.NOM_Class;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics.WMC_Class;

/**
 * @author tomas
 *
 */
public class Generate_XLSX_With_Metrics {
	/**
	 * Receive 2 directory one is the directory of the project and the other to save
	 * the file, and call the function generateXLSX
	 * 
	 * @param fromdirectory Directory to read 
	 * @param todirectory Directory to export the file
	 * @throws IOException Throws IOException if can't read or write the file 
	 */
	public static void generateXLSX(String fromdirectory, String todirectory) throws IOException {
		Map<Integer, Object[]> data = loadDirectory(fromdirectory);
		String[] foldername = fromdirectory.split(Matcher.quoteReplacement(System.getProperty("file.separator")));
		String name = foldername[foldername.length - 1];
		writeToFile(generateWorkbook(data), name, todirectory);
	}

	/**
	 * Search on directory all files .java and load the metrics and return a map
	 * with data from metrics
	 * 
	 * @param directory Directory to read
	 * @return Map to be written on the workbook
	 * @throws IOException Throws IOException if can't read the directory
	 */
	public static Map<Integer, Object[]> loadDirectory(String directory) throws IOException {
		Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
		try (Stream<Path> walk = Files.walk(Paths.get(directory))) {
			// coloca em files todos os ficheiros .java existentes no diretorio e nos seus
			// subdiretorios
			List<File> files = walk.map(x -> x.toFile()).filter(f -> f.getName().endsWith(".java"))
					.collect(Collectors.toList());

			data.put(1, new Object[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class", "WMC_class",
					"LOC_method", "CYCLO_method" });
			int i = 1;
			for (File f : files) {
				String pfile = getPackage(f);
				List<Pair<String, Integer>> linesOfClasses = LOC_Class.getLOC_Class(f);
				List<Pair<String, Integer>> numberOfMethods = NOM_Class.getNOM(f);
				List<Triplet<String, String, Integer>> methodLines = LOC_Method.getLOC_Method(f);
				List<Pair<String, Integer>> wmc = WMC_Class.getWMC(f);
				List<Triplet<String, String, Integer>> cyclosOfMethods = CYCLO_method.getCYCLO(f);
				int j = 0;
				for (Triplet<String, String, Integer> methods : cyclosOfMethods) {
					data.put(i + 1, new Object[] {
							// id,check
							i,
							// package,check
							pfile,
							// class,check
							methods.getValue0(),
							// method,check
							methodLines.get(j).getValue1(),
							// NOM_class,check
							getBValue(numberOfMethods, methods.getValue0()),
							// LOC_class,check
							linesOfClasses.get(0).b,
							// WMC_class,check
							getBValue(wmc, methods.getValue0()),
							// LOC_method,check
							methodLines.get(0).getValue2(),
							// CYCLO_method,check
							methods.getValue2() });
					i++;
					j++;
				}
			}
		}

		return data;
	}

	/**
	 * Build workbook with data from map data
	 * 
	 * @param data Data to write on XLSX 
	 * @return workbook with all the data
	 */
	public static XSSFWorkbook generateWorkbook(Map<Integer, Object[]> data) {
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Metrics");
		Set<Integer> keyset = data.keySet();
		List<Integer> l = new ArrayList<Integer>(keyset);
		Collections.sort(l);
		int rownum = 0;
		for (Integer key : l) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}
		return workbook;
	}

	/**
	 * Write the workbook ins a xlsx file and save the file in the directory with
	 * name name
	 * 
	 * @param workbook
	 * @param name
	 * @param todirectory
	 * @throws IOException
	 */
	private static void writeToFile(XSSFWorkbook workbook, String name, String todirectory) throws IOException {

		// Write the workbook in file system
		FileOutputStream out = new FileOutputStream(new File(todirectory + "\\" + name + "_metrics.xlsx"));
		workbook.write(out);
		workbook.close();
		out.close();

	}

	/**
	 * Return the package of the file, if the file dont has a package return ""
	 * 
	 * @param f
	 * @return name of the package
	 * @throws FileNotFoundException
	 */
	private static String getPackage(File f) throws FileNotFoundException {
		CompilationUnit cu = StaticJavaParser.parse(f);
		Optional<PackageDeclaration> package_name = cu.getPackageDeclaration();
		if (package_name.isEmpty())
			return "";
		return package_name.get().getNameAsString();
	}

	/**
	 * Search in the list of pairs<string, integer>a string that is equal to name
	 * and return the integer pair
	 * 
	 * @param list
	 * @param name
	 * @return Integer
	 */
	private static int getBValue(List<Pair<String, Integer>> list, String name) {
		int result = 0;
		for (Pair<String, Integer> a : list) {
			if (a.a.equals(name)) {
				result = a.b;
				break;
			}
		}
		return result;
	}

}

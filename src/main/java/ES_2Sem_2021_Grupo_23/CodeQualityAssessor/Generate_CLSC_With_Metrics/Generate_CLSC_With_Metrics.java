package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Generate_CLSC_With_Metrics;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.Pair;

import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics.LOC_Class;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics.LOC_Method;
import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics.NOM_Class;

public class Generate_CLSC_With_Metrics {
	public static void main(String[] args) throws URISyntaxException, IOException {
		String folder = ("C:/Users/tomas/PycharmProjects/ES-2Sem-2021-Grupo-23/jasmlFiles");
		Map<Integer, Object[]> data = loadDirectory(folder);
		String[] foldername = folder.split("/");
		String name = foldername[foldername.length - 1];
		writeXLSX(data, name);
	}
	public static void generateXLSX(String fromdirectory,String todirectory) throws IOException {
		String folder = ("C:/Users/tomas/PycharmProjects/ES-2Sem-2021-Grupo-23/jasmlFiles");
		Map<Integer, Object[]> data = loadDirectory(folder);
		String[] foldername = folder.split("/");
		String name = foldername[foldername.length - 1];
		writeXLSX(data, name);
	}

	private static Map<Integer, Object[]> loadDirectory(String directory) throws IOException {

		List<File> resultList = new ArrayList<File>();
		Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
		try (Stream<Path> walk = Files.walk(Paths.get(directory))) {

			List<File> files = walk.map(x -> x.toFile()).filter(f -> f.getName().endsWith(".java"))
					.collect(Collectors.toList());

			data.put(1, new Object[] { "MethodID", "package", "class", "method", "NOM_class", "LOC_class",
					"WMC_class", "is_God_class", "LOC_method", "CYCLO_method", "is_Long_method" });
			int i = 1;
			for (File f : files) {
				String pfile = getPackage(f);
				// falta por aqui todos os metodos
				List<Pair<String, Integer>> linesOfClasses = LOC_Class.getLOC_Class(f);

				List<Pair<String, Integer>> numberOfMethods = NOM_Class.getNOM(f);
				List<Pair<String, Integer>> methodLines = LOC_Method.getLOC_Method(f);
				// System.out.println(linesOfClasses);
				// System.out.println(methodLines);
				// System.out.println("next file!");
				// System.out.println(numberOfMethods);
				// System.out.println(pfile);
				for (Pair<String, Integer> methods : methodLines) {
					data.put(i+1,new Object[] { i, pfile, linesOfClasses.get(0).a, methods.a, numberOfMethods.get(0).b,linesOfClasses.get(0).b, "WMC_class", "is_God_class", methods.b, "CYCLO_method","is_Long_method" });
					i++;
				}
			}
		}
		
		return data;
	}

	private static void writeXLSX(Map<Integer, Object[]> data, String name) {
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Metrics");
		System.out.println(data.size());
		Set<Integer> keyset = data.keySet();
		List<Integer> l=new ArrayList<Integer>(keyset);
		Collections.sort(l);
		int rownum = 0;
		for (Integer key : l) {
			System.out.println(key);
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
		try {
			// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(name + "_metrics.xlsx"));
			workbook.write(out);
			workbook.close();
			out.close();
			System.out.println("howtodoinjava_demo.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String getPackage(File f) throws FileNotFoundException {
		CompilationUnit cu = StaticJavaParser.parse(f);
		Optional<PackageDeclaration> package_name = cu.getPackageDeclaration();
		if (package_name.isEmpty())
			return "";
		return package_name.get().getNameAsString();
	}

}

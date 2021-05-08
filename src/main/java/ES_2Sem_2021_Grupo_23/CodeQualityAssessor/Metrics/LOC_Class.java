package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.Pair;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

/**
* A Class with methods to calculate the metric LOC_Class
*/
public class LOC_Class {

	/**
	 * Receives a file and counts the lines of the classes in that file
	 * 
	 * @param file - to count lines in classes
	 * @return contains the name of the class and the respective number of lines of
	 *         that class
	 */
	public static List<Pair<String, Integer>> getLOC_Class(File file) {
		List<Pair<String, Integer>> classes = new ArrayList<Pair<String, Integer>>();
		try {
			CompilationUnit cu = StaticJavaParser.parse(file);
			LexicalPreservingPrinter.setup(cu);
			ClassOrInterface coi = new ClassOrInterface();
			coi.visit(cu, classes);
		} catch (FileNotFoundException e) {
			return null;
		}
		return classes;
	}

	private static class ClassOrInterface extends VoidVisitorAdapter<List<Pair<String, Integer>>> {

		@Override
		public void visit(ClassOrInterfaceDeclaration coid, List<Pair<String, Integer>> collector) {
			super.visit(coid, collector);
			collector.add(new Pair<String, Integer>(coid.getNameAsString(),
					LexicalPreservingPrinter.print(coid).split("\n").length));
		}
	}
}

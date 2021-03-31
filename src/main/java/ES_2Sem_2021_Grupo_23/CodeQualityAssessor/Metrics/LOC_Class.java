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
	

public class LOC_Class {
	
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
			collector.add(new Pair<String, Integer>(coid.getNameAsString(), LexicalPreservingPrinter.print(coid).split("\n").length));
		}
	}	
}

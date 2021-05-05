package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

public class LOC_Method {

	
	/**
	 * Receives a file and counts the methods of the classes in that file
	 * 
	 * @param file - to count methods in classes
	 * @return contains the name of the class and the number of methods of that class
	 */
	public static List<Triplet<String, String, Integer>> getLOC_Method(File file) {
		List<Triplet<String, String, Integer>> methods = new ArrayList<Triplet<String, String, Integer>>();
		try {
			CompilationUnit cu = StaticJavaParser.parse(file);
			LexicalPreservingPrinter.setup(cu);
			ClassOrInterface coi = new ClassOrInterface();
			coi.visit(cu, methods);
		} catch (FileNotFoundException e) {
			return null;
		}
		return methods;
	}
	
	private static class ClassOrInterface extends VoidVisitorAdapter<List<Triplet<String, String, Integer>>> {

		@Override
		public void visit(ClassOrInterfaceDeclaration coid, List<Triplet<String, String, Integer>> collector) {
			super.visit(coid, collector);
			List<MethodDeclaration> methods = coid.getMethods();
			List<ConstructorDeclaration> constructors = coid.getConstructors();
			for(ConstructorDeclaration cd : constructors) {
				collector.add(new Triplet<String, String, Integer>(coid.getNameAsString(),cd.getDeclarationAsString(false, false, false), LexicalPreservingPrinter.print(cd).split("\n").length));
			}
			for(MethodDeclaration md : methods) {
				String method = md.getDeclarationAsString(false, false, false);
				collector.add(new Triplet<String, String, Integer>(coid.getNameAsString(),method.substring(method.indexOf(" ") + 1 ,method.length()), LexicalPreservingPrinter.print(md).split("\n").length));
			}
		}
		
	}	
}

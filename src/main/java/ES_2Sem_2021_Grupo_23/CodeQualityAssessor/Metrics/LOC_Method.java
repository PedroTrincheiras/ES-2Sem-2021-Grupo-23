package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;
import com.github.javaparser.utils.Pair;

public class LOC_Method {

	public static List<Pair<String, Integer>> getLOC_Method(File file) {
		List<Pair<String, Integer>> methods = new ArrayList<Pair<String, Integer>>();
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
	
	private static class ClassOrInterface extends VoidVisitorAdapter<List<Pair<String, Integer>>> {

		@Override
		public void visit(ClassOrInterfaceDeclaration coid, List<Pair<String, Integer>> collector) {
			super.visit(coid, collector);
			List<MethodDeclaration> methods = coid.getMethods();
			List<ConstructorDeclaration> constructors = coid.getConstructors();
			for(ConstructorDeclaration cd : constructors) {
				collector.add(new Pair<String, Integer>(cd.getDeclarationAsString(false, false, false), LexicalPreservingPrinter.print(cd).split("\n").length));
				System.out.println(cd.getDeclarationAsString(false, false, false));
			}
			for(MethodDeclaration md : methods) {
				collector.add(new Pair<String, Integer>(md.getDeclarationAsString(false, false, false).split(" ")[1], LexicalPreservingPrinter.print(md).split("\n").length));
			}
		}
		
	}	
}

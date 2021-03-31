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
import com.github.javaparser.utils.Pair;

public class NOM_Class {
	
	public static List<Pair<String, Integer>> getNOM(File file) {
		List<Pair<String, Integer>> classes = new ArrayList<Pair<String,Integer>>();
		try {
			CompilationUnit cu = StaticJavaParser.parse(file);
			ClassOrInterface coi = new ClassOrInterface();
			coi.visit(cu, classes);
		} catch (FileNotFoundException e) {
			return null;
		}
		return classes;
	}
	
	private static int getNumberMethods(ClassOrInterfaceDeclaration coid) {
		List<MethodDeclaration> methods = coid.getMethods();
		List<ConstructorDeclaration> constructors = coid.getConstructors();
		return methods.size() + constructors.size();
	}
	
	private static class ClassOrInterface extends VoidVisitorAdapter<List<Pair<String, Integer>>> {

		@Override
		public void visit(ClassOrInterfaceDeclaration coid, List<Pair<String, Integer>> collector) {
			super.visit(coid, collector);
			collector.add(new Pair<String, Integer>(coid.getNameAsString(), getNumberMethods(coid)));
		}
	}

}

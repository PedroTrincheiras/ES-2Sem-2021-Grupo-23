package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class CYCLO_method {
	
	/**
	 * Receives a file and counts the number of if, for, forEach, while, do, and cases in methods and constructors of a class in that file
	 * 
	 * @param file - to count the number of if, for, forEach, while, do, and cases in methods and constructors
	 * @return contains the name of the class, the name of the method/constructor and the number of if, for, forEach, while, do, and cases of that method/constructor
	 */
	public static List<Triplet<String, String, Integer>> getCYCLO(File file) {
		List<Triplet<String, String, Integer>> classes = new ArrayList<Triplet<String, String, Integer>>();
		try {			
			CompilationUnit cu = StaticJavaParser.parse(file);
			ClassVisitor coi = new ClassVisitor();
			coi.visit(cu, classes);
		} catch (FileNotFoundException e) {
			return null;
		}
		return classes;
	}
	
	private static class Visitor extends VoidVisitorAdapter<List<Node>> {

		@Override
		public void visit(ForStmt fs, List<Node> collector) {
			super.visit(fs, collector);
			collector.add(fs);
		}

		@Override
		public void visit(ForEachStmt fes, List<Node> collector) {
			super.visit(fes, collector);
			collector.add(fes);
		}

		@Override
		public void visit(IfStmt is, List<Node> collector) {
			super.visit(is, collector);
			collector.add(is);
		}

		@Override
		public void visit(WhileStmt ws, List<Node> collector) {
			super.visit(ws, collector);
			collector.add(ws);
		}
		
		@Override
		public void visit(SwitchStmt ss, List<Node> collector) {
			super.visit(ss, collector);
			collector.addAll(ss.getEntries());
			
		}
		@Override
		public void visit(DoStmt ds, List<Node> collector) {
            super.visit(ds, collector);
            collector.add(ds);
        }
	}
	
	private static class ClassVisitor extends VoidVisitorAdapter<List<Triplet<String, String, Integer>>>{
		@Override
		public void visit(ClassOrInterfaceDeclaration coid, List<Triplet<String, String, Integer>> collector) {
			super.visit(coid, collector);
			List<MethodDeclaration> lmd = coid.getMethods();
			List<ConstructorDeclaration> lcd = coid.getConstructors();
			for(ConstructorDeclaration x : lcd) {
				collector.add(new Triplet<String, String, Integer>(coid.getNameAsString(),x.getDeclarationAsString(false, false, false), getConstructorSize(x)));
			}
			for(MethodDeclaration x : lmd) {
				String md = x.getDeclarationAsString(false, false, false);
				collector.add(new Triplet<String, String, Integer>(coid.getNameAsString(), md.substring(md.indexOf(" ")+1 ,md.length()), getMethodSize(x)));
			}
		}
	}
	
	/**
	 * Counts the number of if, for, forEach, while, do and cases in a constructor
	 * 
	 * @param cd - a constructor
	 * @return the number of if, for, forEach, while, do, and cases added to 1 in a constructor
	 */
	private static int getConstructorSize(ConstructorDeclaration cd) {
		List<Node> l = new ArrayList<Node>();
		Visitor v = new Visitor();
		v.visit(cd, l);
		return l.size() + 1;
	}
	
	/**
	 * Counts the number of if, for, forEach, while, do and cases in a method
	 * 
	 * @param md - a method
	 * @return the number of if, for, forEach, while, do and cases added to 1 in a method
	 */
	private static int getMethodSize(MethodDeclaration md) {
		List<Node> l = new ArrayList<Node>();
		Visitor v = new Visitor();
		v.visit(md, l);
		return l.size() + 1;
	}
}

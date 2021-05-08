package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.utils.Pair;

/**
* A Class with methods to calculate the metric WMC_Class
*/
public class WMC_Class {

	/**
	 * Receives a file and counts the number of if, for, forEach, while, and cases
	 * of the classes/interfaces in that file
	 * 
	 * @param file - to count the number of if, for, forEach, while, and cases in
	 *             classes/interfaces
	 * @return contains the name of the class and the number of if, for, forEach,
	 *         while, and cases of the class/interface
	 */
	public static List<Pair<String, Integer>> getWMC(File file) {
		List<Pair<String, Integer>> classes = new ArrayList<Pair<String, Integer>>();
		try {
			CompilationUnit cu = StaticJavaParser.parse(file);
			ClassOrInterface coi = new ClassOrInterface();
			coi.visit(cu, classes);
		} catch (FileNotFoundException e) {
			return null;
		}
		return classes;
	}

	/**
	 * Counts the number of if, for, forEach, while, and cases in a class/interface
	 * 
	 * @param coid - a class/interface
	 * @return number of if, for, forEach, while, and cases of the class/interface
	 *         (coid)
	 */
	private static int getClassWMC(ClassOrInterfaceDeclaration coid) {
		List<Node> l = new ArrayList<Node>();
		Visitor v = new Visitor();
		v.visit(coid, l);
		return l.size() + coid.getMethods().size() + coid.getConstructors().size();
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

	private static class ClassOrInterface extends VoidVisitorAdapter<List<Pair<String, Integer>>> {
		@Override
		public void visit(ClassOrInterfaceDeclaration coid, List<Pair<String, Integer>> collector) {
			super.visit(coid, collector);
			collector.add(new Pair<String, Integer>(coid.getNameAsString(), getClassWMC(coid)));
		}
	}

}

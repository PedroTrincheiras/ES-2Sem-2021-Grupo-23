package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;

class CYCLO_methodTest {
	
	@Test
	void GrammarException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\GrammerException.java");
		List<Triplet<String, String, Integer>> cyclosOfMethods = CYCLO_method.getCYCLO(file);
		List<Triplet<String, String, Integer>> expectedNumberOfLines = new ArrayList<Triplet<String, String,Integer>>();
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("GrammerException","GrammerException", 1));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("GrammerException","GrammerException", 1));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("GrammerException","GrammerException", 1));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("GrammerException","GrammerException", 1));
		assertEquals(expectedNumberOfLines, cyclosOfMethods);
	}
	
	@Test
	void ParsingException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\ParsingException.java");
		List<Triplet<String, String, Integer>> cyclosOfMethods = CYCLO_method.getCYCLO(file);
		List<Triplet<String, String, Integer>> expectedNumberOfLines = new ArrayList<Triplet<String, String,Integer>>();
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("ParsingException","ParsingException", 1));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("ParsingException","ParsingException", 1));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("ParsingException","ParsingException", 1));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("ParsingException","ParsingException", 1));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("ParsingException","ParsingException", 1));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("ParsingException","getMessage", 8));
		assertEquals(expectedNumberOfLines, cyclosOfMethods);
	}
	
	@Test
	void SourceCodeParser() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\SourceCodeParser.java");
		List<Triplet<String, String, Integer>> cyclosOfMethods = CYCLO_method.getCYCLO(file);
		List<Triplet<String, String, Integer>> expectedNumberOfLines = new ArrayList<Triplet<String, String,Integer>>();
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("SourceCodeParser","SourceCodeParser", 1));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("SourceCodeParser","SourceCodeParser", 1));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parse", 2));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("SourceCodeParser","preprocessConstantValues", 5));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseClass", 4));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseClassSignature", 10));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseFields", 3));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseField", 19));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethods", 3));
		expectedNumberOfLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethod", 6));
		assertEquals(expectedNumberOfLines, cyclosOfMethods);
	}

}

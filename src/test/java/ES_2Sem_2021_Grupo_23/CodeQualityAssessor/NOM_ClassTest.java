package ES_2Sem_2021_Grupo_23.CodeQualityAssessor;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.javaparser.utils.Pair;

import ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics.NOM_Class;

class NOM_ClassTest {

	@Test
	void GrammarException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\GrammerException.java");
		List<Pair<String, Integer>> numberOfMethods = NOM_Class.getNOM(file);
		List<Pair<String, Integer>> expectedNumberOfMethods = new ArrayList<Pair<String,Integer>>();
		expectedNumberOfMethods.add(new Pair<String, Integer>("GrammerException", 4));
		assertEquals(expectedNumberOfMethods, numberOfMethods);
	}
	
	@Test
	void SourceCodeParser() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\SourceCodeParser.java");
		List<Pair<String, Integer>> numberOfMethods = NOM_Class.getNOM(file);
		List<Pair<String, Integer>> expectedNumberOfMethods = new ArrayList<Pair<String,Integer>>();
		expectedNumberOfMethods.add(new Pair<String, Integer>("OpcodeWrapper", 2));
		expectedNumberOfMethods.add(new Pair<String, Integer>("LabeledInstructions", 1));
		expectedNumberOfMethods.add(new Pair<String, Integer>("SourceCodeParser", 29));
		assertEquals(expectedNumberOfMethods, numberOfMethods);
	}
	
	@Test
	void ParsingException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\ParsingException.java");
		List<Pair<String, Integer>> numberOfMethods = NOM_Class.getNOM(file);
		List<Pair<String, Integer>> expectedNumberOfMethods = new ArrayList<Pair<String,Integer>>();
		expectedNumberOfMethods.add(new Pair<String, Integer>("ParsingException", 6));
		assertEquals(expectedNumberOfMethods, numberOfMethods);
	}
	
	@Test
	void Undefined() {
		File file = new File("Undefined");
		List<Pair<String, Integer>> numberOfMethods = NOM_Class.getNOM(file);
		assertNull(numberOfMethods);
	}


}

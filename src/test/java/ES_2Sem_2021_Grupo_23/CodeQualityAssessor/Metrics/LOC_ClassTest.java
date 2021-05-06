package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.javaparser.utils.Pair;

class LOC_ClassTest {

	@Test
	void GrammarException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\GrammerException.java");
		List<Pair<String, Integer>> linesOfClasses = LOC_Class.getLOC_Class(file);
		List<Pair<String, Integer>> expectedNumberOfLines = new ArrayList<Pair<String, Integer>>();
		expectedNumberOfLines.add(new Pair<String, Integer>("GrammerException", 18));
		assertEquals(expectedNumberOfLines, linesOfClasses);
	}

	@Test
	void SourceCodeParser() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\SourceCodeParser.java");
		List<Pair<String, Integer>> linesOfClasses = LOC_Class.getLOC_Class(file);
		List<Pair<String, Integer>> expectedNumberOfLines = new ArrayList<Pair<String, Integer>>();
		expectedNumberOfLines.add(new Pair<String, Integer>("OpcodeWrapper", 14));
		expectedNumberOfLines.add(new Pair<String, Integer>("LabeledInstructions", 13));
		expectedNumberOfLines.add(new Pair<String, Integer>("SourceCodeParser", 1371));
		assertEquals(expectedNumberOfLines, linesOfClasses);
	}

	@Test
	void ParsingException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\ParsingException.java");
		List<Pair<String, Integer>> linesOfClasses = LOC_Class.getLOC_Class(file);
		List<Pair<String, Integer>> expectedNumberOfLines = new ArrayList<Pair<String, Integer>>();
		expectedNumberOfLines.add(new Pair<String, Integer>("ParsingException", 56));
		assertEquals(expectedNumberOfLines, linesOfClasses);
	}

	@Test
	void Undefined() {
		File file = new File("Undefined");
		List<Pair<String, Integer>> linesOfClasses = LOC_Class.getLOC_Class(file);
		assertNull(linesOfClasses);
	}

}

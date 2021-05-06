package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.javaparser.utils.Pair;

class WMC_ClassTest {

	@Test
	void GrammarException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\GrammerException.java");
		List<Pair<String, Integer>> wmc = WMC_Class.getWMC(file);
		List<Pair<String, Integer>> expectedWMC = new ArrayList<Pair<String, Integer>>();
		expectedWMC.add(new Pair<String, Integer>("GrammerException", 4));
		assertEquals(expectedWMC, wmc);
	}

	@Test
	void SourceCodeParser() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\SourceCodeParser.java");
		List<Pair<String, Integer>> wmc = WMC_Class.getWMC(file);
		List<Pair<String, Integer>> expectedWMC = new ArrayList<Pair<String, Integer>>();
		expectedWMC.add(new Pair<String, Integer>("OpcodeWrapper", 2));
		expectedWMC.add(new Pair<String, Integer>("LabeledInstructions", 1));
		expectedWMC.add(new Pair<String, Integer>("SourceCodeParser", 300));
		assertEquals(expectedWMC, wmc);
	}

	@Test
	void ParsingException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\ParsingException.java");
		List<Pair<String, Integer>> wmc = WMC_Class.getWMC(file);
		List<Pair<String, Integer>> expectedWMC = new ArrayList<Pair<String, Integer>>();
		expectedWMC.add(new Pair<String, Integer>("ParsingException", 13));
		assertEquals(expectedWMC, wmc);
	}

	@Test
	void Undefined() {
		File file = new File("Undefined");
		List<Pair<String, Integer>> wmc = WMC_Class.getWMC(file);
		assertNull(wmc);
	}

}

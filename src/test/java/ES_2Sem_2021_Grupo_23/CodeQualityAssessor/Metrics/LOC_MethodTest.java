package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.javaparser.utils.Pair;

class LOC_MethodTest {

	@Test
	void GrammarException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\GrammerException.java");
		List<Pair<String, Integer>> methodLines = LOC_Method.getLOC_Method(file);
		List<Pair<String, Integer>> expectedMethodLines = new ArrayList<Pair<String,Integer>>();
		expectedMethodLines.add(new Pair<String, Integer>("GrammerException(int, String)", 3));
		expectedMethodLines.add(new Pair<String, Integer>("GrammerException(int, int, int, String)", 3));
		expectedMethodLines.add(new Pair<String, Integer>("GrammerException(String, Exception)", 3));
		expectedMethodLines.add(new Pair<String, Integer>("GrammerException(int, int, String)", 3));
		assertEquals(expectedMethodLines, methodLines);
	}
	
	@Test
	void ParsingException() {
		File file = new File("jasmlFiles\\\\com\\\\jasml\\\\compiler\\\\ParsingException.java");
		List<Pair<String, Integer>> methodLines = LOC_Method.getLOC_Method(file);
		List<Pair<String, Integer>> expectedMethodLines = new ArrayList<Pair<String,Integer>>();
		expectedMethodLines.add(new Pair<String, Integer>("ParsingException(int, int, int, String)", 6));
		expectedMethodLines.add(new Pair<String, Integer>("ParsingException(int, int, String)", 5));
		expectedMethodLines.add(new Pair<String, Integer>("ParsingException(int, String)", 4));
		expectedMethodLines.add(new Pair<String, Integer>("ParsingException(String, Exception)", 3));
		expectedMethodLines.add(new Pair<String, Integer>("ParsingException(String)", 3));
		expectedMethodLines.add(new Pair<String, Integer>("getMessage()", 21));
		expectedMethodLines.add(new Pair<String, Integer>("forEachTest(String)", 5));
		assertEquals(expectedMethodLines, methodLines);
	}
	
	/*
	@Test
	void SourceCodeParser() {
		File file = new File("jasmlFiles\\\\com\\\\jasml\\\\compiler\\\\SourceCodeParser.java");
		List<Pair<String, Integer>> methodLines = LOC_Method.getLOC_Method(file);
		List<Pair<String, Integer>> expectedMethodLines = new ArrayList<Pair<String,Integer>>();
		expectedMethodLines.add(new Pair<String, Integer>("SourceCodeParser(File)", 3));
		expectedMethodLines.add(new Pair<String, Integer>("SourceCodeParser(String)", 3));
		expectedMethodLines.add(new Pair<String, Integer>("parse()", 9));
		expectedMethodLines.add(new Pair<String, Integer>("preprocessConstantValues()", 20));
		expectedMethodLines.add(new Pair<String, Integer>("parseClass()", 18));
		expectedMethodLines.add(new Pair<String, Integer>("parseClass()", 40));
		expectedMethodLines.add(new Pair<String, Integer>("parseFields()", 12));
		expectedMethodLines.add(new Pair<String, Integer>("parseField()", 76));
		expectedMethodLines.add(new Pair<String, Integer>("parseMethods()", 12));
		expectedMethodLines.add(new Pair<String, Integer>("parseMethod()", 33));
		assertEquals(expectedMethodLines, methodLines);
	}
	*/
	
	@Test
	void Invalid() {
		File file = new File("not\\valid\\path.java");
		List<Pair<String, Integer>> results = LOC_Method.getLOC_Method(file);
		assertNull(results);
	}
	

}

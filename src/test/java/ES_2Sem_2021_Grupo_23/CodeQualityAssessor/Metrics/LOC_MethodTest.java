package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;
import org.junit.jupiter.api.Test;

class LOC_MethodTest {
	
	@Test
	void GrammarException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\GrammerException.java");
		List<Triplet<String, String, Integer>> methodLines = LOC_Method.getLOC_Method(file);
		List<Triplet<String, String, Integer>> expectedMethodLines = new ArrayList<Triplet<String, String, Integer>>();
		expectedMethodLines.add(new Triplet<String, String, Integer>("GrammerException","GrammerException(int, String)", 3));
		expectedMethodLines.add(new Triplet<String, String, Integer>("GrammerException","GrammerException(int, int, int, String)", 3));
		expectedMethodLines.add(new Triplet<String, String, Integer>("GrammerException","GrammerException(String, Exception)", 3));
		expectedMethodLines.add(new Triplet<String, String, Integer>("GrammerException","GrammerException(int, int, String)", 3));
		assertEquals(expectedMethodLines, methodLines);
	}
	
	@Test
	void ParsingException() {
		File file = new File("jasmlFiles\\\\com\\\\jasml\\\\compiler\\\\ParsingException.java");
		List<Triplet<String, String, Integer>> methodLines = LOC_Method.getLOC_Method(file);
		List<Triplet<String, String, Integer>> expectedMethodLines = new ArrayList<Triplet<String, String, Integer>>();
		expectedMethodLines.add(new Triplet<String, String, Integer>("ParsingException","ParsingException(int, int, int, String)", 6));
		expectedMethodLines.add(new Triplet<String, String, Integer>("ParsingException","ParsingException(int, int, String)", 5));
		expectedMethodLines.add(new Triplet<String, String, Integer>("ParsingException","ParsingException(int, String)", 4));
		expectedMethodLines.add(new Triplet<String, String, Integer>("ParsingException","ParsingException(String, Exception)", 3));
		expectedMethodLines.add(new Triplet<String, String, Integer>("ParsingException","ParsingException(String)", 3));
		expectedMethodLines.add(new Triplet<String, String, Integer>("ParsingException","getMessage()", 21));
		expectedMethodLines.add(new Triplet<String, String, Integer>("ParsingException","forEachTest(String)", 5));
		assertEquals(expectedMethodLines, methodLines);
	}
	
	
	@Test
	void SourceCodeParser() {
		File file = new File("jasmlFiles\\\\com\\\\jasml\\\\compiler\\\\SourceCodeParser.java");
		List<Triplet<String, String, Integer>> methodLines = LOC_Method.getLOC_Method(file);
		List<Triplet<String, String, Integer>> expectedMethodLines = new ArrayList<Triplet<String, String, Integer>>();
		expectedMethodLines.add(new Triplet<String, String, Integer>("OpcodeWrapper","OpcodeWrapper(Attribute_Code.Opcode)", 5));
		expectedMethodLines.add(new Triplet<String, String, Integer>("OpcodeWrapper","OpcodeWrapper(int, byte, byte[][], Object)", 4));
		expectedMethodLines.add(new Triplet<String, String, Integer>("LabeledInstructions","LabeledInstructions(Attribute_Code.Opcode[], Hashtable, int)", 5));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","SourceCodeParser(File)", 3));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","SourceCodeParser(String)", 3));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parse()", 9));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","preprocessConstantValues()", 20));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseClass()", 18));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseClassSignature()", 40));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseFields()", 12));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseField()", 76));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethods()", 12));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethod()", 33));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethodAttributes(Method, ArrayList, LabeledInstructions, Attribute_Code, ArrayList)", 48));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseLineNumbers(String)", 3));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethodInstructions(Method)", 523));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","updateLabelLinks(Hashtable, ArrayList)", 58));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethodSignature(Method, ArrayList)", 59));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseLocalVariableTable(String, Hashtable)", 61));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseExceptionTable(String, Hashtable)", 66));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","getOffset(String, Hashtable, boolean)", 11));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseAttribute()", 24));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseInnerClasses()", 79));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseClassAttributes()", 19));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMaxStackOrLocals(Attribute_Code)", 23));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMajorOrMinor()", 23));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseInteger(String)", 7));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseLong(String)", 10));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseFloat(String)", 6));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","parseDouble(String)", 6));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","exception(Scanner, String)", 3));
		expectedMethodLines.add(new Triplet<String, String, Integer>("SourceCodeParser","main(String[])", 4));
		assertEquals(expectedMethodLines, methodLines);
	}
	
	@Test
	void Invalid() {
		File file = new File("not\\valid\\path.java");
		List<Triplet<String, String, Integer>> results = LOC_Method.getLOC_Method(file);
		assertNull(results);
	}
	

}

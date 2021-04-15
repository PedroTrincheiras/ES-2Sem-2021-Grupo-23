package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Metrics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNull;

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
		List<Triplet<String, String, Integer>> expectedCyclos = new ArrayList<Triplet<String, String,Integer>>();
		expectedCyclos.add(new Triplet<String, String, Integer>("GrammerException","GrammerException(int, String)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("GrammerException","GrammerException(int, int, int, String)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("GrammerException","GrammerException(String, Exception)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("GrammerException","GrammerException(int, int, String)", 1));
		assertEquals(expectedCyclos, cyclosOfMethods);
	}
	
	@Test
	void ParsingException() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\ParsingException.java");
		List<Triplet<String, String, Integer>> cyclosOfMethods = CYCLO_method.getCYCLO(file);
		List<Triplet<String, String, Integer>> expectedCyclos = new ArrayList<Triplet<String, String,Integer>>();
		expectedCyclos.add(new Triplet<String, String, Integer>("ParsingException","ParsingException(int, int, int, String)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("ParsingException","ParsingException(int, int, String)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("ParsingException","ParsingException(int, String)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("ParsingException","ParsingException(String, Exception)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("ParsingException","ParsingException(String)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("ParsingException","getMessage()", 6));
		expectedCyclos.add(new Triplet<String, String, Integer>("ParsingException","forEachTest(String)", 2));
		assertEquals(expectedCyclos, cyclosOfMethods);
	}
	
	@Test
	void SourceCodeParser() {
		File file = new File("jasmlFiles\\com\\jasml\\compiler\\SourceCodeParser.java");
		List<Triplet<String, String, Integer>> cyclosOfMethods = CYCLO_method.getCYCLO(file);
		List<Triplet<String, String, Integer>> expectedCyclos = new ArrayList<Triplet<String, String,Integer>>();
		expectedCyclos.add(new Triplet<String, String, Integer>("OpcodeWrapper","OpcodeWrapper(Attribute_Code.Opcode)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("OpcodeWrapper","OpcodeWrapper(int, byte, byte[][], Object)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("LabeledInstructions","LabeledInstructions(Attribute_Code.Opcode[], Hashtable, int)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","SourceCodeParser(File)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","SourceCodeParser(String)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parse()", 2));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","preprocessConstantValues()", 5));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseClass()", 4));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseClassSignature()", 8));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseFields()", 3));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseField()", 17));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethods()", 3));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethod()", 4));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethodAttributes(Method, ArrayList, LabeledInstructions, Attribute_Code, ArrayList)", 10));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseLineNumbers(String)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethodInstructions(Method)", 128));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","updateLabelLinks(Hashtable, ArrayList)", 26));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMethodSignature(Method, ArrayList)", 11));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseLocalVariableTable(String, Hashtable)", 12));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseExceptionTable(String, Hashtable)", 15));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","getOffset(String, Hashtable, boolean)", 3));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseAttribute()", 4));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseInnerClasses()", 17));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseClassAttributes()", 3));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMaxStackOrLocals(Attribute_Code)", 7));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseMajorOrMinor()", 4));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseInteger(String)", 2));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseLong(String)", 3));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseFloat(String)", 2));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","parseDouble(String)", 2));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","exception(Scanner, String)", 1));
		expectedCyclos.add(new Triplet<String, String, Integer>("SourceCodeParser","main(String[])", 1));
		assertEquals(expectedCyclos, cyclosOfMethods);
	}
	
	@Test
	void Undefined() {
		File file = new File("Undefined");
		List<Triplet<String, String, Integer>> expectedCyclos = CYCLO_method.getCYCLO(file);
		assertNull(expectedCyclos);
	}

}

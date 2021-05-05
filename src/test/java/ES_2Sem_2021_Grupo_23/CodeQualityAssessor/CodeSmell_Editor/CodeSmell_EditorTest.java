package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.CodeSmell_Editor;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import org.junit.jupiter.api.Test;

import com.github.javaparser.utils.Pair;

import groovy.lang.MissingPropertyException;

class CodeSmell_EditorTest {

	@Test
	void codeSmellIdentifier() {
		Boolean b = CodeSmell_Editor.codeSmellIdentifier("CYCLO_method < 5", 0, 0, 0, 0, 0);
		assertEquals(b, true);
	}
	
	@Test
	void checkValidRule() {
		assertThrows(IllegalArgumentException.class,
				() -> CodeSmell_Editor.getCodeSmellsResults("$$$", "CLASS", "jasmlFiles/jasmlFiles_metrics.xlsx"));
	}

	@Test
	void checkRule() {
		assertThrows(MissingPropertyException.class, () -> CodeSmell_Editor.getCodeSmellsResults("sadasdasdasd",
				"CLASS", "jasmlFiles/jasmlFiles_metrics.xlsx"));
	}

	@Test
	void getCodeSmellsResultsForClass() throws ScriptException, IOException {
		List<Pair<String, Boolean>> list = CodeSmell_Editor
				.getCodeSmellsResults("WMC_class > 50 Or NOM_class > 10", "CLASS", "jasmlFiles/jasmlFiles_metrics.xlsx")
				.subList(0, 10);
		List<Pair<String, Boolean>> expectedList = new ArrayList<Pair<String, Boolean>>();
		expectedList.add(new Pair<String, Boolean>("Attribute", false));
		expectedList.add(new Pair<String, Boolean>("ExceptionTableItem", false));
		expectedList.add(new Pair<String, Boolean>("Opcode", false));
		expectedList.add(new Pair<String, Boolean>("Attribute_Code", false));
		expectedList.add(new Pair<String, Boolean>("Attribute_ConstantValue", false));
		expectedList.add(new Pair<String, Boolean>("Attribute_Deprecated", false));
		expectedList.add(new Pair<String, Boolean>("Attribute_Exceptions", false));
		expectedList.add(new Pair<String, Boolean>("InnerClass", false));
		expectedList.add(new Pair<String, Boolean>("Attribute_InnerClasses", false));
		expectedList.add(new Pair<String, Boolean>("LineNumber", false));
		assertEquals(list.subList(0, 10), expectedList);
	}

	@Test
	void getCodeSmellsResultsForMethod() throws ScriptException, IOException {
		List<Pair<String, Boolean>> list = CodeSmell_Editor.getCodeSmellsResults("WMC_class > 50 Or NOM_class > 10",
				"Method", "jasmlFiles/jasmlFiles_metrics.xlsx").subList(0, 10);
		List<Pair<String, Boolean>> expectedList = new ArrayList<Pair<String, Boolean>>();
		expectedList.add(new Pair<String, Boolean>("1", false));
		expectedList.add(new Pair<String, Boolean>("2", false));
		expectedList.add(new Pair<String, Boolean>("3", false));
		expectedList.add(new Pair<String, Boolean>("4", false));
		expectedList.add(new Pair<String, Boolean>("5", false));
		expectedList.add(new Pair<String, Boolean>("6", false));
		expectedList.add(new Pair<String, Boolean>("7", false));
		expectedList.add(new Pair<String, Boolean>("8", false));
		expectedList.add(new Pair<String, Boolean>("9", false));
		expectedList.add(new Pair<String, Boolean>("10", false));
		assertEquals(list, expectedList);
	}

	@Test
	void getCodeSmellsResultsRuleNameError() throws IOException {
		assertThrows(InputMismatchException.class,
				() -> CodeSmell_Editor.getCodeSmellsResults("WMC_class> 50 OR NOM_class> 10", "test",
						"jasmlFiles/jasmlFiles_metrics_with_indicators.xlsx"));
	}
}

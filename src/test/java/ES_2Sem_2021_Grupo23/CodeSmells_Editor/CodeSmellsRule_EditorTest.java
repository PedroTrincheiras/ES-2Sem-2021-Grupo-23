package ES_2Sem_2021_Grupo23.CodeSmells_Editor;

import static org.junit.jupiter.api.Assertions.*;

import javax.script.ScriptException;

import org.junit.jupiter.api.Test;

class CodeSmellsRule_EditorTest {

	@Test
	void simpleTest() throws ScriptException {
		String compare= CodeSmellsRule_Editor.rule_Evaluator("CYCLO_method < 5", 0, 0,0,0,0);
		assertEquals(compare,"True");
	}
	
	@Test
	void complexTest() throws ScriptException {
		String compare= CodeSmellsRule_Editor.rule_Evaluator("(CYCLO_method < 5) AND (WMC_class >10)", 0, 0,0,0,20);
		assertEquals(compare,"True");
	}

}

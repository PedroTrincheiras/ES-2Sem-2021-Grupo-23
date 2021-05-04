package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.CodeSmell_Editor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.script.ScriptException;

import org.junit.jupiter.api.Test;

import com.github.javaparser.utils.Pair;

class CodeSmell_EditorTest {

	@Test
	void codeSmellIdentifier() throws ScriptException {
		Boolean b = CodeSmell_Editor.codeSmellIdentifier("CYCLO_method < 5", 0, 0, 0, 0, 0);
		assertEquals(b, true);
	}
	
	/*@Test
	void getCodeSmellsResults() throws ScriptException {
		List<Pair<String, Boolean>> list = CodeSmell_Editor.getCodeSmellsResults("WMC_class > 50 OU NOM_class > 10", "CLASS", "C:/Users/Portátil TMAG/Desktop/ES Project/ES-2Sem-2021-Grupo-23/jasmlFiles/jasmlFiles_metrics.xlsx" );
		assertEquals(list, true);
	}*/

}

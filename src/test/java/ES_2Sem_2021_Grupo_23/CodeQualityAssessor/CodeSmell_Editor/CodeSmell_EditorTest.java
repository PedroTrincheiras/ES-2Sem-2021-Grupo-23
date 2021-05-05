package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.CodeSmell_Editor;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import org.junit.jupiter.api.Test;

import com.github.javaparser.utils.Pair;

class CodeSmell_EditorTest {

	@Test
	void codeSmellIdentifier() {
		Boolean b = CodeSmell_Editor.codeSmellIdentifier("CYCLO_method < 5", 0, 0, 0, 0, 0);
		assertEquals(b, true);
	}
	
	@Test
	void getCodeSmellsResults() throws IOException {
		List<Pair<String, Boolean>> list = CodeSmell_Editor.getCodeSmellsResults("WMC_class > 50 Or NOM_class > 10", "CLASS", "C:/Users/Portátil TMAG/Desktop/ES Project/ES-2Sem-2021-Grupo-23/jasmlFiles/jasmlFiles_metrics.xlsx").subList(0, 10);
		List<Pair<String, Boolean>> expectedList = new ArrayList<Pair<String,Boolean>>();
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
		assertEquals(list, expectedList);
	}

}

package ES_2Sem_2021_Grupo23.CodeSmells_Editor;

import static org.junit.jupiter.api.Assertions.*;

import javax.script.ScriptException;

import org.junit.jupiter.api.Test;

class CodeSmellsRule_EditorTest {

    @Test
    void test() throws ScriptException {
        Boolean compare = CodeSmellsRule_Editor.codeSmellIdentifier("CYCLO_method < 5", 0, 0,0,0,0);
        assertEquals(compare,"True");
    }

}
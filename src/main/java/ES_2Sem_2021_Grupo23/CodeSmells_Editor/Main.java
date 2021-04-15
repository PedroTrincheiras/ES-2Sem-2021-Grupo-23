package ES_2Sem_2021_Grupo23.CodeSmells_Editor;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Main {

	public String rule_Evaluator(String rule, int LOC_method, int CYCLO_method, int LOC_class, int NOM_class,
			int WMC_class) throws ScriptException {
		
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		engine.put("LOC_method", LOC_method);
		engine.put("CYCLO_method", CYCLO_method);
		engine.put("LOC_class", LOC_class);
		engine.put("NOM_class", NOM_class);
		engine.put("WMC_class", WMC_class);

		if ((Boolean) engine.eval(rule)) {
			return "True";
		} else
			return "False";
	}

}

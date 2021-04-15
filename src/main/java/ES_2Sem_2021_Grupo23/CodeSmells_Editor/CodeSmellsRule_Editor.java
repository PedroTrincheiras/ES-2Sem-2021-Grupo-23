package ES_2Sem_2021_Grupo23.CodeSmells_Editor;

import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CodeSmellsRule_Editor {

	public static String rule_Evaluator(String rule, int LOC_method, int CYCLO_method, int LOC_class, int NOM_class,
			int WMC_class) throws ScriptException {
		
		Pattern pattern = Pattern.compile("^[A-Za-z><=\s0-9()_&|]*$");

		if (!pattern.matcher(rule).matches()) {
			return "Invalid Expression!";
		} else {
			
			rule = rule.replaceAll("(?i)AND", "&&");
			rule = rule.replaceAll("(?i)OR", "||");
			
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

}

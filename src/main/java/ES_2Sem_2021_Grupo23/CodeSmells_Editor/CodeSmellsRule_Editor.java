package ES_2Sem_2021_Grupo23.CodeSmells_Editor;

import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CodeSmellsRule_Editor {

	/**
	 * 
	 * @param rule -> rule inserted by the user
	 * @param LOC_method -> value of the LOC_method
	 * @param CYCLO_method -> value of the CYCLO_method
	 * @param LOC_class -> value of the LOC_class
	 * @param NOM_class -> value of the NOM_class
	 * @param WMC_class -> value of the WMC_class
	 * @return Boolean -> that identifies if the rule met the criteria that the user specified
	 * @throws ScriptException
	 */
	public static Boolean codeSmellIdentifier(String rule, int LOC_method, int CYCLO_method, int LOC_class,
			int NOM_class, int WMC_class) throws ScriptException {

		if (rule_Evaluator(rule)) {
			throw new IllegalArgumentException();

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

			return (Boolean) engine.eval(rule);
		}
	}

	
	/**
	 * 
	 * @param rule -> rule inserted by the user
	 * @return Boolean -> that evaluates if the rule is a valid rule or not
	 */
	public static Boolean rule_Evaluator(String rule) {
		Pattern pattern = Pattern.compile("^[A-Za-z><=\s0-9()_&|]*$");
		return pattern.matcher(rule).matches();
	}
}

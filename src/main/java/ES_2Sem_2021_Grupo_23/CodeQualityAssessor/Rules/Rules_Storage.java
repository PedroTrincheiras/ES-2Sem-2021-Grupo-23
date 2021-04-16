package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Rules;

import java.io.File;
import java.util.*;

public class Rules_Storage {

	private HashMap<String, String> rules;
	
	public Rules_Storage() {
		rules = new HashMap<String, String>();
	}
	
	public Rules_Storage(File file) {
		// TODO 
	}
	
	public List<String> getRulesNames() {
		List<String> out = new ArrayList<String>();
		for (String ruleName : this.rules.keySet()) {
			out.add(ruleName);
		}
		return out;
	}
	
	public String getRule(String ruleName) {
		return this.rules.get(ruleName);
	}
	
	private void changeRuleName(String oldRuleName, String newRuleName) {
		this.rules.put(newRuleName, this.rules.get(oldRuleName));
		this.rules.remove(oldRuleName);
	}
	
	private void changeRule(String ruleName, String newRule) {
		this.rules.put(ruleName, newRule);
	}
	
	
	
}

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
	
	public Set<String> getRulesNames() {
		return this.rules.keySet();
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

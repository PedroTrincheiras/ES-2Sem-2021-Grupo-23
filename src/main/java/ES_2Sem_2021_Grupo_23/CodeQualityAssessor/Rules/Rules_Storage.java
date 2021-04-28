package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Rules;

import java.io.File;
import java.util.*;

public class Rules_Storage {

	private HashMap<String, String> rules;
	
	/**
	 * Create a new Rule_Storage without any parameter
	 */
	public Rules_Storage() {
		rules = new HashMap<String, String>();
	}
	
	/**
	 * Create a new Rule Storage
	 * @param file - to create a new Rule_Storage with the existing rules in the file
	 */
	public Rules_Storage(File file) {
		// TODO 
	}
	
	/**
	 * Give all rule names
	 * 
	 * @return all the names of the existing rules in the Rule_Storage
	 */
	public Set<String> getRulesNames() {
		return this.rules.keySet();
	}
	
	/**
	 * Receives a rule name and return the corresponding rule
	 * 
	 * @param String - to search the rule in the Rule_Storage
	 * @return the respective rule
	 */
	public String getRule(String ruleName) {
		return this.rules.get(ruleName);
	}
	
	/**
	 * Add a new rule to the Rule_Storage if it doesn't already exists
	 * 
	 * @param String - to check if the rule already exists and if not to use as hashmap key
	 * @param String - rule content
	 * @return if the rule already exits return false, else return true
	 */
	public boolean addRule(String ruleName, String rule) {
		for (String name : this.rules.keySet()) {
			if(name.equals(ruleName)) {
				return false;
			}
		}
		this.rules.put(ruleName, rule);
		return true;
	}
	
	/**
	 * Change a rule name that already exists
	 * 
	 * @param String - to get the rule
	 * @param String - to add as the new key
	 */
	public void changeRuleName(String oldRuleName, String newRuleName) {
		this.rules.put(newRuleName, this.rules.get(oldRuleName));
		this.rules.remove(oldRuleName);
	}
	
	/**
	 * Change a rule that already exists
	 * 
	 * @param String - to get the rule
	 * @param String - to update as the new rule
	 */
	public void changeRule(String ruleName, String newRule) {
		this.rules.put(ruleName, newRule);
	}
	
	
	
}

package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Rules;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;


/**
* Rules_Storage is an object with the purpose of keeping and manipulating the rules
*/
public class Rules_Storage {

	private HashMap<String, String> rules;

	/**
	 * Try to load the rules binary object stored in rules.dat file if can't load
	 * create a new Rule_Storage
	 */
	public Rules_Storage() {
		try {
			rules = loadDatabase();
		} catch (Exception e) {
			rules = new HashMap<String, String>();
		}
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
	 * @param ruleName to search the rule in the Rule_Storage
	 * @return the respective rule
	 */
	public String getRule(String ruleName) {
		return this.rules.get(ruleName);
	}

	/**
	 * Receives a rule name and removes it from the database
	 * 
	 * @param ruleName to search the rule in the Rule_Storage
	 */
	public void removeRule(String ruleName) {
		this.rules.remove(ruleName);
	}
	
	/**
	 * Add a new rule to the Rule_Storage if it doesn't already exists
	 * 
	 * @param ruleName to check if the rule already exists and if not to use as
	 *               hashmap key
	 * @param rule content of the rule
	 * @return if the rule already exits return false, else return true
	 */
	public boolean addRule(String ruleName, String rule) {
		for (String name : this.rules.keySet()) {
			if (name.equals(ruleName)) {
				return false;
			}
		}
		this.rules.put(ruleName, rule);
		return true;
	}

	/**
	 * Change a rule name that already exists
	 * 
	 * @param oldRuleName to get the rule to change
	 * @param newRuleName to add as the new name for the rule
	 */
	public void changeRuleName(String oldRuleName, String newRuleName) {
		this.rules.put(newRuleName, this.rules.get(oldRuleName));
		this.rules.remove(oldRuleName);
	}

	/**
	 * Change a rule that already exists
	 * 
	 * @param ruleName to get the rule
	 * @param newRule to update as the new rule
	 */
	public void changeRule(String ruleName, String newRule) {
		this.rules.put(ruleName, newRule);
	}

	/**
	 * Save the current database
	 * 
	 * @throws Exception Throws a Exception when can not save the File
	 */
	public void saveCurrentDatabase() throws Exception {
		try {
			FileOutputStream fos = new FileOutputStream("rules.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(rules);
			oos.close();
		} catch (Exception e) {
			throw new Exception("Error saving file");
		}
	}

	/**
	 * Load Database
	 * 
	 * @return return the saved HashMap
	 * @throws IOException Throws a IOException when can not read the File
	 * @throws ClassNotFoundException Throws a ClassNotFoundException when the binary object in the file do not match the expected HashMap
	 */
	public HashMap<String, String> loadDatabase() throws ClassNotFoundException, IOException {
		FileInputStream fis = new FileInputStream("rules.dat");
		try (ObjectInputStream ois = new ObjectInputStream(fis)) {
			@SuppressWarnings("unchecked")
			HashMap<String, String> readObject = (HashMap<String, String>) ois.readObject();
			return readObject;
		}
	}

}

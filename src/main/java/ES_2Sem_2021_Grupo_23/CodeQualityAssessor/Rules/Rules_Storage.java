package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Rules;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

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
	 * @param String - to search the rule in the Rule_Storage
	 * @return the respective rule
	 */
	public String getRule(String ruleName) {
		return this.rules.get(ruleName);
	}

	/**
	 * Add a new rule to the Rule_Storage if it doesn't already exists
	 * 
	 * @param String - to check if the rule already exists and if not to use as
	 *               hashmap key
	 * @param String - rule content
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

	/**
	 * Save the current database
	 * 
	 * @throws Exception
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
	 * @throws IOException
	 * @throws ClassNotFoundException
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

package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Rules;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Rules_StorageTest {

	@Test
	void saveDatabase() {
		Rules_Storage storage = new Rules_Storage();
		assertDoesNotThrow(() -> storage.saveCurrentDatabase());
	}
	
	@Test
	void saveRule() {
		Rules_Storage storage = new Rules_Storage();
		assertTrue(storage.addRule("Teste", "Teste"));
	}
	
	@Test
	void saveRuleError() {
		Rules_Storage storage = new Rules_Storage();
		storage.addRule("Teste", "Teste");
		assertFalse(storage.addRule("Teste", "Teste"));
	}
	
	@Test
	void changeRule() {
		Rules_Storage storage = new Rules_Storage();
		storage.addRule("Teste", "Teste");
		storage.changeRule("Teste", "1");
		assertTrue(storage.getRule("Teste") == "1");
	}
	
	@Test
	void changeRuleName() {
		Rules_Storage storage = new Rules_Storage();
		storage.addRule("Teste", "Teste");
		storage.changeRuleName("Teste", "1");
		assertTrue(storage.getRule("1") == "Teste");
	}
	
	@Test
	void getRuleNames() {
		Rules_Storage storage = new Rules_Storage();
		storage.addRule("Teste", "Teste");
		storage.changeRule("Teste", "1");
		assertTrue(storage.getRulesNames() != null);
	}

}

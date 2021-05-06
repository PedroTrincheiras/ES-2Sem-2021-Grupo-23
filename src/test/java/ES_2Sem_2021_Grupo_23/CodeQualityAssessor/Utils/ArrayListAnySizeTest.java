package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Utils;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ArrayListAnySizeTest {

	@Test
	void testOutOfBounds() {
		List<String> t = new ArrayListAnySize<>();
		assertDoesNotThrow(()-> t.add(3,"test"));
	}
	
	@Test
	void testInBounds() {
		List<String> t = new ArrayListAnySize<>();
		t.add(3,"test");
		t.add(3,"test");
		List<String> toCompare = new ArrayList<String>();
		toCompare.add(null);
		toCompare.add(null);
		toCompare.add(null);
		toCompare.add("test");
		toCompare.add("test");
		assertEquals(t, toCompare);
	}

}

package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Calculate_Resume_Metrics;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.github.javaparser.utils.Pair;

class Calculate_Resume_MetricsTest {

	@Test
	void test() throws IOException {
		List<Pair<String, Integer>> toCalculate = Calculate_Resume_Metrics
				.readXLSX("jasmlFiles/jasmlFiles_metrics.xlsx");
		List<Pair<String, Integer>> expectedCalculateMetrics = new ArrayList<Pair<String, Integer>>();
		expectedCalculateMetrics.add(new Pair<String, Integer>("Number Of Packages", 5));
		expectedCalculateMetrics.add(new Pair<String, Integer>("Number Of Classes", 46));
		expectedCalculateMetrics.add(new Pair<String, Integer>("Number Of Methods", 236));
		expectedCalculateMetrics.add(new Pair<String, Integer>("Number Of Lines", 4227));
		assertEquals(expectedCalculateMetrics, toCalculate);
	}

	@Test
	void Undefined() {
		String file = "Undefined";
		assertThrows(IOException.class, () -> Calculate_Resume_Metrics.readXLSX(file));
	}

}

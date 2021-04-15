package ES_2Sem_2021_Grupo23.CodeQualityAssessor.Calculate_Resume_Metrics;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.github.javaparser.utils.Pair;

class Calculate_Resume_MetricsTest {

	@Test
	void test() throws IOException {
		List<Pair<String, Integer>> calculateResumeMetrics = Calculate_Resume_Metrics.readXLSX("jasmlFiles\\jasmlFiles_metrics.xlsx");
		List<Pair<String, Integer>> expectedCalculateResumeMetrics = new ArrayList<Pair<String, Integer>>();
		expectedCalculateResumeMetrics.add(new Pair<String, Integer>("Number Of Packages", 5));
		expectedCalculateResumeMetrics.add(new Pair<String, Integer>("Number Of Classes", 46));
		expectedCalculateResumeMetrics.add(new Pair<String, Integer>("Number Of Methods", 236));
		expectedCalculateResumeMetrics.add(new Pair<String, Integer>("Number Of Lines", 4227));
		assertEquals(expectedCalculateResumeMetrics, calculateResumeMetrics);
	}
	@Test
	void Undefined() throws IOException {
		List<Pair<String, Integer>> expectedCalculateResumeMetrics = Calculate_Resume_Metrics.readXLSX("Undefined");
		assertNull(expectedCalculateResumeMetrics);
	}

}

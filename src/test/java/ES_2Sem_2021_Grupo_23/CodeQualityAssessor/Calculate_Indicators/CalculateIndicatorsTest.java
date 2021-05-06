package ES_2Sem_2021_Grupo_23.CodeQualityAssessor.Calculate_Indicators;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import javax.script.ScriptException;

import org.junit.jupiter.api.Test;
import com.github.javaparser.utils.Pair;

class CalculateIndicatorsTest {

	@Test
	void getIndicatorsClassTest() throws IOException, ScriptException {
		String directory = "jasmlFiles/jasmlFiles_metrics_with_indicators.xlsx";
		String rule = "WMC_class> 50 OR NOM_class> 10";
		String ruleName = "toTestClass";
		List<Pair<String, String>>  indicators = CalculateIndicators.getIndicators(directory, rule, ruleName);
		List<Pair<String, String>> expectedIndicators = new ArrayList<Pair<String, String>>();
		expectedIndicators.add(new Pair<String, String>("Attribute","VN"));
		expectedIndicators.add(new Pair<String, String>("ExceptionTableItem","VN"));
		expectedIndicators.add(new Pair<String, String>("Opcode","VN"));
		expectedIndicators.add(new Pair<String, String>("Attribute_Code","VN"));
		expectedIndicators.add(new Pair<String, String>("Attribute_ConstantValue","VN"));
		expectedIndicators.add(new Pair<String, String>("Attribute_Deprecated","VN"));
		expectedIndicators.add(new Pair<String, String>("Attribute_Exceptions","VN"));
		expectedIndicators.add(new Pair<String, String>("InnerClass","FN"));
		expectedIndicators.add(new Pair<String, String>("Attribute_InnerClasses","FN"));
		expectedIndicators.add(new Pair<String, String>("LineNumber","FN"));
		expectedIndicators.add(new Pair<String, String>("Attribute_LineNumberTable","FN"));
		expectedIndicators.add(new Pair<String, String>("LocalVariable","FN"));
		expectedIndicators.add(new Pair<String, String>("Attribute_LocalVariableTable","FN"));
		expectedIndicators.add(new Pair<String, String>("Attribute_SourceFile","FN"));
		expectedIndicators.add(new Pair<String, String>("Attribute_Synthetic","FN"));
		expectedIndicators.add(new Pair<String, String>("ConstantPool","FN"));
		expectedIndicators.add(new Pair<String, String>("ConstantPoolItem","FN"));
		expectedIndicators.add(new Pair<String, String>("Constant_Class","FN"));
		expectedIndicators.add(new Pair<String, String>("Constant_Double","FN"));
		expectedIndicators.add(new Pair<String, String>("Constant_Fieldref","FN"));
		expectedIndicators.add(new Pair<String, String>("Constant_Float","FN"));
		expectedIndicators.add(new Pair<String, String>("Constant_Integer","FN"));
		expectedIndicators.add(new Pair<String, String>("Constant_InterfaceMethodref","FN"));
		expectedIndicators.add(new Pair<String, String>("Constant_Long","FN"));
		expectedIndicators.add(new Pair<String, String>("Constant_Methodref","FN"));
		expectedIndicators.add(new Pair<String, String>("Constant_NameAndType","VN"));
		expectedIndicators.add(new Pair<String, String>("Constant_String","VN"));
		expectedIndicators.add(new Pair<String, String>("Constant_Utf8","VN"));
		expectedIndicators.add(new Pair<String, String>("Field","VN"));
		expectedIndicators.add(new Pair<String, String>("Method","VN"));
		expectedIndicators.add(new Pair<String, String>("ConstantPoolGenerator","FP"));
		expectedIndicators.add(new Pair<String, String>("GrammerException","VN"));
		expectedIndicators.add(new Pair<String, String>("JavaClassDumpper","VN"));
		expectedIndicators.add(new Pair<String, String>("ParsingException","VN"));
		expectedIndicators.add(new Pair<String, String>("Scanner","FP"));
		expectedIndicators.add(new Pair<String, String>("OpcodeWrapper","VN"));
		expectedIndicators.add(new Pair<String, String>("LabeledInstructions","VN"));
		expectedIndicators.add(new Pair<String, String>("SourceCodeParser","FP"));
		expectedIndicators.add(new Pair<String, String>("JavaClassParser","VP"));
		expectedIndicators.add(new Pair<String, String>("SourceCodeBuilder","VP"));
		expectedIndicators.add(new Pair<String, String>("SourceCodeBuilderConfiguration","VN"));
		expectedIndicators.add(new Pair<String, String>("IntegerArray","VN"));
		expectedIndicators.add(new Pair<String, String>("OpcodeHelper","VN"));
		expectedIndicators.add(new Pair<String, String>("OpcodeLoader","VN"));
		expectedIndicators.add(new Pair<String, String>("Util","FP"));
		expectedIndicators.add(new Pair<String, String>("jasml","VN"));
		assertEquals(expectedIndicators, indicators);
	}
	
	@Test
	void getIndicatorsMethodTest() throws IOException, ScriptException {
		String directory = "jasmlFiles/jasmlFiles_metrics_with_indicators.xlsx";
		String rule = "WMC_class> 50 OR NOM_class> 10";
		String ruleName = "toTestMethod";
		List<Pair<String, String>>  indicators = CalculateIndicators.getIndicators(directory, rule, ruleName);
		List<Pair<String, String>> expectedIndicators = new ArrayList<Pair<String, String>>();
		expectedIndicators.add(new Pair<String, String>("1.0","VN"));
		expectedIndicators.add(new Pair<String, String>("2.0","VN"));
		expectedIndicators.add(new Pair<String, String>("3.0","VN"));
		expectedIndicators.add(new Pair<String, String>("4.0","VN"));
		expectedIndicators.add(new Pair<String, String>("5.0","VN"));
		expectedIndicators.add(new Pair<String, String>("6.0","VN"));
		expectedIndicators.add(new Pair<String, String>("7.0","VN"));
		expectedIndicators.add(new Pair<String, String>("8.0","VN"));
		expectedIndicators.add(new Pair<String, String>("9.0","VN"));
		expectedIndicators.add(new Pair<String, String>("10.0","VN"));
		assertEquals(expectedIndicators, indicators.subList(0, 10));
	}
	
	@Test
	void countIndicatorsTest() throws IOException, ScriptException {
		String directory = "jasmlFiles/jasmlFiles_metrics_with_indicators.xlsx";
		String rule = "WMC_class> 50 OR NOM_class> 10";
		String ruleName = "toTestClass";
		List<Pair<String, String>>  indicators = CalculateIndicators.getIndicators(directory, rule, ruleName);
		List<Pair<String, Integer>>  countIndicators = CalculateIndicators.countIndicators(indicators);
		List<Pair<String, Integer>> expectedCountIndicators = new ArrayList<Pair<String, Integer>>();
		expectedCountIndicators.add(new Pair<String, Integer>("VP",2));
		expectedCountIndicators.add(new Pair<String, Integer>("FP",4));
		expectedCountIndicators.add(new Pair<String, Integer>("VN",22));
		expectedCountIndicators.add(new Pair<String, Integer>("FN",18));
		assertEquals(expectedCountIndicators, countIndicators);
	}
	
	@Test
	void ruleNameError() throws IOException, ScriptException {
		String directory = "jasmlFiles/jasmlFiles_metrics_with_indicators.xlsx";
		String rule = "WMC_class> 50 OR NOM_class> 10";
		String ruleName = "test";
		assertThrows(InputMismatchException.class, () -> CalculateIndicators.getIndicators(directory, rule, ruleName));
	}
}

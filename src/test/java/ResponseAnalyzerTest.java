import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class ResponseAnalyzerTest {

    @DataProvider
    public Object[][] getTestData() {
        String inputString = "01AF04F";
        Map<String, Long> expectedMap = ImmutableMap.of(
                "0", 2L,
                "1", 1L,
                "4", 1L,
                "A", 1L,
                "F", 2L
        );
        return new Object[][]{{inputString, expectedMap}};
    }

    @Test(dataProvider = "getTestData")
    public void responseAnalyzerTest(String inputString, Map<String, Long> expectedMap) {
        assertEquals(ResponseAnalyzer.getCountedCharactersMap(inputString), expectedMap);
    }
}

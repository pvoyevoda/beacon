import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ResponseAnalyzer {

    public static Map<String, Long> getCountedCharactersMap(String outputFromService) {
        return Arrays.stream(outputFromService.split(""))
                .collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()));
    }
}

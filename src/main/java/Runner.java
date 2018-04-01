import java.time.Instant;
import java.util.Map;

public class Runner {
    public static void main(String[] args) {
        Map<String, Long> characterIntegerMap = ResponseAnalyzer.getCountedCharactersMap(
                BeaconServiceHelper.getCurrentRecordOutput(String.valueOf(Instant.now().getEpochSecond())));

        System.out.println("Result Map:");
        characterIntegerMap.forEach((key, value) -> System.out.println(key + "," + value));
    }
}

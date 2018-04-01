import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Instant;

import static org.testng.Assert.assertFalse;

public class BeaconServiceHelperTest {

    @DataProvider
    public Object[][] getTestData() {
        String timeStamp = String.valueOf(Instant.now().getEpochSecond());
        return new Object[][]{{timeStamp}};
    }

    @Test(dataProvider = "getTestData")
    public void beaconServiceHelperTest(String timeStamp) {
        assertFalse(BeaconServiceHelper.getCurrentRecordOutput(timeStamp).isEmpty());
    }
}

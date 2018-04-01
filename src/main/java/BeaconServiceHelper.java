import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class BeaconServiceHelper {
    private static final String beaconCurrentURI = "https://beacon.nist.gov/rest/record/";
    private static final String beaconPreviousURI = beaconCurrentURI + "previous/";

    public static String getCurrentRecordOutput(String timeStamp) {
        String outputValue;

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet request = new HttpGet(beaconCurrentURI + timeStamp);

            HttpResponse response = httpClient.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                System.out.println("No current record found. Getting previous one...");
                request = new HttpGet(beaconPreviousURI + timeStamp);
                response = httpClient.execute(request);
            }

            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(response.getEntity().getContent());

            outputValue = document.getElementsByTagName("outputValue").item(0).getTextContent();

        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException("Failed to get data from Beacon", e);
        }

        System.out.println("Output value from Beacon:" + System.lineSeparator() + outputValue);
        return outputValue;
    }

}

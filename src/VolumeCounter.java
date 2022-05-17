import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class VolumeCounter {
    private URLParser urlParser;
    private ArrayList<String> domains;
    private ArrayList<Long> volume;

    public VolumeCounter(URLParser urlParser) {
        this.urlParser = urlParser;
        domains = urlParser.getDomains();
    }

    public ArrayList<Long> countVolume() throws IOException {
        URL url = new URL(urlParser.getAllDomains());
        for (String domain : domains) {
            long totalLen;
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("HEAD");
            totalLen = http.getContentLength();
            http.disconnect();
            volume.add(totalLen);
        }
        return volume;
    }
}

import java.io.*;
import java.util.ArrayList;

public class CSVWorker {
    private final String path = "C:\\Users\\MihailLovch\\IdeaProjects\\Kontrolnaya\\output.csv";

    private ArrayList<String> domains;
    private ArrayList<String> date;
    private ArrayList<String> country;
    private ArrayList<Long> volumes;

    public CSVWorker(URLParser urlParser, VolumeCounter volumeCounter) throws IOException {
        domains = urlParser.getDomains();
        date = urlParser.getDate();
        country = urlParser.getCountry();
        volumes = volumeCounter.countVolume();
        try {
            new File(path).createNewFile();
        } catch (IOException e) {
            throw new IOException("Incorrect path");
        }
    }

    public void loadInCSV() throws IOException {
        try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(path))) {
            for (int i = 0; i < domains.size(); i++) {
                out.write(domains.get(i) + "," + date.get(i) + "," + country.get(i) + "," + volumes.get(i));
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

}

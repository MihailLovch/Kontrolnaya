import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLParser {
    private String allDomains = "https://api.domainsdb.info/v1/domains/search?domain=";
    private String name;
    private String domain;
    private StringBuilder json = new StringBuilder();
    private ArrayList<String> domains = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<String> country = new ArrayList<>();
    private final String TIMED_DOMAIN_REGEX = "\"domain\": \"([\\w])+\", \"create_date\": \"2021-0[1-7].*,\"update_date\":\"([0-9\\-])\"";

    public URLParser(String name, String domain) throws IOException {
        this.name = name;
        this.domain = domain;
        allDomains += name + "&zone=" + domain + "&update";
        parseDomains();
    }

    private void parseDomains() throws IOException {

        try (BufferedInputStream in = new BufferedInputStream(makeRequest())) {
            int bite;
            while ((bite = in.read()) != -1) {
                json.append((char) bite);
            }
        } catch (MalformedURLException ex) {
            throw new MalformedURLException("Wrong domain or name");
        } catch (IOException e) {
            throw new IOException("", e.getCause());
        }
        Matcher matcher = Pattern.compile(TIMED_DOMAIN_REGEX).matcher(json);
        while (matcher.find()) {
            domains.add(matcher.group(1));
            date.add(matcher.group(2));
            country.add(matcher.group(3));
        }
    }

    private FileInputStream makeRequest() throws FileNotFoundException {
        return new FileInputStream("C:\\Users\\MihailLovch\\IdeaProjects\\Kontrolnaya\\input.json");
    }

    public ArrayList<String> getDomains() {
        return domains;
    }

    public ArrayList<String> getDate() {
        return date;
    }

    public ArrayList<String> getCountry() {
        return country;
    }

    public String getAllDomains() {
        return allDomains;
    }
}

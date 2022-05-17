import java.io.IOException;
import java.util.Scanner;

public class App {

    private Scanner sc;
    private String name;
    private String zone;
    private URLParser urlParser;
    private CSVWorker csvWorker;
    private VolumeCounter volumeCounter;

    public static void main(String[] args) {
        App app = new App();
    }


    public App() {
        init();
        start();
    }

    private void start() {
        try {
            csvWorker.loadInCSV();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void init() {
        System.out.println("Введите домен и регион");
        sc = new Scanner(System.in);
        String input = sc.nextLine();
        name = input.split(" ")[0];
        zone = input.split(" ")[1];

        try {
            urlParser = new URLParser(name, zone);
        } catch (IOException e) {
            System.out.println("Uncorrect input");
        }
        volumeCounter = new VolumeCounter(urlParser);
        try {
            csvWorker = new CSVWorker(urlParser, volumeCounter);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

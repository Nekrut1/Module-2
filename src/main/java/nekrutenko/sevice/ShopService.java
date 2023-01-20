package nekrutenko.sevice;

import nekrutenko.exception.IncorrectInformationException;
import nekrutenko.model.devices.Device;
import nekrutenko.model.devices.Telephone;
import nekrutenko.model.devices.Television;
import nekrutenko.model.invoices.Invoice;
import nekrutenko.repository.InvoiceRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ShopService {
    private final InvoiceRepository repository;
    private final Random random = new Random();
    public ShopService(InvoiceRepository repository) {
        this.repository = repository;
    }

    public String readFile(String fileName) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        StringBuilder builder = new StringBuilder();
        try (InputStream resourceAsStream = contextClassLoader.getResourceAsStream(fileName);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return builder.toString();
    }
    public Invoice createInvoiceFromFile(String filename) {
        String data = readFile(filename);
        String[] dataLines = data.split("\n");
        String[] keyValues = dataLines[0].split(",");
        int deviceAmount = random.nextInt(1, 6);
        int[] deviceIndex = random.ints(deviceAmount, 1, dataLines.length - 1)
                .toArray();
        Map<String, String> map = new HashMap<>();
        Invoice invoice = new Invoice(new PersonService().getRandomCustomer());
        for (int index : deviceIndex) {
            String[] rawValues = dataLines[index].split(",");
            for (int i = 0; i < keyValues.length; i++) {
                String key = keyValues[i];
                String value = rawValues[i];
                if (value.isBlank()) {
                    try {
                        throw new IncorrectInformationException();
                    } catch (IncorrectInformationException e) {
                        System.out.println("Incorrect information in your \"csv\" file");
                    }
                }
                map.put(key, value);
            }
            invoice.addDevice(createRandomDevice(map));
        }
        repository.save(invoice);
        return invoice;
    }
    public void createFifteenInvoices() {
        for (int i = 0; i < 15; i++) {
            createInvoiceFromFile("information.csv");
        }
    }
    private Device createRandomDevice(Map<String, String> map) {
        String type = map.get("deviceType");
        if (type.equals("Telephone")) {
            return createTelephone(map);
        } else if (type.equals("Television")) {
            return createTelevision(map);
        }
        throw new IllegalArgumentException();
    }
    private Telephone createTelephone(Map<String, String> map) {
        String model = map.get("model");
        String series = map.get("series");
        String screenType = map.get("screen type");
        int price = Integer.parseInt(map.get("price"));

        return new Telephone(series, screenType, price, model);
    }
    private Television createTelevision(Map<String, String> map) {
        String series = map.get("series");
        String screenType = map.get("screen type");
        int price = Integer.parseInt(map.get("price"));
        int diagonal = Integer.parseInt(map.get("diagonal"));
        String country = map.get("country");

        return new Television(series, screenType, price, diagonal, country);

    }
}

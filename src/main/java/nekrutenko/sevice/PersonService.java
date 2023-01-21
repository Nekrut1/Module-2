package nekrutenko.sevice;

import nekrutenko.model.Customer;
import nekrutenko.util.RandomGenerator;

public class PersonService {
    private final RandomGenerator randomGenerator = new RandomGenerator();

    public Customer getRandomCustomer() {
        String id = randomGenerator.getRandomString();
        String email = randomGenerator.getRandomString() + "@gmail.com";
        int age = randomGenerator.randomNumber();

        return new Customer(id, email, age);
    }
}

package day7;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class FakerDataGenerator {

    @Test
    void testGenerateDummyData(){
        Faker faker = new Faker();
        String fullName = faker.name().fullName();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        String userName = faker.name().username();
        String password = faker.internet().password();

        String phoneNumber = faker.phoneNumber().phoneNumber();

        String emailAddress = faker.internet().emailAddress();

        String creditCardExpiry = faker.business().creditCardExpiry();

        String colorName = faker.color().name();

        System.out.println("Full Name: "+fullName);
        System.out.println("First Name: "+firstName);
        System.out.println("Last Name: "+lastName);
        System.out.println("User Name: "+userName);
        System.out.println("Password: "+password);
        System.out.println("Phone Number: "+phoneNumber);
        System.out.println("Email Address: "+emailAddress);
        System.out.println("Credit Card Expiry: "+creditCardExpiry);
        System.out.println("Color Name: "+colorName);

    }
}

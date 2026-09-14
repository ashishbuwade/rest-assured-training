package day6;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class XMLSchemaValidationC {

    @Test
    void xmlSchemaValidate(){

        given()

                .when()
                .get("http://192.168.1.47:8080/travelers.xml")

                .then()
                .assertThat().body(matchesXsdInClasspath("travelerxmlschema.xsd"));

    }

}

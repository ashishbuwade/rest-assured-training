package day3;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;


public class LogDemo {

    @Test(priority = 1)
    void getLogInfo(){


        given()

                .when()
                .get("https://google.com")

                .then()
//                .log().body();
//                .log().cookies();
//                 .log().headers();
                .log().all();

    }

}

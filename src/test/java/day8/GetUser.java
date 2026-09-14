package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetUser {

    @Test
    void test_getUser(ITestContext context){

//        int id = (Integer) context.getAttribute("user_id");
        int id = (Integer) context.getSuite().getAttribute("user_id");

        String bearerToken = "";

        given()
                .headers("Autorization","Bearer "+bearerToken)
                .pathParam("id",id)

                .when()
                .get("https://gorest.co.in/public/v2/users/{id}")

                .then()
                .statusCode(200)
                .log().all();

    }
}

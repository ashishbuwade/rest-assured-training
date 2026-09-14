package day8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {

    @Test
    void test_updateUser(ITestContext context){
        Faker faker = new Faker();

        JSONObject data = new JSONObject();

        data.put("name", faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","Active");

        String bearerToken = "";

//        int id = (Integer) context.getAttribute("user_id");
        int id = (Integer) context.getSuite().getAttribute("user_id");
        given()
                .headers("Autorization","Bearer "+bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .pathParam("id",id)

                .when()
                .put("https://gorest.co.in/public/v2/users")

                .then()
                .statusCode(200)
                .log().all();

    }

}

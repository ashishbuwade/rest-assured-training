package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeleteUser {
    @Test
    void test_deleteUser(ITestContext context){
//        int id = (Integer) context.getAttribute("user_id");
        int id = (Integer) context.getSuite().getAttribute("user_id");
        String bearerToken = "";

        given()
                .headers("Authorization","Bearer "+bearerToken)
                .pathParam("id",id)

                .when()
                .delete("https://gorest.co.in/public/v2/users/{id}")

                .then()
                .statusCode(204)
                .log().all();

    }

}

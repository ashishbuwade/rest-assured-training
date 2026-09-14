package day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CookiesDemo {

    @Test(priority = 1)
    void testCookies(){
                given()

                .when()
                        .get("https://www.google.com/")

                .then()
                        .cookie("AEC","AdJVEavn7BarnFQfBTlPQvZYWwL73sVI9qsjw3WG1Qn60eeV9cZr5_HBsw")
                        .log().all();
    }

    @Test(priority = 2)
    void getCookiesInfo(){
        Response res = given()

                .when()
                .get("http://www.google.com/");


        //Get Single Cookie Info
//        String cookie_val = res.getCookie("AEC");
//        System.out.println(cookie_val);

        //Get All Cookies Info

        Map<String, String> cookies_val = res.getCookies();

        for(String k : cookies_val.keySet()){
            System.out.println(k+ ": "+ res.getCookie(k));
        }
    }
}

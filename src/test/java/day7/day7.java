package day7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class day7 {
    @Test(priority = 1)
    void testBasicAuthentication(){
        given()
                .auth().basic("postman","password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)

                .body("authenticated",equalTo(true))
                .log().all();


    }

    @Test(priority = 2)
    void testDigestAuthentication(){
        given()
                .auth().digest("postman","password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)

                .body("authenticated",equalTo(true))
                .log().all();


    }

    @Test(priority = 3)
    void testPreemptiveAuthentication(){
        given()
                .auth().preemptive().basic("postman","password")

                .when()
                .get("https://postman-echo.com/basic-auth")

                .then()
                .statusCode(200)

                .body("authenticated",equalTo(true))
                .log().all();


    }

    @Test(priority = 4)
    void testBearerAuthentication(){

        String bearerToken = System.getenv("GITHUB_TOKEN");

        given()
                
                .headers("Authorization","Bearer "+bearerToken)

                .when()
                .get("https://api.github.com/user/repos")

                .then()
                .statusCode(200)
                .log().all();


    }

    @Test(priority = 5)
    void testOauth1Authentication(){

        given()
                .auth().oauth("consumerkey","consumersecret","accessToken","tokenSecret")

                .when()
                .get("URL")

                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 6)
    void testOauth2Authentication(){

        given()

                .when()
                .get("URL")

                .then()
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 7)
    void testAPIKeyAuthentication(){

        String apiKey = System.getenv("OPENWEATHER_API_KEY");

        //Method1
        given()
                .queryParam("appid",apiKey) //appid is API Key

                .when()
                .get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")

                .then()
                .statusCode(200)
                .log().all();

        //Method2
        given()
                .queryParam("appid",apiKey) //appid is API Key
                .pathParam("mypath","data/2.5/forecast/daily")
                .queryParam("units","metric")
                .queryParam("cnt","7")

                .when()
                .get("https://api.openweathermap.org")

                .then()
                .statusCode(200)
                .log().all();
    }

}

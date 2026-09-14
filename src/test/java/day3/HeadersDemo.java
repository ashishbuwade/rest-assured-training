package day3;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HeadersDemo {

    @Test(priority = 1)
    void validateHeadersInfo(){

        given()

                .when()
                .get("https://google.com")

                .then()
                .header("content-type","text/html; charset=ISO-8859-1")
                .and()
                .header("content-encoding","gzip")
                .and()
                .header("server","gws");

    }

    @Test(priority = 2)
    void getHeaderInfo(){
       Response res = given()

                .when()
                .get("https://google.com");

       //Get Single Header Info
//       String content_type_val = res.getHeader("content-type");
//        System.out.println("Content Type: "+content_type_val);

        //Get All Headers Info

        Headers headerinfo = res.getHeaders();

        for(Header hd : headerinfo){
            System.out.println(hd.getName()+": "+hd.getValue());
        }

    }

}

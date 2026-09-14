package day5;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;

public class ParsingXMLResponse {
    @Test
    void testXMLResponse(){

        given()

                .when()
                .get("http://127.0.0.1:8080/travelers.xml")

                .then()
                .statusCode(200)
                .header("Content-Type","application/xml")
//                .body("TravelerinformationResponse.page",equalTo("1"))
                .body("TravelerinformationResponse.Travelers.Travelerinformation[0].name",equalTo("Ashish"));


    }

    @Test
    void testXMLResponse2(){

        Response res = given()

                .when()
                .get("http://127.0.0.1:8080/travelers.xml");

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/xml");


        String travelerName = res.xmlPath().get("TravelerinformationResponse.Travelers.Travelerinformation[0].name").toString();
        Assert.assertEquals(travelerName,"Ashish");

    }


    @Test
    void testXMLResponse3(){

        Response res = given()

                .when()
                .get("http://127.0.0.1:8080/travelers.xml");

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/xml");


        XmlPath xmlPath = new XmlPath(res.asString());

        List<String> travelerNames = xmlPath.getList("TravelerinformationResponse.Travelers.Travelerinformation.name");

        boolean status = false;
        for(String travelerName : travelerNames){
            if(travelerName.equals("Soniya")){
                status = true;
            }
        }

        Assert.assertEquals(status, true);
    }
}

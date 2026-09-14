package day4;

import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.http.ContentType;


public class ParsingJSONResponseData {

    @Test(priority = 1)
    void testJSONResponse(){

        //Approach1
                given()
                .contentType(ContentType.JSON)

                .when()
                .get("http://localhost:3000/store")

                .then()
                .statusCode(200)
                .header("Content-Type","application/json")
                        .log().all()
                        .body("book[1].category",equalTo("fiction"));

        //Approach2
//        Response res = given()
//                .contentType(ContentType.JSON)
//
//                .when()
//                .get("http://localhost:3000/store");
//
//        Assert.assertEquals(res.getStatusCode(),200);
//        Assert.assertEquals(res.header("Content-Type"),"application/json");
//
//        String loc = res.jsonPath().get("book[0].title").toString();
//        Assert.assertEquals(loc,"Micky Mouse");

    }

    @Test(priority = 2)
    void testJSONResponse2(){
        //Approach 3
        Response res = given()
                .contentType(ContentType.JSON)

                .when()
                .get("http://localhost:3000/store");

        JSONObject jo = new JSONObject(res.asString());

       //Get all the books title

//        for(int i=0; i<jo.getJSONArray("book").length();i++){
//            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
//            System.out.println(bookTitle);
//        }

// Search for the title of the book in json

//        boolean status = false;
//
//        for(int i = 0; i<jo.getJSONArray("book").length(); i++){
//            String bookTitle = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
//
//            if(bookTitle.equals("Shaktimaan")){
//                status = true;
//                break;
//            }

//        }
//        Assert.assertTrue(status);

        //validate total price of books

        int total = 0;
        for(int i=0; i<jo.getJSONArray("book").length();i++){
            String tempPrice = jo.getJSONArray("book").getJSONObject(i).get("price").toString();
            total = total + Integer.parseInt(tempPrice);
        }

        Assert.assertEquals(total,1900);

    }
}

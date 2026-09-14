package day2;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import day6.Student;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class DiffWayToCreatePostReqBody {

//    @Test(priority = 1)
    void testPostUsingHashMap() {

        HashMap data = new HashMap();
        data.put("name", "dudu");
        data.put("location", "bubududuworld");
        data.put("phone", "1234567890");
        String coursesArr[] = {"Java","Python"};
        data.put("courses", coursesArr);

        given()
                .contentType("application/json")
                .body(data)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("dudu"))
                .body("location", equalTo("bubududuworld"))
                .body("phone",equalTo("1234567890"))
                .body("courses[0]",equalTo("Java"))
                .body("courses[1]", equalTo("Python"))
                .header("Content-Type", "application/json")
                .log().all();

    }

//    @Test(priority=2)
    void testDelete() {
        given()

                .when()
                .delete("http://localhost:3000/students/9sdcJs1SOtw")
                .then()
                .statusCode(200);
    }

//    @Test(priority = 1)
    void testPostUsingJSONLibrary() {

        JSONObject data = new JSONObject();
        data.put("name", "dudu");
        data.put("location", "bubududuworld");
        data.put("phone", "1234567890");
        String coursesArr[] = {"Java","Python"};
        data.put("courses", coursesArr);

        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("dudu"))
                .body("location", equalTo("bubududuworld"))
                .body("phone",equalTo("1234567890"))
                .body("courses[0]",equalTo("Java"))
                .body("courses[1]", equalTo("Python"))
                .header("Content-Type", "application/json")
                .log().all();

    }

//    @Test(priority = 1)
    void testPostUsingPOJO() {

        Student ppr = new Student();

        ppr.setName("DuduKing");
        ppr.setLocation("bubududuworld");
        ppr.setPhone("1234567890");
        String coursesArr[] = {"Data Science","AI"};
        ppr.setCourses(coursesArr);

        given()
                .contentType("application/json")
                .body(ppr)

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("DuduKing"))
                .body("location", equalTo("bubududuworld"))
                .body("phone",equalTo("1234567890"))
                .body("courses[0]",equalTo("Data Science"))
                .body("courses[1]", equalTo("AI"))
                .header("Content-Type", "application/json")
                .log().all();

    }

    @Test(priority = 1)
    void testPostUsingExternalJSONFile() throws FileNotFoundException {

        File f = new File(".//body.json");

        FileReader fr = new FileReader(f);

        JSONTokener jt = new JSONTokener(fr);

        JSONObject data = new JSONObject(jt);



        given()
                .contentType("application/json")
                .body(data.toString())

                .when()
                .post("http://localhost:3000/students")

                .then()
                .statusCode(201)
                .body("name", equalTo("BubuQueen"))
                .body("location", equalTo("bubududuworld"))
                .body("phone",equalTo("1234567890"))
                .body("courses[0]",equalTo("Java"))
                .body("courses[1]", equalTo("Selenium"))
                .header("Content-Type", "application/json")
                .log().all();

    }

}

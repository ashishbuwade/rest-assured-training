package day5;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.equalTo;


public class FileUploadDownload {

    @Test
    void singleFileUpload(){


        File myFile = new File("C:\\Users\\ashis\\OneDrive\\Desktop\\API Testing\\File1.txt");
        given()
                .multiPart("file",myFile)
                .contentType("multipart/form-data")

                .when()
                .post("http://localhost:8080/uploadFile")

                .then()
                .statusCode(200)
                .body("fileName",equalTo("File1.txt"))
                .log().all();
    }

    @Test
    void multipleFileUpload(){


        File myFile1 = new File("C:\\Users\\ashis\\OneDrive\\Desktop\\API Testing\\File1.txt");
        File myFile2 = new File("C:\\Users\\ashis\\OneDrive\\Desktop\\API Testing\\File2.txt");

        File myFiles[] = {myFile1,myFile2};

        given()
                .multiPart("files",myFiles)
                .contentType("multipart/form-data")

                .when()
                .post("http://localhost:8080/uploadFile")

                .then()
                .statusCode(200)
                .body("[0].fileName",equalTo("File1.txt"))
                .body("[1].fileName",equalTo("File2.txt"))
                .log().all();
    }

    @Test(priority = 2)
    void fileDownload(){
        given()

                .when()
                .get("http://localhost:8080/downloadFile/File1.txt")

                .then()
                .statusCode(200)
                .log().all();
    }

}

package day6;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
//POJO -- Serialize --> JSON Object -- De-serialize --> POJO
public class SerializationDeserialization {


    //POJO -> JSON
    @Test
    void convertPojo2Json() throws JsonProcessingException {

        //Created Java Object using POJO class
        Student studObj = new Student();    //POJO

        studObj.setName("Ashish");
        studObj.setLocation("Bangalore");
        studObj.setPhone("1234567890");
        String coursesArr[] = {"Java", "Python"};
        studObj.setCourses(coursesArr);

        //Convert Java Object --> JSON Object (Serialization)

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(studObj); //Convert POJO to JSON

        System.out.println(jsonData);

    }

    //JSON -> POJO
    @Test
    void convertJSON2Pojo() throws JsonProcessingException {

        //JSON Data
        String jsonData = "{\n" +
                "  \"name\" : \"Ashish\",\n" +
                "  \"location\" : \"Bangalore\",\n" +
                "  \"phone\" : \"1234567890\",\n" +
                "  \"courses\" : [ \"Java\", \"Python\" ]\n" +
                "}";

        //Convert JSON Data to POJO object

        ObjectMapper objectMapper = new ObjectMapper();

        Student student = objectMapper.readValue(jsonData,Student.class); //Convert JSON to POJO

        System.out.println("Name : "+student.getName());
        System.out.println("Location : "+student.getLocation());
        System.out.println("Phone : "+student.getPhone());
        System.out.println("Course 1 : "+student.getCourses()[0]);
        System.out.println("Course 2 : "+student.getCourses()[1]);

    }


}

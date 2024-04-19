package restassuredautomation;



import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APITestsOnRemoteAPI {

    @Test
    public void get_1(){
        Response response = get("https://reqres.in/api/users?page=2");
        System.out.println(response.getStatusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);  //successful get
    }

    //@Test
    public void get_2(){
        baseURI = "https://reqres.in/api";
            given().
               get("/users?page=2").
            then().
               statusCode(200).
               body("data[4].first_name", equalTo("George")).
               body("data.first_name", hasItems("George", "Rachel"));
    }

    //@Test
    public void post(){
        JSONObject request = new JSONObject();
        request.put("name", "Derek");
        request.put("job", "Chairman");
        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("/users").
        then().
                statusCode(201)  //successful creation
                .log().all();
    }

    //@Test
    public void put(){
        JSONObject request = new JSONObject();
        request.put("name", "Bob");
        request.put("job", "Builder");
        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in/api";
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                put("/users/2").
        then().
                statusCode(200)  //successful update
                .log().all();

    }

    //@Test
    public void patch(){
        JSONObject request = new JSONObject();
        request.put("name", "Zalan");
        System.out.println(request.toJSONString());

        baseURI = "https://reqres.in";
        given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                patch("api/users/2").
        then().
                statusCode(200)  //successful update
                .log().all();
    }

    //@Test
    public void delete(){
        baseURI = "https://reqres.in";
        when().
                delete("api/users/2").
        then().
                statusCode(204)  //successful delete
                .log().all();
    }

}

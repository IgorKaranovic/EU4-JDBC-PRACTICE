package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;

public class SpartanGetRequest {


    String spartanurl = "http://34.207.206.103:8000";

    @Test
    public void test1(){

        Response response = when().get(spartanurl + "/api/spartans");

        System.out.println(response.statusCode());

        response.prettyPrint();
    }

    /* TASK
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json;charset=UTF-8
        and json body should contain Fidole

        Test NG  test way
     */

    @Test
    public void test2(){

        Response response = when().get(spartanurl + "/api/spartans/3");

        System.out.println(response.statusCode());

        //verify status code
        Assert.assertEquals(response.statusCode(),200);

        //verify containType
        Assert.assertEquals(response.contentType(),"application/json;charset=UTF-8");

        //verify Fidole
        Assert.assertTrue(response.body().asString().contains("Fidole"));


    }

      /* TASK
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json;charset=UTF-8
        and json body should contain Fidole

        RestAssured test way
     */

    @Test
    public void test3(){


        when().get(spartanurl + "/api/spartans/3").
                then().assertThat().statusCode(200).and().
                contentType("application/json;charset=UTF-8");
    }

    /*
        Given no headers provided
        When Users sends GET request to /api/hello
        Then response status code should be 200
        And Content type header should be “text/plain;charset=UTF-8”
        And header should contain date
        And Content-Length should be 17
        And body should be “Hello from Sparta"
        */


    @Test
    public void helloTest(){
        //request
        Response response = when().get(spartanurl + "/api/hello");

        //verify status code
        Assert.assertEquals(response.statusCode(),200);
        //verify content-type
        Assert.assertEquals(response.contentType(),"text/plain;charset=UTF-8");

        //verify we have headers named date
        Assert.assertTrue(response.headers().hasHeaderWithName("Date"));
        //to get any header passing as a key
        System.out.println(response.header("Content-Length"));
        System.out.println(response.header("Date"));

        //verify content length is 17
        Assert.assertEquals(response.header("Content-Length"),"17");

        //verify hello from sparta
        Assert.assertEquals(response.getBody().asString(),"Hello from Sparta");
    }










}

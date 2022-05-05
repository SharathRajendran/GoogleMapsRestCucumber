package StepDefinitions;

import PojoClasses.GoogleAddPlaceAPIPojo;
import PojoClasses.location;
import Resources.enumAPIResources;
import Resources.specBuilders;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.*;

public class GoogleMapsStepDefinitions extends specBuilders {


    RequestSpecification req;
    RequestSpecification reqBody;
    ResponseSpecification res;
    Response response;

    String resourcePath;

    HashMap<String, Object> map;

    JsonPath jp;

    public static String place_ID;


    @Given("AddPlace Payload is used with {string} {string} {string}")
    public void add_place_payload_is_used_with(String name, String address, String website) throws IOException {

        map = Resources.testDataBuild.hashMapJsonBuild(name, address, website);

        reqBody = given().log().all().spec(requestSpecBuilder()).body(map);

    }

    @When("User calls {string} with {string} HTTP Request")
    public void user_calls_with_http_request(String resource, String method) throws IOException {

        enumAPIResources value = Resources.enumAPIResources.valueOf(resource);



        resourcePath = value.getResource();

        System.out.println(resourcePath);

        res = new ResponseSpecBuilder().expectStatusCode(200).build();


        if (method.equalsIgnoreCase("Post")) {

            response = reqBody.when().post(resourcePath).then().spec(res).extract().response();

        } else if (method.equalsIgnoreCase("Get")) {
            response = reqBody.when().get(resourcePath).then().spec(res).extract().response();
        } else if (method.equalsIgnoreCase("Delete")) {
            response = reqBody.when().delete(resourcePath).then().spec(res).extract().response();
        }


    }

    @Then("The API call should be successful with status code {int}")
    public void the_api_call_should_be_successful_with_status_code(Integer status) {
        Assert.assertEquals(response.getStatusCode(),status.intValue());
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ObjectValue) {

        Assert.assertEquals(getJSONPathValue(response, keyValue), ObjectValue);
    }

    @Then("verify {string} in {string}")
    public void verify_in_get_place_api(String expectedValue, String resource) throws IOException {

         place_ID = getJSONPathValue(response, "place_id");

        reqBody = given().spec(requestSpecBuilder()).queryParam("place_id", place_ID);

        user_calls_with_http_request(resource, "GET");

        String actualValue = getJSONPathValue(response, "name");

        Assert.assertEquals(actualValue, expectedValue);


    }

    @Given("DeletePlace API Payload is used")
    public void delete_place_api_payload_is_used() throws IOException {
        reqBody = given().spec(requestSpecBuilder()).body(Resources.testDataBuild.deletePlaceAPI(place_ID));
    }
}

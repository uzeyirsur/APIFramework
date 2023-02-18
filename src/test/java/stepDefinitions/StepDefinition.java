package stepDefinitions;

import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class StepDefinition extends Utils {
    ResponseSpecification resSpec;
    RequestSpecification res;
    TestDataBuild data = new TestDataBuild();
    Response response;
    static String place_id;

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String httpMethod) {
        APIResources apiResource = APIResources.valueOf(resource);
        System.out.println(apiResource.getResource());

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        if (httpMethod.equalsIgnoreCase("POST")) {
            response = res.when().post(apiResource.getResource());
        } else if (httpMethod.equalsIgnoreCase("GET")) {
            response = res.when().get(apiResource.getResource());
        } else if (httpMethod.equalsIgnoreCase("DELETE")) {
            response = res.when().delete(apiResource.getResource());
        }

    }

    @Then("the API call is success with status code {int}")
    public void the_api_call_is_success_with_status_code_number(int code) {
        assertEquals(response.getStatusCode(), code);
    }

    @Then("{string} in response body is {string}")
    public void key_in_response_body_is_value(String key, String value) {

        assertEquals(getJsonPath(response, key), value);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_ıd_created_maps_to_using(String nameWritten, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        res = given().spec(requestSpecification()).queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "get");
        String nameFromResponse = getJsonPath(response, "name");

        System.out.println("nameFromResponse = " + nameFromResponse);
        System.out.println("nameWritten = " + nameWritten);

        assertEquals(nameFromResponse, nameWritten);


    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {

        res = given().spec(requestSpecification()).body("{\"place_id\": \""+place_id+"\"}");


    }
}

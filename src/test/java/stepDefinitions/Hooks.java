package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinition sd = new StepDefinition();
        if (StepDefinition.place_id == null) {
            sd.add_place_payload_with("Uzo", "Spanish", "Ist");
            sd.user_calls_with_http_request("addPlaceAPI", "Post");
            sd.verify_place_ıd_created_maps_to_using("Uzo", "getPlaceAPI");
        }

    }
}

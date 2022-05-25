package StepDefinitions;

import StepDefinitions.GoogleMapsStepDefinitions;
import io.cucumber.java.Before;

import java.io.IOException;

public class hooks {

    @Before("@DeletePlace")
    public void deletePlaceBeforeHook() throws IOException {

        GoogleMapsStepDefinitions g = new GoogleMapsStepDefinitions();
        if(GoogleMapsStepDefinitions.place_ID==null) {

            g.add_place_payload_is_used_with("Carlton", "VIC 3053", "calrton.com");
            g.user_calls_with_http_request("AddPlaceAPI", "Post");
            g.verify_in_get_place_api("Carlton", "GetPlaceAPI");
        }
    }
}

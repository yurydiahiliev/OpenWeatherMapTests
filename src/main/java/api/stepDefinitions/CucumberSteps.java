package api.stepDefinitions;

import api.responseModels.WeatherResponse;
import api.restassured.BaseRestAssuredClient;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.IsEqual.equalTo;

@Component
@Configurable
public class CucumberSteps extends BaseRestAssuredClient {

    private ValidatableResponse response;

    @When("^the user calls current weather by (.+)$")
    public void the_user_calls_current_weather_by_London(String cityName) {
        response = requestSpecification()
                .when()
                .queryParam("q", cityName)
                .get(openWeatherConfig.currentWeatherUrl())
                .then();
    }

    @Then("^the user receives status code of (\\d+)$")
    public void the_user_receives_status_code_of(int expectedStatusCode) {
        response.assertThat().statusCode(expectedStatusCode);
    }

    @And("^the user receives weather in country (.+)$")
    public void the_user_receives_weather_in_country_GB(String country) {
        WeatherResponse weatherResponse = response
                .extract()
                .as(WeatherResponse.class);
        assertThat(weatherResponse, not(nullValue()));
        assertThat(weatherResponse.getSys().getCountry(), equalTo(country));
    }
}

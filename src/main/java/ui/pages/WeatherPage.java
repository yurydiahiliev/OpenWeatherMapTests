package ui.pages;

import api.responseModels.Coord;
import api.responseModels.Main;
import api.responseModels.Weather;
import api.responseModels.WeatherResponse;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.core.BasePage;
import ui.core.PageUrl;

import java.util.Collections;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@PageUrl("/city")
public class WeatherPage extends BasePage {

    private SelenideElement searchCityInput = $x("//input[@placeholder='Your city name']");
    private SelenideElement humidity = $x("//td[text()='Humidity']/../td[2]");
    private SelenideElement latCoordinate = $("span#wrong-data-lat");
    private SelenideElement lonCoordinate = $("span#wrong-data-lon");
    private SelenideElement weatherDescription = $("p.weather-widget__main");
    private SelenideElement toC = $x("//span[@id='metric']");

    @Step
    public SearchCityResultsPage searchCity(String city) {
        searchCityInput.setValue(city).pressEnter();
        return new SearchCityResultsPage();
    }

    @Step
    public WeatherResponse getActualWeatherResponse() {
        toC.click();
        Coord coord = new Coord();
        coord.setLat(Double.parseDouble(latCoordinate.getText()));
        coord.setLon(Double.parseDouble(lonCoordinate.getText()));

        Weather weather = new Weather();
        weather.setDescription(weatherDescription.getText());

        Main main = new Main();
        main.setHumidity(Integer.parseInt(humidity.getText().replace(" %", "")));

        WeatherResponse weatherResponse = new WeatherResponse();
        weatherResponse.setBase("stations");
        weatherResponse.setCoord(coord);
        weatherResponse.setWeather(Collections.singletonList(weather));
        weatherResponse.setMain(main);

        return weatherResponse;
    }
}

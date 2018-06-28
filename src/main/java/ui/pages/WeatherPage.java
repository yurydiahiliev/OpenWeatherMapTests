package ui.pages;

import api.responseModels.Coord;
import api.responseModels.Main;
import api.responseModels.Weather;
import api.responseModels.WeatherResponse;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
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
    private SelenideElement windParams = $("td#weather-widget-wind");
    private SelenideElement sunriseElement = $x("//td[text()='Sunrise']/../td[2]");
    private SelenideElement sunsetElement = $x("//td[text()='Sunset']/../td[2]");
    private SelenideElement toC = $x("//span[@id='metric']");

    public SearchCityResultsPage searchCity(String city) {
        searchCityInput.setValue(city).pressEnter();
        return new SearchCityResultsPage();
    }

    public Double getWindSpeed() {
        String[] windParameters = windParams.shouldBe(Condition.visible).getText().split(",");
        return Double.parseDouble(windParameters[2]);
    }

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

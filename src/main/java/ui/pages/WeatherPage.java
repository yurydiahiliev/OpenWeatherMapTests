package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ui.core.BasePage;
import ui.core.PageUrl;

import static com.codeborne.selenide.Selenide.$x;

@PageUrl("/city")
public class WeatherPage extends BasePage {

    private SelenideElement searchCityInput = $x("//input[@placeholder='Your city name']");
    private SelenideElement humidility = $x("//td[text()='Humidity']/../td[2]");

    public SearchCityResultsPage searchCity(String city) {
        searchCityInput.setValue(city).pressEnter();
        return new SearchCityResultsPage();
    }

    public Integer getHumidity() {
        return Integer.parseInt(humidility.shouldBe(Condition.visible).getText().replace(" %", ""));
    }

}

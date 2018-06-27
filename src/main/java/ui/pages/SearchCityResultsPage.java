package ui.pages;

import com.codeborne.selenide.Condition;
import ui.core.BasePage;

import static com.codeborne.selenide.Selenide.$$;

public class SearchCityResultsPage extends BasePage {

    public WeatherPage clickFirstLink() {
        $$("table.table a").filter(Condition.visible).first().click();
        return new WeatherPage();
    }
}

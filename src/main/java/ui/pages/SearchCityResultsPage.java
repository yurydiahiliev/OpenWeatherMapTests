package ui.pages;

import ui.core.BasePage;

import static com.codeborne.selenide.Selenide.$$;

public class SearchCityResultsPage extends BasePage {

    public WeatherPage clickFirstLink() {
        $$("table.table a").first().click();
        return new WeatherPage();
    }
}

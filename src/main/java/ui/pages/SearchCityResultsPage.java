package ui.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import ui.core.BasePage;

import static com.codeborne.selenide.Selenide.$$;

public class SearchCityResultsPage extends BasePage {

    private ElementsCollection searchResults = $$("table.table a");

    @Step
    public WeatherPage clickFirstLink() {
        searchResults.shouldHave(CollectionCondition.sizeGreaterThan(0));
        searchResults.filter(Condition.visible).first().click();
        return new WeatherPage();
    }
}

package ui.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import ui.core.BasePage;
import ui.core.PageUrl;

import static com.codeborne.selenide.Selenide.$;

@PageUrl("/")
public class MainPage extends BasePage {

    public SelenideElement notificationAlertElement = $("div.panel-body");

    @Step
    public SelenideElement getNotificationAlertElement() {
        return notificationAlertElement;
    }
}

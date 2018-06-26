package ui.pages;

import com.codeborne.selenide.SelenideElement;
import ui.core.BasePage;
import ui.core.PageUrl;

import static com.codeborne.selenide.Selenide.$;

@PageUrl("/")
public class MainPage extends BasePage {

    private SelenideElement notificationAlertElement = $("div.panel-body");

    public SelenideElement getNotificationAlertElement() {
        return notificationAlertElement;
    }


}

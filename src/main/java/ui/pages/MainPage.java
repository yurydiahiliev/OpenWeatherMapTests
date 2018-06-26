package ui.pages;

import com.codeborne.selenide.SelenideElement;
import ui.core.PageUrl;

import static com.codeborne.selenide.Selenide.$;

@PageUrl("/")
public class MainPage {

    private SelenideElement notificationAlertElement = $("div.panel-body");

    public SelenideElement getNotificationAlertElement() {
        return notificationAlertElement;
    }


}

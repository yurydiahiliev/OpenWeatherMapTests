package ui.pages;

import com.codeborne.selenide.SelenideElement;
import ui.core.PageUrl;

import static com.codeborne.selenide.Selenide.$;

@PageUrl("/")
public class MainPage {

    private SelenideElement notificationAlertElement = $("div.panel-body");
    private SelenideElement signInLink = $("");

    public SelenideElement getNotificationAlertElement() {
        return notificationAlertElement;
    }

    public SignInPage goToSignInPage() {
        return new SignInPage();
    }


}

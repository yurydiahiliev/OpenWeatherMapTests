package ui.pages;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ui.core.BasePage;
import ui.core.PageUrl;
import ui.models.User;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@PageUrl("/users/sign_in")
public class SignInPage extends BasePage {

    private SelenideElement emailInput = $(By.id("user_email"));
    private SelenideElement passwordInput = $(By.id("user_password"));
    private SelenideElement submitBtn = $x("//input[@value='Submit']");
    public MainPage toMainPage = new MainPage();

    public MainPage signInAsUser(User user) {
        emailInput.setValue(user.getEmail());
        passwordInput.setValue(user.getPassword());
        submitBtn.click();
        return new MainPage();

    }
}

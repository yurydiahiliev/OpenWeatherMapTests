package com.openweathermap.tests;

import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeClass;
import ui.pages.SignInPage;

import static ui.core.BasePage.open;

public class LoginRequiredTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void loginToSystemAsUser() {
        open(SignInPage.class)
                .signInAsUser(user)
                .getNotificationAlertElement()
                .shouldHave(Condition.text("Signed in successfully."));
    }
}

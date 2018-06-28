package com.openweathermap.tests;

import com.codeborne.selenide.Condition;
import org.testng.annotations.BeforeClass;
import ui.pages.SignInPage;

import static ui.core.BasePage.at;
import static ui.core.BasePage.open;

public class LoginRequiredTest extends BaseTest {

    @BeforeClass(alwaysRun = true)
    public void loginToSystemAsUser() {
        SignInPage signInPage = open(SignInPage.class);
        if (!at(SignInPage.class)
                .toMainPage
                .notificationAlertElement
                .has(Condition.text("You are already signed in."))) {
            signInPage.signInAsUser(user)
                    .getNotificationAlertElement()
                    .shouldHave(Condition.text("Signed in successfully."));
        }
    }
}

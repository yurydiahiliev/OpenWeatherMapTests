package ui.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class NavigationFooter {

    @Step
    public <T> T openTab(Class<T> tClass, String tabName) {
        $x("//a[text()='" + tabName + "']").click();
        return page(tClass);
    }
}

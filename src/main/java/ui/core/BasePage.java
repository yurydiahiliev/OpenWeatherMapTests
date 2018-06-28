package ui.core;

import api.BaseApiConfig;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

public class BasePage extends BaseApiConfig {

    public static <T> T open(Class<T> tClass) {

        try {
            PageUrl pageUrl = tClass.getDeclaredAnnotation(PageUrl.class);
            if (pageUrl == null) {
                throw new RuntimeException("There is no @PageUrl annotation for class" + tClass.getCanonicalName());
            }

            T pageObject = tClass.newInstance();

            open(openWeatherConfig.baseUiUrl() + pageUrl.value());

            return pageObject;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T at(Class<T> tClass) {
        try {
            return tClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Step
    public static void open(String url) {
        Selenide.open(url);
    }

}

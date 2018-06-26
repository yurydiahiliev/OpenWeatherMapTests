package ui.core;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import utils.OpenWeatherConfig;

public class BasePage {

    private static OpenWeatherConfig config = ConfigFactory.create(OpenWeatherConfig.class);

    public static <T> T open(Class<T> tClass) {

        try {
            PageUrl pageUrl = tClass.getDeclaredAnnotation(PageUrl.class);
            if (pageUrl == null) {
                throw new RuntimeException("There is no @PageUrl annotation for class" + tClass.getCanonicalName());
            }

            T pageObject = tClass.newInstance();

            open(config.baseUiUrl() + pageUrl.value());

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

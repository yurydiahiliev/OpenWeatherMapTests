package ui.utils;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class CustomDriverProvider implements WebDriverProvider {
    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("--ignore-certificate-errors");
        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("chrome");
        browser.setVersion("66.0");
        browser.setCapability("enableVNC", true);
        browser.setCapability("name", "OpenWeatherMap");
        browser.setCapability("timeZone", "Europe/Kiev");
        browser.setCapability(ChromeOptions.CAPABILITY, options);
        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://selenoid:selenoid@35.229.91.161:4445/wd/hub").toURL(),
                    browser
            );
            driver.manage().window().setSize(new Dimension(1920, 1080));
            return driver;
        } catch (Exception e) {
            System.out.println(e);
            return createDriver(desiredCapabilities);
        }
    }
}

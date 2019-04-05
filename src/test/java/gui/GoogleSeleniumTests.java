package gui;

import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Stream;

public class GoogleSeleniumTests {

    @Test
    public void myGoogleTest() throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        // Query the driver to find out more information
        Capabilities actualCapabilities = ((RemoteWebDriver) driver).getCapabilities();
        Stream<?> stream = actualCapabilities.asMap().values().stream();
        stream.forEach(System.out::println);
        // And now use it
        driver.get("http://www.google.com");

        // tear down
        driver.quit();
    }
}

package topics;

import base.AppiumServer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class nativeToWeb {
    @Test
    public  static void sampleTest() throws MalformedURLException, InterruptedException {
        AppiumServer.start();
        // Webdriver io app
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone SE (3rd generation)")
                .setPlatformVersion("17.5")
                .setPlatformName("ios")
                .setSafariAllowPopups(true)
                .setBundleId("org.reactjs.native.example.wdiodemoapp");

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/"),options);

        System.out.println("Current context: " + driver.getContext());
        System.out.println("Handles" + driver.getContextHandles());
        driver.findElement(AppiumBy.accessibilityId("Webview")).click();
        Thread.sleep(3000);
        System.out.println("Handles" + driver.getContextHandles());

        Set<String> handles = driver.getContextHandles();
        driver.context((String) handles.toArray()[1]); // we are on webview
        Thread.sleep(1000);
        System.out.println("after switch webview Current context: " + driver.getContext());

        // click started button
        driver.findElement(By.cssSelector("[class='buttons_pzbO'] [href='/docs/gettingstarted']")).click();
        Thread.sleep(2000);

        driver.context("NATIVE_APP");
        driver.findElement(AppiumBy.accessibilityId("Login")).click();
        driver.findElement(AppiumBy.accessibilityId("input-email")).sendKeys("abc@gmail.com");
        Thread.sleep(2000);

        driver.quit();

        AppiumServer.stop();

    }
}

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSSampleTest {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        XCUITestOptions options = new XCUITestOptions();
        options.setDeviceName("iPhone SE (3rd generation)") //xcrun simctl list | egrep '(Booted)'
                .setPlatformVersion("17.5")
                .setBundleId("com.saucelabs.mydemoapp.rn");
               // .setApp("")
                //.setNoReset(true) // not install the app if it's already installed

        AppiumDriver driver = new IOSDriver(new URL("http://0.0.0.0:4723/"), options);
        Thread.sleep(10000);
        driver.findElement(AppiumBy.accessibilityId("tab bar option menu")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("menu item log in")).click();
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("bob@example.com");
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("Password input field")).sendKeys("10203040");
        Thread.sleep(1000);
        driver.findElement(AppiumBy.accessibilityId("Login button")).click();
        Thread.sleep(3000);
        driver.quit();
    }
}

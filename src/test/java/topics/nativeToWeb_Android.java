package topics;

import base.AppiumServer;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class nativeToWeb_Android {
    @Test
    public  static void sampleTest_android() throws MalformedURLException, InterruptedException {
        AppiumServer.start();
        // Webdriver io app
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("emulator-5554") //adb devices
                .setPlatformVersion("11.0") //adb shell getprop ro.build.version.release
                .setAppPackage("com.wdiodemoapp")
                .setAppActivity("com.wdiodemoapp.MainActivity");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"),options);


        System.out.println("Current context: " + driver.getContext()); // apply for both ios and android - on Native app
        System.out.println("Handles" + driver.getContextHandles()); // on Native app

        driver.findElement(AppiumBy.accessibilityId("Webview")).click();
        Thread.sleep(3000);
        System.out.println("Handles" + driver.getContextHandles()); // two context: 1- Native appp, 2. Webdriver io(webview)

        Set<String> handles = driver.getContextHandles();
        // WEB VIEW
        driver.context((String) handles.toArray()[1]); // we are on webview
        Thread.sleep(1000);
        System.out.println("after switch webview Current context: " + driver.getContext()); //webview only

        // click menu opption
        driver.findElement(By.cssSelector("[aria-label='Toggle navigation bar']")).click();
        Thread.sleep(2000);
        // Click on theme button
        driver.findElement(By.cssSelector("[class='toggle_vylO margin-right--md'] [class='clean-btn toggleButton_gllP']")).click();
        Thread.sleep(2000);
        // Click close button
        driver.findElement(By.cssSelector("button[aria-label='Close navigation bar']")).click();
        Thread.sleep(2000);

        // click started button
        driver.findElement(By.cssSelector("[class='buttons_pzbO'] [href='/docs/gettingstarted']")).click();
        Thread.sleep(2000);

        // NATIVE APP
        driver.context("NATIVE_APP");
        driver.findElement(AppiumBy.accessibilityId("Login")).click();
        driver.findElement(AppiumBy.accessibilityId("input-email")).sendKeys("abc@gmail.com");
        Thread.sleep(2000);

        driver.quit();

        AppiumServer.stop();

    }
}

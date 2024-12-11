/* Appium 1.x */
/*
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
*/

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.util.HashMap;

import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class iOSApp {

    String userName = System.getenv("LT_USERNAME") == null ? "username" :
                    System.getenv("LT_USERNAME");
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "accessKey" :
                    System.getenv("LT_ACCESS_KEY");
    String app_id = System.getenv("LT_APP_ID") == null ? "lt://APP10160522181733921948769896" :
                    System.getenv("LT_APP_ID");
    String grid_url = System.getenv("LT_GRID_URL") == null ?
                    "mobile-hub.lambdatest.com" : System.getenv("LT_GRID_URL");

    IOSDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void iOSApp1(String device, String version, String platform) {

        try {
           /************** Appium 1.x -- Working fine **************/
            DesiredCapabilities capabilities = new DesiredCapabilities();
            /*
            capabilities.setCapability("build", "Java TestNG");
            capabilities.setCapability("name", platform + " " + device + " " + version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion", version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("app", app_id); //Enter your app url
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            //capabilities.setCapability("geoLocation", "HK");
            */

            HashMap<String, Object> ltOptions = new HashMap<String, Object>();

            ltOptions.put("w3c", true);
            ltOptions.put("platformName", platform);
            ltOptions.put("deviceName", device);
            ltOptions.put("platformVersion", version);
            ltOptions.put("minorOsVersionEnabled",true);
            ltOptions.put("devicelog", true);
            ltOptions.put("visual", true);
            ltOptions.put("geoLocation","IN");
            ltOptions.put("network", true);
            ltOptions.put("timezone","UTC+05:30");
            ltOptions.put("video", true);
            ltOptions.put("build","iOS App");
            ltOptions.put("name", "iOS App");
            /* App or Bundle Identifier for pre-installed apps */
            /* ltOptions.put("bundleId", "com.example.myapp"); */
            ltOptions.put("app", app_id);
            /* Set this capability to true for private devices */
            ltOptions.put("private", false);
            ltOptions.put("language", "en");
            ltOptions.put("appiumVersion", "2.2.1");
            ltOptions.put("locale", "en");
            ltOptions.put("autoAcceptAlerts", true);
            ltOptions.put("isRealMobile", true);

            capabilities.setCapability("LT:Options", ltOptions);

            String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";

            /************** Appium 1.x **************/
            // driver = new AppiumDriver(new URL(hub), capabilities);
            /************** Appium 2.x **************/
            /************** works with latest Java Client 9.3.0 **************/
            driver = new IOSDriver(new URL(hub), capabilities);

            WebDriverWait Wait = new WebDriverWait(driver,
                        Duration.ofSeconds(30));

            //Changes the color of the text
            Wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("color"))).click();
            Thread.sleep(1000);

            //Changes the text to "Proverbial"
            Wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Text"))).click();
            Thread.sleep(1000);

            //Toast will be visible
            Wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("toast"))).click();
            Thread.sleep(1000);

            //Notification will be visible
            Wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("notification"))).click();
            Thread.sleep(4000);

            //Opens the geolocation page
            Wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("geoLocation"))).click();
            Thread.sleep(4000);

            //Takes back
            driver.navigate().back();

            //Takes to speedtest page
            Wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("speedTest"))).click();
            Thread.sleep(4000);
            driver.navigate().back();

            //Opens the browser
            Wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("Browser"))).click();
            Thread.sleep(1000);
            WebElement url = (WebElement) driver.findElement(AppiumBy.accessibilityId("url"));
            url.click();
            url.sendKeys("https://www.lambdatest.com");
            Wait.until(ExpectedConditions.presenceOfElementLocated(
                AppiumBy.accessibilityId("find"))).click();
            Thread.sleep(1000);
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

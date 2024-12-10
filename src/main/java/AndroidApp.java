import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.HashMap;

public class AndroidApp {

    String userName = System.getenv("LT_USERNAME") == null ? "username" : 
            System.getenv("LT_USERNAME"); //Add username here
    String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "accessKey" : 
            System.getenv("LT_ACCESS_KEY"); //Add accessKey here
    String app_id = System.getenv("LT_APP_ID") == null ? "lt://APP10160541141733490781359873" :
            System.getenv("LT_APP_ID");
    String grid_url = System.getenv("LT_GRID_URL") == null ? "mobile-hub.lambdatest.com" :
            System.getenv("LT_GRID_URL");

    /* Appium 1.x */
    /* AppiumDriver driver; */
    /* Appium 2.x */
    AndroidDriver driver;

    @Test
    @org.testng.annotations.Parameters(value = {"device", "version", "platform"})
    public void AndroidApp1(String device, String version, String platform) {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            /************** Appium 1.x -- Working fine **************/
            /*
            capabilities.setCapability("build", "Java TestNG");
            capabilities.setCapability("name", platform + " " + device + " " + version);
            capabilities.setCapability("deviceName", device);
            capabilities.setCapability("platformVersion", version);
            capabilities.setCapability("platformName", platform);
            capabilities.setCapability("isRealMobile", true);
            capabilities.setCapability("app", app_id); //Enter your app url
            capabilities.setCapability("deviceOrientation", "PORTRAIT");
            capabilities.setCapability("network", false);
            capabilities.setCapability("visual", true);
            capabilities.setCapability("devicelog", true);
            capabilities.setCapability("autoGrantPermissions", true);
            */

            /************** Appium 1.x -- Working fine **************/
            /* Sai Gist - https://gist.github.com/saikrishna321/3ef1ba38bae46e52b2eebabcca14bb04 */

            /*
            capabilities.setCapability("appium:build", "Java TestNG");
            capabilities.setCapability("appium:name", platform + " " + device + " " + version);
            capabilities.setCapability("appium:deviceName", device);
            capabilities.setCapability("appium:platformVersion", version);
            capabilities.setCapability("appium:platformName", platform);
            capabilities.setCapability("appium:isRealMobile", true);
            capabilities.setCapability("appium:app", app_id); //Enter your app url
            capabilities.setCapability("appium:deviceOrientation", "PORTRAIT");
            capabilities.setCapability("appium:network", false);
            capabilities.setCapability("appium:visual", true);
            capabilities.setCapability("appium:devicelog", true);
            capabilities.setCapability("appium:autoGrantPermissions", true);
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
            ltOptions.put("build","MMT Troubleshooting");
            ltOptions.put("name", "MMT");
            ltOptions.put("app", app_id);
            ltOptions.put("language", "en");
            ltOptions.put("appiumVersion", "2.2.1");
            ltOptions.put("locale", "en");
            ltOptions.put("autoAcceptAlerts", false);
            ltOptions.put("isRealMobile", true);

            capabilities.setCapability("LT:Options", ltOptions);

            String hub = "https://" + userName + ":" + accessKey + "@" + grid_url + "/wd/hub";

            /************** Appium 1.x **************/
            // driver = new AppiumDriver(new URL(hub), capabilities);
            /************** Appium 2.x **************/
            /************** works with latest Java Client 9.3.0 **************/
            driver = new AndroidDriver(new URL(hub), capabilities);

            WebElement color = (WebElement) driver.findElement(By.id("com.lambdatest.proverbial:id/color"));
            //Changes color to pink
            color.click();
            Thread.sleep(1000);
            //Back to original color
            color.click();

            WebElement text = (WebElement) driver.findElement(By.id("com.lambdatest.proverbial:id/Text"));
            //Changes the text to "Proverbial"
            text.click();

            //toast will be visible
            WebElement toast = (WebElement) driver.findElement(By.id("com.lambdatest.proverbial:id/toast"));
            toast.click();

            //notification will be visible
            WebElement notification = (WebElement) driver.findElement(By.id("com.lambdatest.proverbial:id/notification"));
            notification.click();
            Thread.sleep(2000);

            //Opens the geolocation page
            WebElement geo = (WebElement) driver.findElement(By.id("com.lambdatest.proverbial:id/geoLocation"));
            geo.click();
            Thread.sleep(5000);

            //takes back to home page
            WebElement home = (WebElement) driver.findElement(AppiumBy.accessibilityId("Home"));
            home.click();

            //Takes to speed test page
            WebElement speedtest = (WebElement) driver.findElement(By.id("com.lambdatest.proverbial:id/speedTest"));
            speedtest.click();
            Thread.sleep(5000);

            WebElement Home = (WebElement) driver.findElement(AppiumBy.accessibilityId("Home"));
            Home.click();

            //Opens the browser
            WebElement browser = (WebElement) driver.findElement(AppiumBy.accessibilityId("Browser"));
            browser.click();

            WebElement url = (WebElement) driver.findElement(By.id("com.lambdatest.proverbial:id/url"));
            url.sendKeys("https://www.lambdatest.com");

            WebElement find = (WebElement) driver.findElement(By.id("com.lambdatest.proverbial:id/find"));
            find.click();
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

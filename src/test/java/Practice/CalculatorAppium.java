package Practice;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

@Listeners(EvcultListernce.class)
public class CalculatorAppium {
	public static AppiumDriver driver;
		@Test
		public void test(ITestContext context) throws IOException {
			DesiredCapabilities ds=new DesiredCapabilities();
			ds.setCapability("platformName", "Android");
			ds.setCapability("appium:platformVersion", "16");
			ds.setCapability("appium:automationName", "UiAutomator2");
			ds.setCapability("appium:appActivity", "com.android.calculator2.Calculator");
			ds.setCapability("appium:appPackage", "com.google.android.calculator");
			ds.setCapability("appium:deviceName", "emulator-5554");
			
			driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),ds);
			
			context.setAttribute("driver", driver);
			
			
			driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"9\"]")).click();
            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"plus\"]")).click();
            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"6\"]")).click();
            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"equals\"]")).click();
            // Get result
            WebElement resultElement = driver.findElement(By.xpath("//android.widget.TextView[@resource-id=\"com.google.android.calculator:id/result_final\"]"));
            String resultText = resultElement.getText();
            System.out.println("Result: " + resultText);
            //fail
            driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"saddam\"]")).click();
            // Check result
            if (resultText.equals("15")) {
                System.out.println("pass");
            } else {
                System.out.println("fail");
            }
            
            driver.quit();
		}
}

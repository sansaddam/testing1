package Practice;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class EVCULT {
	
	@Test
	public void test() throws MalformedURLException {
		DesiredCapabilities ds=new DesiredCapabilities();
		ds.setCapability("platformName", "Android");
		ds.setCapability("appium:platformVersion", "16");
		ds.setCapability("appium:automationName", "UiAutomator2");
		ds.setCapability("appium:appActivity", "com.iotapp.MainActivity");
		ds.setCapability("appium:appPackage", "com.iotapp");
		ds.setCapability("appium:deviceName", "emulator-5554");
		AppiumDriver driver=new AndroidDriver(new URL("http://127.0.0.1:4723"),ds);
	}

}

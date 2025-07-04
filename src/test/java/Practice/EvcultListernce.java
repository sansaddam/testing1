package Practice;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.appium.java_client.AppiumDriver;

public class EvcultListernce  implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ITestContext context=result.getTestContext();
		AppiumDriver driver=(AppiumDriver) context.getAttribute("driver");
		if (driver!=null) {
			String time = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	        String filename = "screenshot"+time+".png";
	         File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	         File destFile = new File("../Camunda/Screenshot/"+filename);
	         try {
				FileUtils.copyFile(srcFile, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("driver is null");
		}
		
	}
	

}

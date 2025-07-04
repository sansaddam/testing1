package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Groups {

	@Test(groups = {"smoke"})
	public void smoke() {
		WebDriver wd=new ChromeDriver();
	}
	
	@Test(groups = {"sanity"})
	public void sanity() {
		WebDriver wd=new FirefoxDriver();
		System.out.println("Hussain");
	}
}

package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Demo {
	@Test(groups = {"sanity"})
	public void main() {
		WebDriver wd=new ChromeDriver();
		System.out.println("saddam");
	}
}

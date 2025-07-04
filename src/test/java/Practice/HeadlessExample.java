package Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class HeadlessExample {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless"); // Run in headless mode
//        options.addArguments("--disable-gpu"); // For Windows compatibility
//        options.addArguments("--window-size=1920,1080");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://inspector.appiumpro.com/");

        System.out.println(driver.getTitle());
        driver.quit();
    }
}

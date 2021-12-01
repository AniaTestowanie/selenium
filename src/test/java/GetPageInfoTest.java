import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class GetPageInfoTest {

    /*
    Tu pokazane zostanie w jaki sposób pobrać tytuł strony oraz jego adres url
     */

    @Test
    public void getPageInfo() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        System.out.println(driver.getTitle());
        System.out.println(driver.getCurrentUrl());



    }
}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class OurFirstTest {

    /*
    To jest komentarz wielolinijkowy
     */

    //To jest komentarz jednolinijkowy

    @Test
    public void sayHello() {
        System.out.println("Cześć! Witam Cię w kursie Selenium!");
        WebDriver driver = new FirefoxDriver();
    }
}

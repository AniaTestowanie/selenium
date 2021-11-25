import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;

public class PracaDomowa {
    /*
    Otwórz przeglądarkę, otwórz dowolną stronę internetową
    Skorzystaj z narzędzi deweloperskich i spróbuj zlokalizować różne elementy na stronie internetowej
    za pomocą:
    1) selektorów CSS
    2) selektorów XPath
     */



    /*
    Napisz metodę która jako parametr będzie przyjmowała Stringa i będzie zwracała wartość true albo false
    Metoda ma sprawdzać, czy jakaś wartość znajduje się w naszym selekcie.
     */

    @Test
    public void trueOrFalseSelect() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        WebElement selectCar = driver.findElement(By.cssSelector("select"));
        Select cars = new Select(selectCar);
        List<WebElement> options = cars.getOptions();

        String ckeckOption = "Volvo";

        for(WebElement option : options){
            if(option.getText().equals("Volvo")) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }


    }

    /*
    Powyżej była samodzielna próba, zresztą nieudana. Poniżej bardziej elastyczna metoda, którą można zastosować w naszej
    klasie AkcjeNaELementach
     */

    public boolean checkOption (String optionText, WebElement element) {
      Select select = new Select(element);
      List<WebElement> allOptions = select.getOptions();
      for(WebElement option : allOptions) {
          if(option.getText().equals(optionText))
              return true;
        }
        return false;
    }
}

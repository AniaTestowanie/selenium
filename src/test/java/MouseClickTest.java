import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class MouseClickTest {

    /*
    Aby zasymulować wykonanie na stronie prawego przycisku myszką, posłużymy się klasą Actions.
    Stworzymy nową zmienną (np. action) i obiekt klasy, a jako parametr podamy webdrivera.
    Następnie będziemy mogli wywołać metodę contextClick(). Metoda ta ma dwie implementacje:
    dla jednej z nich możemy przekazać WebElement, dla drugiej nie.

    W naszym kodzie zaczniemy od drugiej możliwości.
    Aby to zadziałało, to dodatkowo musimy zawsze skorzystać z metody perform().

    Taka metoda spowoduje kliknięcie prawym przyciskiem myszy w dowolnym miejscu.

    Jeśli chcielibyśmy kliknąć prawym przyciskiem myszy w określone miejsce, to musimy skorzystać z pierwszej
    implementacji, która przyjmuje WebElement.


    Następnie spróbujemy zasymulować podwójne szybkie kliknięcie lewym przyciskiem myszy.
    W tym celu skorzystamy ze strony testowej: https://testeroprogramowania.github.io/selenium/doubleclick.html
    na której znajduje się przycisk, który na podwójne kliknięcie myszy reaguje otworzeniem nowej strony.

    Do podwójnego kliknięcia przeznaczona jest metoda doubleClick() i podobnie jak w poprzednim przypadku mamy do czynienia
    z dwoma implementacjami. W tym wypadku jeśli chcemy wykonać podwójne kliknięcie w konkretnym miejscu, to musimy
    skorzystać z implementacji przyjmującej WebElement

    Skoro znamy metodę click(), to czemu nie możemy zastąpić: action.doubleClick(driver.findElement(By.id("bottom"))).perform();
    następującym zapisem:

    WebElement button = driver.findElement(By.id("bottom"));
    button.click();
    button.click();

    Otóż w powyższym przykładzie i tak otrzymamy pozytywny wynik testu, ale akcja wywołana szybkim podwójnym kliknięciem
    (otwarcie nowej strony) nie wykona się.


    Kolejną akcją jaką spróbujemy wykonać, będzie najechanie na element (hover). Może to być potrzebne np. w takiej sytuacji, że
    po najechaniu na dany element może się zmienić styl lub mogą się pojawić dodatkowe opcje

    Taką funkcjonalność będzie można zaobserwować na stronie podstawowej

    Wówczas korzystamy z metody moveToElement() i podajemy element na który chcemy najechać

    W klasie Actions możemy też skorzystać z wielu innych metod takich jak np. dragAndDrop czy np. clickAndHold etc.

     */

    @Test
    public void mouseClick() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/doubleclick.html");


        Actions action = new Actions(driver);

        //action.contextClick().perform();
        action.contextClick(driver.findElement(By.id("bottom"))).perform();

        action.doubleClick(driver.findElement(By.id("bottom"))).perform();


        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        WebElement heading = driver.findElement(By.tagName("h1"));

        action.moveToElement(heading).perform();


    }
}

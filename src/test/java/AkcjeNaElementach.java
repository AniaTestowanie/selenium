import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class AkcjeNaElementach {

    @Test
    public void AkcjeNaElementach() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\dane do kursu selenium\\Test.html");


        // Klikanie

        WebElement clickOnMeButton = driver.findElement(By.id("clickOnMe"));

        /*
        Najpierw musimy odnaleźć żądany element. Kiedy już to zrobimy, możemy wywołać na takim obiekcie metodę click,
        która kliknie na nasz element
         */

        clickOnMeButton.click();


        // Obsługa niespodziewanych alertów

        /*
        Powyższa akcja kliknięcia uruchamia nam popup.
        Dopóki taki popup jest otwarty nie będziemy mogli wykonywać innych akcji na elementach strony,
        a każda taka próba zakończy się fiaskiem testu i informacją o wyjątku:
        org.openqa.selenium.UnhandledAlertException: unexpected alert open: {Alert text : Hello world!}

        Zatem zanim przystąpimy do dalszych testów musimy obsłużyć alert

        W tym celu musimy przełączyć się do tego alertu i wykonać na nim określoną akcję
        (w naszym przypadku będzie to ok, czyli jego zaakceptowanie, ale równie dobrze możemy wybrać dismiss)

        Możemy to zapisać dwojako - bez przypisywania zmiennej:
        driver.switchTo().alert().accept();
        lub z przypisaniem zmiennej:
        Alert alert = driver.switchTo().alert();
        alert.accept();
         */

        Alert alert = driver.switchTo().alert();
        alert.accept();


        // Wprowadzanie wartości do pól tekstowych

        WebElement firstNameInput = driver.findElement(By.id("fname"));
        firstNameInput.sendKeys("Jaśmina");

        //W metodzie sendKeys() musimy podać CharSequence czyli sekwencję znaków

        /*
        Gdybyśmy chcieli podać następnie kolejne imię do sprawdzenia i powtórzylibyśmy powyższą metodę,
        to dopisze ona nam podaną wartość do pierwszego podanego imienia. Nie jest to dla nas pożądany efekt,
        dlatego najpierw musimy wyczyścić pole
         */

        firstNameInput.clear();
        firstNameInput.sendKeys("Bardzobardzobardzobardzobardzodługieimię");


        // Klasa Select - wybór opcji z dropdowna

        /*
        Selenium dostarcza nam klasę Select, która ułatwia nam wybór opcji z listy rozwijanej

        Korzystamy z klasy Select i tworzymy nowy obiekt tej klasy carSelect; w nawiasach musimy podać WebElement,
        zatem musimy zlokalizować właściwy element na stronie; wiemy że mamy tylko jeden tag select na stronie, zatem możemy skorzystać
        z techniki tagname
        Na stworzonym obiekcie klasy Select możemy korzystać ze specjalnych metod i określić w jaki sposób mamy wybrać opcję
         */

        Select carSelect = new Select(driver.findElement(By.tagName("select")));
        carSelect.selectByIndex(3);                    // pamiętamy że indeksy rozpoczynają się od 0; jest to kolejność na liście rozwijanej
        carSelect.selectByVisibleText("Volvo");        // wybieramy po tekście widocznym na liście rozwijanej
        carSelect.selectByValue("mercedes");           // wybieramy po value które jest zaszyte w strukturze html
    }

}

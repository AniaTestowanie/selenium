import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ElementExistsTest {


    /*
    Dla sprawdzenia, czy dany element istnieje na stronie (przez to rozumiemy, że jest dostępny, istnieje w strukturze html strony
    -> jak np. ukryty, niewidoczny dla użytkownika paragraf), metoda findElement nie jest idealna, ponieważ
    nie znalezienie danego elementu przerywa nam wykonanie testu - otrzymujemy wyjątek NoSuchElementException oraz fail testu.
    Takie zachowanie zaobserwujemy np. w przypadku:
    driver.findElement(By.id("topSecret"));

    Dlatego wyszukiwanie naszego elementu możemy opakować w metodę boolean (zwracającą true lub false). Dodatkowo,
    wiedząc o możliwości pojawienia się wyjątku, uwzględniamy try - catch.
    Patrz na metodę: elementExist

    Możemy też pobrać listę elementów i sprawdzić czy jej długość jest większa od zera (jeśli lista ma zero elementów, oznacza to że element
    nie istnieje)
    Patrz na metodę: elementExistSecond

    Jeśli będziemy chcieli sprawdzić, czy element będzie wyświetlany, czyli dostępny dla użytkownika, możemy posłużyć się prostszą,
    specjalnie dedykowaną metodą: isDisplayed(); Zwróci nam ona true, jeśli znaleziony zostanie element widoczny dla użytkownika oraz
    zwróci nam false, jeśli element jest dostępny na stronie, ale jest on niewidoczny dla użytkownika

    Kolejną możliwością do zweryfikowania jest sprawdzenie, czy możemy wejść w interakcję z elementem. Gdyby się okazało, że jakieś
    pole/przycisk na którym chcielibyśmy wykonać akcję jest nieaktywne / wyszarzone, to wówczas również otrzymalibyśmy wyjątek.
    Dlatego warto uwzględnić takie sprawdzenie w swoim teście.

    Aby zobaczyć jak wygląda nieaktywny element, możemy tymczasowo edytować kod html i dezaktywować go, np. przycisk "Kliknij mnie!".
    W tym celu edytujemy kod html w zakładce Elements i dodajemy mu atrybut disabled:
    <button type="button" id="clickOnMe" onclick="alert('Hello world!')" disabled="">Kliknij mnie!</button>
    Podobnie można zrobić z inputem
    Taka edycja jak powyżej tylko tymczasowo zmienia nam właściwość strony i nie powoduje trwałej zmiany (po odświeżeniu
    wskazane pola znowu będą aktywne)

    Do weryfikacji możliwości wejścia w interakcję z elementem, możemy wykorzystać dedykowaną metodę isEnabled();
    Analogicznie jak w przypadku poprzedniej metody, otrzymamy true jeśli możemy wejść w interakcję z elementem, a false
    jeśli nie ma takiej możliwości (nadal jeśli nie zostanie znaleziony element na stronie otrzymamy wyjątek!)

    Do sprawdzenia czy checkbox jest zaznaczony również posiadamy dedykowaną metodę.


    */

    WebDriver driver;

    @Test
    public void elementExistsTest() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        // sprawdzenie po wyjątku:
        System.out.println("p = " + elementExist(By.tagName("p")));
        System.out.println("topSecret = " + elementExist(By.id("topSecret")));

        // sprawdzenie długości listy:
        System.out.println("2p = " + elementExistSecond(By.tagName("p")));
        System.out.println("2topSecret = " + elementExistSecond(By.id("topSecret")));

        // sprawdzenie wyświetlania na stronie:
        System.out.println("is not displayed = " + driver.findElement(By.className("topSecret")).isDisplayed());
        System.out.println("is displayed = " + driver.findElement(By.id("newPage")).isDisplayed());

        // sprawdzenie możliwości wejścia w interakcję z elementem:
        System.out.println("is enabled = " + driver.findElement(By.id("clickOnMe")).isEnabled());

        // sprawdzenie czy checkbox jest zaznaczony:
        WebElement checkbox = driver.findElement(By.cssSelector("input[type='checkbox']"));
        System.out.println("is selected = " + checkbox.isSelected());
        checkbox.click();
        System.out.println("is selected = " + checkbox.isSelected());
    }

    public boolean elementExist(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public boolean elementExistSecond(By locators) {
        return driver.findElements(locators).size() > 0;
    }



}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WeryfikacjaSelektorówCssNaPrzegladarce {

    /*
    Aby sprawdzić, czy dany selektor CSS jest poprawny i czy znajdujemy go na stronie, musimy najpierw otworzyć stronę internetową, na której będziemy szukać elementów.
    W naszym przypadku będzie to: https://testeroprogramowania.github.io/selenium/basics.html
    Następnie musimy otworzyć narzędzia deweloperskie (F12 lub ctrl+shift+k).
    Będzie nas w tym przypadku interesować zakładka "Console", a nie "Elements" jak do tej pory, na której była opisana struktura naszej strony.

    Aby sprawdzić czy nasz selektor jest poprawny, to musimy w zakładce Console wprowadzić:

    $$("NaszSelektor"), na przykład:

    $$("#clickOnMe")

    Wówczas po kliknięciu przycisku enter powinniśmy otrzymać informację o znalezieniu takiego elementu. W tym wypadku wiemy już, że to jest przycisk

    Takie przetestowanie na przeglądarce selektora jest szybsze i daje nam pewność, że możemy wykorzystać ten element na kodzie.

    Kolejne przykłady

    $$(".topSecret") -> dostajemy informację o znalezieniu odpowiedniej klasy
    $$("input") -> dostajemy informację o znalezieniu aż ośmiu elementów typu input (pamiętamy że numeracja zaczyna się od 0, dlatego ostatni element będzie oznaczony cyfrą 7)
    $$("a") -> dostajemy informację o dwóch linkach, wraz z podaniem konkretnych adresów stron internetowych
    $$("[name='fname']")
    $$("[class='topSecret']") -> znalezienie paragrafu topSecret

    Wszystkie te elementy będą nam się podświetlać po najechaniu
     */

    /*
    Pozostałe akcje które możemy wykonać za pomocą zakładki Console w narzędziach deweloperskich:

    Zaznaczenie wszystkich elementów na stronie
    W zakładce Console należy wpisać:
    $$("*")
    Taka komenda pozwoli nam znaleźć wszystkie tagi na stronie
    */


    @Test
    public void findElements(){
        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

    }
}

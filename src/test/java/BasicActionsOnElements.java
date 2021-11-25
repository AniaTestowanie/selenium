import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.List;

public class BasicActionsOnElements {

    /*

     */

    @Test
    public void performActionsTest() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/");
        driver.manage().window().maximize();


        // Klikanie na element

        /*
        Najpierw musimy odnaleźć żądany element. Kiedy już to zrobimy, możemy wywołać na takim obiekcie metodę click,
        która kliknie na nasz element
         */

        WebElement basicPageLink = driver.findElement(By.linkText("Podstawowa strona testowa"));
        basicPageLink.click();

        // alternatywne podejście za pomocą zapisu jednolinijkowego
        // (najpierw kliknięcie pola input z id "fname", a następnie buttona z id "clickOnMe"):
        driver.findElement(By.id("fname")).click();
        driver.findElement(By.id("clickOnMe")).click();

        // Obsługa niespodziewanych alertów

        /*
        Powyższa akcja kliknięcia na przycisk clickOnMe uruchamia nam pop-up (alert).
        Dopóki taki popup jest otwarty nie będziemy mogli wykonywać innych akcji na elementach strony,
        a każda taka próba zakończy się fiaskiem testu i informacją o wyjątku:
        org.openqa.selenium.UnhandledAlertException: unexpected alert open: {Alert text : Hello world!}

        Zatem zanim przystąpimy do dalszych testów musimy obsłużyć alert

        W tym celu musimy przełączyć się do tego alertu i wykonać na nim określoną akcję
        (w naszym przypadku będzie to ok, czyli jego zaakceptowanie)

        Możemy to zapisać dwojako - bez przypisywania zmiennej:
        driver.switchTo().alert().accept();
        lub z przypisaniem zmiennej (w tym wypadku odwołujemy się do zmiennej klasy Alert):
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Są różne rodzaje alertów. Możemy się z nimi zapoznać na stronie: https://www.w3schools.com/js/js_popup.asp

        Wśród dostępnych akcji na alertach są także metody:
        alert.dismiss();            // umożliwia wybór opcji Cancel
        alert.sendKeys("abc");      // umożliwia wpisanie wartości tekstowej jeśli w naszym alercie jest pole tekstowe typu input

         */

        Alert alert = driver.switchTo().alert();
        alert.accept();




        // Wybieranie opcji z check-boxa lub radio-buttona

        /*
        Najpierw sprawdzamy w strukturze strony, po jakim atrybucie możemy zlokalizować dany element
        Następnie musi tylko opisać klikanie po odpowiednich elementach.
        Dla checkboxa będzie to wyglądało w następujący sposób:
         */

        driver.findElement(By.cssSelector("[type='checkbox']")).click();

        // dla radio-buttona będzie to z kolei:

        driver.findElement(By.cssSelector("[value='male']")).click();


        // Wprowadzanie wartości do pól tekstowych

        WebElement firstNameInput = driver.findElement(By.id("fname"));
        firstNameInput.sendKeys("Jaśmina");

        //W metodzie sendKeys() musimy podać CharSequence czyli sekwencję znaków

        /*
        Gdybyśmy chcieli podać następnie kolejne imię do sprawdzenia i powtórzylibyśmy powyższą metodę,
        to dopisze ona nam podaną wartość do pierwszego podanego imienia. Nie jest to dla nas pożądany efekt,
        dlatego najpierw musimy wyczyścić pole
        Gdybyśmy tego pola nie wyczyścili, to wprowadzając kolejną wartość do inputa, dopisze się nam ona do już istniejącego ciągu znaków.
         */

        firstNameInput.clear();

        firstNameInput.sendKeys("Bardzobardzobardzobardzobardzodługieimię");

        firstNameInput.clear();

        // Możemy również w inny sposób zapisać podanie wartości do inputa (tym razem bez definiowania zmiennej WebElement):

        driver.findElement(By.id("fname")).sendKeys("123");

        WebElement usernameInput = driver.findElement(By.name("username"));
        usernameInput.clear();
        usernameInput.sendKeys("admin");

        // Symulowanie naciśnięcia przycisku na klawiaturze

        /*
        Na naszej stronie testowej po naciśnięciu przycisku enter na klawiaturze będąc na polu "Nazwa użytkownika" lub "Hasło"
        formularz będzie się wysyłał (zachowanie analogiczne jak kliknięcie przycisku "Submit")
        Z kolei po kliknięciu tabulatorem, kursor zostanie przeniesiony na kolejne pole
        Aby zasymulować takie zachowanie, podobnie jak w przypadku wprowadzania ciągu znaków, będziemy się posługiwać się metodą sendKeys,
        przy czym w nawiasie nie wpisujemy ciągu znaków, a wybieramy opcję Keys.WYBRANY_PRZYCISK
         */

        usernameInput.sendKeys(Keys.TAB);       // przeniesie nas do pola Hasło
        driver.findElement(By.name("password")).sendKeys(Keys.ENTER);

        // wysłanie formularza powoduje nam pojawienie się dwóch alertów następujących po sobie,
        // zatem musimy te alerty obsłużyć zanim przejdziemy do kolejnych testów

        Alert firstAlert = driver.switchTo().alert();
        firstAlert.accept();
        Alert secondAlert = driver.switchTo().alert();
        secondAlert.accept();
        // w przypadku drugiego alertu moglibyśmy także zapisać to w prostszy sposób:
        // driver.switchTo().alert().accept();


        // Wybór opcji z dropdowna, selecta

        /*
        Selenium dostarcza nam klasę Select, która ułatwia nam wybór opcji z listy rozwijanej

        Korzystamy z klasy Select i tworzymy nowy obiekt tej klasy carSelect; w nawiasach musimy podać WebElement,
        zatem musimy zlokalizować właściwy element na stronie; wiemy że mamy tylko jeden tag select na stronie, zatem możemy skorzystać
        z techniki tagname
        Na stworzonym obiekcie klasy Select możemy korzystać ze specjalnych metod i określić w jaki sposób mamy wybrać opcję

        Istnieją dwie możliwości zapisania naszej klasy Select; w zależności na co się zdecydujemy, przy zaznaczaniu z opcji rozwijanej
        będziemy się powoływać na zmienną cars lub carSelect (wynik będzie taki sam dla obydwu zmiennych)
         */

        WebElement selectCar = driver.findElement(By.cssSelector("select"));
        Select cars = new Select(selectCar);

        // lub:

        Select carSelect = new Select(driver.findElement(By.tagName("select")));

        cars.selectByIndex(3);                    // pamiętamy że indeksy rozpoczynają się od 0; jest to kolejność na liście rozwijanej
        carSelect.selectByVisibleText("Volvo");        // wybieramy po tekście widocznym na liście rozwijanej
        carSelect.selectByValue("mercedes");           // wybieramy po value które jest zaszyte w strukturze html


        // Pobieranie tekstu znajdującego się wewnątrz znaczników option z listy rozwijanej, z selecta

        /*
        Najpierw pobieramy wszystkie dostępne opcje:
        cars.getOptions();

        Metoda ta zwraca nam listę.

        Następnie przypisujemy wynik do zmiennej:
         */

        List<WebElement> options = cars.getOptions();

        /*
        Czy moglibyśmy teraz wypisać na ekranie dostępne opcje? Nie do końca - to są obiekty WebElement, coś więcej niż tekst
        Ale możemy z tego WebElementu uzyskać ten tekst. Zamiast wypisywać jeden po drugim, możemy skorzystać z pętli
         */

        for (WebElement option : options) {  // dla elementu option znajdującego się w liście options wykonamy określoną akcję
            System.out.println(option.getText());
        }

        /*
        Metoda getText(); jest dostępna dla wszystkich WebElementów
        Możemy na przykład wypisać tekst, który znajduje się w naszym linku
         */

        WebElement testLink = driver.findElement(By.linkText("IamWeirdLink"));
        System.out.println(testLink.getText());

        // Poniżej rozwiązanie pracy domowej:

        PracaDomowa selectCheck = new PracaDomowa();
        System.out.println(selectCheck.checkOption("Audi", selectCar));
        System.out.println(selectCheck.checkOption("Volkswagen", selectCar));


        // Pobieranie wartości z pola tekstowego

        /*
        Aby pobrać wprowadzoną do pola input wartość, nie możemy posłużyć się metodą getText(),
        jest do tego przeznaczona zupełnie inna metoda:
         */

        WebElement password = driver.findElement(By.name("password"));
        System.out.println(password.getAttribute("value"));

        // Pobieranie wartości z ukrytego elementu

        /*
        Spróbujemy pobrać treść ukrytego paragrafu znajdującego się na stronie testowej za pomocą kilku różnych metod
         */

        WebElement parHidden = driver.findElement(By.cssSelector(".topSecret"));
        System.out.println("getText(): " + parHidden.getText());                                            // nie będzie podanej żadnej wartości -> metoda ta nie zadziała
        System.out.println("getAttribute(value): " + parHidden.getAttribute("value"));                // tu zwrócony zostanie null, gdyż nie mamy w przypadku paragrafu takiego atrybutu jak value
        System.out.println("getAttribute(text content): " + parHidden.getAttribute("textContent"));   // to jest prawidłowa metoda





    }

}

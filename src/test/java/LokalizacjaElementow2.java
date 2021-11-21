import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

public class LokalizacjaElementow2 {





    @Test
    public void LokalizacjaElementow() {

        WebDriver driver = getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");


        // Lokalizacja elementów po id


        /*
        id to identyfikator który powinien być unikalny w całej strukturze. Za jego pomocą najłatwiej jest zidentyfikować dany element
        */
        driver.findElement(By.id("clickOnMe"));

        /*
        Gdy naciśniemy ctrl i klikniemy findElement dowiemy się, że findElement zwraca obiekt typu WebElement, a WebElement jest interfejsem
        Dla ułatwienia i przejrzystości możemy przypisać go do konkretnej zmiennej
        */
        WebElement firstNameInputID = driver.findElement(By.id("fname"));
        WebElement buttonClickMeID = driver.findElement(By.id("newPage"));


        /* Lub jeszcze łatwiej: */
        By buttonKliknijMnieID = By.id("clickOnMe");
        WebElement buttonKliknijMnie = driver.findElement(buttonKliknijMnieID);

        /*
        Znajdowanie elementów na stronie za pomocą id to pierwsza technika lokalizacji i uważana jest za najlepsze rozwiązanie,
        o ile deweloperzy frontendowi nadają unikalne id elementom na stronie
        */


        // Lokalizacja elementów po name

        By firstNameName = By.name("fname");
        WebElement firstName = driver.findElement(firstNameName);

        /* lub alternatywny zapis: */

        WebElement firstNameInputName = driver.findElement(By.name("fname"));

        /* W naszym przypadku  - inputu First Name wartość id jest taka sama jak wartości name, ale nie zawsze tak musi być */


        // Lokalizacja elementów po tekście linku

        /*
        Lokalizacja elementów po tekście linku, czyli tekstu który znajduje się wewnątrz tagu html,
        a ten tag to jest po prostu literka a - tak oznaczamy linki
        Chodzi o tekst linku (np. sprawdzisz to tu), a nie adres strony

        Problem z tą techniką jest taki, że jeśli nie znamy całości tekstu linku, to ta metoda nam nie zadziała
        (nawet jeśli ucięlibyśmy lub dodalibyśmy ostatnią literkę)
        Dlatego też mamy podobną technikę, która może nam eliminować ten problem - znajduje ona element
        po częściowym tekście linku. Metoda ta znajdzie nam również cały tekst linku.
         */

        WebElement w3SchoolLink = driver.findElement(By.linkText("Visit W3Schools.com!"));
        WebElement w3SchoolLink2 = driver.findElement(By.partialLinkText("Visit"));
        WebElement w3SchoolLink3 = driver.findElement(By.partialLinkText("Visit W3Schools.com!"));

        /*
        Pamiętamy, że powyższe metody dotyczą wyłącznie tekstów linku (a nie zadziałają nam już np. do labeli)
        */


        // Lokalizacja elementów po klasie

        /*
        Na stronie testowej jeden z akapitów był ukryty atrybutem hidden, abyśmy mogli go znaleźć,
        musimy wyedytować kod html i usunąć tę adnotację
         */

        WebElement topSecretParagraph = driver.findElement(By.className("topSecret"));


        // Lokalizacja elementów po tagu html

        /*
        Lokalizacja elementu po tagu html pozwala nam odnaleźć element znając nazwę tagu html,
        który jest użyty do stworzenia tego elementu.
        Dla przykładu tagiem do linków będzie "a"
        Inne tagi w naszym pliku tekstowym to np. html. head, charset, script, body, button, label, input, table itd.
        */

        WebElement firstInput = driver.findElement(By.tagName("input"));    // pierwszy element z tagiem input
        firstInput.sendKeys("Pierwszy input");                   // sprawdzamy czy znaleziony został pierwszy input, wpisując do niego wartość

        WebElement firstLink = driver.findElement(By.tagName("a"));         // pierwszy element z tagiem a

        /*
        Jeśli chcemy aby zlokalizowany zostały wszystkie elementy z danym tagiem, musimy posłużyć się inną metodą, która zwróci nam listę
         */

        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        System.out.println(inputs.size());                                  // sprawdzimy ile mamy znalezionych inputów


        // Lokalizacja elementów za pomocą selektorów CSS

        /*
        Opis selektorów znajduje się w pliku docx CSS_Selectors oraz w pliku index.html
        */


        WebElement cssID = driver.findElement(By.cssSelector("#clickOnMe"));                // wyszukiwanie po ID
        WebElement cssTagLink = driver.findElement(By.cssSelector("a"));                    // wyszukiwanie po linku
        WebElement cssTagInput = driver.findElement(By.cssSelector("input"));               // wyszukiwanie po tagu
        WebElement cssClass = driver.findElement(By.cssSelector(".topSecret"));             // wyszukiwanie po klasie
        WebElement cssClass2 = driver.findElement(By.cssSelector("[class='topSecret']"));   // wyszukiwanie po klasie
        WebElement cssName = driver.findElement(By.cssSelector("[name='fname']"));          // wyszukiwanie po name

        /* Pamiętamy też o alternatywnym zapisie: */
        By cssID2 = By.cssSelector("#clickOnMe");
        driver.findElement(cssID2);

        WebElement tdFirstChild = driver.findElement(By.cssSelector("td:first-child")); // wszystkie elementy td będące pierwszym dzieckiem tagu nadrzędnego =>January


        // Lokalizacja elementów na stronie za pomocą XPatha

        /*
        Opis pliku xml oraz podstawy XPath znajdują się w pliku student.xml, który jest w resources
        */

        // Spróbujmy zlokalizować button clickOnMe

        WebElement clickOnMeXpathButton = driver.findElement(By.xpath("/html/body/button"));

        /*
        Pamiętajmy, że używanie pojedynczego / może być karkołomnym zadaniem w przypadku, gdyby ten przycisk był głębiej
        zaszyty w strukturze. Dodatkowo zmiany w strukturze najprawdopodbniej doprowadzą też do failu naszego testu.
        Alternatywą może być // Jest to sposób mniej dokładny, ale wygodniejszy
        */

        WebElement clickOnMeXpathButton2 = driver.findElement(By.xpath("//button"));

        WebElement linksXpath = driver.findElement(By.xpath("//a"));

        WebElement topSecretXpath = driver.findElement(By.xpath("//p[@class='topSecret']"));

        /*
        p jak paragraph
        Powyższe wyszukanie znajdzie nam wszystkie paragrafy z klasą topSecret
        W tym przypadku nie przeszkadza nam, że ten paragraf jest ukryty

        Poniższy sposób również odnajdzie nasz paragraf, ale musimy pamiętać, że nie jest on tak specyficzny
        jak w górnym zapisie, co oznacza, że znalazłby nam również inne tagi (nie tylko p) mające klasę topSecret
         *//*

        WebElement topSecretXpath2 = driver.findElement(By.xpath("//*[@class='topSecret']"));

        WebElement linkTextXpath = driver.findElement(By.xpath("//a[text()='IamWeirdLink']"));


        // Lokalizowanie elementów za pomocą wybranego selektora

        *//*
        Jak pobierać listę elementów a nie tylko pojedynczy (pierwszy) element i jak sprawdzić,
        czy dany element istnieje na stronie
        Otóż musimy zmienić metodę z findElement na findElements i wskazać że nie oczekujemy pojedynczego WebElementu
        a listę
        Poniżej przykład (możemy stworzyć analogiczną listę dla innych tagów niż linki (a) lub użyć analogicznie innej techniki lokalizacji)
         *//*

        List<WebElement> allLinks = driver.findElements(By.tagName("a"));

        *//*
        Jeśli w tagname podamy nazwę nieistniejącego tagu np. "adjfijs", to nie otrzymamy informacji o wyjątku,
        test nie zostanie oblany, tylko po prostu nasza lista będzie pusta.
        Możemy wypisać sobie informację o liczbie znalezionych elementów i sprawdzić dla tagu poprawnego i niepoprawnego
         *//*

        System.out.println("Liczba znalezionych linków:  " + allLinks.size());

        if(allLinks.size()>0) {
                System.out.println("linki istnieją");
            } else {
                System.out.println("linki nie istnieją");
            }


        // Lokalizacja elementów przy korzystaniu z Firefox Developer Edition

        WebElement clickOnMeCssFirefoxDev = driver.findElement(By.cssSelector("#clickOnMe"));
        WebElement clickOnMeXpathFirefoxDev = driver.findElement(By.xpath("//*[@id=\"clickOnMe\"]"));
        // znak \ przed " jest potrzebny, żeby dać informację że będziemy mieć znak specjalny i nie kończymy naszego Stringa


        *//*
        Chociaż zaleca się szukania elementów po id lub po name, istnieje szereg alternatywnych technik
        pozwalających na zlokalizowanie elementu. Czasem bowiem jeden sposób nie sprawdzi się, dlatego
        warto znać je wszystkie
         */
        }




    public WebDriver getDriver(String browser) {
        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe");
                return new ChromeDriver();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\geckodriver.exe");
                return new FirefoxDriver();
            case "ie":
                System.setProperty("webdriver.ie.driver", "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\IEDriverServer.exe");
                return new InternetExplorerDriver();
            default:
                throw new InvalidArgumentException("Invalid browser name");
        }

    }

    }





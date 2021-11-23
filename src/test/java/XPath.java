import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class XPath {
  /*
    XPath to XML Path Language, czyli język ścieżek XML i jest to język służący do znajdowania części dokumentu XML ale ma on też zastosowanie do znajdowania elementów
    na stronach HTML.
    Do wskazania konkretnego znacznika lub zbioru znaczników możemy skorzystać z tzw. ścieżki lokalizacji.

    Jak zwykle korzystać będziemy z naszej strony testowej: "https://testeroprogramowania.github.io/selenium/basics.html"

    Korzystając z narzędzi deweloperskich, inaczej niż w przypadku selektorów css dla których korzystaliśmy z komendy $$(""), będziemy używać:
    $x("TuPodajemyNaszegoXpatha")

    Tą ścieżką jest podanie lokalizacji naszego znacznika aż od samego początku dokumentu HTML. Analogicznie jak w przypadku określania ścieżek
    windowsowych, do określania ścieżki / lokalizacji elementu będziemy korzystali ze znaku "/":

    $x("/html/body/button")     -> w tym przypadku znalezione zostają dwa przyciski, które znajdują się wewnątrz tagu body

    $x("/html/body/p")          -> w tym przypadku znaleziony zostaje paragraf o klasie .topSecret

    $x("/html/p")               -> w tym przypadku nie odnajdzie nam żadnej wartości, bo ścieżka jest niekompletna (pominęliśmy "body")

    $x("/html/body/table/tbody/tr")     -> w tym przypadku znalezione dwa wiersze tabeli

    XPath to nie tylko prosty sposób lokalizacji elementów. Można dodatkowo dodawać wartości danych atrybutów.

    Ten najprostszy sposób określania lokalizacji ma wiele ograniczeń - podanie długiej ścieżki niesie za sobą ryzyko, że w przypadku zmiany struktury
    kod przestanie nam działać (np. jeśli przycisk będzie opakowany divem, taki selektor już nam nie zadziała - nie znajdzie nam tego przycisku)


    Aby nasz selektor nie przechodził kolejnych elementów struktury, a poszukał konkretych tagów w całej strukturze, musimy się posłużyć podwójnym slashem
    "//" zamiast pojedynczym "/".

    $x("//tr")          -> komenda umożliwia przeszukanie tagów tr w całej strukturze strony

    Użycie pojedynczych "/" umożliwia dokładniejsze określenie danego elementu (w przypadku "//" znajdowane są wszystkie tagi, przy pojedynczych "/"
    tylko te spełniające konkretny warunek lokalizacji)

    $x("/html/body/table//tr")      -> "//" można też użyć w dowolnym miejscu naszej ścieżki - tu pominęliśmy tag pośredni tbody -> znajdujemy wszystkie tagi tr wewnątrz tagu table

    $x("/html/body/div/ul")         -> znajduje nam jedną listę
    $x("/html/body/div//ul")        -> znajduje nam dwie listy (w tym tą zagnieżdżoną w tagu li)


   */

    @Test
    public void findElements() {
        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        By fullPath = By.xpath("/html/body/div/ul");
        driver.findElement(fullPath);

        By shortPath = By.xpath("//ul");
        driver.findElement(shortPath);


        /*
        Następnie spróbujemy zmienić stworzone wcześniej wyszukiwania tak, aby użyty został XPath z konkretną wartością atrybutu.

        Pierwotny kod:
        By buttonId = By.id("clickOnMe");
        driver.findElement(buttonId);

        W poniższym zapisie, skoro podajemy id które powinno być unikalne, możemy zrezygnować z podawania konkretnej ścieżki i od razu
        wyszukać button w całej strukturze strony
        */
        By buttonIdLong = By.xpath("/html/body/button[@id='clickOnMe']");
        driver.findElement(buttonIdLong);

        By buttonIdShort = By.xpath("//button[@id='clickOnMe']");
        driver.findElement(buttonIdShort);

        By firstName = By.xpath("//input[@name='fname']");
        driver.findElement(firstName);

        By paragraphHidden = By.xpath("//p[@class='topSecret']");
        driver.findElement(paragraphHidden);

        By allInputs = By.xpath("//input");
        WebElement input = driver.findElement(allInputs);
        input.sendKeys("Pierwszy");          // wprowadzenie wartości "Pierwszy" do naszego inputu
        List<WebElement> inputs = driver.findElements(allInputs);
        System.out.println(inputs.size());              // wyświetlenie liczby inputów

        By linkText = By.xpath("//a[text()='Visit W3Schools.com!']");
        driver.findElement(linkText);

        By partialLinkText = By.xpath("//a[contains(text(),'Visit')]");
        driver.findElement(partialLinkText);

        // metody text() i contains można również używać w stosunku do innych tagów, nie tylko do linków
        // wykorzystanie tych dwóch metod było niemożliwe w przypadku selektorów css

        /*
        Można też zlokalizować element o dowolnym tagu
        Wówczas dowolny tag oznacza się symbolem *

        Znalezienie wszystkich tagów na stronie:
        $x("//*")

        Znalezienie pierwszego tagu na stronie:
        $x("/*")    lub     $x("*")

        Znalezienie wszystkich tagów z klasą topSecret:
        $x("//*[@class='topSecret']")

        Znalezienie wszystkich tagów z klasą inną niż topSecret
        $x("//*[@class!='topSecret']")

        Znalezienie wszystkich tagów z atrybutem name/id (bez względu na wartość tego atrybutu):
        $x("//*[@name]")
        $x("//*[@id]")

        Dodatkowo możemy wprowadzić indeksowanie
        Znalezienie drugiego inputu na stronie:
        $x("(//input)[2]")
        Tu pamiętamy, że jest tak jak w przypadku selektorów css, tj. numerowanie zaczynamy od jedynki

        Znalezienie ostatniego inputu na stronie możliwe jest dzięki metodzie last:
        $x("(//input)[last()]")

        $x("(//button[@id])[last()]")
         */

        By allXPath = By.xpath("//*");
        driver.findElement(allXPath);

        By secondElement = By.xpath("(//input)[2]");
        driver.findElement(secondElement);

        By lastElement = By.xpath("(//input)[last()]");
        driver.findElement(lastElement);

        By elementWithAttribute = By.xpath("//*[@name]");
        driver.findElement(elementWithAttribute);

        By attributeEqual = By.xpath("//button[@id='clickOnMe']");
        driver.findElement(attributeEqual);

        By attributeNotEqual = By.xpath("//button[@id!='clickOnMe']");
        driver.findElement(attributeNotEqual);


        /*
        Dlaczego czasem warto korzystać z metody findElement zamiast findElements (mimo że spodziewamy się więcej niż jednego elementu):
        Otóż metoda findELements nie wyrzuci nam wyjątku, jeśli element nie zostanie zaleziony na stronie (po prostu lista będzie pusta)
        Jeśli celem naszego testu jest weryfikacja, czy dany element znajduje się na stronie, powinniśmy zastosować metodę findElement,
        gdyż tylko dzięki niej dostaniemy informację o braku pożądanego elementu (wówczas otrzymujemy wyjątek: NoSuchElementException
         */

        By partialName = By.xpath("//*[contains(@name,'name')]");    // znajduje elementy, które zawierają w atrybucie name frazę "name"
        driver.findElement(partialName);

        By startsWith = By.xpath("//*[starts-with(@name,'use')]");    // znajduje elementy, których atrybut name zaczyna się frazą "use"
        driver.findElement(startsWith);

        /*
        Ponieważ wyszukiwanie po frazie kończącej, może być problematyczne - metoda ends-with jest dostępna od wyższych wersji xpatha
        należy ją obejść za pomocą metody substring, która jest też znana w javie

        Metoda substring wyciąga z jakiegoś ciągu znaków jakiś podciąg znaków (np. "name" z "username").

        W praktyce wygląda to w następujący sposób:
         */


        String testowyString = "username";
        String testowySubstring1 = testowyString.substring(3);      // jako wartość nasza metoda przyjmuje begin index, czyli indeks od którego mamy wyciąć z naszego stringa ciąg
        String testowySubstring2 = testowyString.substring(testowyString.length()-4);  //metoda substring wytnie nam ciąg znaków od: długość naszego stringa minus 4 - czyli cztery ostatnie litery
        // pamiętamy przy tym, że w javie indeksy numerowane są od 0 a w xpathie od 1 -
        // czyli w komendzie w konsoli w przeglądarce musimy podać wartość o jeden większą:
        // weryfikując czy $x("//*[substring(@name,string-length(@name)-string-length('ame')+1)='ame']")
        // sprawdzamy czy końcówka tekstu w atrybucie jest równa 'name'
        String testowySubstring3 = testowyString.substring(testowyString.length()-"ame".length());  //długość naszego stringa minus 4

        System.out.println(testowySubstring1);
        System.out.println(testowySubstring2);
        System.out.println(testowySubstring3);


        By endsWith = By.xpath("//*[substring(@name,string-length(@name)-string-length('name')+1)='name']");    // znajduje elementy, których atrybut name kończy się frazą "name"
        driver.findElement(endsWith);


        /*
        Dzieci, rodzice, wstępni, zstępni w kontekście selektorów XPath


        Aby wyszukać w konsoli w narzędziach deweloperskich znacznik ul, który jest dzieckiem tagu div możemy posłużyć się komendą:
        $x("//div/child::ul")   -> znajdzie nam tylko jeden element
        Jeśli chcielibyśmy znaleźć wszystkie tagi ul w obrębie diva (czyli nie tylko bezpośrednie dziecko) możemy się posłużyć dwoma alternatywnymi zapisami:
        znanym już:
        $x("//div//ul")
        oraz alternatywnym zapisem poszukiwaniem wszystkich wstępnych:
        $x("//div/descendant::ul")

        Wszystkie tagi w obrębie diva zostaną znalezione z kolei przez:
        $x("//div/descendant::*")

        Możemy też znaleźć znaczniki wstępne (nadrzędne):
        $x("//div/ancestor::*")

        Do znajdowania bezpośredniego rodzica naszego tagu służy:
        $x("//div/..")

        A gdy chcemy wejść jeszcze o jeden poziom wyżej, możemy zapisać to w następujący sposób:
        $x("//div/../..")

        Do znalezienia wszystkich (bez względu na poziom struktury) tagów znajdujących się po wybranych tagu (w tym przypadku będzie to obrazek) możemy zastosować komendę:

        $x("//img/following::*")

        Do znalezienia wszystkich (na tym samym poziomie struktury) tagów znajdujących się po wybranych tagu (w tym przypadku będzie to obrazek) możemy zastosować komendę:
        $x("//img/following-sibling::*")

        Analogicznie w przypadku znajdowania wszystkich tagów znajdujących się przed wybranym tagiem:

        $x("//img/preceding::*")            // na wszystkich poziomach struktury
        $x("//img/preceding-sibling::*")    // na tym samym poziomie struktury

         */

        By child = By.xpath("//div/child::ul");
        driver.findElement(child);

        By parent = By.xpath("//div/..");
        driver.findElement(parent);

        By desc = By.xpath("//div/descendant::*");
        driver.findElement(desc);

        By asc = By.xpath("//div/ancestor::*");
        driver.findElement(asc);

        By following = By.xpath("//img/following::*");
        driver.findElement(following);

        By followingSibling = By.xpath("//img/following-sibling::*");
        driver.findElement(followingSibling);

        By preceding = By.xpath("//img/preceding::*");
        driver.findElement(preceding);

        By precedingSibling = By.xpath("//img/preceding-sibling::*");
        driver.findElement(precedingSibling);

        /*
        Za pomocą XPatha możemy też łączyć wyszukiwanie większej liczby różnych znaczników, np. inputów i linków. Wyglądałoby to w następujący sposób:
        $x("//a | //input")   -> suma dwóch selektorów $x("//a") oraz $x("//input")
        lub np. jeśli chcielibyśmy sprawdzić trzy różne znaczniki byłoby to:
        $x("//div | //input | // a")

        Gdybyśmy w jednej składowej tej sumy podali zapis, który nam nie zwróci żadnej wartości, to nadal ten selektor będzie nam działał
        (zwróci nam wówczas wartości dla drugiego składnika), np.
        $x("//div/a | //input") -> dla $x("//div/a") nie znajdzie nam żadnego tagu

        Możemy też łączyć warunki dla naszych komend:
        $x("//input[@name='fname' and @id='fname']")  -> szukamy inputa którego name i id są równe 'fname' (obydwa warunki muszę być spełnione)
        $x("//input[@name='fname' or @id='name']")    -> tu wystarczy że jeden z warunków będzie spełniony


         */

        By divsAndLinks = By.xpath("//div | // a");
        driver.findElement(divsAndLinks);

        By endOperator = By.xpath("//input[@name='fname' and @id='fname']");
        driver.findElement(endOperator);

        By orOperator = By.xpath("//input[@name='gender' or @id='fname']");
        driver.findElement(orOperator);

        /*
        Aby w łatwiejszy sposób korzystać z lokalizowania elementów za pomocą XPath, możemy posłużyć się pewną sztuczką.
        W tym celu otwieramy narzędzia deweloperskie i zakładkę Elements. Wybieramy element, który nas interesuje.
        Gdy mamy już widoczną linijkę kodu, klikamy prawym przyciskiem myszy i wybieramy opcję: Copy - Copy XPath lub Copy full XPath

        Copy full XPath korzysta z pojedynczego "/" i kopiuje nam całą ścieżkę do danego elementu
        Copy XPath korzysta z podwójnego "//" i przeszukuje całą strukturę w poszukiwaniu danego elementu

        Skopiowany selektor XPath można następnie dostosować, aby zawęzić możliwe wyniki (staramy się zawsze jak najdokładniej go określić)
         */

        driver.close();
    }
}

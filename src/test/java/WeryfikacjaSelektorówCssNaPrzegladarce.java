import org.openqa.selenium.By;
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

    Jeśli mamy wiele komend i chcemy do którejś wrócić, np. aby ją edytować, to wystarczy w konsoli na nowym wierszu przyciskać górną strzałkę
    - otrzymamy wówczas podpowiedzi z już użytymi wcześniej komendami

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

    Zaznaczenie wszystkich elementów na stronie:
    W zakładce Console należy wpisać:
    $$("*")
    Taka komenda pozwoli nam znaleźć wszystkie tagi na stronie

    Na stronie testowej mamy listę. Pamiętamy, że jest ona oznaczona tagami <ul></ul>, a jej pojedyncze elementy tagami <li></li>
    Dodatkowo mamy też zagnieżdżoną listę (listę wewnątrz listy) -> listę <ul> wewnątrz elementu <li>
    Aby wybrać wszystkie listy znajdujące się w kontenerze <div></div>, istnieje specjalny do tego specjalny selektor:
    $$("div ul")

    Analogicznie jeśli chcemy znaleźć inny element możemy zmodyfikować zapis:
    $$("table tr")  -> szukanie wierszy wewnątrz tabeli - tagu table
    $$("tbody tr")  -> szukanie wierszy dla tagu tbody

    Za pomocą takiego selektora nie trzeba podawać tagu bezpośrednio nadrzędnego, np. rodzica - można podać dowolny nadrzędny tag
    (pamiętamy że np. w przypadku naszej strony testowej tbody to rodzin tr, a tr to dziecko tbody; nie ma już takiej zależności między table a tr
    -> table to rodzic tbody, a tbody to dziecko table)

    Jeśli chcielibyśmy wywołać tylko pierwszą listę, której rodzicem jest tag div, wówczas powinniśmy się posłużyć komendą:
    $$("div > ul")

    Inne przykłady:
    $$("li > ul")       -> znajdzie nam listę zagnieżdżoną w innej liście
    $$("table > tr")    -> nie znajdzie nam nic - table nie jest rodzicem tr

    Jeśli chcielibyśmy znaleźć PIERWSZY tag form, który jest po tagu label (liczy się tu jedynie kolejność,
    a nie żadna zależność, np. rodzic - dziecko), to powinniśmy zapisać to w następujący sposób:
    $$("label + form")
    Gdybyśmy jednak chcieli znaleźć WSZYSTKIE tagi form po tagu label, to wówczas mamy:
    $$("label ~ form")



    Selektory css które znajdują elementy korzystając z wartości atrybutów

    Raz użyliśmy już takiego selektora. Pamiętając, że w strukturze strony istnieje tag input dla którego wartość name = fname, mamy:

    $$("input[name='fname']")  -> który znajduje nam input o nazwie fname

    Co jednak w sytuacji, kiedy nie znamy dokładnej nazwy danego taga:

    $$("input[name*='ame']")  -> znajduje wszystkie inputy które zawierają w nazwie podany ciąg znaków (w naszym przypadku znajdzie nam dwa, o nazwach: fname i username)

    $$("[name*='ame']")       -> zwraca nam wszystkie atrybuty które zawierają w nazwie podany ciąg znaków

    $$("[name^='user']")      -> zwraca nam wszystkie elementy, których nazwa zaczyna się od "user"

    $$("[id^='fn']")          -> zwraca mam wszystkie elementy, których id zaczyna się na "fn"

    $$("[name$='name']")      -> zwraca nam wszystkie elementy, których nazwa kończy się frazą "name"


    Selektory css które pozwolą nam wybrać pierwsze / ostatnie / któreś z kolei dzieci tagu nadrzędnego

    Najpierw zapoznajmy się ze strukturą naszej strony testowej

    Wpisujemy konkretną komendę pamiętając o zasadzie, że najpierw wpisujemy tag elementu, którego szukamy, a następnie określamy którym on jest dzieckiem
    z tagu nadrzędnego

    $$("li:first-child")   -> taki zapis znajdzie nam na naszej stronie dwa elementy - to będą pierwsze elementy li z obydwu list (listy nadrzędnej i zagnieżdżonej)
    $$("li:last-child")    -> w ten sposób znajdujemy ostatnie dziecko li w obydwu listach
    $$("li:nth-child(2)")  -> umożliwi nam znalezienie ntego dziecka; uwaga zapis: $$("li:nth-child(1)") jest równoznaczny z $$("li:first-child"), dodatkowa uwaga:
    tutaj numerację zaczynamy od jedynki, czyli inaczej niż przy tablicach czy kolekcjach


    Kopiowanie selektorów CSS z przeglądarki - ułatwienie w korzystaniu z selektorów

    Jeżeli przejdziemy do strony internetowej i otworzymy zakładkę Elements, a następnie za pomocą inspektora wybierzemy dany element na stronie
    (a co za tym idzie wyświetlimy informacje o nim w zakładce), to wystarczy kliknąć na odpowiedniej linijce prawym przyciskiem myszy, wybrać opcję
    Copy - Copy selector, a skopiuje nam się selektor. Tak przeklejony selektor pozwoli nam znaleźć dany element
    Ta funkcjonalność umożliwia nam w łatwy sposób zastosowanie selektorów w kodzie. Jednak nie zwalnia nas to ze znajomości selektorów, ponieważ
    nie zawsze skopiowany selektor będzie tym najbardziej optymalnym, np. kiedy chcemy na naszej stronie znaleźć paragraf topSecret:
    $$("body > p")        -> wynik kopiowania, moglibyśmy mieć dużo więcej paragrafów w body, szczególnie gdyby nasza struktura na stronie by się zmieniło
    $$(".topSecret")      -> bardziej optymalny odpowiednik, bardziej odporny na zmianę (wyszukuje wszystkie elementy o klasie topSecret)


    */




    @Test
    public void findElements(){
        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/basics.html");

        By all = By.cssSelector("*");
        driver.findElement(all);        // zwróci nam pierwszy element html
        driver.findElements(all);       // zwróci nam wszystkie 60 elementów

        By ulInDiv = By.cssSelector("div ul");
        driver.findElements(ulInDiv);

        By trInTable = By.cssSelector("table tr");
        driver.findElement(trInTable);

        By trInTbody = By.cssSelector("tbody tr");
        driver.findElement(trInTbody);

        By firstChildUlinDiv = By.cssSelector("div > ul");
        driver.findElement(firstChildUlinDiv);

        By firstChildTrinTbody = By.cssSelector("tbody > tr");
        driver.findElement(firstChildTrinTbody);

        By firstTagFormAfterLabel = By.cssSelector("label + form");
        driver.findElement(firstTagFormAfterLabel);

        By allTagsFormAfterLabel = By.cssSelector("label ~ form");
        driver.findElements(allTagsFormAfterLabel);

        By attributeTag = By.cssSelector("input[name='fname']");
        By attributeContains = By.cssSelector("[name*='name']");
        By attributeStarts = By.cssSelector("[name^='f']");
        By attributeEnds = By.cssSelector("[name$='name']");

        driver.findElements(attributeTag);
        driver.findElements(attributeContains);
        driver.findElements(attributeStarts);
        driver.findElements(attributeEnds);

        By firstChild = By.cssSelector("li:first-child");
        By lastChild = By.cssSelector("li:last-child");
        By thirdChild = By.cssSelector("li:nth-child(3)");

        driver.findElement(firstChild);
        driver.findElement(lastChild);
        driver.findElement(thirdChild);
    }
}

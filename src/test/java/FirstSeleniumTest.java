import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class FirstSeleniumTest {

    /*
    Korzystamy z anotacji @Test, która mówi Intellij że jest to metoda testowa
     */

    @Test
    public void googleOpenTest() {

        /*
        Gdybyśmy zostawili sam ten zapis:
        WebDriver driver = new ChromeDriver();
        nasz test zakończyłby się failem, gdyż Intellij nie wiedziałby którego drivera ma używać.
        Musimy wskazać mu odpowiednią ściężkę
        Możemy to zrobić przez System.setProperty lub ustawić w zmiennych środowiskowych tak jak dla całego naszego komputera
        (jednak przy drugim rozwiązaniu należy mieć na uwadze, że test odpalony na innym komputerze nie zadziała nam dopóki nie określimy ścieżki do sterownika)
        Aby znaleźć odpowiednią ścieżkę otwieramy nasz projekt eksploratorem plików i znajdujemy interesujący nas plik w odpowiednim folderze

        Pamiętamy, że ścieżkę w Intellij trzeba określić jak poniżej.

        Można też skorzystać z innych przeglądarek, dodając odpowiedni plik exe, określając właściwą ścieżkę do niego oraz definiując właściwy obiekt klasy
         */
        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        /*
        Aby określić wielkość przeglądarki możemy się posłużyć klasą dostarczoną przez Selenium i tworząc nowy obiekt tej klasy
        w konstruktorze określamy parametry szerokości i wysokości
         */
        Dimension dimension = new Dimension(1000,800);

        WebDriver driver = new ChromeDriver();      // za pomocą tego zapisu powinno nam się otworzyć nowe okno chrome


        /*
        Pierwszą podstawową metodą jest metoda get, która będzie nam otwierać konkretną stronę
        Jako parametr podajemy Stringa z adresem strony
         */

        driver.get("http://www.google.com");

        /*
        Aby ustawić żądaną wielkość przeglądarki
        Jako parametr podajemy zmienną dimension

        Oczywiście pamiętamy o fakcie, że w zależności którą metodę wpiszemy jako pierwszą takie będziemy mieli kroki wykonania
         */

        driver.manage().window().setSize(dimension);

        /*
        Zamiast hardcodować wymiary okna możemy też skorzystać z opcji maximize, która otworzy nam okno na cały ekran

        driver.manage().window().maximize();
         */


        /*
        Aby zamknąć okno przeglądarki po skończonym teście, musimy posłużyć się metodą driver.quit();
         */
        driver.quit();



        /*
        Poniżej drugie otworzenie przeglądarki, tym razem ze stroną zapisaną na dysku
         */

        WebDriver driver2 = new ChromeDriver();      // za pomocą tego zapisu powinno nam się otworzyć nowe okno chrome
        driver2.get("C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\dane do kursu selenium\\Test.html");
        driver2.manage().window().setSize(dimension);

        /*
        Jeżeli chcemy kliknąć w dany przycisk na stronie musimy najpierw otworzyć stronę w notatniku
        lub w przeglądarce kliknąć prawym przyciskiem myszy i wybrać opcję wyświetl źródło strony (ctrl + U),
        aby odczytać id danego przycisku
        To umożliwi nam skorzystanie z metody findElement, w której definiujemy konkretny id po którym element ma zostać znaleziony,
        a następnie metody click, która naciśnie dany przycisk
         */

        driver2.findElement(By.id("newPage")).click();

        /*
        W momencie kiedy w wyniku testu otworzą nam się dwa lub więcej okna przeglądarki i zastosujemy metodę
        driver2.quit();
        zamkną mi się wszystkie otwarte podczas testu okna

        Gdybyśmy chcieli zachować któreś z okien otwarte, musimy skorzystać z metody
        driver2.close() która zamknie nam stronę testową (stronę pierwotną), a zostawi nam okno,
        które zostało otworzone przez kliknięcie przycisku

         */

        driver2.close();

    }

}

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class UploadTest {

/*
W tej klasie spróbujemy zasymulować upload pliku
W tym celu będziemy korzystać ze strony:
https://testeroprogramowania.github.io/selenium/fileupload.html

Najprostszą metodą jest odnalezienie inputu, który otwiera nam okno wyboru pliku do uploadu oraz podanie
ścieżki do naszego pliku za pomocą metody sendKeys()

Niemożliwe jest za to dokonanie uploadu pliku za pomocą przejścia do innego okna wyboru pliku - nie jest to strona html
(okno powiązane z systemem operacyjnym).

Gdyby się jednak okazało, że ten sposób nie zadziała, bo input będzie wyszarzony / zablokowany i nie będzie można wprowadzić
do niego ścieżki, to będziemy musieli skorzystać z alternatywnego podejścia i zrobić to tak jak robi to użytkownik, ale wówczas
będziemy musieli korzystać nie tylko z selenium, ale też z zewnętrznych narzędzi (np. sikuliX).

Ale jak wówczas będzie wyglądał test?
Do momentu otworzenia właściwej strony i kliknięcia w odpowiedni element korzystamy z Selenium, a następnie korzystamy z sikuliX.
Sikulix rozpoznaje za pomocą naszych screenshotów odpowiednie obszary, które musi obsłużyć.
To rozwiązanie nie jest idealne - tylko kiedy ekran wyboru jest widoczny, to narzędzie zadziałą. Oznacza to, że ten test
może nie być stabilny.

W internecie dostępne są wskazówki jak to rozwiązać (wystarczy wpisać np. w google.com "sikulix file upload)



 */

    @Test
    public void uploadFile() {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/fileupload.html");

        driver.findElement(By.id("myFile")).sendKeys("C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\test.txt");
    }

}

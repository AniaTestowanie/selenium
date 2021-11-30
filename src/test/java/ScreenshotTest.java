import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;

public class ScreenshotTest {

/*
Bazując na początku metody z naszym uploadem, tym razem spróbujemy wykonać zrzut ekranu podczas naszego testu
Co ważne będziemy tu musieli scastować naszego drivera do odpowiedniego interfejsu (TakesScreenshot)

Nasz screenshot będzie zapisywany w nowoutworzonym folderze.
Folder ten należy oznaczyć jako "Test Resources Root", aby to zrobić, klikamy prawym przyciskiem myszy na folder
i wybieramy opcję "Mark Directory as"



 */

    @Test
    public void makeScreenshot() throws IOException {

        String driverPath = "C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\selenium\\src\\main\\resources\\executables\\drivers\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://testeroprogramowania.github.io/selenium/fileupload.html");

        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File openPage = screenshot.getScreenshotAs(OutputType.FILE);
        /*
        Jeśli będziemy mieli tak jak poniżej określoną ścieżkę zapisu screenshota, to przy każdym wykonaniu testu
        nasz plik się nadpiszę kolejnym zrzutem ekranu
         */
        FileUtils.copyFile(openPage, new File("src/test/resources/openPage.png"));
        /*
        Zatem jeśli będziemy mieli wiele kroków z wykonaniem screenshotów w naszym teście, możemy
        albo w każdym kroku określić inną nazwę pliku ze screenshotem (ale wówczas nadal istnieje ograniczenie
        że będziemy przechowywać tylko screenshoty z ostatnio wykonanego testu):
         */

        driver.findElement(By.id("myFile")).sendKeys("C:\\Users\\Saturn\\Desktop\\szkolenie Ania\\test.txt");

        File uploadFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(uploadFile, new File("src/test/resources/uploadFile.png"));

        /*
        albo skorzystać z alternatywnego podejścia, w którym określamy zmiennego Stringa (np. będzie się
        zmieniać liczba, data godzine etc. -> konstrukcja obojętna - ważne by nazwa była unikalna również
        z testu na test)
        W naszym przypadku będziemy generować Stringa z losową liczbą
        Będziemy musieli skorzystać z klasy Mathi metody random. Mnożąc przez 1000 określamy zakres liczb,
        jaki z tej metody będą zwracane

         */
        int randomNumber = (int) (Math.random()*1000);
        String fileName = "after upload" + randomNumber + ".png";
        File afterUpload = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(afterUpload, new File("src/test/resources/" + fileName));

    }

}

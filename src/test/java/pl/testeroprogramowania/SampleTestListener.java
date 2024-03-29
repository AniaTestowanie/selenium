package pl.testeroprogramowania;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class SampleTestListener implements ITestListener {


    /*
    Dzięki implementacji interfejsu ITestListener możemy wywoływać określone metody przy spełnieniu danego warunku

    Zaimplementowaliśmy więc interfejs ITestListener a następnie dodaliśmy wszystkie metody tego interfejsu
    Nie dla wszystkich musimy dostarczyć body. Te metody dla których nie uzupełnimy body, po prostu się nie wykonają

    Jeśli chcemy dodać Listenera do naszego testu, to wystarczy nad nazwą klasy w której jest nasz test, umieścić
    adnotację @Listeners a następnie w nawwiasach podać: (value = {nazwaKlasyZeZdefiniowanymiMetodamiInterfejsuTestListener.class})
    My umieścimy taką adnotację w klasie FirstTest
     */

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("I am starting test");
    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("I am taking screenshot");

        WebDriver driver = DriverFactory.getDriver();

        int randomNumber = (int) (Math.random()*1000);

        TakesScreenshot screenshot = (TakesScreenshot) driver;

        File failedTest = screenshot.getScreenshotAs(OutputType.FILE);

        String fileName = "Screenshot" + randomNumber + ".png";

        try {
            FileUtils.copyFile(failedTest, new File("src/test/resources/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}

package pl.testeroprogramowania;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/*
Zależności między testami nie są zalecane (testy powinny być niezależne, powinna być możliwość uruchomienia ich
w izolacji), jednak TestNG oferuje nam taką możliwość
Wówczas po adnotacji @Test dodajemy (dependsOnMethods = {nazwaMetody})
Podczas uruchamiania testu z zależnością, najpierw wykona się test od którego zależy nasza
metoda, a następnie wywoływany test docelowy

 */

public class FourthTest extends BaseTest{

    @Test(dependsOnMethods = {"thirdTest"})
    public void firstTest() {
        System.out.println("I am first test");
    }

    @Test(dependsOnMethods = {"firstTest"})
    public void secondTest() {
        System.out.println("I am second test");
    }

    @Test
    public void thirdTest() {
        System.out.println("I am third test");
    }

    @Test(dataProvider = "data")
    public void dataProviderTest(String value, String number) {
        System.out.println(value + " " + number);

        /*
        W ramach tej metody test wykonany zostanie trzykrotnie z danymi określonymi za pomocą DataProvider
        w kolejności w jakiej wprowadziliśmy te dane

        W DataProvider nie trzeba określać danych na sztywno - można ja obierać z pliku tekstowego, z excela itp.
         */
    }

    @DataProvider(name = "data")
    public Object[][] DataProvider() {
        return new Object[][] {{"I am first test","First"},{"I am second test","Second"},{"I am third test", "Third"}};
    }
}

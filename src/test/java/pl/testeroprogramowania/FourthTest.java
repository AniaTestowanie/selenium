package pl.testeroprogramowania;

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
}

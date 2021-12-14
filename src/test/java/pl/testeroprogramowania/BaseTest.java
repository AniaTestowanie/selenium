package pl.testeroprogramowania;

import org.testng.annotations.*;

public class BaseTest {
    /*
    klasa ta będzie rodzicem naszych klas testowych pl.testeroprogramowania.FirstTest oraz pl.testeroprogramowania.SecondTest
    W ramach niej utworzone zostaną metody oznaczone adnotacjami @BeforeTest oraz @BeforeMethod

    Adnotacja @BeforeMethod powoduje, że ta metoda zostaje uruchomiona przed każdą metodą testową
    z klasy dziecka
    natomiast adnotacja @BeforeTest spowoduje, że ta metoda zostanie uruchomiona przed pierwszym testem

    Adnotacje @AfterMethod i @AfterTest są analogiczne do wyżej przedstawionych, ale działają po zakończeniu każdej metody
    lub po zakończeniu testów (po ostatnim teście)

    Obydwie klasy testowe oraz klasa nadrzędna zostały przeniesione do odrębnej paczki, aby móc uruchamiaś wszystkie
    testy znajdujące się w jednej paczce (nie chodzi tu o uruchamianie ich równocześnie, ale by nie trzeba było wielokrotnie
    wybierać opcji run test). W tym celu wystarczy kliknąć prawym przyciskiem myczy na paczkę i wybrać opcję:
    Run Test in nazwa.paczki

    Przy uruchamianiu wszystkich testów z paczki metody @BeforeTest i @AfterTest uruchomią się tylko raz mimo posiadania
    dwóch klas testowych

    Następnie utworzone zostaną następne metody z adnotacjami: @BeforeSuite i @BeforeClass oraz @AfterSuite i @AfterClass

    @BeforeSuite oraz @AfterSuite uruchamiają się przed @BeforeTest oraz po @AfterTest. W całej serii testów metody te zostaną
    wywołane tylko raz.

    Adnotacje @BeforeClass i @AfterClass z kolei uruchomią się przed / po każdą klasą testową, bez względu na to, ile będzie
    ona miała w sobie zaszytych testów.

    Powyższe adnotacje są najważniejszymi.


    Ignorowanie testu - metoda oznaczona adnotacją @Test nie zostaje wykonana. Aby to osiągnąć, wystarczy po adnotacji @Test
    dodać kolejną adnotację @Ignore

    Poprzez nadawanie priorytetów naszym testom, możemy sterować kolejnością ich wykonania - przykłady w klasie ThirdTest
    Aby dodać priorytet do adnotacji @Test dodajemy nawias i wybieramy opcję priority, a następnie wpisujemy wartość
    liczbową naszego priorytetu. Domyślną wartością jest 0.
    Co do zasady, testy odpalane są od najniższej wartości priorytetu do najwyższej.

     */

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("I am running before suite");
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("I am running before class");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("I am running after suite");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("I am running after class");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("I am running before test");
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("I am running before method");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("I am running after test");
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("I am running after method");
    }
}

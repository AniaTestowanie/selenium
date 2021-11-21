public class InformacjeOgolne {

    /*

    * Fazy cyklu budowania projektu w Mavenie:
    Jasno zdefiniowany proces tworzenia i dystrybucji projektu, który ma określone fazy, m.in.:
    - mvn compile - kompiluje kod źródłowy
    - mvn test - uruchamia testy
    - mvn package - pakuje skompilowany kod, np. do jara
    - mvn install - instalacja paczki do naszego lokalnego repozytorium

    Fazy cyklu są od siebie zależne. Tzn. komenda mvn install (lub jakakolwiek inna) wykona wszystkie poprzednie komendy
    (w tym przypadku mvn compile, mvn test oraz mvn install)

    Komenda mvn clean czyści nam pliki wynikowe wszystkich poprzednich buildów, wszystkich poprzednich runów
    (zawierają się one w folderze target)



    * Narzędzia deweloperskie w przeglądarce

    Narzędzia deweloperskie w przeglądarce pozwalają nam sprawdzić kod html naszej strony,
    sprawdzać odpowiednie informacje odnośnie danego elementu, abyśmy mogli go później zlokalizować z poziomu naszego testu
    i wykonać na nim jakąś akcję.

    F12 na otworzonej stronie otworzy nam narzędzia deweloperskie

    W zakładce Elements mamy struturę naszego pliku HTML.
    Wybierając i naciskając ikonę Select an element in the page to inspect it (lub klikając ctrl + shift + c),
    możemy najechać kursorem na dany element strony, aby podświetlić konkretną linijkę kodu,
    który go dotyczy oraz istotne informacje o nim. Analogicznie przejeżdżając kursorem po konkretnych linijkach kodu,
    podświetlą nam się elementy strony. Pozwala to na lokalizację elementów.



    * Firefox wersja dla programistów

    W celu ułatwienia swojej pracy (usprawnienia wyszukiwania css i xpathów)
    możemy zainstalować przeglądarkę Firefox w wersji dla programistów
    W tym celu wyszukujemy Firefox Developer Edition a następnie instalujemy

    Naszą stronę testową Test.html możemy następnie otworzyć za pomocą Firefox Developer Edition
    Kiedy jesteśmy w trybie inspekcji i klikniemy na wybrany element prawym przyciskiem myszy,
    wyświetli nam się lista dostępnych możliwości. Wybierając opcję kopiuj mamy możliwość od razu
    skopiować selektor css lub XPath

    Niestety niektóre selektory dostarczone przez Firefoxa nie są optymalne i lepiej jest pisać je ręcznie,
    jednak kiedy nie mamy pomysłu, jak się "dobrać" do jakiegoś elementu, może być to dla nas pomocne


    * HTML
    Informacje o strukturze kodu html znajdują się w folderze szkolenie Ania -> HTML

    * Strony do ćwiczeń w ramach testu dostępne są tu:
    https://testeroprogramowania.github.io/selenium/


     */


}

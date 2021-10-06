package pl.akai;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

   private static String[] sentences = {
      "Taki mamy klimat",
      "Wszędzie dobrze ale w domu najlepiej",
      "Wyskoczył jak Filip z konopii",
      "Gdzie kucharek sześć tam nie ma co jeść",
      "Nie ma to jak w domu",
      "Konduktorze łaskawy zabierz nas do Warszawy",
      "Jeżeli nie zjesz obiadu to nie dostaniesz deseru",
      "Bez pracy nie ma kołaczy",
      "Kto sieje wiatr ten zbiera burzę",
      "Być szybkim jak wiatr",
      "Kopać pod kimś dołki",
      "Gdzie raki zimują",
      "Gdzie pieprz rośnie",
      "Swoją drogą to gdzie rośnie pieprz?",
      "Mam nadzieję, że poradzisz sobie z tym zadaniem bez problemu",
      "Nie powinno sprawić żadnego problemu, bo Google jest dozwolony"
   };
       /* TODO Twoim zadaniem jest wypisanie na konsoli trzech najczęściej występujących słów
                w tablicy 'sentences' wraz z ilością ich wystąpień.                Przykładowy wynik:
                1. "mam" - 12
                2. "tak" - 5
                3. "z" - 2
        */
   public static void main(String[] args) {
      Arrays.stream(sentences).flatMap(l -> Arrays.stream(l.split(" ")))
         .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
         .entrySet().stream()
         .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
         .limit(3).forEach((i)-> System.out.println("\""+i.getKey()+"\" - "+i.getValue()));
   }

}

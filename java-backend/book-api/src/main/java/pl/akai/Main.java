package pl.akai;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    /*
        Twoim zadaniem jest napisanie prostego programu do pobierania i transformowania danych
        udostępnianych przez API. Dokumentacje API możesz znależć pod poniższym linkiem:
        https://akai-recruitment.herokuapp.com/documentation.html

        Całe API zawiera jeden endpoint: https://akai-recruitment.herokuapp.com/book
        Endpoint ten zwraca liste książek zawierajacch informację takie jak:
        - id
        - tytuł
        - autor
        - ocena

        Twoim zadaniem jest:
        1. Stworzenie odpowiedniej klasy do przechowywania informacji o książce
        2. Sparsowanie danych udostępnianych przez endpoint. Aby ułatwić to zadanie,
           do projektu są dołaczone 3 najpopularniejsze biblioteki do parsowania JSONów
           do obiektów Javy - Gson, Org.Json, Jackson. Możesz wykorzystać dowolną z nich
        3. Po sparsowaniu JSONu do obiektów Javy, uzupełnij program o funkcję wypisującą 3 autorów z
           najwyższą średnią ocen (wypisz także średnie ocen)

       Projekt został utworzony przy użyciu najnowszej Javy 17,
       jednakże nic nie stoi na przeszkodzie użycia innej wersji jeśli chcesz
     */

   public static void main(String[] args) throws IOException {
      Gson gson = new Gson();
      List<Book> books = gson.fromJson(jsonGetRequest("https://akai-recruitment.herokuapp.com/book"), new TypeToken<List<Book>>() {
      }.getType());
      List<Book> sortedBooksByRating = books.stream().sorted(Comparator.comparingDouble(Book::getRating)).collect(Collectors.toList());
      Collections.reverse(sortedBooksByRating);
      sortedBooksByRating.stream().limit(3).forEach(b -> System.out.println(b.getAuthor()));
      System.out.println(books.stream().map(book -> book.getRating()).collect(Collectors.toList()).stream().mapToDouble(d -> d).average().getAsDouble());
   }

   public static String jsonGetRequest(String urlQueryString) {
      String json = null;
      try {
         URL url = new URL(urlQueryString);
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         connection.setDoOutput(true);
         connection.setInstanceFollowRedirects(false);
         connection.setRequestMethod("GET");
         connection.setRequestProperty("Content-Type", "application/json");
         connection.setRequestProperty("charset", "utf-8");
         connection.connect();
         InputStream inStream = connection.getInputStream();
         json = streamToString(inStream); // input stream to string
      } catch (IOException ex) {
         ex.printStackTrace();
      }
      return json;
   }

   private static String streamToString(InputStream inputStream) {
      String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
      return text;

   }
}



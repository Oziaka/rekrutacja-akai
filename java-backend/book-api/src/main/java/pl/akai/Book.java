package pl.akai;

public class Book {
  private String id;
  private String title;
  private String author;
  private double rating;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public double getRating() {
      return rating;
   }

   public void setRating(double rating) {
      this.rating = rating;
   }

   @Override
   public String toString() {
      return "Book{" +
         "id='" + id + '\'' +
         ", title='" + title + '\'' +
         ", author='" + author + '\'' +
         ", rating=" + rating +
         '}';
   }
}

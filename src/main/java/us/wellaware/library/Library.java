package us.wellaware.library;

import java.util.List;

public interface Library {
    boolean addBookToShelf(long isbn, String title, String author, String genre, String publisher,
                        int publicationYear, int pageCount);

    String getBookTitle(long isbn);

    List<String> getShelfNames();

    String findShelfNameForISBN(long isbn);

    List<Long> getISBNsOnShelf(String shelfName);

    List<Long> getISBNsForGenre(String genre, int limit);

    List<Long> getISBNsForAuthor(String author, int limit);

    List<Long> getISBNsForPublisher(String publisher, int limit);

    List<Long> getISBNsPublishedAfterYear(short publicationYear, int limit);

    List<Long> getISBNsWithMinimumPageCount(int minimumPageCount, int limit);
}

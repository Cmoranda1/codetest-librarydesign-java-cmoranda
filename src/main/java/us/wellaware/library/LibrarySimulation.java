package us.wellaware.library;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class LibrarySimulation implements Library {
    private final int maxShelfSize;

    public LibrarySimulation(int shelfSize) {
        maxShelfSize = shelfSize;
    }

    public boolean addBookToShelf(long isbn, String title, String author, String genre, String publisher,
                               int publicationYear, int pageCount) {
        throw new UnsupportedOperationException();
    }

    public String getBookTitle(long isbn) {
        throw new UnsupportedOperationException();
    }

    public List<String> getShelfNames() {
        throw new UnsupportedOperationException();
    }

    public String findShelfNameForISBN(long isbn) {
        throw new UnsupportedOperationException();
    }

    public List<Long> getISBNsOnShelf(String shelfName) {
        throw new UnsupportedOperationException();
    }

    public List<Long> getISBNsForGenre(String genre, int limit) {
        throw new UnsupportedOperationException();
    }

    public List<Long> getISBNsForAuthor(String author, int limit) {
        throw new UnsupportedOperationException();
    }

    public List<Long> getISBNsForPublisher(String publisher, int limit) {
        throw new UnsupportedOperationException();
    }

    public List<Long> getISBNsPublishedAfterYear(short publicationYear, int limit) {
        throw new UnsupportedOperationException();
    }

    public List<Long> getISBNsWithMinimumPageCount(int minimumPageCount, int limit) {
        throw new UnsupportedOperationException();
    }
}

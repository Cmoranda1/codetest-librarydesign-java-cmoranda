package us.wellaware.library;

import java.lang.UnsupportedOperationException;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.*;

public class LibrarySimulation implements Library {
    /*My Implementation of the Library Simulation Class
      The Library Simulation is composed of two smaller objects
      A library is made up of Shelves and Shelves are made up of Books*/
    
    private class Book implements Comparable<Book> {
        /*A book is the fundamental object of a library*/
        private long isbn;
        private String title;
        private String author;
        private String genre;
        private String publisher;
        private int publicationYear;
        private int pageCount;    
        
        private Book(long isbn, String title, String author, String genre, String publisher,
                               int publicationYear, int pageCount){
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.publisher = publisher;
            this.publicationYear = publicationYear;
            this.pageCount = pageCount;
        }

        @Override
        public int compareTo(Book o) {
            /*A book of a particular genre is ordered alphabetically, 
              first by Author and then by Title (If author is the same)*/ 
            int compare = this.author.compareTo(o.author);
            if(compare == 0) {
                compare = this.title.compareTo(o.title);
            }
            return compare;
        }
    }
    
    private class Shelf {
        /*a shelf is a list of books of a particular genre*/
        private int shelf_num;
        private String genre;
        private int shelf_size;
        List<Book> books = new ArrayList<Book>();

        private Shelf(int shelf_num, String genre, int shelf_size) {
            this.shelf_num = shelf_num;
            this.genre = genre;
            this.shelf_size = shelf_size;
        }

    }
    private final int maxShelfSize;

    /*The isbnMap is used for quick lookup to see if an isbn already
      exists in the library before adding it, and is also used to return
      titles for a specified isbn*/
    private HashMap<Long, String> isbnMap = new HashMap<>();

    /*This is the main data structure for the library, this allows
      the lookup of a specific shelf class based on name*/
    private HashMap<String, Shelf> library = new HashMap<>();

    
    public LibrarySimulation(int shelfSize) {
        maxShelfSize = shelfSize;
    }

    private String shelfNameString(String genre, int num) {
        StringBuilder str = new StringBuilder();
        str.append(genre);
        str.append(" - ");
        str.append(Integer.toString(num));
        return str.toString();
    }

    /*go through shelves, add book, sort, add back*/
    private void findSpot(Book book, int num_shelves) {
        List<Book> genreBooks = new LinkedList<Book>();
        String str = "";
        /*For all shelves in a particular genre, add every book to a 
          temporary list for sorting purposes*/
        for(int i = 1; i <= num_shelves; i++) {
            str = shelfNameString(book.genre, i);
            Shelf s = this.library.get(str);
            for(Book bs : s.books) {
                genreBooks.add(bs);
            }
        }
        genreBooks.add(book);
        Collections.sort(genreBooks);
        int rel_index = 0;
        int shelf_num = 1;
        Shelf temp_shelf = new Shelf(1, book.genre, this.maxShelfSize);
        for(Book b : genreBooks) {
            if(rel_index == this.maxShelfSize) {
                rel_index = 0;
                shelf_num++;
                this.library.put(str.toString(), temp_shelf);
            }
            if(rel_index == 0) {
                str = shelfNameString(book.genre, shelf_num);
                temp_shelf = this.library.get(str);
                temp_shelf.books = new ArrayList<Book>();
            }
            temp_shelf.books.add(rel_index, b);
            rel_index++;
        } 
    }

    private void findShelf(Book book) {
        int x = 1;
        String str = shelfNameString(book.genre, x);
        
        /*if no shelves have been made for this genre, create the first one and
          add book*/
        if(!this.library.containsKey(str)) {
            Shelf s = new Shelf(x, book.genre, this.maxShelfSize);
            s.books.add(book);
            this.library.put(str, s);
            return;
        }

        while(this.library.containsKey(str)) {
            x++;
            str = shelfNameString(book.genre, x);
        }
        x--;
        str = shelfNameString(book.genre, x);;

        /*if the current shelf is full, create a new one*/
        if(this.library.get(str).books.size() == this.maxShelfSize) {
            x++;
            str = shelfNameString(book.genre, x);
            Shelf s = new Shelf(x, book.genre, this.maxShelfSize);
            this.library.put(str, s);
            /*Sort the shelves*/
            findSpot(book, x);
            return;
        }

        /*Sort the shelves*/
        findSpot(book, x);
    }

    public boolean addBookToShelf(long isbn, String title, String author, String genre, String publisher,
                               int publicationYear, int pageCount) {
        /*Before adding a book to the library, check ISBN and return false if
          ISBN is present*/
        if(isbnMap.containsKey(isbn)) {
            System.out.println("A book with this ISBN already exists");
            return false;
        }
       
        /*If ISBN is not already in library, add it to the isbnMap*/
        else {
            isbnMap.put(isbn, title);
        }
        /*Before adding, check for the correct shelf or create a new one*/
        Book b = new Book(isbn, title, author, genre, publisher, publicationYear, pageCount);
        findShelf(b);
        return true;
    }

    /*A helper function for debugging*/
    /*public void printMap() {
        for(String s : this.library.keySet()) {
            System.out.println(s);
            for(Book b : this.library.get(s).books) {
                System.out.println(b.author);
            }
        }
    }*/

    public String getBookTitle(long isbn) {
        return this.isbnMap.get(isbn);
    }

    public List<String> getShelfNames() {
        List<String> shelfNames = new ArrayList<String>();
        for(String s : this.library.keySet()) {
            shelfNames.add(s);
        }
        return shelfNames;
    }

    public String findShelfNameForISBN(long isbn) {
        for(String s : this.library.keySet()) {
            for(Book b : this.library.get(s).books) {
                if(b.isbn == isbn) {
                    return s;
                }
            }
        }
        System.out.println("That isbn was not found on any shelves");
        return null;
    }

    public List<Long> getISBNsOnShelf(String shelfName) {
        List<Long> isbnsOnShelf = new ArrayList<Long>();
        Shelf s = this.library.get(shelfName);
        for(Book b : s.books) {
            isbnsOnShelf.add(b.isbn);
        }
        return isbnsOnShelf;
    }

    public List<Long> getISBNsForGenre(String genre, int limit) {
        List<Long> isbnsForGenre = new ArrayList<Long>();

        StringBuilder str = new StringBuilder();
        int x = 1;
        str.append(genre);
        str.append(" - ");
        str.append(Integer.toString(x));


        while(this.library.containsKey(str.toString())) {
            for(Book b : this.library.get(str.toString()).books) {
                if(isbnsForGenre.size() == limit) {
                    return isbnsForGenre;
                }
                isbnsForGenre.add(b.isbn);
            }
            x++;
            str = new StringBuilder();
            str.append(genre);
            str.append(" - ");
            str.append(Integer.toString(x));
        }

        return isbnsForGenre;                     
    }

    public List<Long> getISBNsForAuthor(String author, int limit) {
        List<Long> isbnsAuthor = new ArrayList<Long>();
        for(String s : this.library.keySet()) {
            for(Book b : this.library.get(s).books) {
                if(isbnsAuthor.size() == limit) {
                    return isbnsAuthor;
                }
                if(b.author == author) {
                    isbnsAuthor.add(b.isbn);
                }
            }
        }
        return isbnsAuthor;
    }

    public List<Long> getISBNsForPublisher(String publisher, int limit) {
        List<Long> isbnsPublisher = new ArrayList<Long>();
        for(String s : this.library.keySet()) {
            for(Book b : this.library.get(s).books) {
                if(isbnsPublisher.size() == limit) {
                    return isbnsPublisher;
                }
                if(b.publisher == publisher) {
                    isbnsPublisher.add(b.isbn);
                }
            }
        }
        return isbnsPublisher;

    }

    public List<Long> getISBNsPublishedAfterYear(short publicationYear, int limit) {
        List<Long> isbnsAfterYear = new ArrayList<Long>();
        for(String s : this.library.keySet()) {
            for(Book b : this.library.get(s).books) {
                if(isbnsAfterYear.size() == limit) {
                    return isbnsAfterYear;
                }
                if(b.publicationYear > publicationYear) {
                    isbnsAfterYear.add(b.isbn);
                }
            }
        }
        return isbnsAfterYear;

    }

    public List<Long> getISBNsWithMinimumPageCount(int minimumPageCount, int limit) {
        List<Long> isbnsMinPage = new ArrayList<Long>();
        for(String s : this.library.keySet()) {
            for(Book b : this.library.get(s).books) {
                if(isbnsMinPage.size() == limit) {
                    return isbnsMinPage;
                }
                if(b.pageCount >= minimumPageCount) {
                    isbnsMinPage.add(b.isbn);
                }
            }
        }
        return isbnsMinPage;
    }
}

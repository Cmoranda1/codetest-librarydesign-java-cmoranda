package us.wellaware.library;

import java.util.List;

public class Main {

    public static void main(String[] args) {
	    Library myLibrary = new LibrarySimulation(5);

	    myLibrary.addBookToShelf(345404475, "Do Androids Dream of Electric Sheep?", "Dick, Philip",
                "Science Fiction", "Ballantine Books", 1996, 244);
        myLibrary.addBookToShelf(517542095, "The Hitchhiker's Guide to the Galaxy", "Adams, Douglas",
                "Science Fiction", "Harmony Books", 1989, 224);
        myLibrary.addBookToShelf(684818701, "The Joy of Cooking: Seventh Edition", "Rombauer, Irma",
                "Cooking", "Simon and Schuster", 1997, 1136);
        myLibrary.addBookToShelf(70064520, "Aunt Erma's cope book", "Bombeck, Erma", "Humor",
                "McGraw-Hill", 1979, 180);
        myLibrary.addBookToShelf(609600672, "Dave Barry is not taking this sitting down!", "Barry, Dave",
                "Humor", "Crown Publishers", 2000, 229);

        List<String> shelves = myLibrary.getShelfNames();

        for (String shelf : shelves) {
            List<Long> bookIds = myLibrary.getISBNsOnShelf(shelf);

            System.out.println(String.format("Found shelf '%s' with %d books.", shelf, bookIds.size()));

            for (Long isbn : bookIds) {
                String title = myLibrary.getBookTitle(isbn);
                System.out.println(String.format("   %s", title));
            }
        }
    }
}

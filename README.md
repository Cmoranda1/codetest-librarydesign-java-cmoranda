# codetest-librarydesign-java
A coding test to design a simple model in Java for a library of books

## Background

Refer to the classes in the code repository.

Implement a simulated library that contains books on shelves in the specified order and groupings.

## Problem

Implement the **Library** interface methods in the **LibrarySimulation** implementing class. These methods will allow the calling program to adds books to library shelves and fetch books by certain criteria.

Your implementation must adhere to the following rules:
1. Book ISBN numbers are unique.  There cannot be two books with the same ISBN number, so an attempt to add a book to a shelf when that ISBN number already exists in the library should fail.
2. Books are only put on the same shelf as other books of the same genre.
3. Each shelf can only hold a maximum number of books, specified by the library class member variable.
4. When a shelf is full, a new shelf must be created to accommodate another book for that genre.
5. Each shelf will have an associated genre and shelf number within the genre.  The name of the shelf should be formatted as "Genre - Number". For example, “Science Fiction – 1”, “Science Fiction – 2”, “Science Fiction – 3”, “Cooking - 1”, “Cooking – 2”, “Travel – 1”.
6. Books are ordered alphabetically first by author, then title.  The first books in the order are placed on that genre’s shelf #1, when that shelf is full the next books in order go on that genre’s shelf #2, etc.  When adding a book to a shelf that’s already full, books will get displaced to the next numbered shelf to maintain sort order.
7. Only create the number of shelves necessary for all the books.  There should not be any empty shelves in the library.

You can choose the method that you use to test your **LibrarySimulation** class, such as calling methods and testing results in the program **main** method, writing **junit** tests, etc. A sample data csv file has been provided in the resources folder if you would like to use it for sample data, or feel free to use your own test book data for testing.

## Instructions
1. Sign into your GitHub account.
2. Navigate to this code test repository (https://github.com/wellaware/codetest-librarydesign-java).
3. Click the green "Use this template" button on the repository's Code tab.
4. Give your copy of the repository a name. The same or similar name as the template repository is suggested.
5. Making your repository Public will be the easiest way to share with us. If you would prefer to keep it Private, we can arrange for you to add a WellAware account as a collaborator of your repository.
6. As described in the Problem statement, implement the **Library** interface methods in the **LibrarySimulation** implementing class in the repository.
7. When finished, send us a link to your repository to review your work.

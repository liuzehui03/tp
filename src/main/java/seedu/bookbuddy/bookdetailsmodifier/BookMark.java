package seedu.bookbuddy.bookdetailsmodifier;

import exceptions.BookReadAlreadyException;
import exceptions.BookUnreadAlreadyException;
import seedu.bookbuddy.Ui;
import seedu.bookbuddy.book.BookMain;
import seedu.bookbuddy.book.Read;
import seedu.bookbuddy.book.Title;
import seedu.bookbuddy.booklist.BookList;
import utils.DateTimeUtils;

import java.util.ArrayList;
import java.util.logging.Level;

import static seedu.bookbuddy.BookBuddy.LOGGER;

public class BookMark {

    //@@author lordgareth10
    /**
     * Marks a book as read by its index.
     *
     * @param bookList
     * @param index The index of the book to mark as read.
     */
    public static void markDoneByIndex(BookList bookList, int index) throws IndexOutOfBoundsException,
            BookReadAlreadyException {
        try {
            assert index > 0 && index <= bookList.getBooks().size() : "Index out of valid range";
            if (Read.getRead(bookList.getBooks().get(index - 1))) {
                throw new BookReadAlreadyException("That book is already marked as read! " +
                        "Type 'list' to view the list of books.");
            }
            assert !Read.getRead(bookList.getBooks().get(index - 1)) : "Book is already marked as read. ";
            markBookAsRead(bookList.getBooks().get(index - 1));
            assert Read.getRead(bookList.getBooks().get(index - 1)) : "Book should be marked as read";
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid book index. Please enter a valid index. " +
                    "Type 'list' to view the list of books.");
        } catch (BookReadAlreadyException e) {
            System.out.println("That book is already marked as read! " +
                    "Type 'list' to view the list of books.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "An unexpected error occurred: {0}", e.getMessage());
            throw e; // Rethrow or handle as needed
        }
    }

    //@@author lordgareth10
    /**
     * Marks a book as unread by its index.
     *
     * @param bookList
     * @param index The index of the book to mark as unread.
     */
    public static void markUndoneByIndex(BookList bookList, int index) throws IndexOutOfBoundsException,
            BookReadAlreadyException{
        try {
            assert index > 0 && index <= bookList.getBooks().size() : "Index out of valid range";
            if (!Read.getRead(bookList.getBooks().get(index - 1))) {
                throw new BookUnreadAlreadyException("That book is already marked as unread! " +
                        "Type 'list' to view the list of books.");
            }
            assert Read.getRead(bookList.getBooks().get(index - 1)) : "Book is already marked as unread. " +
                    "Type 'list' to view the list of books.";
            markBookAsUnread(bookList.getBooks().get(index - 1));
            assert !Read.getRead(bookList.getBooks().get(index - 1)) : "Book should be marked as unread. " +
                    "Type 'list' to view the list of books.";
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid book index. Please enter a valid index. " +
                    "Type 'list' to view the list of books.");
        } catch (BookUnreadAlreadyException e) {
            System.out.println("That book is already marked as unread! Type 'list' to view the list of books.");
        } catch (Exception e) { // Generic catch block for any other exceptions
            System.out.println("An unexpected error occurred. Please contact support.");
        }
    }

    //@@author lordgareth10
    /**
     * Marks the book as read.
     *
     * @param book
     */
    public static void markBookAsRead(BookMain book) {
        Read.setRead(book, true);
        Ui.printShortLine();
        System.out.println("Successfully marked [" + Title.getTitle(book) + "] as read.");
    }

    /**
     * Marks the book as unread.
     *
     * @param book
     */
    public static void markBookAsUnread(BookMain book) {
        Read.setRead(book, false);
        Ui.printShortLine();
        System.out.println("Successfully marked [" + Title.getTitle(book) + "] as unread.");
    }

    /**
     * Prints all books sorted by date in descending order.
     *
     * @param books
     */
    public static void printBooksByDateRead(BookList books) {
        ArrayList<BookMain> sortedlist = sortBooksByDateRead(books);
        if (books.getBooks().isEmpty()) {
            System.out.println("The list is empty. Add books by 'add [book]'");
        } else if (sortedlist.isEmpty()){
            System.out.println("You have not read any books yet! Marks books by 'mark [BOOK_INDEX]'");
        } else {
            for (BookMain book : sortedlist) {
                String datetime = Read.getDateTimeRead(book);
                System.out.println(Title.getTitle(book) + " : " + datetime);
            }
        }
    }

    /**
     * Sorts all books sorted by date in descending order.
     *
     * @param books
     */
    public static ArrayList<BookMain> sortBooksByDateRead(BookList books) {
        ArrayList<BookMain> bookRead = new ArrayList<>();
        for (BookMain book : books.getBooks()) {
            if (Read.getRead(book)) {
                bookRead.add(book);
            }
        }

        bookRead.sort((book1, book2) -> {
            String dateTimeStr1 = Read.getDateTimeRead(book1);
            String dateTimeStr2 = Read.getDateTimeRead(book2);
            return DateTimeUtils.convertStringToDateTime(dateTimeStr2).compareTo(
                    DateTimeUtils.convertStringToDateTime(dateTimeStr1));
        });

        return bookRead;
    }
}

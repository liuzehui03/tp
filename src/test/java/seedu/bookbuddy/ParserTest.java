package seedu.bookbuddy;

import exceptions.BookNotFoundException;
import exceptions.InvalidBookIndexException;
import exceptions.InvalidCommandArgumentException;
import exceptions.UnsupportedCommandException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ParserTest {
    @Test
    void testParser() {
        BookList books = new BookList();
        books.addBook("Don Quixote");
        books.addBook("Gulliver's Travels");
        assertEquals(2, books.getSize());
        books.markDoneByIndex(1);
        assertEquals("[R] Don Quixote", books.getBook(0).toString());
        assertEquals("[U] Gulliver's Travels", books.getBook(1).toString());
        books.deleteBook(1);
        books.markDoneByIndex(1);
        assertTrue(books.getBook(0).isRead);
        assertEquals("[R] Gulliver's Travels", books.getBook(0).toString());
    }

    @Test
    void parseAddCommand() {
        BookList testBookList = new BookList();
        Parser.parseCommand("add The Great Gatsby", testBookList);
        assertEquals(1, testBookList.getSize());
        assertEquals("The Great Gatsby", testBookList.getBook(0).getTitle());
    }

    @Test
    void parseRemoveCommand() {
        BookList books = new BookList();
        books.addBook("The Great Gatsby");
        Parser.parseCommand("remove 1", books);
        assertEquals(0, books.getSize());
    }

    @Test
    void parseMarkCommand() {
        BookList books = new BookList();
        books.addBook("The Great Gatsby");
        Parser.parseCommand("mark 1", books);
        assertTrue(books.getBook(0).isRead());
    }

    @Test
    void parseUnmarkCommand() {
        BookList books = new BookList();
        books.addBook("The Great Gatsby");
        Parser.parseCommand("mark 1", books);
        Parser.parseCommand("unmark 1", books);
        assertFalse(books.getBook(0).isRead());
    }

    @Test
    void parseInvalidAddCommandThrowsException() {
        BookList books = new BookList();
        String input = "add"; // No book title provided
        assertThrows(InvalidCommandArgumentException.class,
                () -> Parser.parseCommand(input, books), "The add command requires a book title.");
    }

    @Test
    void parseInvalidRemoveCommandThrowsException() {
        BookList books = new BookList();
        String input = "remove notAnIndex"; // Invalid index provided
        assertThrows(InvalidBookIndexException.class,
                () -> Parser.parseCommand(input, books), "Book index must be an integer.");
    }

    @Test
    void parseRemoveCommandForNonExistentBookThrowsException() {
        BookList books = new BookList();
        String input = "remove 1"; // No books in the list, so index 1 is invalid
        assertThrows(BookNotFoundException.class,
                () -> Parser.parseCommand(input, books), "Book not found at the provided index.");
    }

    @Test
    void parseUnsupportedCommandThrowsException() {
        BookList books = new BookList();
        String input = "Geronimo Stilton"; // Completely unsupported command
        assertThrows(UnsupportedCommandException.class,
                () -> Parser.parseCommand(input, books), "Sorry but that is not a valid command. Please try again");
    }
}



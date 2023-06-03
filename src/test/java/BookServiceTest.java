import org.example.Book;
import org.example.BookService;
import org.example.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class BookServiceTest {
    @Mock
    private BookService bookService;
    @Mock
    private User user;
    @Mock
    private Book book;
    @Mock
    private List<Book> bookDatabase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchBook() {
        List<Book> searchResults = new ArrayList<>();
        searchResults.add(book);

        when(bookService.searchBook(any(String.class))).thenReturn(searchResults);

        List<Book> actualSearchResults = bookService.searchBook("Keyword");

        assertEquals(searchResults, actualSearchResults, "Search results should match the expected list");
        verify(bookService).searchBook("Keyword");
    }

    @Test
    public void testPurchaseBook() {
        when(bookService.purchaseBook(user, book)).thenReturn(true);
        boolean purchaseResult = bookService.purchaseBook(user, book);
        assertTrue(purchaseResult, "Book purchase should be successful");
        verify(bookService).purchaseBook(user, book);
    }

    @Test
    public void testAddBook() {
        when(bookService.addBook(book)).thenReturn(true);
        boolean addBookResult = bookService.addBook(book);
        assertTrue(addBookResult, "Book added successfully");
        verify(bookService).addBook(book);
    }
    @Test
    @DisplayName("Search Book by Title")
    public void searchBookTitlePositive() {
        Book book1 = new Book("Green Eggs and Ham", "Dr. Seuss", "Children", 10.00);
        Book book2 = new Book("Dragon Loves Fart", "Hollywood Kay", "Fantasy", 8.60);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        when(bookDatabase.stream()).thenReturn(books.stream());

        List<Book> results = bookService.searchBook("Dragon Loves Fart");
        assertNotNull(1, String.valueOf(results.size()));
        assertFalse(results.contains(book2));
    }
    @Test
    @DisplayName("Search Book by Author")
    public void searchBookAuthorPositive() {
        Book book1 = new Book("Green Eggs and Ham", "Dr. Seuss", "Children", 10.00);
        Book book2 = new Book("Dragon Loves Fart", "Hollywood Kay", "Fantasy", 8.60);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        when(bookDatabase.stream()).thenReturn(books.stream());

        List<Book> results = bookService.searchBook("Hollywood Kay");
        assertNotNull(1, String.valueOf(results.size()));
        assertFalse(results.contains(book2));
    }
    @Test
    @DisplayName("Search Book by Genre - Positive Match")
    public void searchBookGenrePositive() {
        Book book1 = new Book("Green Eggs and Ham", "Dr. Seuss", "Children", 10.00);
        Book book2 = new Book("Dragon Loves Fart", "Hollywood Kay", "Fantasy", 8.60);
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        when(bookDatabase.stream()).thenReturn(books.stream());

        List<Book> results = bookService.searchBook("Children");
        assertNotNull(1, String.valueOf(results.size()));
        assertFalse(results.contains(book1));
    }

    @Test
    public void purchaseBookPositive() {
        Book book1 = new Book("Green Eggs and Ham", "Dr. Seuss", "Children", 10.00);
        User user = new User("vuelee123", "password123", "vuelee123@gmail.com");
        when(bookDatabase.contains(book1)).thenReturn(true);
        boolean result = bookService.purchaseBook(user, book1);
        assertFalse(result);
    }
}

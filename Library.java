import java.util.*;

// Book class representing the details of a book
class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;
    
    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailability(boolean available) {
        this.isAvailable = available;
    }
    
    @Override
    public String toString() {
        return "Book [Title=" + title + ", Author=" + author + ", ISBN=" + ISBN + "]";
    }
}

// Member class representing a library member
class Member {
    private String name;
    private String memberId;
    private int borrowedBooks;
    private List<Book> borrowedBookList;
    
    public Member(String name, String memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = 0;
        this.borrowedBookList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }
    
    public List<Book> getBorrowedBookList() {
        return borrowedBookList;
    }

    public void borrowBook(Book book) {
        borrowedBookList.add(book);
        borrowedBooks++;
    }
    
    public void returnBook(Book book) {
        borrowedBookList.remove(book);
        borrowedBooks--;
    }

    @Override
    public String toString() {
        return "Member [Name=" + name + ", Member ID=" + memberId + ", Borrowed Books=" + borrowedBooks + "]";
    }
}

// Library class representing the library management system
class Library {
    private List<Book> books;
    private Map<String, Member> members;
    private Map<String, Date> borrowRecords;
    private final int BORROW_LIMIT = 5;
    private final int MAX_BORROW_DAYS = 14;
    
    public Library() {
        books = new ArrayList<>();
        members = new HashMap<>();
        borrowRecords = new HashMap<>();
    }

    // Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // Register a member
    public void registerMember(Member member) {
        members.put(member.getMemberId(), member);
    }

    // Search a book by ISBN
    public Book searchBookByISBN(String ISBN) {
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                return book;
            }
        }
        return null;
    }

    // Borrow a book
    public boolean borrowBook(String ISBN, String memberId) {
        Book book = searchBookByISBN(ISBN);
        Member member = members.get(memberId);
        
        if (book == null || member == null) {
            System.out.println("Invalid book or member.");
            return false;
        }
        
        if (!book.isAvailable()) {
            System.out.println("Book is currently unavailable.");
            return false;
        }
        
        if (member.getBorrowedBooks() >= BORROW_LIMIT) {
            System.out.println("Borrow limit reached. Cannot borrow more than " + BORROW_LIMIT + " books.");
            return false;
        }

        book.setAvailability(false);
        member.borrowBook(book);
        borrowRecords.put(ISBN, new Date());
        System.out.println("Book borrowed successfully by " + member.getName());
        return true;
    }

    // Return a book and calculate fine if applicable
    public boolean returnBook(String ISBN, String memberId) {
        Book book = searchBookByISBN(ISBN);
        Member member = members.get(memberId);

        if (book == null || member == null || !borrowRecords.containsKey(ISBN)) {
            System.out.println("Invalid return attempt.");
            return false;
        }

        book.setAvailability(true);
        member.returnBook(book);

        Date borrowDate = borrowRecords.get(ISBN);
        borrowRecords.remove(ISBN);

        int fine = calculateFine(borrowDate);
        if (fine > 0) {
            System.out.println("Book returned late. Fine: $" + fine);
        } else {
            System.out.println("Book returned on time. No fine.");
        }

        return true;
    }

    // Calculate fine for late return
    private int calculateFine(Date borrowDate) {
        long borrowTime = new Date().getTime() - borrowDate.getTime();
        long borrowDays = borrowTime / (1000 * 60 * 60 * 24); // Convert to days

        if (borrowDays > MAX_BORROW_DAYS) {
            return (int)(borrowDays - MAX_BORROW_DAYS) * 2; // $2 fine per day
        }
        return 0;
    }

    // Display all available books
    public void displayAvailableBooks() {
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    // Display borrowed books by a member
    public void displayBorrowedBooks(String memberId) {
        Member member = members.get(memberId);
        if (member != null) {
            System.out.println("\nBorrowed Books by " + member.getName() + ":");
            for (Book book : member.getBorrowedBookList()) {
                System.out.println(book);
            }
        } else {
            System.out.println("Member not found.");
        }
    }
}

// Main class to simulate the library system
public class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        
        // Add books
        library.addBook(new Book("The Alchemist", "Paulo Coelho", "ISBN001"));
        library.addBook(new Book("1984", "George Orwell", "ISBN002"));
        library.addBook(new Book("The Catcher in the Rye", "J.D. Salinger", "ISBN003"));
        
        // Register members
        Member member1 = new Member("John Doe", "MEM001");
        Member member2 = new Member("Jane Smith", "MEM002");
        library.registerMember(member1);
        library.registerMember(member2);
        
        // Display available books
        library.displayAvailableBooks();
        
        // Borrow books
        library.borrowBook("ISBN001", "MEM001");
        library.borrowBook("ISBN002", "MEM002");

        // Display available books after borrowing
        library.displayAvailableBooks();
        
        // Display borrowed books by member
        library.displayBorrowedBooks("MEM001");

        // Return a book
        library.returnBook("ISBN001", "MEM001");

        // Display available books after return
        library.displayAvailableBooks();
    }
}

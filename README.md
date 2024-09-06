# Library Management System

## Project Description

The **Library Management System** is a Java-based project that simulates the basic functions of a library. It allows users to manage books, register members, borrow and return books, and calculate fines for overdue returns. The system demonstrates key Object-Oriented Programming (OOP) concepts such as encapsulation, inheritance, and polymorphism.

This project can be used for educational purposes, learning how to implement OOP in Java and managing real-world entities such as books and users in a structured and scalable manner.

## Features

- **Add Books**: The library can store book details such as title, author, and ISBN.
- **Register Members**: Users can be registered with a unique member ID.
- **Borrow Books**: Members can borrow books if they are available and if they haven't reached their borrowing limit.
- **Return Books**: Members can return books, and the system calculates overdue fines.
- **Fine Calculation**: A fine of $2 per day is applied for books returned late (after 14 days).
- **View Available Books**: List all available books in the library.
- **View Borrowed Books**: List all borrowed books for a specific member.

## Technologies Used

- **Programming Language**: Java
- **Concepts**: Object-Oriented Programming (OOP)
    - Classes and Objects
    - Inheritance
    - Polymorphism
    - Encapsulation
- **Data Structures**: `List`, `Map`

## Classes

### 1. `Book`
Represents a book in the library. It has attributes like:
- Title
- Author
- ISBN
- Availability (whether it’s borrowed or available)

### 2. `Member`
Represents a library member who can borrow books. It has attributes like:
- Name
- Member ID
- List of borrowed books

### 3. `Library`
Represents the library system. It includes methods to:
- Add books to the library
- Register members
- Borrow and return books
- Calculate fines for overdue books
- Display available and borrowed books

### 4. `LibrarySystem`
The main class where the system is initialized, and different operations (like borrowing and returning books) are executed.

## How to Run the Project

### Prerequisites:
- Java Development Kit (JDK) installed.
- Any IDE like IntelliJ IDEA, Eclipse, or a text editor like VS Code with Java extensions.

### Steps:
1. **Clone the Repository:**

   ```bash
   git clone https://github.com/YourUsername/LibraryManagementSystem.git
   ```

2. **Navigate to the Project Directory:**

   ```bash
   cd LibraryManagementSystem
   ```

3. **Compile the Program:**

   If you are using the command line, you can compile the Java files using:

   ```bash
   javac LibrarySystem.java
   ```

4. **Run the Program:**

   ```bash
   java LibrarySystem
   ```

5. **Using an IDE:**
   - Open the project in your preferred IDE.
   - Build and run the project from the IDE's "Run" button or menu.

## Example Output

```bash
Available Books:
Book [Title=The Alchemist, Author=Paulo Coelho, ISBN=ISBN001]
Book [Title=1984, Author=George Orwell, ISBN=ISBN002]
Book [Title=The Catcher in the Rye, Author=J.D. Salinger, ISBN=ISBN003]

Book borrowed successfully by John Doe

Available Books:
Book [Title=The Catcher in the Rye, Author=J.D. Salinger, ISBN=ISBN003]

Borrowed Books by John Doe:
Book [Title=The Alchemist, Author=Paulo Coelho, ISBN=ISBN001]

Book returned on time. No fine.
```

## Project Structure

```bash
LibraryManagementSystem/
│
├── Book.java              # Represents the book entity
├── Member.java            # Represents the member entity
├── Library.java           # Manages the library's operations
├── LibrarySystem.java     # Main class for simulating the library system
└── README.md              # Project documentation
```

## Future Improvements

- Implement a database (MySQL or SQLite) for persistent data storage.
- Add more functionalities like:
  - Searching books by title or author.
  - Categorizing books by genres.
  - Managing book reservations.
  - Generating detailed reports (most borrowed books, overdue fines summary, etc.).

## Contributing

Contributions are welcome! If you find any bugs or have feature requests, feel free to open an issue or submit a pull request.

import java.io.*;
import java.util.*;

public class BookManager {
    // TODO: your code here
    // attribute books
    private ArrayList<Book> books;

    public BookManager() {
        // TODO: your code here
        loadFromFile();
    }

    public ArrayList<Book> getBooks() {
        // TODO: your code here
        return books;
    }

    /**
     * update this.books by reading books from file books.txt
     */
    public void loadFromFile() {
        System.out.println("Loading books...");
        books = new ArrayList<>();
        // TODO: your code here
        File file = new File("books.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            int id = Integer.parseInt(line.substring(0, 5).trim());
            String name = line.substring(6, 50);
            double price = Double.parseDouble(line.substring(51, 61).trim());
            Book book = new Book(id, name, price);
            books.add(book);
        }
    }

    /**
     * print books (one/line) in required format
     */
    public void printBooks(ArrayList<Book> books) {

        // TODO: your code here
        if (books.size() == 0) {
            System.out.println("(empty)");
        } else {
            System.out.printf("%-5s %-45s %-10s" + "\n", "ID", "Name", "Price");
            for (Book book : books) {
                System.out.println(book.toString());
            }
        }


    }

    /**
     * if book.id is not duplicated, add book to this.books
     * return whether added or not
     */
    public boolean add(Book book) {
        // TODO: your code here
        if (getBookById(book.getId()) == null) {
            books.add(book);
            System.out.println("Added successfully.");
            return true;
        }
        System.out.println("Duplicate ID!");
        return false;
    }

    /**
     * return book specified by id, null if not found
     */
    public Book getBookById(int id) {
        // TODO: your code here
        for (Book book : books) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    /**
     * remove book from this.books
     */
    public void remove(Book book) {
        if (books.remove(book)) {
            System.out.println("Deleted successfully!");
        } else {
            System.out.println("Invalid ID");
        }
    }

    /**
     * update this.books to be sorted by price from high -> low
     */
    public void sortDescByPrice() {
        // TODO: your code here
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return Double.compare(o2.getPrice(), o1.getPrice());
            }
        });
        System.out.println("After sorting:");
        printBooks(books);
    }

    /**
     * return all books having name contains keyword (case in-sensitive)
     */
    public ArrayList<Book> searchByName(String keyword) {
        ArrayList<Book> matches = new ArrayList<>();
        // TODO: your code here
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getName().toLowerCase().contains(keyword.toLowerCase())) {
                matches.add(books.get(i));
            }
        }
        return matches;
    }

    /**
     * write this.books to file books.txt in required format
     */
    public void saveToFile() {
        // TODO: your code here
        System.out.println("Saving to file...");
        System.out.println("Bye!");
        File file = new File("books.txt");

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            PrintWriter writer = new PrintWriter(outputStream);
            for (int i = 0; i < books.size(); i++) {
                String bookFormat = String.format("%5d %-45s %10.2f", books.get(i).getId(), books.get(i).getName(), books.get(i).getPrice());
//                System.out.println(bookFormat);
                writer.println(bookFormat);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }


    }


}

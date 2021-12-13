import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // TODO: your code here

        BookManager manager = new BookManager();
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("-----------------------------------");
            System.out.println("1. list all books");
            System.out.println("2. add a new book");
            System.out.println("3. edit book");
            System.out.println("4. delete a book");
            System.out.println("5. search books by name");
            System.out.println("6. sort books descending by price\n");
            System.out.println("0. save & exit");
            System.out.println("-----------------------------------");
            System.out.print("Your option: ");
            option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    listAllBooks(manager);
                    break;
                case 2:
                    addANewBook(manager, scanner);
                    break;
                case 3:
                    editBook(manager, scanner);
                    break;
                case 4:
                    deleteABook(manager, scanner);
                    break;
                case 5:
                    searchBookByName(manager, scanner);
                    break;
                case 6:
                    sortBooksDescendingByPrice(manager);
                    break;
                case 0:
                    saveAndExit(manager);
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        } while (true);

    }

    private static void saveAndExit(BookManager manager) {
        manager.saveToFile();
        System.exit(0);
    }

    private static void searchBookByName(BookManager manager, Scanner scanner) {
        System.out.print("Enter book name: ");
        String keyWord = scanner.nextLine();
        manager.printBooks(manager.searchByName(keyWord));
    }

    private static void sortBooksDescendingByPrice(BookManager manager) {
        manager.sortDescByPrice();
    }

    private static void deleteABook(BookManager manager, Scanner scanner) {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        manager.remove(manager.getBookById(id));
    }

    private static void editBook(BookManager manager, Scanner scanner) {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Book book = manager.getBookById(id);
        if (book != null) {
            System.out.print("Enter book name: ");
            String name = scanner.nextLine();
            System.out.print("Enter book price: ");
            double price = Double.parseDouble(scanner.nextLine());
            book.setName(name);
            book.setPrice(price);
            System.out.println("Updated successfully.");
        } else {
            System.out.println("Invalid ID!");
        }
    }

    private static void addANewBook(BookManager manager, Scanner scanner) {
        System.out.print("Enter book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter book name: ");
        String name = scanner.nextLine();
        System.out.print("Enter book price: ");
        double price = Double.parseDouble(scanner.nextLine());
        Book book = new Book(id, name, price);
        manager.add(book);
    }

    private static void listAllBooks(BookManager manager) {
        manager.printBooks(manager.getBooks());
    }
}

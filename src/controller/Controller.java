package controller;

import javafx.collections.ObservableList;
import datamodel.Book;

public class Controller {
    private ObservableList<Book> books;

    public Controller(ObservableList<Book> books) {
        this.books = books;
    }

    public void newFile() {
        books.clear();
    }

    public void generateFile() {
        books.clear();
        books.addAll(new BookGenerator().generate());
    }

    public void openFile(String filePath) {
        books.clear();
        new FileProcessor(filePath).read(books);
    }

    public void saveFile(String filePath) {
        new FileProcessor(filePath).write(books);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(ObservableList<Book> booksToRemove) {
        books.removeAll(booksToRemove);
    }
}

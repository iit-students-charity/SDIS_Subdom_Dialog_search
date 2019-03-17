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
        new SAXReader(books, filePath).read();
    }

    public void saveFile(String filePath) {
        new DOMWriter(books, filePath).write();
    }

    public void addBook(Book book) {
        if (isBookExist(book)) {
            return;
        }
        books.add(book);
    }

    public void removeBook(ObservableList<Book> booksToRemove) {
        books.removeAll(booksToRemove);
    }

    private boolean isBookExist(Book book) {
        for (Book curBook : books) {
            if (curBook.equals(book)) {
                return true;
            }
        }

        return false;
    }
}

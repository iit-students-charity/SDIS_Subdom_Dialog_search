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

    public void addBook(Book book) {
        if (isBookExist(book)) {
            return;
        }
        books.add(book);
    }

    public void removeBook(ObservableList<Book> booksToRemove) {
        books.removeAll(booksToRemove);
    }

    public void openFile() {

    }

    public void saveFile(String filePath) {
        new DOMWriter(books, filePath).write();
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

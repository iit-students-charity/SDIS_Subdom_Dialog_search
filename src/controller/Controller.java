package controller;

import javafx.collections.ObservableList;
import datamodel.Book;

public class Controller {
    private ObservableList<Book> books;

    public Controller(ObservableList<Book> books) {
        this.books = books;
    }

    public void add(Book book) {
        if (!isBookExist(book)) {
            books.add(book);
        }
    }

    public void remove(ObservableList<Book> booksToRemove) {
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

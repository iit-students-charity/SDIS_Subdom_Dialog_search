package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.ParameterCondition;
import datamodel.Book;

public class SearchStrategy {
    private ObservableList<Book> books;
    private ParameterCondition condition;
    private Book exampleBook;
    private int volCountUp;
    private int volCOuntDown;



    public SearchStrategy(ObservableList<Book> books, ParameterCondition condition, Book exampleBook) {
        this.books = books;
        this.condition = condition;
        this.exampleBook = exampleBook;
    }

    public ObservableList<Book> find() {
        ObservableList<Book> foundBooks = FXCollections.observableArrayList();

        switch (condition) {
            case AUTHOR: {
                for (Book book : books) {
                    if (book.getFirstName().equals(exampleBook.getFirstName()) &&
                            book.getMiddleName().equals(exampleBook.getMiddleName()) &&
                            book.getLastName().equals(exampleBook.getLastName())) {
                        foundBooks.add(book);
                    }
                }

                break;
            }
            case PUBLISHING_AUTHOR: {
                for (Book book : books) {
                    if (book.getFirstName().equals(exampleBook.getFirstName()) &&
                            book.getMiddleName().equals(exampleBook.getMiddleName()) &&
                            book.getLastName().equals(exampleBook.getLastName()) &&
                            book.getPublishing().equals(exampleBook.getPublishing())) {
                        foundBooks.add(book);
                    }
                }

                break;
            }
            case VOLUME_COUNT: {
                for (Book book : books) {
                    if (book.getVolumeCount() == exampleBook.getVolumeCount()) {
                        foundBooks.add(book);
                    }
                }

                break;
            }
            case NAME: {
                for (Book book : books) {
                    if (book.getName().equals(exampleBook.getName())) {
                        foundBooks.add(book);
                    }
                }

                break;
            }
            case CIRCULATION: {
                for (Book book : books) {
                    if (book.getCirculation() == exampleBook.getCirculation()) {
                        foundBooks.add(book);
                    }
                }

                break;
            }
            case VOLUME_COUNT_TOTAL: {
                for (Book book : books) {
                    if (book.getVolumeCountTotal() == exampleBook.getVolumeCountTotal()) {
                        foundBooks.add(book);
                    }
                }

                break;
            }
        }

        return foundBooks;
    }
}

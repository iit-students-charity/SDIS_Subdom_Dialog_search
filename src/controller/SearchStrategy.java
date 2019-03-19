package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.ParameterCondition;
import datamodel.Book;

public class SearchStrategy {
    private ObservableList<Book> books;
    private ParameterCondition condition;
    private Book exampleBook;
    private int countUpLimit;
    private int countDownLimit;
    private int countBorder;
    private boolean isGreaterThanBorder;


    public SearchStrategy(ObservableList<Book> books, ParameterCondition condition, Book exampleBook,
                          int countUpLimit, int countDownLimit, int countBorder, boolean isGreaterThanBorder) {
        this.books = books;
        this.condition = condition;
        this.exampleBook = exampleBook;
        this.countUpLimit = countUpLimit;
        this.countDownLimit = countDownLimit;
        this.countBorder = countBorder;
        this.isGreaterThanBorder = isGreaterThanBorder;
    }

    public ObservableList<Book> find() {
        ObservableList<Book> foundBooks = FXCollections.observableArrayList();

        switch (condition) {
            case AUTHOR: {
                for (Book book : books) {
                    if (book.getAuthor().equals(exampleBook.getAuthor())) {
                        foundBooks.add(book);
                    }
                }

                break;
            }
            case PUBLISHING_AUTHOR: {
                for (Book book : books) {
                    if (book.getAuthor().equals(exampleBook.getAuthor()) &&
                            book.getPublishing().equals(exampleBook.getPublishing())) {
                        foundBooks.add(book);
                    }
                }

                break;
            }
            case VOLUME_COUNT: {
                for (Book book : books) {
                    if (book.getVolumeCount() <= countUpLimit && book.getVolumeCount() >= countDownLimit) {
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
                if (isGreaterThanBorder) {
                    for (Book book : books) {
                        if (book.getPublishing().getCirculation() > countBorder) {
                            foundBooks.add(book);
                        }
                    }
                } else {
                    for (Book book : books) {
                        if (book.getPublishing().getCirculation() < countBorder) {
                            foundBooks.add(book);
                        }
                    }
                }

                break;
            }
            case VOLUME_COUNT_TOTAL: {
                if (isGreaterThanBorder) {
                    for (Book book : books) {
                        if (book.getVolumeCountTotal() > countBorder) {
                            foundBooks.add(book);
                        }
                    }
                } else {
                    for (Book book : books) {
                        if (book.getVolumeCountTotal() < countBorder) {
                            foundBooks.add(book);
                        }
                    }
                }

                break;
            }
        }

        return foundBooks;
    }
}

package controller;

import datamodel.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;

public class BookGenerator {
    private static final int booksMaxCount = 100;
    private static final int volumeMaxCount = 5;
    private static final int cirMaxCount = 5000;


    public ObservableList<Book> generate() {
        ObservableList<Book> books = FXCollections.observableArrayList();

        int booksSize = new Random().nextInt(booksMaxCount);
        for (int bookCounter = 0; bookCounter < booksSize; bookCounter++) {
            books.add(new Book(
                getRandomItem(BookData.name),
                getRandomItem(BookData.firstName),
                getRandomItem(BookData.middleName),
                getRandomItem(BookData.lastName),
                getRandomItem(BookData.publishing),
                new Random().nextInt(volumeMaxCount) + 1,
                new Random().nextInt(cirMaxCount) + 1
            ));
        }

        return books;
    }

    private String getRandomItem(String[] array) {
        return array[new Random().nextInt(array.length)];
    }
}

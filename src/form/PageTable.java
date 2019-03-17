package form;

import java.lang.String;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import datamodel.Book;

public class PageTable {
    public enum controlType {FIRST, PREV, NEXT, LAST}


    private TableView<Book> bookTable;
    private TextField countOfBooksOnPageTextField;
    private Label booksCounterLabel;
    private Label pagesCounterLabel;
    private HBox paginationBox;

    private VBox rootVBox;

    private int countOfBooksOnPage;
    private int countOfBooks;
    private int currentPage;
    private int countOfPages;
    private ObservableList<Book> books;
    private ObservableList<Book> currentBooks;


    public PageTable(ObservableList<Book> books) {
        this.books = books;
        currentBooks = FXCollections.observableArrayList();
        bookTable = createBookTable();
        paginationBox = createPaginationBox();

        rootVBox = new VBox(bookTable, paginationBox);
        rootVBox.setSpacing(10);

        countOfBooksOnPage = 0;
        currentPage = 1;
        countOfPages = 1;
    }

    public TableView<Book> createBookTable() {
        TableView<Book> bookTable = new TableView<>();
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        bookTable.setItems(currentBooks);

        TableColumn<Book, String> nameCol = new TableColumn<>(Constant.NAME);
        TableColumn<Book, String> authorCol = new TableColumn<>(Constant.AUTHOR);
        TableColumn<Book, String> pubCol = new TableColumn<>(Constant.PUBLISHING);
        TableColumn<Book, Integer> volCountCol = new TableColumn<>(Constant.VOLUME_COUNT);
        TableColumn<Book, Integer> cirCol = new TableColumn<>(Constant.CIRCULATION);
        TableColumn<Book, Integer> volCountTotalCol = new TableColumn<>(Constant.VOLUME_COUNT_TOTAL);

        nameCol.setCellValueFactory(new PropertyValueFactory<>(Constant.NAME_FIELD));
        authorCol.setCellValueFactory(new PropertyValueFactory<>(Constant.AUTHOR_FIELD));
        pubCol.setCellValueFactory(new PropertyValueFactory<>(Constant.PUBLISHING_FIELD));
        volCountCol.setCellValueFactory(new PropertyValueFactory<>(Constant.VOLUME_COUNT_FIELD));
        cirCol.setCellValueFactory(new PropertyValueFactory<>(Constant.CIRCULATION_FIELD));
        volCountTotalCol.setCellValueFactory(new PropertyValueFactory<>(Constant.VOLUME_COUNT_TOTAL_FIELD));

        nameCol.setSortable(false);
        authorCol.setSortable(false);
        pubCol.setSortable(false);
        volCountCol.setSortable(false);
        cirCol.setSortable(false);
        volCountTotalCol.setSortable(false);

        bookTable.getColumns().addAll(nameCol, authorCol, pubCol, volCountCol, cirCol, volCountTotalCol);

        return bookTable;
    }

    private HBox createPaginationBox() {
        booksCounterLabel = new Label(String.valueOf(countOfBooksOnPage) + '/' + String.valueOf(books.size()));
        countOfBooksOnPageTextField = new TextField();

        Button nextPage = new Button(Constant.NEXT_PAGE);
        Button prevPage = new Button(Constant.PREV_PAGE);
        pagesCounterLabel = new Label("1/1");
        Button firstPage = new Button(Constant.FIRST_PAGE);
        Button lastPage = new Button(Constant.LAST_PAGE);

        nextPage.setOnAction(e -> {
            updateCurrentPage(controlType.NEXT);
        });

        prevPage.setOnAction(e -> {
            updateCurrentPage(controlType.PREV);
        });

        firstPage.setOnAction(e -> {
            updateCurrentPage(controlType.FIRST);
        });

        lastPage.setOnAction(e -> {
            updateCurrentPage(controlType.LAST);
        });

        countOfBooksOnPageTextField.setOnKeyPressed(e -> {
            if (e.getCode().equals(KeyCode.ENTER)) {
                updateCountOfBooksOnPage();
            }
        });


        return createHBox(
                30,
                createHBox(5, new Label(Constant.BOOKS), booksCounterLabel),
                new Separator(Orientation.VERTICAL),
                createHBox(15, new Label(Constant.COUNT_OF_BOOKS_ON_PAGE), countOfBooksOnPageTextField),
                new Separator(Orientation.VERTICAL),
                createHBox(5, firstPage, prevPage, pagesCounterLabel, nextPage, lastPage)
        );
    }

    private HBox createHBox(int spacing, Node... nodes) {
        HBox hBox = new HBox(nodes);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(spacing);

        return hBox;
    }

    // Updaters
    public void updateCountOfBooks() {
        countOfBooks = books.size();
        if (countOfBooksOnPage == 0) {
            countOfBooksOnPage = 10;
        }
        updateCountOfPages();
        booksCounterLabel.setText(String.valueOf(countOfBooksOnPage) + '/' + String.valueOf(countOfBooks));
        updateCurrentPage(controlType.FIRST);
    }

    private void updateCountOfBooksOnPage() {
        try {
            countOfBooksOnPage = Integer.parseInt(countOfBooksOnPageTextField.getText());
        } catch (NumberFormatException ex) {
            return;
        }
        booksCounterLabel.setText(String.valueOf(countOfBooksOnPage) + '/' + String.valueOf(countOfBooks));
        updateCountOfPages();
    }

    private void updateCountOfPages() {
        countOfPages = countOfBooks % countOfBooksOnPage > 0 ?
                countOfBooks / countOfBooksOnPage + 1 : countOfBooks / countOfBooksOnPage;
        updateCurrentPage(controlType.FIRST);
    }

    private void updateCurrentBooks() {
        int toIndex = currentPage * countOfBooksOnPage;

        if (toIndex >= countOfBooks) {
            toIndex = countOfBooks;
        }

        currentBooks.clear();
        currentBooks.addAll(books.subList(
                (currentPage - 1) * countOfBooksOnPage,
                toIndex
        ));
    }

    public void updateCurrentPage(controlType type) {
        switch (type) {
            case NEXT: {
                if (currentPage < countOfPages) {
                    currentPage++;
                }
                System.out.println(currentPage);

                break;
            }
            case PREV: {
                if (currentPage > 1) {
                    currentPage--;
                }

                break;
            }
            case LAST: {
                currentPage = countOfPages;

                break;
            }
            case FIRST: {
                currentPage = 1;

                break;
            }
        }
        pagesCounterLabel.setText(String.valueOf(currentPage) + '/' + String.valueOf(countOfPages));
        updateCurrentBooks();
    }

    // Getters
    public TableView<Book> getBookTable() {
        return bookTable;
    }

    public VBox getRootVBox() {
        return rootVBox;
    }
}

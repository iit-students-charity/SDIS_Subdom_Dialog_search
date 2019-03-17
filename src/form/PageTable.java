package form;

import java.lang.String;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    private int countOfBooksOnPageRepresent;
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

        countOfBooksOnPageRepresent = 10;
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
        bookTable.setPrefWidth(650);

        return bookTable;
    }

    private HBox createPaginationBox() {
        booksCounterLabel = new Label(String.valueOf(countOfBooksOnPageRepresent) + '/' + String.valueOf(books.size()));
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
                updateBooksCounterLabel();
            }
        });


        return createHBox(
                20,
                createHBox(5, new Label(Constant.BOOKS), booksCounterLabel),
                new Separator(Orientation.VERTICAL),
                createHBox(10, new Label(Constant.BOOKS_ON_PAGE), countOfBooksOnPageTextField),
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
        updateCurrentBooks();
        updateCountOfPages();
        updateBooksCounterLabel();
        updateCurrentPage(controlType.FIRST);
    }

    private void updateCountOfPages() {
        countOfPages = countOfBooks % countOfBooksOnPageRepresent > 0 ?
                countOfBooks / countOfBooksOnPageRepresent + 1 : countOfBooks / countOfBooksOnPageRepresent;
        updateCurrentPage(controlType.FIRST);
    }

    public void updateCurrentPage(controlType type) {
        switch (type) {
            case NEXT: {
                if (currentPage < countOfPages) {
                    currentPage++;
                }

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

    private void updateCurrentBooks() {
        int toIndex = currentPage * countOfBooksOnPageRepresent;

        if (toIndex >= countOfBooks) {
            toIndex = countOfBooks;
        }

        currentBooks.clear();
        currentBooks.addAll(books.subList(
                (currentPage - 1) * countOfBooksOnPageRepresent,
                toIndex
        ));
    }

    private void updateCountOfBooksOnPage() {
        if (currentBooks.size() == 0) {
            countOfBooksOnPageRepresent = 10;
            return;
        }

        try {
            countOfBooksOnPageRepresent = Integer.parseInt(countOfBooksOnPageTextField.getText());

            if (countOfBooksOnPageRepresent > books.size()) {
                countOfBooksOnPageRepresent = books.size();
            }

            if (countOfBooksOnPageRepresent <= 0) {
                return;
            }
        } catch (NumberFormatException ex) {
            return;
        }
        updateBooksCounterLabel();
        updateCountOfPages();
    }

    private void updateBooksCounterLabel() {
        booksCounterLabel.setText(String.valueOf(currentBooks.size()) + '/' + String.valueOf(countOfBooks));
    }

    // Getters
    public TableView<Book> getBookTable() {
        return bookTable;
    }

    public VBox getRootVBox() {
        return rootVBox;
    }
}

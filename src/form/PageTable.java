package form;

import java.lang.String;

import controller.ControllerConstant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import datamodel.Book;

public class PageTable {
    public enum controlType { FIRST, PREV, NEXT, LAST }


    private TableView<Book> bookTable;
    private TextField countOfBooksOnPageTextField;
    private Label booksCounterLabel;
    private Label pagesCounterLabel;

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

        rootVBox = new VBox(bookTable, createPaginationBar());

        countOfBooksOnPageRepresent = 10;
        currentPage = 1;
        countOfPages = 1;
    }

    public TableView<Book> createBookTable() {
        TableView<Book> bookTable = new TableView<>();
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        bookTable.setItems(currentBooks);

        TableColumn<Book, String> nameCol = new TableColumn<>(ViewConstant.NAME);
        TableColumn<Book, String> authorCol = new TableColumn<>(ViewConstant.AUTHOR);
        TableColumn<Book, String> pubCol = new TableColumn<>(ViewConstant.PUBLISHING);
        TableColumn<Book, Integer> volCountCol = new TableColumn<>(ViewConstant.VOLUME_COUNT);
        TableColumn<Book, Integer> cirCol = new TableColumn<>(ViewConstant.CIRCULATION);
        TableColumn<Book, Integer> volCountTotalCol = new TableColumn<>(ViewConstant.VOLUME_COUNT_TOTAL);

        nameCol.setCellValueFactory(new PropertyValueFactory<>(ControllerConstant.NAME_FIELD));
        authorCol.setCellValueFactory(new PropertyValueFactory<>(ControllerConstant.AUTHOR_FIELD));
        pubCol.setCellValueFactory(new PropertyValueFactory<>("publishingName"));
        volCountCol.setCellValueFactory(new PropertyValueFactory<>(ControllerConstant.VOLUME_COUNT_FIELD));
        cirCol.setCellValueFactory(new PropertyValueFactory<>("publishingCirculation"));
        volCountTotalCol.setCellValueFactory(new PropertyValueFactory<>(ControllerConstant.VOLUME_COUNT_TOTAL_FIELD));

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

    private ToolBar createPaginationBar() {
        booksCounterLabel = new Label(String.valueOf(countOfBooksOnPageRepresent) + '/' + String.valueOf(books.size()));
        countOfBooksOnPageTextField = new TextField();
        countOfBooksOnPageTextField.setPrefWidth(50);

        Button nextPage = new Button(ViewConstant.NEXT_PAGE);
        Button prevPage = new Button(ViewConstant.PREV_PAGE);
        pagesCounterLabel = new Label("1/1");
        Button firstPage = new Button(ViewConstant.FIRST_PAGE);
        Button lastPage = new Button(ViewConstant.LAST_PAGE);


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

        return new ToolBar(
                createHBox(10, new Label(ViewConstant.BOOKS_ON_PAGE), countOfBooksOnPageTextField),
                new Separator(),
                createHBox(5, firstPage, prevPage, pagesCounterLabel, nextPage, lastPage),
                new Separator(),
                createHBox(5, new Label(ViewConstant.BOOKS), booksCounterLabel)
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
        if (currentBooks.size() == 0) {
            countOfPages = currentPage = 1;
        }

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

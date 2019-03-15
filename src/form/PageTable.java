package form;
import com.sun.org.apache.xpath.internal.operations.String;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import datamodel.Book;

public class PageTable {
    private TableView<Book> bookTable;
    private HBox paginationBox;
    private ObservableList<Book> books;
    private VBox rootVBox;

    public PageTable(ObservableList<Book> books) {
        this.books = books;
        bookTable = createBookTable();
        paginationBox = createPaginationBox();

        rootVBox = new VBox(bookTable, paginationBox);
        rootVBox.setSpacing(10);
    }

    public TableView<Book> createBookTable() {
        TableView<Book> bookTable = new TableView<>();
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        bookTable.setItems(books);

        TableColumn<Book, String> nameCol = new TableColumn<>(FormContentText.NAME);
        TableColumn<Book, String> authorCol = new TableColumn<>(FormContentText.AUTHOR);
        TableColumn<Book, String> pubCol = new TableColumn<>(FormContentText.PUBLISHING);
        TableColumn<Book, Integer> volCountCol = new TableColumn<>(FormContentText.VOLUME_COUNT);
        TableColumn<Book, Integer> cirCol = new TableColumn<>(FormContentText.CIRCULATION);
        TableColumn<Book, Integer> volCountTotalCol = new TableColumn<>(FormContentText.VOLUME_COUNT_TOTAL);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        pubCol.setCellValueFactory(new PropertyValueFactory<>("publishing"));
        volCountCol.setCellValueFactory(new PropertyValueFactory<>("volumeCount"));
        cirCol.setCellValueFactory(new PropertyValueFactory<>("circulation"));
        volCountTotalCol.setCellValueFactory(new PropertyValueFactory<>("volumeCountTotal"));

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
        Button nextPage = new Button(FormContentText.NEXT_PAGE);
        Button prevPage = new Button(FormContentText.PREV_PAGE);
        Label pageNumerator = new Label("1/1");
        Button firstPage = new Button(FormContentText.FIRST_PAGE);
        Button lastPage = new Button(FormContentText.LAST_PAGE);

        HBox hBox = new HBox(firstPage, prevPage, pageNumerator, nextPage, lastPage);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);

        return hBox;
    }

    public TableView<Book> getBookTable() {
        return bookTable;
    }

    public VBox getRootVBox() {
        return rootVBox;
    }
}

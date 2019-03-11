package sample;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class MainForm {
    MenuBar menuBar;
    ToolBar toolBar;
    TableView<Book> bookTable;

    VBox vBox;


    public MainForm() {
        menuBar = createMenuBar();
        toolBar = createToolBar();
        bookTable = createBookTable();

        vBox = new VBox(menuBar, bookTable, toolBar);
    }

    public VBox getVBox() {
        return vBox;
    }

    public MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(createFileMenu(), createEditMenu(), createSearchMenu());

        return menuBar;
    }

    public Menu createFileMenu() {
        Menu fileMenu = new Menu("File");

        MenuItem newFile = new MenuItem("New");
        MenuItem openFile = new MenuItem("Open");
        MenuItem saveFile = new MenuItem("Save");

        fileMenu.getItems().addAll(newFile, openFile, saveFile);

        return fileMenu;
    }

    public Menu createEditMenu() {
        Menu editMenu = new Menu("Edit");

        MenuItem add = new MenuItem("Add");
        MenuItem remove = new MenuItem("Remove");

        editMenu.getItems().addAll(add, remove);

        return editMenu;
    }

    public Menu createSearchMenu() {
        Menu searchMenu = new Menu("Search");

        MenuItem byAuthor = new MenuItem("Author");
        MenuItem byPubAndAuthor = new MenuItem("Publishing & author");
        MenuItem byVolCount = new MenuItem("Volume count");
        MenuItem byName = new MenuItem("Name");
        MenuItem byCir = new MenuItem("Circulation");
        MenuItem byVolCountTotal = new MenuItem("Volume count total");

        searchMenu.getItems().addAll(byAuthor, byPubAndAuthor, byVolCount, byName, byCir, byVolCountTotal);

        return searchMenu;
    }

    public TableView<Book> createBookTable() {
        TableView<Book> bookTable = new TableView<>();
        bookTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Book, String> nameCol = new TableColumn<>("Name");
        TableColumn<Book, String> authorCol = new TableColumn<>("Author");
        TableColumn<Book, String> pubCol = new TableColumn<>("Publishing");
        TableColumn<Book, Integer> volCountCol = new TableColumn<>("Vol. count");
        TableColumn<Book, Integer> cirCol = new TableColumn<>("Circulation");
        TableColumn<Book, Integer> volCountTotalCol = new TableColumn<>("Total vol. count");

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

    public ToolBar createToolBar() {
        ToolBar toolBar = new ToolBar();

        Button newFileButton = new Button("New");
        Button openFileButton = new Button("Open");
        Button saveFileButton = new Button("Save");
        Button searchButton = new Button("Search");
        Button addButton = new Button("Add");
        Button removeButton = new Button("Remove");

        toolBar.getItems().addAll(newFileButton, openFileButton, saveFileButton);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().addAll(searchButton, addButton, removeButton);

        return toolBar;
    }
}

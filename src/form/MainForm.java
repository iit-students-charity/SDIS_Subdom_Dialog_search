package form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import controller.SearchStrategy;
import datamodel.Book;
import controller.Controller;
import sample.ParameterCondition;

import java.util.regex.Pattern;

public class MainForm {
    private MenuBar menuBar;
    private ToolBar toolBar;
    private VBox pageTable;

    private VBox vBox;
    private Controller controller;
    private ObservableList<Book> books;


    public MainForm(Controller controller, ObservableList<Book> books) {
        this.controller = controller;
        this.books = books;

        menuBar = createMenuBar();
        toolBar = createToolBar();
        pageTable = new PageTable(books).getRootVBox();

        vBox = new VBox(menuBar, pageTable, toolBar);
    }

    public VBox getVBox() {
        return vBox;
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(createFileMenu(), createEditMenu(), createSearchMenu());

        return menuBar;
    }

    private Menu createFileMenu() {
        Menu fileMenu = new Menu("File");

        MenuItem newFile = new MenuItem("New");
        MenuItem openFile = new MenuItem("Open");
        MenuItem saveFile = new MenuItem("Save");

        fileMenu.getItems().addAll(newFile, openFile, saveFile);

        return fileMenu;
    }

    private Menu createEditMenu() {
        Menu editMenu = new Menu("Edit");

        MenuItem add = new MenuItem("Add");
        Menu remove = new Menu("Remove");
        MenuItem byAuthor = new MenuItem("Author");
        MenuItem byPubAndAuthor = new MenuItem("Publishing & author");
        MenuItem byVolCount = new MenuItem("Volume count");
        MenuItem byName = new MenuItem("Name");
        MenuItem byCir = new MenuItem("Circulation");
        MenuItem byVolCountTotal = new MenuItem("Volume count total");

        remove.getItems().addAll(byAuthor, byPubAndAuthor, byVolCount, byName, byCir, byVolCountTotal);

        byAuthor.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.AUTHOR).show();
        });

        byPubAndAuthor.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.PUBLISHING_AUTHOR).show();
        });

        byVolCount.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.VOLUME_COUNT).show();
        });

        byName.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.NAME).show();
        });

        byCir.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.CIRCULATION).show();
        });

        byVolCountTotal.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.VOLUME_COUNT_TOTAL).show();
        });

        add.setOnAction(e -> {
            createAddBookDialog().show();
        });

        editMenu.getItems().addAll(add, remove);

        return editMenu;
    }

    private Menu createSearchMenu() {
        Menu searchMenu = new Menu("Search");

        MenuItem byAuthor = new MenuItem("Author");
        MenuItem byPubAndAuthor = new MenuItem("Publishing & author");
        MenuItem byVolCount = new MenuItem("Volume count");
        MenuItem byName = new MenuItem("Name");
        MenuItem byCir = new MenuItem("Circulation");
        MenuItem byVolCountTotal = new MenuItem("Volume count total");

        byAuthor.setOnAction(e -> {
            createSearchBookDialog(ParameterCondition.AUTHOR).show();
        });

        byPubAndAuthor.setOnAction(e -> {
            createSearchBookDialog(ParameterCondition.PUBLISHING_AUTHOR).show();
        });

        byVolCount.setOnAction(e -> {
            createSearchBookDialog(ParameterCondition.VOLUME_COUNT).show();
        });

        byName.setOnAction(e -> {
            createSearchBookDialog(ParameterCondition.NAME).show();
        });

        byCir.setOnAction(e -> {
            createSearchBookDialog(ParameterCondition.CIRCULATION).show();
        });

        byVolCountTotal.setOnAction(e -> {
            createSearchBookDialog(ParameterCondition.VOLUME_COUNT_TOTAL).show();
        });

        searchMenu.getItems().addAll(byAuthor, byPubAndAuthor, byVolCount, byName, byCir, byVolCountTotal);

        return searchMenu;
    }

    private ToolBar createToolBar() {
        ToolBar toolBar = new ToolBar();

        Button newFile = new Button("New");
        Button openFile = new Button("Open");
        Button saveFile = new Button("Save");
        Button search = new Button("Search");
        Button add = new Button("Add");
        MenuButton remove = new MenuButton("Remove");
        MenuItem byAuthor = new MenuItem("Author");
        MenuItem byPubAndAuthor = new MenuItem("Publishing & author");
        MenuItem byVolCount = new MenuItem("Volume count");
        MenuItem byName = new MenuItem("Name");
        MenuItem byCir = new MenuItem("Circulation");
        MenuItem byVolCountTotal = new MenuItem("Volume count total");

        toolBar.getItems().addAll(newFile, openFile, saveFile);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().addAll(search, add, remove);

        add.setOnAction(e -> {
            createAddBookDialog().show();
        });

        byAuthor.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.AUTHOR).show();
        });

        byPubAndAuthor.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.PUBLISHING_AUTHOR).show();
        });

        byVolCount.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.VOLUME_COUNT).show();
        });

        byName.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.NAME).show();
        });

        byCir.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.CIRCULATION).show();
        });

        byVolCountTotal.setOnAction(e -> {
            createRemoveBookDialog(ParameterCondition.VOLUME_COUNT_TOTAL).show();
        });

        remove.getItems().addAll(byAuthor, byPubAndAuthor, byVolCount, byName, byCir, byVolCountTotal);

        return toolBar;
    }

    private Alert createAddBookDialog() {
        AddBookForm addBookForm = new AddBookForm();

        Alert addBookAlert = createEmptyDialog("Add", addBookForm.getVBox());

        ButtonType ADD = new ButtonType("Add");
        addBookAlert.getDialogPane().getButtonTypes().add(ADD);
        Button addButton = (Button) addBookAlert.getDialogPane().lookupButton(ADD);
        addButton.setOnAction(e -> {
            int tempVolumeCount;
            int tempCirculation;

            String regexAuthor = "^[^\\s]+[^\\d]+$";
            String regexName = "^[^\\s]+([^\\s]+\\s*)+$";
            String name = addBookForm.getNameTxt();
            String firstName = addBookForm.getFirstNameTxt();
            String middleName = addBookForm.getMiddleNameTxt();
            String lastName = addBookForm.getLastNameTxt();
            boolean isNameValid = Pattern.matches(regexName, name);
            boolean isAuthorValid = Pattern.matches(regexAuthor, firstName) &&
                    Pattern.matches(regexAuthor, middleName) && Pattern.matches(regexAuthor, lastName);

            try {
                tempVolumeCount = Integer.parseInt(addBookForm.getVolCountTxt());
                tempCirculation = Integer.parseInt(addBookForm.getCirTxt());

                if (isNameValid && isAuthorValid) {
                    Book book = new Book(name, firstName, middleName, lastName, addBookForm.getPubTxt(),
                            tempVolumeCount, tempCirculation);
                    controller.add(book);
                }
            } catch (Exception ex) {
                Alert info = createEmptyDialog("Error", new Label("The entered data isn't valid"));
                info.getButtonTypes().add(ButtonType.OK);
                info.show();
            }
        });

        return addBookAlert;
    }

    private Alert createRemoveBookDialog(ParameterCondition condition) {
        ParameterForm removeBookForm = new ParameterForm(condition);

        Alert removeBookAlert = createEmptyDialog("Remove", removeBookForm.getVBox());

        ButtonType REMOVE = new ButtonType("Remove");
        removeBookAlert.getDialogPane().getButtonTypes().add(REMOVE);
        Button removeButton = (Button) removeBookAlert.getDialogPane().lookupButton(REMOVE);
        removeButton.setOnAction(e -> {
            ObservableList<Book> foundBooks = new SearchStrategy(books, condition, generateValidBook(removeBookForm)).find();
            controller.remove(foundBooks);

            Alert info = createEmptyDialog("Removed info", new Label("Removed: " + String.valueOf(foundBooks.size())));
            info.getButtonTypes().add(ButtonType.OK);
            info.show();
        });

        return removeBookAlert;
    }

    private Alert createSearchBookDialog(ParameterCondition condition) {
        ParameterForm searchBookForm = new ParameterForm(condition);

        TableView<Book> bookTable = new PageTable(FXCollections.observableArrayList()).createBookTable();
        Button find = new Button("Find");
        find.setOnAction(e -> {
            ObservableList<Book> foundBooks = new SearchStrategy(books, condition, generateValidBook(searchBookForm)).find();
            bookTable.setItems(foundBooks);
        });

        searchBookForm.getVBox().getChildren().addAll(bookTable, find);

        Alert findBookAlert = createEmptyDialog("Search", searchBookForm.getVBox());
        findBookAlert.getButtonTypes().add(ButtonType.CLOSE);

        return findBookAlert;
    }

    private Alert createEmptyDialog(String title, Node content) {
        Alert alert = new Alert(Alert.AlertType.NONE);

        alert.setTitle(title);
        alert.getDialogPane().setContent(content);

        return alert;
    }

    private Book generateValidBook(ParameterForm form) {
        String name = form.getNameTxt();
        String firstName = form.getFirstNameTxt();
        String middleName = form.getMiddleNameTxt();
        String lastName = form.getLastNameTxt();
        String publishing = form.getPubTxt();
        String volCountString = form.getVolCountTxt();
        String cirString = form.getCirTxt();
        String volCountTotalString = form.getVolCountTotalTxt();

        String regex = "^\\d+$";
        int volCount = Pattern.matches(regex, volCountString) ? Integer.parseInt(volCountString) : 0;
        int cir = Pattern.matches(regex, cirString) ? Integer.parseInt(cirString) : 0;
        int volCountTotal = Pattern.matches(regex, volCountTotalString) ? Integer.parseInt(volCountTotalString) : 0;

        name = (name.equals("")) ? " " : name;
        firstName = (firstName.equals("")) ? " " : firstName;
        middleName = (middleName.equals("")) ? " " : middleName;
        lastName = (lastName.equals("")) ? " " : lastName;
        publishing = (publishing.equals("")) ? " " : publishing;

        Book validBook = new Book(name, firstName, middleName, lastName, publishing, volCount, cir);
        validBook.setVolumeCountTotal(volCountTotal);

        return validBook;
    }
}

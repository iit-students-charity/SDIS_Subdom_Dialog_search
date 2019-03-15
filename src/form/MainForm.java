package form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        Menu fileMenu = new Menu(FormContentText.FILE);

        MenuItem newFile = new MenuItem(FormContentText.NEW);
        MenuItem generate = new MenuItem(FormContentText.GENERATE);
        MenuItem openFile = new MenuItem(FormContentText.OPEN);
        MenuItem saveFile = new MenuItem(FormContentText.SAVE);

        newFile.setOnAction(e -> {
            books.clear();
        });

        saveFile.setOnAction(e -> {

        });

        openFile.setOnAction(e -> {

        });

        saveFile.setOnAction(e -> {

        });

        fileMenu.getItems().addAll(newFile, generate, openFile, saveFile);

        return fileMenu;
    }

    private Menu createEditMenu() {
        Menu editMenu = new Menu(FormContentText.EDIT);

        MenuItem add = new MenuItem(FormContentText.ADD);
        Menu remove = new Menu(FormContentText.REMOVE);
        MenuItem byAuthor = new MenuItem(FormContentText.AUTHOR);
        MenuItem byPubAndAuthor = new MenuItem(FormContentText.PUBLISHING_AUTHOR);
        MenuItem byVolCount = new MenuItem(FormContentText.VOLUME_COUNT);
        MenuItem byName = new MenuItem(FormContentText.NAME);
        MenuItem byCir = new MenuItem(FormContentText.CIRCULATION);
        MenuItem byVolCountTotal = new MenuItem(FormContentText.VOLUME_COUNT_TOTAL);

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
        Menu searchMenu = new Menu(FormContentText.SEARCH);

        MenuItem byAuthor = new MenuItem(FormContentText.AUTHOR);
        MenuItem byPubAndAuthor = new MenuItem(FormContentText.PUBLISHING_AUTHOR);
        MenuItem byVolCount = new MenuItem(FormContentText.VOLUME_COUNT);
        MenuItem byName = new MenuItem(FormContentText.NAME);
        MenuItem byCir = new MenuItem(FormContentText.CIRCULATION);
        MenuItem byVolCountTotal = new MenuItem(FormContentText.VOLUME_COUNT_TOTAL);

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

    private Menu createRemoveMenu() {
        Menu remove = new Menu(FormContentText.REMOVE);
        MenuItem byAuthor = new MenuItem(FormContentText.AUTHOR);
        MenuItem byPubAndAuthor = new MenuItem(FormContentText.PUBLISHING_AUTHOR);
        MenuItem byVolCount = new MenuItem(FormContentText.VOLUME_COUNT);
        MenuItem byName = new MenuItem(FormContentText.NAME);
        MenuItem byCir = new MenuItem(FormContentText.CIRCULATION);
        MenuItem byVolCountTotal = new MenuItem(FormContentText.VOLUME_COUNT_TOTAL);

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

        return remove;
    }

    private ToolBar createToolBar() {
        ToolBar toolBar = new ToolBar();

        Button newFile = new Button(FormContentText.NEW);
        Button openFile = new Button(FormContentText.OPEN);
        Button saveFile = new Button(FormContentText.SAVE);
        Button add = new Button(FormContentText.ADD);
        MenuButton search = new MenuButton(FormContentText.SEARCH);
        MenuButton remove = new MenuButton(FormContentText.REMOVE);
        search.getItems().addAll(createSearchMenu().getItems());
        remove.getItems().addAll(createRemoveMenu().getItems());

        toolBar.getItems().addAll(newFile, openFile, saveFile);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().addAll(search, add, remove);

        add.setOnAction(e -> {
            createAddBookDialog().show();
        });

        return toolBar;
    }

    private Alert createAddBookDialog() {
        AddBookForm addBookForm = new AddBookForm();

        Alert addBookAlert = createEmptyDialog(FormContentText.ADD, addBookForm.getVBox());

        ButtonType ADD = new ButtonType(FormContentText.ADD);
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
                Alert info = createEmptyDialog(FormContentText.ERROR, new Label(FormContentText.INVALID_INPUT_DATA));
                info.getButtonTypes().add(ButtonType.OK);
                info.show();
            }
        });

        return addBookAlert;
    }

    private Alert createRemoveBookDialog(ParameterCondition condition) {
        ParameterForm removeBookForm = new ParameterForm(condition);

        Alert removeBookAlert = createEmptyDialog(FormContentText.REMOVE, removeBookForm.getVBox());

        ButtonType REMOVE = new ButtonType(FormContentText.REMOVE);
        removeBookAlert.getDialogPane().getButtonTypes().add(REMOVE);
        Button removeButton = (Button) removeBookAlert.getDialogPane().lookupButton(REMOVE);
        removeButton.setOnAction(e -> {
            ObservableList<Book> foundBooks = new SearchStrategy(books, condition, generateValidBook(removeBookForm)).find();
            controller.remove(foundBooks);

            Alert info = createEmptyDialog(FormContentText.REMOVED + FormContentText.INFO,
                    new Label(FormContentText.REMOVED + ": " + String.valueOf(foundBooks.size())));
            info.getButtonTypes().add(ButtonType.OK);
            info.show();
        });

        return removeBookAlert;
    }

    private Alert createSearchBookDialog(ParameterCondition condition) {
        ParameterForm searchBookForm = new ParameterForm(condition);

        PageTable pageTable = new PageTable(FXCollections.observableArrayList());
        Button find = new Button(FormContentText.FIND);
        find.setOnAction(e -> {
            ObservableList<Book> foundBooks = new SearchStrategy(books, condition, generateValidBook(searchBookForm)).find();
            pageTable.getBookTable().setItems(foundBooks);
        });

        searchBookForm.getVBox().getChildren().addAll(pageTable.getRootVBox(), find);

        Alert findBookAlert = createEmptyDialog(FormContentText.SEARCH, searchBookForm.getVBox());
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

        name = (name.equals(FormContentText.EMPTY_STRING)) ? FormContentText.WHITE_SPACE : name;
        firstName = (firstName.equals(FormContentText.EMPTY_STRING)) ? FormContentText.WHITE_SPACE : firstName;
        middleName = (middleName.equals(FormContentText.EMPTY_STRING)) ? FormContentText.WHITE_SPACE : middleName;
        lastName = (lastName.equals(FormContentText.EMPTY_STRING)) ? FormContentText.WHITE_SPACE : lastName;
        publishing = (publishing.equals(FormContentText.EMPTY_STRING)) ? FormContentText.WHITE_SPACE : publishing;

        Book validBook = new Book(name, firstName, middleName, lastName, publishing, volCount, cir);
        validBook.setVolumeCountTotal(volCountTotal);

        return validBook;
    }
}

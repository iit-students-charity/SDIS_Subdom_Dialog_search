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
        Menu fileMenu = new Menu(ContentText.FILE);

        MenuItem newFile = new MenuItem(ContentText.NEW);
        MenuItem generate = new MenuItem(ContentText.GENERATE);
        MenuItem openFile = new MenuItem(ContentText.OPEN);
        MenuItem saveFile = new MenuItem(ContentText.SAVE);

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
        Menu editMenu = new Menu(ContentText.EDIT);

        MenuItem add = new MenuItem(ContentText.ADD);
        Menu remove = new Menu(ContentText.REMOVE);
        MenuItem byAuthor = new MenuItem(ContentText.AUTHOR);
        MenuItem byPubAndAuthor = new MenuItem(ContentText.PUBLISHING_AUTHOR);
        MenuItem byVolCount = new MenuItem(ContentText.VOLUME_COUNT);
        MenuItem byName = new MenuItem(ContentText.NAME);
        MenuItem byCir = new MenuItem(ContentText.CIRCULATION);
        MenuItem byVolCountTotal = new MenuItem(ContentText.VOLUME_COUNT_TOTAL);

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
        Menu searchMenu = new Menu(ContentText.SEARCH);

        MenuItem byAuthor = new MenuItem(ContentText.AUTHOR);
        MenuItem byPubAndAuthor = new MenuItem(ContentText.PUBLISHING_AUTHOR);
        MenuItem byVolCount = new MenuItem(ContentText.VOLUME_COUNT);
        MenuItem byName = new MenuItem(ContentText.NAME);
        MenuItem byCir = new MenuItem(ContentText.CIRCULATION);
        MenuItem byVolCountTotal = new MenuItem(ContentText.VOLUME_COUNT_TOTAL);

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
        Menu remove = new Menu(ContentText.REMOVE);
        MenuItem byAuthor = new MenuItem(ContentText.AUTHOR);
        MenuItem byPubAndAuthor = new MenuItem(ContentText.PUBLISHING_AUTHOR);
        MenuItem byVolCount = new MenuItem(ContentText.VOLUME_COUNT);
        MenuItem byName = new MenuItem(ContentText.NAME);
        MenuItem byCir = new MenuItem(ContentText.CIRCULATION);
        MenuItem byVolCountTotal = new MenuItem(ContentText.VOLUME_COUNT_TOTAL);

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

        Button newFile = new Button(ContentText.NEW);
        Button openFile = new Button(ContentText.OPEN);
        Button saveFile = new Button(ContentText.SAVE);
        Button add = new Button(ContentText.ADD);
        MenuButton search = new MenuButton(ContentText.SEARCH);
        MenuButton remove = new MenuButton(ContentText.REMOVE);
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

        Alert addBookAlert = createEmptyDialog(ContentText.ADD, addBookForm.getVBox());

        ButtonType ADD = new ButtonType(ContentText.ADD);
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
                    controller.addBook(book);
                }
            } catch (Exception ex) {
                Alert info = createEmptyDialog(ContentText.ERROR, new Label(ContentText.INVALID_INPUT_DATA));
                info.getButtonTypes().add(ButtonType.OK);
                info.show();
            }
        });

        return addBookAlert;
    }

    private Alert createRemoveBookDialog(ParameterCondition condition) {
        ParameterForm removeBookForm = new ParameterForm(condition);

        Alert removeBookAlert = createEmptyDialog(ContentText.REMOVE, removeBookForm.getVBox());

        ButtonType REMOVE = new ButtonType(ContentText.REMOVE);
        removeBookAlert.getDialogPane().getButtonTypes().add(REMOVE);
        Button removeButton = (Button) removeBookAlert.getDialogPane().lookupButton(REMOVE);
        removeButton.setOnAction(e -> {
            String upLimitString = removeBookForm.getCountUpLimitTxt();
            String downLimitString = removeBookForm.getCountDownLimitTxt();
            String borderString = removeBookForm.getCountBorderTxt();

            String regex = "^\\d+$";
            int upLimit = Pattern.matches(regex, upLimitString) ? Integer.parseInt(upLimitString) : 0;
            int downLimit = Pattern.matches(regex, downLimitString) ? Integer.parseInt(downLimitString) : 0;
            int border = Pattern.matches(regex, borderString) ? Integer.parseInt(borderString) : 0;

            Toggle selectedToggle = removeBookForm.getGreaterOrLessTglGrp().getSelectedToggle();
            boolean isGreaterThanBorder = (selectedToggle != null) &&
                    selectedToggle.getUserData().equals(ContentText.GREATER);

            ObservableList<Book> foundBooks = new SearchStrategy(books, condition, generateValidBook(removeBookForm),
                    upLimit, downLimit, border, isGreaterThanBorder).find();
            controller.removeBook(foundBooks);

            Alert info = createEmptyDialog(ContentText.REMOVED + ContentText.INFO,
                    new Label(ContentText.REMOVED + ": " + String.valueOf(foundBooks.size())));
            info.getButtonTypes().add(ButtonType.OK);
            info.show();
        });

        return removeBookAlert;
    }

    private Alert createSearchBookDialog(ParameterCondition condition) {
        ParameterForm searchBookForm = new ParameterForm(condition);

        PageTable pageTable = new PageTable(FXCollections.observableArrayList());

        Button find = new Button(ContentText.FIND);
        find.setOnAction(e -> {
            String upLimitString = searchBookForm.getCountUpLimitTxt();
            String downLimitString = searchBookForm.getCountDownLimitTxt();
            String borderString = searchBookForm.getCountBorderTxt();

            String regex = "^\\d+$";
            int upLimit = Pattern.matches(regex, upLimitString) ? Integer.parseInt(upLimitString) : 0;
            int downLimit = Pattern.matches(regex, downLimitString) ? Integer.parseInt(downLimitString) : 0;
            int border = Pattern.matches(regex, borderString) ? Integer.parseInt(borderString) : 0;

            Toggle selectedToggle = searchBookForm.getGreaterOrLessTglGrp().getSelectedToggle();
            boolean isGreaterThanBorder = (selectedToggle != null) &&
                    selectedToggle.getUserData().equals(ContentText.GREATER);

            ObservableList<Book> foundBooks = new SearchStrategy(books, condition, generateValidBook(searchBookForm),
                    upLimit, downLimit, border, isGreaterThanBorder).find();
            pageTable.getBookTable().setItems(foundBooks);
        });

        searchBookForm.getVBox().getChildren().addAll(pageTable.getRootVBox(), find);

        Alert findBookAlert = createEmptyDialog(ContentText.SEARCH, searchBookForm.getVBox());
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
        String cirString = form.getCirTxt();
        String volCountTotalString = form.getVolCountTotalTxt();

        String regex = "^\\d+$";
        int cir = Pattern.matches(regex, cirString) ? Integer.parseInt(cirString) : 0;
        int volCountTotal = Pattern.matches(regex, volCountTotalString) ? Integer.parseInt(volCountTotalString) : 0;

        name = (name.equals(ContentText.EMPTY_STRING)) ? ContentText.WHITE_SPACE : name;
        firstName = (firstName.equals(ContentText.EMPTY_STRING)) ? ContentText.WHITE_SPACE : firstName;
        middleName = (middleName.equals(ContentText.EMPTY_STRING)) ? ContentText.WHITE_SPACE : middleName;
        lastName = (lastName.equals(ContentText.EMPTY_STRING)) ? ContentText.WHITE_SPACE : lastName;
        publishing = (publishing.equals(ContentText.EMPTY_STRING)) ? ContentText.WHITE_SPACE : publishing;

        Book validBook = new Book(name, firstName, middleName, lastName, publishing, 0, cir);
        validBook.setVolumeCountTotal(volCountTotal);

        return validBook;
    }
}

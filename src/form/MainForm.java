package form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import controller.SearchStrategy;
import datamodel.Book;
import controller.Controller;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.ParameterCondition;

import java.io.File;
import java.util.regex.Pattern;

public class MainForm {
    private MenuBar menuBar;
    private ToolBar toolBar;
    private VBox pageTableVBox;
    private PageTable pageTable;

    private VBox vBox;
    private Stage ownerStage;

    private Controller controller;
    private ObservableList<Book> books;


    public MainForm(Controller controller, ObservableList<Book> books, Stage ownerStage) {
        this.controller = controller;
        this.books = books;

        menuBar = createMenuBar();
        toolBar = createToolBar();
        pageTable = new PageTable(books);
        pageTableVBox = pageTable.getRootVBox();

        vBox = new VBox(menuBar, pageTableVBox, toolBar);
        this.ownerStage = ownerStage;
    }

    public VBox getVBox() {
        return vBox;
    }

    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(createFileMenu(), createEditMenu(), createSearchMenu());

        return menuBar;
    }


    // Menu creating
    private Menu createFileMenu() {
        Menu fileMenu = new Menu(Constant.FILE);

        MenuItem newFile = new MenuItem(Constant.NEW);
        MenuItem generate = new MenuItem(Constant.GENERATE);
        MenuItem openFile = new MenuItem(Constant.OPEN);
        MenuItem saveFile = new MenuItem(Constant.SAVE);

        newFile.setOnAction(e -> {
            controller.newFile();
            pageTable.updateCurrentPage(PageTable.controlType.FIRST);
        });

        generate.setOnAction(e -> {
            controller.generateFile();
            pageTable.updateCountOfBooks();
        });

        openFile.setOnAction(openFileEventHandler());

        saveFile.setOnAction(saveFileEventHandler());

        fileMenu.getItems().addAll(newFile, generate, openFile, saveFile);

        return fileMenu;
    }

    private Menu createEditMenu() {
        Menu editMenu = new Menu(Constant.EDIT);

        MenuItem add = new MenuItem(Constant.ADD);
        Menu remove = createRemoveMenu();

        add.setOnAction(e -> {
            createAddBookDialog().show();
        });

        editMenu.getItems().addAll(add, remove);

        return editMenu;
    }

    private Menu createRemoveMenu() {
        Menu remove = new Menu(Constant.REMOVE);
        MenuItem byAuthor = new MenuItem(Constant.AUTHOR);
        MenuItem byPubAndAuthor = new MenuItem(Constant.PUBLISHING_AUTHOR);
        MenuItem byVolCount = new MenuItem(Constant.VOLUME_COUNT);
        MenuItem byName = new MenuItem(Constant.NAME);
        MenuItem byCir = new MenuItem(Constant.CIRCULATION);
        MenuItem byVolCountTotal = new MenuItem(Constant.VOLUME_COUNT_TOTAL);

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

    private Menu createSearchMenu() {
        Menu searchMenu = new Menu(Constant.SEARCH);

        MenuItem byAuthor = new MenuItem(Constant.AUTHOR);
        MenuItem byPubAndAuthor = new MenuItem(Constant.PUBLISHING_AUTHOR);
        MenuItem byVolCount = new MenuItem(Constant.VOLUME_COUNT);
        MenuItem byName = new MenuItem(Constant.NAME);
        MenuItem byCir = new MenuItem(Constant.CIRCULATION);
        MenuItem byVolCountTotal = new MenuItem(Constant.VOLUME_COUNT_TOTAL);

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

        Button newFile = new Button(Constant.NEW);
        Button openFile = new Button(Constant.OPEN);
        Button generateFile = new Button(Constant.GENERATE);
        Button saveFile = new Button(Constant.SAVE);
        Button add = new Button(Constant.ADD);
        MenuButton search = new MenuButton(Constant.SEARCH);
        MenuButton remove = new MenuButton(Constant.REMOVE);

        newFile.setOnAction(e -> {
            controller.newFile();
            pageTable.updateCountOfBooks();
        });

        generateFile.setOnAction(e -> {
            controller.generateFile();
            pageTable.updateCountOfBooks();
        });

        openFile.setOnAction(openFileEventHandler());

        saveFile.setOnAction(saveFileEventHandler());

        search.getItems().addAll(createSearchMenu().getItems());
        remove.getItems().addAll(createRemoveMenu().getItems());

        toolBar.getItems().addAll(newFile, generateFile, openFile, saveFile);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().addAll(search, add, remove);

        add.setOnAction(e -> {
            createAddBookDialog().show();
        });

        return toolBar;
    }


    // Dialog creating
    private Alert createAddBookDialog() {
        AddBookForm addBookForm = new AddBookForm();

        Alert addBookAlert = createEmptyDialog(Constant.ADD, addBookForm.getVBox());

        ButtonType ADD = new ButtonType(Constant.ADD);
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
                Alert info = createEmptyDialog(Constant.ERROR, new Label(Constant.INVALID_INPUT_DATA));
                info.getButtonTypes().add(ButtonType.OK);
                info.show();
            }

            pageTable.updateCountOfBooks();
        });

        return addBookAlert;
    }

    private Alert createRemoveBookDialog(ParameterCondition condition) {
        ParameterForm removeBookForm = new ParameterForm(condition);

        Alert removeBookAlert = createEmptyDialog(Constant.REMOVE, removeBookForm.getVBox());

        ButtonType REMOVE = new ButtonType(Constant.REMOVE);
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
                    selectedToggle.getUserData().equals(Constant.GREATER);

            ObservableList<Book> foundBooks = new SearchStrategy(books, condition, generateValidBook(removeBookForm),
                    upLimit, downLimit, border, isGreaterThanBorder).find();
            controller.removeBook(foundBooks);

            Alert info = createEmptyDialog(Constant.REMOVED + Constant.INFO,
                    new Label(Constant.REMOVED + ": " + String.valueOf(foundBooks.size())));
            info.getButtonTypes().add(ButtonType.OK);
            info.show();

            pageTable.updateCountOfBooks();
        });

        return removeBookAlert;
    }

    private Alert createSearchBookDialog(ParameterCondition condition) {
        ParameterForm searchBookForm = new ParameterForm(condition);

        ObservableList<Book> foundBooks = FXCollections.observableArrayList();
        PageTable foundBooksPageTable = new PageTable(foundBooks);

        Button find = new Button(Constant.FIND);
        find.setOnAction(e -> {
            String upLimitString = searchBookForm.getCountUpLimitTxt();
            String downLimitString = searchBookForm.getCountDownLimitTxt();
            String borderString = searchBookForm.getCountBorderTxt();
            System.out.println(borderString);

            String regex = "^\\d+$";
            int upLimit = Pattern.matches(regex, upLimitString) ? Integer.parseInt(upLimitString) : 0;
            int downLimit = Pattern.matches(regex, downLimitString) ? Integer.parseInt(downLimitString) : 0;
            int border = Pattern.matches(regex, borderString) ? Integer.parseInt(borderString) : 0;

            Toggle selectedToggle = searchBookForm.getGreaterOrLessTglGrp().getSelectedToggle();
            boolean isGreaterThanBorder = (selectedToggle != null) &&
                    selectedToggle.getUserData().equals(Constant.GREATER);

            foundBooks.clear();
            foundBooks.addAll(new SearchStrategy(books, condition, generateValidBook(searchBookForm),
                    upLimit, downLimit, border, isGreaterThanBorder).find());
            foundBooksPageTable.updateCountOfBooks();
        });

        searchBookForm.getVBox().getChildren().addAll(foundBooksPageTable.getRootVBox(), find);

        Alert findBookAlert = createEmptyDialog(Constant.SEARCH, searchBookForm.getVBox());
        findBookAlert.getButtonTypes().add(ButtonType.CLOSE);

        return findBookAlert;
    }

    private Alert createEmptyDialog(String title, Node content) {
        Alert alert = new Alert(Alert.AlertType.NONE);

        alert.setTitle(title);
        alert.getDialogPane().setContent(content);

        return alert;
    }


    // File event handlers
    private EventHandler<ActionEvent> openFileEventHandler() {
        return event -> {
            FileChooser openFileChooser = new FileChooser();
            openFileChooser.setTitle(Constant.OPEN);
            openFileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("XML files", "*.xml"),
                    new FileChooser.ExtensionFilter("All files", "*.*")
            );
            File selectedFile = openFileChooser.showOpenDialog(ownerStage);

            if (selectedFile != null) {
                controller.openFile(selectedFile.getAbsolutePath());
            }
            pageTable.updateCountOfBooks();
        };
    }

    private EventHandler<ActionEvent> saveFileEventHandler() {
        return event -> {
            FileChooser saveFileChooser = new FileChooser();
            saveFileChooser.setTitle(Constant.SAVE);
            File selectedFile = saveFileChooser.showSaveDialog(ownerStage);
            saveFileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("All files", "*.*")
            );

            if (selectedFile != null) {
                controller.saveFile(selectedFile.getAbsolutePath());
            }
            pageTable.updateCountOfBooks();
        };
    }


    private Book generateValidBook(ParameterForm form) {
        String name = form.getNameTxt();
        String firstName = form.getFirstNameTxt();
        String middleName = form.getMiddleNameTxt();
        String lastName = form.getLastNameTxt();
        String publishing = form.getPubTxt();

        name = (name.equals(Constant.EMPTY_STRING)) ? Constant.WHITE_SPACE : name;
        firstName = (firstName.equals(Constant.EMPTY_STRING)) ? Constant.WHITE_SPACE : firstName;
        middleName = (middleName.equals(Constant.EMPTY_STRING)) ? Constant.WHITE_SPACE : middleName;
        lastName = (lastName.equals(Constant.EMPTY_STRING)) ? Constant.WHITE_SPACE : lastName;
        publishing = (publishing.equals(Constant.EMPTY_STRING)) ? Constant.WHITE_SPACE : publishing;

        Book validBook = new Book(name, firstName, middleName, lastName, publishing, 0, 0);

        return validBook;
    }
}

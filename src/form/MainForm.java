package form;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private PageTable pageTable;

    private VBox vBox;
    private Stage ownerStage;

    private Controller controller;
    private ObservableList<Book> books;


    public MainForm(Controller controller, ObservableList<Book> books, Stage ownerStage) {
        this.controller = controller;
        this.books = books;

        pageTable = new PageTable(books);

        vBox = new VBox(createMenuBar(), pageTable.getRootVBox(), createToolBar());
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
        Menu fileMenu = new Menu(ViewConstant.FILE);

        MenuItem newFile = new MenuItem(ViewConstant.NEW);
        MenuItem generate = new MenuItem(ViewConstant.GENERATE);
        MenuItem openFile = new MenuItem(ViewConstant.OPEN);
        MenuItem saveFile = new MenuItem(ViewConstant.SAVE);

        newFile.setOnAction(e -> {
            controller.newFile();
            pageTable.updateCountOfBooks();
        });

        generate.setOnAction(e -> {
            controller.generateFile();
            pageTable.updateCountOfBooks();
        });

        openFile.setOnAction(e -> {
            File selectedFile = createOpenFileDialog();

            if (selectedFile != null) {
                controller.openFile(selectedFile.getAbsolutePath());
                pageTable.updateCountOfBooks();
            }
        });

        saveFile.setOnAction(e -> {
            File selectedFile = createSaveFileDialog();

            if (selectedFile != null) {
                controller.saveFile(selectedFile.getAbsolutePath());
                pageTable.updateCountOfBooks();
            }
        });

        fileMenu.getItems().addAll(newFile, generate, openFile, saveFile);

        return fileMenu;
    }

    private Menu createEditMenu() {
        Menu editMenu = new Menu(ViewConstant.EDIT);

        MenuItem add = new MenuItem(ViewConstant.ADD);
        Menu remove = createRemoveMenu();

        add.setOnAction(e -> {
            createAddBookDialog().show();
        });

        editMenu.getItems().addAll(add, remove);

        return editMenu;
    }

    private Menu createRemoveMenu() {
        Menu remove = new Menu(ViewConstant.REMOVE);
        MenuItem byAuthor = new MenuItem(ViewConstant.AUTHOR);
        MenuItem byPubAndAuthor = new MenuItem(ViewConstant.PUBLISHING_AUTHOR);
        MenuItem byVolCount = new MenuItem(ViewConstant.VOLUME_COUNT);
        MenuItem byName = new MenuItem(ViewConstant.NAME);
        MenuItem byCir = new MenuItem(ViewConstant.CIRCULATION);
        MenuItem byVolCountTotal = new MenuItem(ViewConstant.VOLUME_COUNT_TOTAL);

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
        Menu searchMenu = new Menu(ViewConstant.SEARCH);

        MenuItem byAuthor = new MenuItem(ViewConstant.AUTHOR);
        MenuItem byPubAndAuthor = new MenuItem(ViewConstant.PUBLISHING_AUTHOR);
        MenuItem byVolCount = new MenuItem(ViewConstant.VOLUME_COUNT);
        MenuItem byName = new MenuItem(ViewConstant.NAME);
        MenuItem byCir = new MenuItem(ViewConstant.CIRCULATION);
        MenuItem byVolCountTotal = new MenuItem(ViewConstant.VOLUME_COUNT_TOTAL);

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

        Button newFile = new Button();
        Button openFile = new Button();
        Button generateFile = new Button();
        Button saveFile = new Button();
        Button add = new Button();
        MenuButton search = new MenuButton();
        MenuButton remove = new MenuButton();

        newFile.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(Icon.NEW_FILE))));
        openFile.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(Icon.OPEN_FILE))));
        generateFile.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(Icon.GENERATE_FILE))));
        saveFile.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(Icon.SAVE_FILE))));
        add.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(Icon.ADD_BOOK))));
        search.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(Icon.SEARCH_BOOK))));
        remove.setGraphic(new ImageView(new Image(getClass().getResourceAsStream(Icon.REMOVE_BOOK))));

        newFile.setOnAction(e -> {
            controller.newFile();
            pageTable.updateCountOfBooks();
        });

        generateFile.setOnAction(e -> {
            controller.generateFile();
            pageTable.updateCountOfBooks();
        });

        openFile.setOnMouseClicked(e -> {
            File selectedFile = createOpenFileDialog();

            if (selectedFile != null) {
                controller.openFile(selectedFile.getAbsolutePath());
                pageTable.updateCountOfBooks();
            }
        });

        saveFile.setOnMouseClicked(e -> {
            File selectedFile = createSaveFileDialog();

            if (selectedFile != null) {
                controller.saveFile(selectedFile.getAbsolutePath());
                pageTable.updateCountOfBooks();
            }
        });

        add.setOnAction(e -> {
            createAddBookDialog().show();
        });

        search.getItems().addAll(createSearchMenu().getItems());
        remove.getItems().addAll(createRemoveMenu().getItems());

        toolBar.getItems().addAll(newFile, generateFile, openFile, saveFile);
        toolBar.getItems().add(new Separator());
        toolBar.getItems().addAll(search, add, remove);

        return toolBar;
    }


    // Dialog creating
    private Alert createAddBookDialog() {
        AddBookForm addBookForm = new AddBookForm();

        Alert addBookAlert = createEmptyDialog(ViewConstant.ADD, addBookForm.getVBox());

        ButtonType ADD = new ButtonType(ViewConstant.ADD);
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

                    for (Book curBook : books) {
                        if (curBook.equals(book)) {
                            return;
                        }
                    }

                    controller.addBook(book);
                }
            } catch (Exception ex) {
                Alert info = createEmptyDialog(ViewConstant.ERROR, new Label(ViewConstant.INVALID_INPUT_DATA));
                info.getButtonTypes().add(ButtonType.OK);
                info.setOnCloseRequest(event -> {
                    addBookAlert.show();
                });
                info.show();
            }

            pageTable.updateCountOfBooks();
        });

        return addBookAlert;
    }

    private Alert createRemoveBookDialog(ParameterCondition condition) {
        ParameterForm removeBookForm = new ParameterForm(condition);

        Alert removeBookAlert = createEmptyDialog(ViewConstant.REMOVE, removeBookForm.getVBox());

        ButtonType REMOVE = new ButtonType(ViewConstant.REMOVE);
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
                    selectedToggle.getUserData().equals(ViewConstant.GREATER);

            ObservableList<Book> foundBooks = new SearchStrategy(books, condition, generateValidBook(removeBookForm),
                    upLimit, downLimit, border, isGreaterThanBorder).find();
            controller.removeBook(foundBooks);

            Alert info = createEmptyDialog(ViewConstant.REMOVED + ViewConstant.INFO,
                    new Label(ViewConstant.REMOVED + ": " + String.valueOf(foundBooks.size())));
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

        Button find = new Button(ViewConstant.FIND);
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
                    selectedToggle.getUserData().equals(ViewConstant.GREATER);

            foundBooks.clear();
            foundBooks.addAll(new SearchStrategy(books, condition, generateValidBook(searchBookForm),
                    upLimit, downLimit, border, isGreaterThanBorder).find());
            System.out.println(generateValidBook(searchBookForm).getAuthor().getMiddleName());
            foundBooksPageTable.updateCountOfBooks();
        });

        searchBookForm.getVBox().getChildren().addAll(foundBooksPageTable.getRootVBox(), find);

        Alert findBookAlert = createEmptyDialog(ViewConstant.SEARCH, searchBookForm.getVBox());
        findBookAlert.getButtonTypes().add(ButtonType.CLOSE);

        return findBookAlert;
    }

    private Alert createEmptyDialog(String title, Node content) {
        Alert alert = new Alert(Alert.AlertType.NONE);

        alert.setTitle(title);
        alert.getDialogPane().setContent(content);

        return alert;
    }

    private File createOpenFileDialog() {
        FileChooser openFileChooser = new FileChooser();
        openFileChooser.setTitle(ViewConstant.OPEN);
        openFileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("XML files", "*.xml"),
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        return openFileChooser.showOpenDialog(ownerStage);
    }

    private File createSaveFileDialog() {
        FileChooser saveFileChooser = new FileChooser();
        saveFileChooser.setTitle(ViewConstant.SAVE);
        saveFileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All files", "*.*")
        );

        return saveFileChooser.showSaveDialog(ownerStage);
    }


    private Book generateValidBook(ParameterForm form) {
        String name = form.getNameTxt();
        String firstName = form.getFirstNameTxt();
        String middleName = form.getMiddleNameTxt();
        String lastName = form.getLastNameTxt();
        String publishing = form.getPubTxt();

        name = (name.equals(ViewConstant.EMPTY_STRING)) ? ViewConstant.WHITE_SPACE : name;
        firstName = (firstName.equals(ViewConstant.EMPTY_STRING)) ? ViewConstant.WHITE_SPACE : firstName;
        middleName = (middleName.equals(ViewConstant.EMPTY_STRING)) ? ViewConstant.WHITE_SPACE : middleName;
        lastName = (lastName.equals(ViewConstant.EMPTY_STRING)) ? ViewConstant.WHITE_SPACE : lastName;
        publishing = (publishing.equals(ViewConstant.EMPTY_STRING)) ? ViewConstant.WHITE_SPACE : publishing;

        return new Book(name, firstName, middleName, lastName, publishing, 0, 0);
    }
}

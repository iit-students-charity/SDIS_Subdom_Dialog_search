package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.Controller;
import datamodel.Book;
import form.MainForm;

public class Main extends Application {
    private final String FORM_TITLE = "Subject domain with Dialog search";
    private final int FORM_WIDTH = 800;
    private final int FORM_HEIGHT = 400;


    @Override
    public void start(Stage primaryStage) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        Controller controller = new Controller(books);
        MainForm mainForm = new MainForm(controller, books, primaryStage);

        primaryStage.setWidth(FORM_WIDTH);
        primaryStage.setHeight(FORM_HEIGHT);
        primaryStage.setTitle(FORM_TITLE);

        primaryStage.setScene(new Scene(mainForm.getVBox()));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

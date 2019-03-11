package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final String WINFORM_TITLE = "Subject domain with Dialog search";

    private final int WINFORM_WIDTH = 1000;
    private final int WINFORM_HEIGHT = 500;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(WINFORM_WIDTH);
        primaryStage.setHeight(WINFORM_HEIGHT);
        primaryStage.setTitle(WINFORM_TITLE);

        MainForm mainForm = new MainForm();
        primaryStage.setScene(new Scene(mainForm.getVBox()));

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

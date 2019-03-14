package form;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AddBookForm {
    private TextField nameTxtField;
    private TextField firstNameTxtField;
    private TextField middleNameTxtField;
    private TextField lastNameTxtField;
    private TextField pubTxtField;
    private TextField volCountTxtField;
    private TextField cirTxtField;
    private Label nameLabel;
    private Label firstNameLabel;
    private Label middleNameLabel;
    private Label lastNameLabel;
    private Label pubLabel;
    private Label volCountLabel;
    private Label cirLabel;

    private VBox vBox;


    public AddBookForm() {
        initControls();

        vBox = createVBox();
        vBox.setAlignment(Pos.CENTER);
    }

    private void initControls() {
        nameTxtField = new TextField();
        firstNameTxtField = new TextField();
        middleNameTxtField = new TextField();
        lastNameTxtField = new TextField();
        pubTxtField = new TextField();
        volCountTxtField = new TextField();
        cirTxtField = new TextField();

        nameLabel = new Label("Name");
        firstNameLabel = new Label("First name");
        middleNameLabel = new Label("Middle name");
        lastNameLabel = new Label("Last name");
        pubLabel = new Label("Publishing");
        volCountLabel = new Label("Volumes count");
        cirLabel = new Label("Circulation");
    }

    private VBox createVBox() {
        return new VBox(
                new GridComplexComponent(nameLabel, nameTxtField).getGridPane(),
                new GridComplexComponent(firstNameLabel, firstNameTxtField).getGridPane(),
                new GridComplexComponent(middleNameLabel, middleNameTxtField).getGridPane(),
                new GridComplexComponent(lastNameLabel, lastNameTxtField).getGridPane(),
                new GridComplexComponent(pubLabel, pubTxtField).getGridPane(),
                new GridComplexComponent(volCountLabel, volCountTxtField).getGridPane(),
                new GridComplexComponent(cirLabel, cirTxtField).getGridPane()
        );
    }

    public VBox getVBox() {
        return vBox;
    }

    public String getNameTxt() {
        return nameTxtField.getText();
    }

    public String getFirstNameTxt() {
        return firstNameTxtField.getText();
    }

    public String getMiddleNameTxt()  {
        return middleNameTxtField.getText();
    }

    public String getLastNameTxt() {
        return lastNameTxtField.getText();
    }

    public String getPubTxt() {
        return pubTxtField.getText();
    }

    public String getVolCountTxt() {
        return volCountTxtField.getText();
    }

    public String getCirTxt() {
        return cirTxtField.getText();
    }
}

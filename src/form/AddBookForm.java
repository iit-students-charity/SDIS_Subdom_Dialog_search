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

        nameLabel = new Label(ViewConstant.NAME);
        firstNameLabel = new Label(ViewConstant.FIRST_NAME);
        middleNameLabel = new Label(ViewConstant.MIDDLE_NAME);
        lastNameLabel = new Label(ViewConstant.LAST_NAME);
        pubLabel = new Label(ViewConstant.PUBLISHING);
        volCountLabel = new Label(ViewConstant.VOLUME_COUNT);
        cirLabel = new Label(ViewConstant.CIRCULATION);
    }

    private VBox createVBox() {
        return new VBox(
                new TwoNodesGrid(nameLabel, nameTxtField).getGridPane(),
                new TwoNodesGrid(firstNameLabel, firstNameTxtField).getGridPane(),
                new TwoNodesGrid(middleNameLabel, middleNameTxtField).getGridPane(),
                new TwoNodesGrid(lastNameLabel, lastNameTxtField).getGridPane(),
                new TwoNodesGrid(pubLabel, pubTxtField).getGridPane(),
                new TwoNodesGrid(volCountLabel, volCountTxtField).getGridPane(),
                new TwoNodesGrid(cirLabel, cirTxtField).getGridPane()
        );
    }

    public VBox getVBox() {
        return vBox;
    }

    // Get TextField text
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

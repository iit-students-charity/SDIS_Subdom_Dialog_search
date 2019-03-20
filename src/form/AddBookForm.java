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

    private VBox vBox;


    public AddBookForm() {
        nameTxtField = new TextField();
        firstNameTxtField = new TextField();
        middleNameTxtField = new TextField();
        lastNameTxtField = new TextField();
        pubTxtField = new TextField();
        volCountTxtField = new TextField();
        cirTxtField = new TextField();

        vBox = createVBox();
        vBox.setAlignment(Pos.CENTER);
    }

    private VBox createVBox() {
        return new VBox(
                new TwoNodesGrid(new Label(ViewConstant.NAME), nameTxtField).getGridPane(),
                new TwoNodesGrid(new Label(ViewConstant.FIRST_NAME), firstNameTxtField).getGridPane(),
                new TwoNodesGrid(new Label(ViewConstant.MIDDLE_NAME), middleNameTxtField).getGridPane(),
                new TwoNodesGrid(new Label(ViewConstant.LAST_NAME), lastNameTxtField).getGridPane(),
                new TwoNodesGrid(new Label(ViewConstant.PUBLISHING), pubTxtField).getGridPane(),
                new TwoNodesGrid(new Label(ViewConstant.VOLUME_COUNT), volCountTxtField).getGridPane(),
                new TwoNodesGrid(new Label(ViewConstant.CIRCULATION), cirTxtField).getGridPane()
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

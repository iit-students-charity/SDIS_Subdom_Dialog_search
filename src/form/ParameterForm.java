package form;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import sample.ParameterCondition;

public class ParameterForm {
    ParameterCondition condition;

    private TextField nameTxtField;
    private TextField firstNameTxtField;
    private TextField middleNameTxtField;
    private TextField lastNameTxtField;
    private TextField pubTxtField;
    private TextField volCountTxtField;
    private TextField volCountTotalTxtField;
    private TextField cirTxtField;

    VBox vBox;


    public ParameterForm(ParameterCondition condition) {
        this.condition = condition;
        nameTxtField = new TextField();
        firstNameTxtField = new TextField();
        middleNameTxtField = new TextField();
        lastNameTxtField = new TextField();
        pubTxtField = new TextField();
        volCountTxtField = new TextField();
        volCountTotalTxtField = new TextField();
        cirTxtField = new TextField();

        vBox = createVBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
    }

    private VBox createVBox() {
        switch (condition) {
            case AUTHOR: {
                return createAuthorVBox();
            }
            case PUBLISHING_AUTHOR: {
                return createPubAuthorVBox();
            }
            case VOLUME_COUNT: {
                return createVolCountVBox();
            }
            case NAME: {
                return createNameVBox();
            }
            case CIRCULATION: {
                return createCirculationVBox();
            }
            case VOLUME_COUNT_TOTAL: {
                return createVolCountTotalVBox();
            }
        }

        return new VBox();
    }

    private VBox createAuthorVBox() {
        firstNameTxtField = new TextField();
        middleNameTxtField = new TextField();
        lastNameTxtField = new TextField();

        Label firstNameLabel = new Label(FormContentText.FIRST_NAME);
        Label middleNameLabel = new Label(FormContentText.MIDDLE_NAME);
        Label lastNameLabel = new Label(FormContentText.LAST_NAME);

        return new VBox(
                new GridComplexComponent(firstNameLabel, firstNameTxtField).getGridPane(),
                new GridComplexComponent(middleNameLabel, middleNameTxtField).getGridPane(),
                new GridComplexComponent(lastNameLabel, lastNameTxtField).getGridPane()
        );
    }

    private VBox createPubAuthorVBox() {
        VBox vBox = createAuthorVBox();

        pubTxtField = new TextField();
        Label pubLabel = new Label(FormContentText.PUBLISHING);

        vBox.getChildren().add(new GridComplexComponent(pubLabel, pubTxtField).getGridPane());

        return vBox;
    }

    private VBox createVolCountVBox() {
        volCountTxtField = new TextField();
        Label volCountLabel = new Label(FormContentText.VOLUME_COUNT);

        return new VBox(new GridComplexComponent(volCountLabel, volCountTxtField).getGridPane());
    }

    private VBox createNameVBox() {
        nameTxtField = new TextField();
        Label nameLabel = new Label(FormContentText.NAME);

        return new VBox(new GridComplexComponent(nameLabel, nameTxtField).getGridPane());
    }

    private VBox createCirculationVBox() {
        cirTxtField = new TextField();
        Label cirLabel = new Label(FormContentText.CIRCULATION);

        return new VBox(new GridComplexComponent(cirLabel, cirTxtField).getGridPane());
    }

    private VBox createVolCountTotalVBox() {
        volCountTotalTxtField = new TextField();
        Label volCountTotalLabel = new Label(FormContentText.VOLUME_COUNT_TOTAL);

        return new VBox(new GridComplexComponent(volCountTotalLabel, volCountTotalTxtField).getGridPane());
    }

    public String getNameTxt() {
        return nameTxtField.getText();
    }

    public String getFirstNameTxt() {
        return firstNameTxtField.getText();
    }

    public String getMiddleNameTxt()  {
        return firstNameTxtField.getText();
    }

    public String getLastNameTxt() {
        return firstNameTxtField.getText();
    }

    public String getPubTxt() {
        return pubTxtField.getText();
    }

    public String getVolCountTxt() {
        return volCountTxtField.getText();
    }

    public String getVolCountTotalTxt() {
        return volCountTotalTxtField.getText();
    }

    public String getCirTxt() {
        return cirTxtField.getText();
    }

    public VBox getVBox() {
        return vBox;
    }
}

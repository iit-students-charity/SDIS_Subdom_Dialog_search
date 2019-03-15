package form;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import sample.ParameterCondition;

public class ParameterForm {
    private ParameterCondition condition;

    private TextField nameTxtField;
    private TextField firstNameTxtField;
    private TextField middleNameTxtField;
    private TextField lastNameTxtField;
    private TextField pubTxtField;
    private TextField volCountTotalTxtField;
    private TextField cirTxtField;
    private TextField countUpLimit;
    private TextField countDownLimit;
    private TextField countBorder;

    private RadioButton less;
    private RadioButton greater;
    private ToggleGroup greaterOrLessTglGrp;

    private VBox vBox;


    public ParameterForm(ParameterCondition condition) {
        this.condition = condition;
        nameTxtField = new TextField();
        firstNameTxtField = new TextField();
        middleNameTxtField = new TextField();
        lastNameTxtField = new TextField();
        pubTxtField = new TextField();
        volCountTotalTxtField = new TextField();
        cirTxtField = new TextField();
        countUpLimit = new TextField();
        countDownLimit = new TextField();
        countBorder = new TextField();

        less = new RadioButton(ContentText.LESS);
        greater = new RadioButton(ContentText.GREATER);
        less.setUserData(ContentText.LESS);
        greater.setUserData(ContentText.GREATER);
        greaterOrLessTglGrp = new ToggleGroup();
        greaterOrLessTglGrp.getToggles().addAll(less, greater);
        greaterOrLessTglGrp.selectToggle(less);

        vBox = createVBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
    }

    // Box creating
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
        Label firstNameLabel = new Label(ContentText.FIRST_NAME);
        Label middleNameLabel = new Label(ContentText.MIDDLE_NAME);
        Label lastNameLabel = new Label(ContentText.LAST_NAME);

        return new VBox(
                new TwoNodesGrid(firstNameLabel, firstNameTxtField).getGridPane(),
                new TwoNodesGrid(middleNameLabel, middleNameTxtField).getGridPane(),
                new TwoNodesGrid(lastNameLabel, lastNameTxtField).getGridPane()
        );
    }

    private VBox createPubAuthorVBox() {
        VBox vBox = createAuthorVBox();
        Label pubLabel = new Label(ContentText.PUBLISHING);

        vBox.getChildren().add(new TwoNodesGrid(pubLabel, pubTxtField).getGridPane());

        return vBox;
    }

    private VBox createVolCountVBox() {
        Label upLimit = new Label(ContentText.UP_LIMIT);
        Label downLimit = new Label(ContentText.DOWN_LIMIT);

        return new VBox(
                new TwoNodesGrid(downLimit, countDownLimit).getGridPane(),
                new TwoNodesGrid(upLimit, countUpLimit).getGridPane()
        );
    }

    private VBox createNameVBox() {
        Label nameLabel = new Label(ContentText.NAME);

        return new VBox(new TwoNodesGrid(nameLabel, nameTxtField).getGridPane());
    }

    private VBox createCirculationVBox() {
        Label cirLabel = new Label(ContentText.BORDER);

        return new VBox(
                new TwoNodesGrid(cirLabel, cirTxtField).getGridPane(),
                new TwoNodesGrid(less, greater).getGridPane()
        );
    }

    private VBox createVolCountTotalVBox() {
        Label volCountTotalLabel = new Label(ContentText.BORDER);

        return new VBox(
                new TwoNodesGrid(volCountTotalLabel, volCountTotalTxtField).getGridPane(),
                new TwoNodesGrid(less, greater).getGridPane()
        );
    }

    // Field text' getters
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

    public String getVolCountTotalTxt() {
        return volCountTotalTxtField.getText();
    }

    public String getCirTxt() {
        return cirTxtField.getText();
    }

    public String getCountUpLimitTxt() {
        return countUpLimit.getText();
    }

    public String getCountDownLimitTxt() {
        return countDownLimit.getText();
    }

    public String getCountBorderTxt() {
        return countBorder.getText();
    }


    public ToggleGroup getGreaterOrLessTglGrp() {
        return greaterOrLessTglGrp;
    }

    public VBox getVBox() {
        return vBox;
    }
}

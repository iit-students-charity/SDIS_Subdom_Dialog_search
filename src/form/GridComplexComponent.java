package form;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class GridComplexComponent {
    private Label label;
    private TextField textField;

    private GridPane gridPane;

    public GridComplexComponent(Label label, TextField textField) {
        this.label = label;
        this.textField = textField;

        gridPane = createGridPane();
    }

    private GridPane createGridPane() {
        gridPane = new GridPane();

        ColumnConstraints column = new ColumnConstraints();
        column.setPercentWidth(50);
        column.setHalignment(HPos.LEFT);

        int insets = 8;
        gridPane.getColumnConstraints().addAll(column, column);
        GridPane.setMargin(label, new Insets(insets));
        GridPane.setMargin(textField, new Insets(insets));

        gridPane.add(label, 0,0);
        gridPane.add(textField,1,0);

        return gridPane;
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}

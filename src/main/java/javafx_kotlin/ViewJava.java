package javafx_kotlin;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * An example of creation of the same GUI layout as in View.kt in Java
 */
public class ViewJava extends StackPane {
    private TextField textField;
    private final ArrayList<Button> buttons = new ArrayList<>();

    public ViewJava() {
        setPadding(new Insets(5.0));

        for(int i = 1; i <= 9; i++) {
            Button button = new Button(String.valueOf(i));
            int finalI = i;
            button.setOnAction(actionEvent ->
                    textField.setText(textField.getText() + finalI));

            buttons.add(button);
        }

        getChildren().add(createLayout());
    }

    private Node createLayout() {
        VBox vBox = new VBox(4.0);
        ObservableList<Node> vBoxChildren = vBox.getChildren();

        vBoxChildren.add(new Label("Hello, Java!"));

        textField = new TextField();
        vBoxChildren.add(textField);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(2.0);
        gridPane.setVgap(2.0);

        for(int i = 0; i <= 2; i++) {
            for(int j = 0; j <= 2; j++) {
                gridPane.add(buttons.get(i * 3 + j), i, j);
            }
        }

        Button buttonZero = new Button("0");
        buttonZero.setOnAction(actionEvent ->
                textField.setText(textField.getText() + "0"));

        Button buttonClear = new Button("C");
        buttonClear.setOnAction(actionEvent -> textField.setText(""));

        gridPane.add(buttonZero, 0, 3);
        gridPane.add(buttonClear, 1, 3);

        vBoxChildren.add(gridPane);

        return vBox;
    }
}

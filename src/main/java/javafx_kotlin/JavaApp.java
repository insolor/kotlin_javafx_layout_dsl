package javafx_kotlin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * An example of a java class which uses kotlin code to create GUI.<br/>
 * Equivalent to javafx_kotlin/App.kt class/module
 */
public class JavaApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        var layout = new ViewTornadoFx();
        var scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

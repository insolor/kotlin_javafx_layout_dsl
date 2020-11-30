package javafx_kotlin;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * An example of java class which uses kotlin code to create GUI<br/>
 * Equivalent to javafx_kotlin/App.kt class/module
 */
public class JavaApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        Parent layout = new View();

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

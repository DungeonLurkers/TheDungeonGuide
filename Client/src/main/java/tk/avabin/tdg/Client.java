package tk.avabin.tdg;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Avabin on 06.03.2017.
 */
public class Client extends Application {
    public void start(Stage primaryStage) throws Exception {
        Pane gPane = new GridPane();
        Scene main = new Scene(gPane, 240, 320);
        primaryStage.setScene(main);
        primaryStage.show();
    }
}

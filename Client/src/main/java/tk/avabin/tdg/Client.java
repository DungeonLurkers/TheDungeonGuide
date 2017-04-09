package tk.avabin.tdg;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.java.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Avabin on 06.03.2017.
 */
@Log
public class Client extends Application {
    public void start(Stage primaryStage) throws Exception {
        String server = "http://localhost";
        int port = 8080;
        ExecutorService executor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 7; i++) {
            executor.submit(new ConnectionThread(server, port, "ping"));
        }

        executor.awaitTermination(3, TimeUnit.SECONDS);
    }
}

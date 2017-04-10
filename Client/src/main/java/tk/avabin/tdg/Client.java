package tk.avabin.tdg;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import tk.avabin.tdg.beans.Entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Avabin on 06.03.2017.
 */
@Log
public class Client extends Application {
    public void start(Stage primaryStage) throws Exception {
        Base64Proccessor proccessor = new Base64Proccessor();
        User u = new User();
        String server = "http://localhost";
        int port = 8080;
        ExecutorService executor = Executors.newFixedThreadPool(8);

        u.setUsername("Avabino");
        u.setEmail("email@ok.pl");
        u.setPassword("wololo");
        executor.submit(new ConnectionThread(server, port, "createuser?base64object=" + proccessor.objectToString(u)));

        executor.awaitTermination(3, TimeUnit.SECONDS);
    }
}

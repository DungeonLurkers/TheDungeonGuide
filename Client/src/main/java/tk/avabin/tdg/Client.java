package tk.avabin.tdg;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.java.Log;
import tk.avabin.tdg.beans.Entities.Feat;
import tk.avabin.tdg.beans.Entities.Item;
import tk.avabin.tdg.beans.Entities.Spell;
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
        Base64Processor processor = new Base64Processor();
        User u = new User();
        String server = "http://localhost";
        int port = 8080;
        ExecutorService executor = Executors.newFixedThreadPool(8);

        u.setUsername("Avabino");
        u.setEmail("email@ok.pl");
        u.setPassword("wololo");

        Item i = new Item();
        i.setName("TestItem");
        i.setPrice(900L);
        i.setDesc("That is just testing item.");

        Spell s = new Spell();
        s.setName("Wololo");
        s.setRank((short) 9);
        s.setDesc("Wololo? Wololo!");

        Feat f = new Feat();
        f.setName("Almighty test strike!");
        f.setDesc("As name says.");
        executor.submit(new ConnectionThread(server, port, "saveorupdate?b64ob=" + processor.objectToString(u)));
        executor.submit(new ConnectionThread(server, port, "saveorupdate?b64ob=" + processor.objectToString(i)));
        executor.submit(new ConnectionThread(server, port, "saveorupdate?b64ob=" + processor.objectToString(s)));
        executor.submit(new ConnectionThread(server, port, "saveorupdate?b64ob=" + processor.objectToString(f)));

        executor.awaitTermination(3, TimeUnit.SECONDS);
    }
}

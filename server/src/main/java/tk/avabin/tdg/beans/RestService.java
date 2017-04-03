package tk.avabin.tdg.beans;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Avabin on 03.04.2017.
 */
@RestController
public class RestService {

    @RequestMapping("/ping")
    public String pong() {
        return "Pong!";
    }
}

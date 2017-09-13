package tk.avabin.tdg.beans.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Avabin on 03.04.2017.
 */
@RestController
public class PingRestController {

    @RequestMapping("/ping")
    public String ping() {
        return "Pong!";
    }

    @RequestMapping("/pong")
    public String pong() {
        return "Hey! My job is to pong!";
    }
}

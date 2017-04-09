package tk.avabin.tdg.beans.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Avabin on 09.04.2017.
 */
@RestController
public class DatabaseRestController {

    @RequestMapping("/admin")
    public String adminPage() {
        return "Yes, you are an admin.";
    }
}

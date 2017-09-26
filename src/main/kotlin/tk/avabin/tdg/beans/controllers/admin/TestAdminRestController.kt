package tk.avabin.tdg.beans.controllers.admin

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin")
class TestAdminRestController {

    @RequestMapping("/ping")
    fun pong(): String {
        return "pong"
    }
}
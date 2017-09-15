package tk.avabin.tdg.beans.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by Avabin on 03.04.2017.
 */
@RestController
class PingRestController {

    @RequestMapping("/ping")
    fun ping(): String {
        return "Pong!"
    }

    @RequestMapping("/pong")
    fun pong(): String {
        return "Hey! My job is to pong!"
    }

    @RequestMapping("/teapot")
    fun teapot(): ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.I_AM_A_TEAPOT)
    }
}

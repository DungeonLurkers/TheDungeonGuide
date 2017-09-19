package tk.avabin.tdg.beans.controllers

import org.springframework.util.ClassUtils
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

/**
 * Created by Avabin on 09.04.2017.
 */
@RestController
class ErrorRestController {

    @ExceptionHandler(Throwable::class)
    fun handleAnyException(ex: Throwable, request: HttpServletRequest): String {
        return ClassUtils.getShortName(ex.javaClass)
    }
}

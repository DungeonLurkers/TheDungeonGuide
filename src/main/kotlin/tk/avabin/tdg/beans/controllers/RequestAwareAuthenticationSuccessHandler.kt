package tk.avabin.tdg.beans.controllers

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache
import org.springframework.security.web.savedrequest.RequestCache
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Avabin on 21.04.2017.
 */
@Component
class RequestAwareAuthenticationSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {

    var requestCache: RequestCache = HttpSessionRequestCache()

    @Throws(ServletException::class, IOException::class)
    override fun onAuthenticationSuccess(

            request: HttpServletRequest,
            response: HttpServletResponse,
            authentication: Authentication) {

        val savedRequest = requestCache.getRequest(request, response)

        if (savedRequest == null) {
            clearAuthenticationAttributes(request)
            return
        }
        val targetUrlParam = targetUrlParameter
        if (isAlwaysUseDefaultTargetUrl || targetUrlParam != null && StringUtils.hasText(request.getParameter(targetUrlParam))) {
            requestCache.removeRequest(request, response)
            clearAuthenticationAttributes(request)
            return
        }

        clearAuthenticationAttributes(request)
    }
}
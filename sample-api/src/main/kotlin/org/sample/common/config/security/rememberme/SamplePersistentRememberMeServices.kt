package org.sample.common.config.security.rememberme

import org.sample.common.config.security.SampleUserDetailsService
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SamplePersistentRememberMeServices(
    key: String,
    userDetailsService: UserDetailsService,
    persistentTokenRepository: PersistentTokenRepository
) : PersistentTokenBasedRememberMeServices(key, userDetailsService, persistentTokenRepository) {

    private val rememberMeHeader = "X-RememberMe-Key"

    override fun extractRememberMeCookie(request: HttpServletRequest): String? {
        return request.getHeader(rememberMeHeader)
    }

    override fun setCookie(
        tokens: Array<out String>,
        maxAge: Int,
        request: HttpServletRequest,
        response: HttpServletResponse
    ) {
        val cookieValue = encodeCookie(tokens)
        response.setHeader(rememberMeHeader, cookieValue)
    }

    override fun rememberMeRequested(request: HttpServletRequest?, parameter: String?): Boolean {
        return true
//        return super.rememberMeRequested(request, parameter)
    }
}
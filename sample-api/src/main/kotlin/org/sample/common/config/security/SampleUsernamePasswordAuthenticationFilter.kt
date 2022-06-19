package org.sample.common.config.security

import org.sample.common.SampleConstants
import org.sample.common.util.JsonUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SampleUsernamePasswordAuthenticationFilter : UsernamePasswordAuthenticationFilter {
    constructor(
        authenticationManager: AuthenticationManager,
        authSuccessHandler: SampleAuthSuccessHandler,
        authFailureHandler: SampleAuthFailureHandler,
        rememberMeServices: PersistentTokenBasedRememberMeServices
    ) {
        this.authenticationManager = authenticationManager
        setAuthenticationSuccessHandler(authSuccessHandler)
        setAuthenticationFailureHandler(authFailureHandler)
        setFilterProcessesUrl("/api/login")
//        this.rememberMeServices = rememberMeServices
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication? {
        val parsedRequest = request.reader.readText()
        val authReq = JsonUtil.deserialize(parsedRequest, AuthRequest::class)
        logger.debug { authReq }
        val authentication = UsernamePasswordAuthenticationToken(authReq.username, authReq.password)
        request.setAttribute(SampleConstants.REQUEST_ATTR_AUTH_REQUEST, authentication)
        setDetails(request, authentication)
        return authenticationManager.authenticate(authentication)
    }
}
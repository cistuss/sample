package org.sample.common.config.security

import mu.KotlinLogging
import org.sample.common.SampleConstants
import org.sample.common.util.JsonUtil
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SampleAuthSuccessHandler : SimpleUrlAuthenticationSuccessHandler() {

    private val klogger = KotlinLogging.logger {}

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        if (request.getHeader("Content-Type") == "application/json") {
            trackLogin(request, authentication)
            response.setHeader("x-auth-token", request.session.id)

            val body = mapOf(
                "success" to true,
                "token" to request.session.id
            )
            response.writer.print(JsonUtil.serialize(body))
            response.writer.flush()
        } else {
            super.onAuthenticationSuccess(request, response, authentication)
        }
    }

    private fun trackLogin(request: HttpServletRequest, authentication: Authentication) {
        val accountId = getAccountId(authentication)
        val addr = request.remoteAddr
        val currentTime = LocalDateTime.now()
        klogger.info { "TRACKING LOGIN $accountId[$addr] $currentTime" }
    }

    private fun getAccountId(authentication: Authentication): Long {
        return authentication.authorities
            .filter { it.authority.startsWith(SampleConstants.AUTHORITY_PREFIX_ACCOUNT_ID) }
            .map { it.authority.split(":")[1].toLong() }
            .firstOrNull() ?: throw IllegalStateException("Authority ACCOUNTID: not found")
    }
}
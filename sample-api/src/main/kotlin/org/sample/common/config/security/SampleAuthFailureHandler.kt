package org.sample.common.config.security

import org.sample.api.common.SampleApiCommonResponse
import org.sample.common.SampleApiErrorCode
import org.sample.common.util.JsonUtil
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SampleAuthFailureHandler : SimpleUrlAuthenticationFailureHandler() {
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        if (request.getHeader("Content-Type") == "application/json") {
            // TODO increase login failure count to account lock
            val body = SampleApiCommonResponse.fail(SampleApiErrorCode.AUTH_FAILED)
            response.status = HttpStatus.BAD_REQUEST.value()
            response.writer.print(JsonUtil.serialize(body))
            response.writer.flush()
        } else {
            super.onAuthenticationFailure(request, response, exception)
        }
    }
}
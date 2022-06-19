package org.sample.common.config.security

import org.sample.common.util.JsonUtil
import org.springframework.http.HttpStatus
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SampleAuthEntryPoint : Http403ForbiddenEntryPoint() {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        arg2: AuthenticationException?
    ) {
        if (request.getHeader("Content-Type") == "application/json") {
            val map = mapOf("reason" to "authFail")
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.writer.print(JsonUtil.serialize(map))
            response.writer.flush()
        } else {
            super.commence(request, response, arg2)
        }
    }
}
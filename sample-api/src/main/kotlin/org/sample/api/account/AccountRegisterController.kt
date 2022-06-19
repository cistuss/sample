package org.sample.api.account

import mu.KLogging
import org.sample.api.common.SampleApiCommonResponse
import org.sample.domain.account.AccountRegisterModel
import org.sample.domain.account.AccountRegisterService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account/register")
class AccountRegisterController(
    private val accountRegisterService: AccountRegisterService
) {
    companion object: KLogging()

    @PostMapping("")
    fun register(@RequestBody body: AccountRegisterRequest): SampleApiCommonResponse {
        accountRegisterService.registerAccountOnetime(AccountRegisterModel(
            emailAddress = body.emailAddress,
            firstName = body.firstName,
            lastName = body.lastName
        ))
        return SampleApiCommonResponse.success()
    }


}
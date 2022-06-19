package org.sample.api.account

import mu.KLogging
import org.sample.api.common.SampleApiCommonResponse
import org.sample.api.common.SampleApiListResponse
import org.sample.domain.account.Account
import org.sample.domain.account.AccountSearchService
import org.sample.domain.auth.AuthenticationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/account")
class AccountController(
    private val authenticationService: AuthenticationService,
    private val accountSearchService: AccountSearchService
) {
    companion object : KLogging()

    @GetMapping("/me")
    fun me(): AccountResponse {
        val account = authenticationService.getLoginUser() ?: throw IllegalStateException("Login user not found")
        return toResponse(account)
    }

    @GetMapping("/all")
    fun all(): SampleApiListResponse<AccountResponse> {
        val accounts = accountSearchService.searchAllAccounts()
        return SampleApiListResponse(items = accounts.map(::toResponse))
    }

    private fun toResponse(model: Account): AccountResponse {
        return AccountResponse(
            id = model.id,
            emailAddress = model.emailAddress,
            password = model.password,
            firstName = model.firstName,
            lastName = model.lastName,

            registerDatetime = model.registerDatetime,
            registerAccountId = model.registerAccountId,
            updateDatetime = model.updateDatetime,
            updateAccountId = model.updateAccountId
        )
    }
}
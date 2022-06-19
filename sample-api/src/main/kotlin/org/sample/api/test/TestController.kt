package org.sample.api.test

import org.sample.infra.account.AccountRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/open/test")
class TestController(
    private val accountRepository: AccountRepository
) {

    @RequestMapping("")
    fun test(): TestResponse {
        val account = accountRepository.selectByEmail("admin@sample.jp")
        return TestResponse(account = account)
    }
}
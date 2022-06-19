package org.sample.api.test

import org.sample.infra.account.AccountEntity

data class TestResponse(
    val account: AccountEntity?
)

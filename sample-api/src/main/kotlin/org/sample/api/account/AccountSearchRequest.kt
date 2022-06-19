package org.sample.api.account

data class AccountSearchRequest(
    val pageSize: Int = 20,
    val pageNumber: Int = 1
)

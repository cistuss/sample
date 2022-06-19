package org.sample.api.account

data class AccountRegisterRequest(
    val firstName: String,
    val lastName: String,
    val emailAddress: String
)

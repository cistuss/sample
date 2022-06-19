package org.sample.domain.account

import java.time.LocalDateTime

data class Account(
    val id: Long,
    val emailAddress: String,
    val password: String,
    val firstName: String?,
    val lastName: String?,

    val registerDatetime: LocalDateTime? = null,
    val registerAccountId: Long? = null,
    val updateDatetime: LocalDateTime? = null,
    val updateAccountId: Long? = null
)

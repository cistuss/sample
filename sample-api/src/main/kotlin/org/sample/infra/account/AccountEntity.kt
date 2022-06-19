package org.sample.infra.account

import java.time.LocalDateTime

data class AccountEntity(
    val id: Long? = null,
    val emailAddress: String?,
    val password: String?,
    val firstName: String?,
    val lastName: String?,

    val registerDatetime: LocalDateTime?,
    val registerAccountId: Long?,
    val updateDatetime: LocalDateTime?,
    val updateAccountId: Long?,
    val deleteFlg: Char = '0'
)

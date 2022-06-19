package org.sample.domain.account

import org.sample.infra.account.AccountEntity

object AccountModelConverter {

    fun convertToModel(entity: AccountEntity): Account {
        return Account(
            id = entity.id!!,
            emailAddress = entity.emailAddress!!,
            password = entity.password!!,
            firstName = entity.firstName,
            lastName = entity.lastName,

            registerDatetime = entity.registerDatetime,
            registerAccountId = entity.registerAccountId,
            updateDatetime = entity.updateDatetime,
            updateAccountId = entity.updateAccountId
        )
    }
}
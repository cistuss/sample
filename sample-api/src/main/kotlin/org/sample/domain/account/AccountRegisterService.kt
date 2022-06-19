package org.sample.domain.account

import mu.KLogging
import org.sample.infra.account.AccountEntity
import org.sample.infra.account.AccountRepository
import org.sample.infra.onetime.OnetimeKeyRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class AccountRegisterService(
    private val accountRepository: AccountRepository,
    private val onetimeKeyRepository: OnetimeKeyRepository
) {
    companion object: KLogging()

    fun registerAccountOnetime(model: AccountRegisterModel) {
        val now = LocalDateTime.now()
        val entity = AccountEntity(
            emailAddress = model.emailAddress,
            password = "password",
            firstName = model.firstName,
            lastName = model.lastName,
            registerDatetime = now,
            registerAccountId = null,
            updateDatetime = now,
            updateAccountId = null
        )
        accountRepository.insert(entity)

        val key = UUID.randomUUID().toString()
        onetimeKeyRepository.add(key, entity.id.toString(), 30 * 60)

        logger.info { "__DEBUG:" }
        logger.info { "KEY: $key" }
    }
}
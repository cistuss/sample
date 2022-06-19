package org.sample.domain.account

import org.sample.infra.account.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountSearchService(
    private val accountRepository: AccountRepository
) {

    fun searchAllAccounts(): List<Account> {
        val entities = accountRepository.selectAll()
        return entities.map(AccountModelConverter::convertToModel)
    }
}
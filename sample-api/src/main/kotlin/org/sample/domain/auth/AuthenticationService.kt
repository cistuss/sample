package org.sample.domain.auth

import org.sample.common.SampleConstants
import org.sample.domain.account.Account
import org.sample.domain.account.AccountModelConverter
import org.sample.infra.account.AccountRepository
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val accountRepository: AccountRepository
) {

    fun getLoginUser(): Account? {
        val auth: Authentication? = SecurityContextHolder.getContext().authentication
        if (auth != null) {
            val id = getAccountId(auth)
            val entity = accountRepository.selectById(id)
            return entity?.let(AccountModelConverter::convertToModel)
        }
        return null
    }

    fun getAccountId(authentication: Authentication): Long {
        val prefix = SampleConstants.AUTHORITY_PREFIX_ACCOUNT_ID
        return authentication.authorities
            .filter { it.authority.startsWith(prefix) }
            .map { it.authority.split(":")[1].toLong() }
            .firstOrNull() ?: throw IllegalStateException("Authority ACCOUNTID: not found")
    }
}
package org.sample.common.config.security

import org.sample.common.SampleConstants
import org.sample.infra.account.AccountRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class SampleUserDetailsService(
    private val accountRepository: AccountRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val entity = accountRepository.selectByEmail(username)
        return entity?.let {
            val authorities = arrayOf(
                "ROLES:",
                "${SampleConstants.AUTHORITY_PREFIX_ACCOUNT_ID}:${it.id}"
            )

            User
                .withUsername(username)
                .password(it.password)
                .authorities(*authorities)
                .build()
        } ?: throw UsernameNotFoundException("Account Not Found: $username")
    }
}
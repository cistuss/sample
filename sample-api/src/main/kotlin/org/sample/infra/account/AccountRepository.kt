package org.sample.infra.account

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper

@Mapper
interface AccountRepository {
    fun selectAll(): List<AccountEntity>
    fun selectById(id: Long): AccountEntity?
    fun selectByEmail(email: String): AccountEntity?

    fun insert(accountEntity: AccountEntity)
}
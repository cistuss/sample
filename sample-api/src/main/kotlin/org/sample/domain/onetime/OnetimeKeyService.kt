package org.sample.domain.onetime

import org.sample.infra.onetime.OnetimeKeyRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Service
class OnetimeKeyService(
    private val onetimeKeyRepository: OnetimeKeyRepository
) {
    fun generateOnetimeKey(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        val uuid = UUID.fromString(LocalDateTime.now().format(formatter))
        val onetimeKey = uuid.toString()
        onetimeKeyRepository.add("", onetimeKey, 30 * 60) // expire in 30 min
        return onetimeKey
    }
}
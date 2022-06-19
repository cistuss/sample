package org.sample.infra.onetime

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

@Service
class OnetimeKeyRepository(
    private val redisTemplate: StringRedisTemplate
) {
    companion object {
        const val REDIS_KEY_PREFIX: String = "application:sample:onetime:"
    }

    fun add(userKey: String, value: String, expireSec: Long) {
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + userKey, value, expireSec, TimeUnit.SECONDS)
    }
}
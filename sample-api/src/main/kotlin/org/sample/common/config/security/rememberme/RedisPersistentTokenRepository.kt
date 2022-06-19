package org.sample.common.config.security.rememberme

import org.sample.common.util.JsonUtil
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
import org.springframework.stereotype.Service
import java.util.*
import java.util.concurrent.TimeUnit

@Service
class RedisPersistentTokenRepository(
    private val redisTemplate: StringRedisTemplate
) : PersistentTokenRepository {

    private val seriesPrefix = "application:sample:security:series:"
    private val userPrefix = "application:sample:security:user:"

    override fun createNewToken(token: PersistentRememberMeToken) {
        val current = redisTemplate.opsForValue().get(token.series)
        if(current != null) {
            throw DataIntegrityViolationException("Series id '${token.series}' already exist")
        }
        val data = SamplePersistentRememberMeToken(
            username = token.username,
            series = token.series,
            value = token.tokenValue,
            date = token.date
        )

        redisTemplate.opsForValue().set(seriesPrefix + token.series, JsonUtil.serialize(data), 365, TimeUnit.DAYS)
        redisTemplate.opsForValue().set(userPrefix + token.username, token.series, 365, TimeUnit.DAYS)
    }

    override fun updateToken(series: String, tokenValue: String, lastUsed: Date) {
        val token = getTokenForSeries(series) ?: return
        val newToken = PersistentRememberMeToken(token.username, series, tokenValue, Date())
        val data = SamplePersistentRememberMeToken(
            username = newToken.username,
            series = newToken.series,
            value = newToken.tokenValue,
            date = newToken.date
        )
        redisTemplate.opsForValue().set(seriesPrefix + series, JsonUtil.serialize(data), 365, TimeUnit.DAYS)
    }

    @Synchronized
    override fun getTokenForSeries(seriesId: String?): PersistentRememberMeToken? {
        val value = redisTemplate.opsForValue().get(seriesPrefix + seriesId)
        return if(value != null) {
            val token = JsonUtil.deserialize(value, SamplePersistentRememberMeToken::class)
            token.let {
                PersistentRememberMeToken(it.username, it.series, it.value, it.date)
            }
        } else null
    }

    override fun removeUserTokens(username: String?) {
        val value = redisTemplate.opsForValue().get(userPrefix + username)
        value?.let {
            redisTemplate.delete(userPrefix + username)
            redisTemplate.delete(seriesPrefix + it)
        }
    }
}
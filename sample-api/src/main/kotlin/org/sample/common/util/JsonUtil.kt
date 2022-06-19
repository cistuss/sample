package org.sample.common.util

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import kotlin.reflect.KClass

object JsonUtil {
    private val mapper = ObjectMapper()
    init {
        mapper.registerKotlinModule()
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    }

    fun serialize(obj: Any): String = mapper.writeValueAsString(obj)
    fun <T : Any> deserialize(json: String, clazz: KClass<T>): T {
        return json.let { mapper.readValue(it, clazz.java) }
    }
}
package org.sample.api.common

data class SampleApiListResponse<out T>(
    val items: List<T>
)

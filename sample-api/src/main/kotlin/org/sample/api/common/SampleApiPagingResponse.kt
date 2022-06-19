package org.sample.api.common

data class SampleApiPagingResponse<out T>(
    val items: List<T>,
    val pageSize: Int = 20,
    val pageCount: Int = 0,
    val allCount: Int
)
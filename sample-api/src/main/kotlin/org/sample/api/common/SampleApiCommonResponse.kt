package org.sample.api.common

import org.sample.common.SampleApiErrorCode

data class SampleApiCommonResponse(
    val success: Boolean = true,
    val errorCd: String? = null
) {
    companion object {
        fun success(): SampleApiCommonResponse = SampleApiCommonResponse()
        fun fail(errorCd: String): SampleApiCommonResponse {
            return SampleApiCommonResponse(false, errorCd)
        }

        fun fail(errorCd: SampleApiErrorCode): SampleApiCommonResponse {
            return SampleApiCommonResponse(false, errorCd.code)
        }
    }
}
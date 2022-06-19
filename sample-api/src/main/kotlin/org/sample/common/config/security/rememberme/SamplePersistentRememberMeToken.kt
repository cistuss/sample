package org.sample.common.config.security.rememberme

import java.util.Date

data class SamplePersistentRememberMeToken(
    val username: String,
    val series: String,
    val value: String,
    val date: Date
)

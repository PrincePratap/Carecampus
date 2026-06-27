package org.parowings.common.authentication

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val message: String
)

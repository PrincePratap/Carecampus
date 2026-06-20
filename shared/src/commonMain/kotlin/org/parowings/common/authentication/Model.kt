package org.parowings.common.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Login Request
@Serializable
data class LoginRequest(
    @SerialName("login")
    val login: String,

    @SerialName("password")
    val password: String
)
@Serializable
data class LoginResponse(
    @SerialName("message")
    val message: String,

    @SerialName("token")
    val token: String,

)

data class AuthResultData(
    val id: String,
    val name: String,
    val email: String,
    val phone: String,
    val token: String
)
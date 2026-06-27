package org.parowings.common.authentication

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleLoginRequest(
    @SerialName("firebase_uid")
    val firebaseUid: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("email")
    val email: String,
    @SerialName("photo_url")
    val photoUrl: String
)

@Serializable
data class GoogleLoginResponse(
    @SerialName("access_token")
    val accessToken: String,
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("user")
    val user: GoogleLoginUser
)

@Serializable
data class GoogleLoginUser(
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val fullName: String,
    @SerialName("email")
    val email: String,
    @SerialName("photo_url")
    val photoUrl: String
)

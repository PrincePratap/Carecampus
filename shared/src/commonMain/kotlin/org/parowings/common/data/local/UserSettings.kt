package org.parowings.common.data.local

import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val userId: String = "",
    val fullName: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val accessToken: String = "",
)

//fun UserSettings.toAuthResultData(): AuthResultData {
//    return AuthResultData(id, name, email,  ",",token)
//}
//
//fun AuthResultData.toUserSettings(): UserSettings {
//    return UserSettings(id, name, email, token)
//}
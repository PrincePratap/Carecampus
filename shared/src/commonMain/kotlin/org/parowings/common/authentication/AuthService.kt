package org.parowings.common.authentication

internal interface AuthService {
    suspend fun checkAuth(): AuthResponse
}

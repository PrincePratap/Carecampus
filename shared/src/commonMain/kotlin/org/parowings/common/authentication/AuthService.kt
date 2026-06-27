package org.parowings.common.authentication

internal interface AuthService {
    suspend fun checkAuth(): AuthResponse
    suspend fun googleLogin(request: GoogleLoginRequest): GoogleLoginResponse
}

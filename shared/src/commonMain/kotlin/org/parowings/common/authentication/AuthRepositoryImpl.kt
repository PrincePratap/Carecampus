package org.parowings.common.authentication

import kotlinx.coroutines.withContext
import org.parowings.common.data.remote.Result
import org.parowings.common.util.DispatcherProvider

internal class AuthRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val authService: AuthService
) : AuthRepository {

    override suspend fun checkAuth(): Result<String> {
        return withContext(dispatcher.io) {
            try {
                val authResponse = authService.checkAuth()
                Result.Success(authResponse.message)
            } catch (e: Exception) {
                Result.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun googleLogin(request: GoogleLoginRequest): Result<GoogleLoginResponse> {
        return withContext(dispatcher.io) {
            try {
                Result.Success(authService.googleLogin(request))
            } catch (e: Exception) {
                Result.Error(e.message ?: "Unknown error")
            }
        }
    }
}

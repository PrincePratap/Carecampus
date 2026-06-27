package org.parowings.common.authentication

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody

internal class AuthServiceImpl(private val client: HttpClient) : AuthService {

    override suspend fun checkAuth(): AuthResponse = client.get("/auth/").body()

    override suspend fun googleLogin(request: GoogleLoginRequest): GoogleLoginResponse {
        return client.post("/auth/google-login") {
            setBody(request)
        }.body()
    }
}

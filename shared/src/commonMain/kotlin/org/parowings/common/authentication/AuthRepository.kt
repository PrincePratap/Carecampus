package org.parowings.common.authentication

import org.parowings.common.data.remote.Result

interface AuthRepository {
    suspend fun checkAuth(): Result<String>
}

package org.parowings.common.data.remote

sealed class Result<out T> {
    /**
     * Represents a successful result.
     * @param data The data returned from the successful operation.
     */
    data class Success<out T>(val data: T) : Result<T>()

    /**
     * Represents a failed result.
     * @param message A descriptive message explaining the error.
     *
     * This class inherits from 'Result<Nothing>' because in an error state,
     * there is no successful data of type T to return.
     */
    data class Error(val message: String) : Result<Nothing>()
}

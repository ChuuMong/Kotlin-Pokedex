package dev.marcosfarias.pokedex.data

import dev.marcosfarias.pokedex.data.exception.ServerErrorException
import dev.marcosfarias.pokedex.data.model.Result
import retrofit2.Response

suspend fun <T, R : Any> safeApiCall(
    call: suspend () -> Response<T>,
    transform: (T) -> R
): Result<R> {
    return try {
        val response = call.invoke()
        if (response.isSuccessful) {
            Result.Success(transform(response.body()!!))
        } else {
            Result.Error(ServerErrorException(response.code(), response.message()))
        }
    } catch (e: Exception) {
        Result.Error(e)
    }
}

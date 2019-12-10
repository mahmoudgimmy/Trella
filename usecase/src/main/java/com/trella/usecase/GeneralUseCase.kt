package com.trella.usecase

import com.elm.entities.ErrorMessageException
import com.elm.entities.NoInterNetConnectionException
import com.elm.entities.TrellaResult
import com.elm.entities.UnAuthorizedException
import com.elm.network.IWebClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException

// a general useCase to handle all useCases and all exceptions in one place
/*
    p ->input of useCase
    K -> response of useCase
 */
abstract class GeneralUseCase<in P, K> : KoinComponent {

    //use client in all useCases
    internal val client: IWebClient by inject()

    //use operator style in useCase invocation
    suspend operator fun invoke(parameters: P): TrellaResult<List<K>> {
        return try {
            val result = execute(parameters)
            return TrellaResult.Success(result)

        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    // parsing error body from API
                    return TrellaResult.ServerFail(
                        when (e.code()) {
                            403 -> "Unauthorized user."
                            else -> "There is a Problem with connecting to server."
                        }
                        ,
                        when (e.code()) {
                            403 -> UnAuthorizedException()
                            else -> ErrorMessageException()
                        }
                    )
                }
                is UnknownHostException -> {
                    return TrellaResult.Fail(
                        "There is no Internet connection",
                        NoInterNetConnectionException()
                    )
                }
                is ConnectException -> {

                    return TrellaResult.Fail(
                        "There is no Internet connection",
                        NoInterNetConnectionException()
                    )
                }
                else -> return TrellaResult.NothingToRetrieve
            }

        }
    }

    // abstract fun to handle input and output of usecase
    protected abstract suspend fun execute(parameters: P): List<K>

}
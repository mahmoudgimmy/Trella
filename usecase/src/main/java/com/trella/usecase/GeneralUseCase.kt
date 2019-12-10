package com.trella.usecase

import androidx.lifecycle.MutableLiveData
import com.elm.entities.*
import com.elm.network.IWebClient
import com.google.gson.Gson
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException


abstract class GeneralUseCase<in P, K> : KoinComponent {
    internal val client: IWebClient by inject()
    suspend operator fun invoke(parameters: P): TrellaResult<List<K>> {
        return try {
            val result = execute(parameters)
            return TrellaResult.Success(result!!)

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
                is UnknownHostException ->{
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

    protected abstract suspend fun execute(parameters: P): List<K>

}
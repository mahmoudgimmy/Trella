package com.elm.entities

import java.lang.Exception

sealed class TrellaResult<out R> {
    // if isSuccess = true
    data class Success<R> constructor(
        val result: List<R>
    ) : TrellaResult<List<R>>()

    // if isSuccess = false
    data class NotSuccess constructor(
        val error: String
    ) : TrellaResult<Nothing>()

    // if there is a failure from the server
    data class ServerFail(
        var error: String? = null,
        val exp: Throwable
    ) : TrellaResult<Nothing>()

    // if there is any service error (noInternetConnection, etc..)
    data class Fail(val error: String, val exp: Throwable) : TrellaResult<Nothing>()

    // if there is any kotlin exception
    object NothingToRetrieve : TrellaResult<Nothing>()
}


class UnAuthorizedException : Exception()
class NoInterNetConnectionException : Exception()
class ErrorMessageException : Exception()
package com.elm.entities

sealed class TrellaResult<out R> {
    // rapping success calls
    data class Success<R> constructor(
        val result: List<R>
    ) : TrellaResult<List<R>>()

    // rapping failures from the server
    data class ServerFail(
        var error: String? = null,
        val exp: Throwable
    ) : TrellaResult<Nothing>()

    // rapping communications failures (noInternetConnection, etc..)
    data class Fail(val error: String, val exp: Throwable) : TrellaResult<Nothing>()

    // if there is any kotlin exception
    object NothingToRetrieve : TrellaResult<Nothing>()
}


class UnAuthorizedException : Exception()
class NoInterNetConnectionException : Exception()
class ErrorMessageException : Exception()
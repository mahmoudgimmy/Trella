package com.trella.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elm.entities.NoInterNetConnectionException
import com.elm.entities.TrellaResult
import com.elm.entities.UnAuthorizedException
import kotlinx.coroutines.cancelChildren

open class BaseViewModel : ViewModel() {
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val messages: MutableLiveData<Pair<String, Exception>> = MutableLiveData()

    // function to handle responses from usecase
    fun <T> filteringResult(
        result: TrellaResult<List<T>>,
        successPoster: MutableLiveData<List<T>>
    ) {
        when (result) {
            is TrellaResult.Success -> {
                successPoster.postValue(result.result)
            }
            is TrellaResult.ServerFail -> {

                if (result.exp is UnAuthorizedException) {
                    showMessages(result.error, UnAuthorizedException())
                }
                viewModelScope.coroutineContext.cancelChildren()
            }

            is TrellaResult.Fail -> {
                if (result.exp is NoInterNetConnectionException) {
                    showMessages(result.error, NoInterNetConnectionException())
                    viewModelScope.coroutineContext.cancelChildren()
                }
            }
            is TrellaResult.NothingToRetrieve -> {

            }
        }
        showLoading(false)

    }

    fun showMessages(message: String?, exception: Exception) {
        messages.postValue(Pair(message!!, exception))
    }

    fun showLoading(isShown: Boolean) {
        loading.postValue(isShown)
    }

}
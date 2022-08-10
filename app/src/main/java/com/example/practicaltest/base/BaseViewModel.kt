package com.example.practicaltest.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicaltest.util.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {
    var progressLiveEvent = SingleLiveEvent<Boolean>()
    var errorMessage = SingleLiveEvent<String>()

    fun launchOnUI(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.Main) { block() }
    }

    inline fun <T> launchAsync(
        crossinline execute: suspend () -> Response<T>,
        crossinline onSuccess: (T) -> Unit,
        showProgress: Boolean = true
    ) {
        viewModelScope.launch() {
            if (showProgress)
                progressLiveEvent.value = true
            try {
                val result = execute()
                if (result.isSuccessful)
                    onSuccess(result.body()!!)
                else
                    errorMessage.value = result.message()
            } catch (ex: Exception) {
                errorMessage.value = ex.message
            } finally {
                progressLiveEvent.value = false
            }
        }
    }


}
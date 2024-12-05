package com.degel.android_interview.core.presentation.util

import android.content.Context
import com.degel.android_interview.R
import com.degel.android_interview.core.domain.util.NetworkError


fun NetworkError.toString(context: Context):String{
   val resId =  when(this){
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_requests
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.SERVER_ERROR -> R.string.error_server_error
        NetworkError.SERIALIZATION_ERROR -> R.string.error_serialization
        NetworkError.UNKNOWN -> R.string.error_unknown
    }
    return context.getString(resId)

}
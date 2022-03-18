package com.android.demo.casestudy.network

import okhttp3.ResponseBody

sealed class Resource<out CaseStudyResponse> {
    data class Success<out CaseStudyResponse>(val value: CaseStudyResponse) : Resource<CaseStudyResponse>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}
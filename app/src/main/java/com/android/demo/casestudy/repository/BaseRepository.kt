package com.android.demo.casestudy.repository

import com.android.demo.casestudy.network.BaseApi
import com.android.demo.casestudy.network.SafeApiCall
import javax.inject.Inject

class BaseRepository @Inject
constructor(
    private val api: BaseApi
) : SafeApiCall {

    suspend fun loadCaseStudy(
    ) = safeApiCall {
        api.loadCaseStudy()
    }



}
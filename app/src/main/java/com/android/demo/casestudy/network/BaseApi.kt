package com.android.demo.casestudy.network

import com.android.demo.casestudy.response.CaseStudyResponse
import retrofit2.http.GET

interface BaseApi {
    @GET("v1/caseStudies.json")
    suspend fun loadCaseStudy(): CaseStudyResponse
}
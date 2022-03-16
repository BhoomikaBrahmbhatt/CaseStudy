package com.android.demo.casestudy.response

import com.google.gson.annotations.SerializedName


data class CaseStudyResponse (

    @SerializedName("case_studies" ) var caseStudies : ArrayList<CaseStudies> = arrayListOf()

)

data class CaseStudies (

    @SerializedName("id"            ) var id           : Int?                = null,
    @SerializedName("client"        ) var client       : String?             = null,
    @SerializedName("teaser"        ) var teaser       : String?             = null,
    @SerializedName("title"         ) var title        : String?             = null,
    @SerializedName("hero_image"    ) var heroImage    : String?             = null,

)

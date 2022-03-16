package com.android.demo.casestudy.response

import com.google.gson.annotations.SerializedName


data class CaseStudyResponse (

    @SerializedName("case_studies" ) var caseStudies : ArrayList<CaseStudies> = arrayListOf()

)

data class CaseStudies (

    @SerializedName("id"            ) var id           : Int?                = null,
    @SerializedName("client"        ) var client       : String?             = null,
    @SerializedName("teaser"        ) var teaser       : String?             = null,
    @SerializedName("vertical"      ) var vertical     : String?             = null,
    @SerializedName("is_enterprise" ) var isEnterprise : Boolean?            = null,
    @SerializedName("title"         ) var title        : String?             = null,
    @SerializedName("hero_image"    ) var heroImage    : String?             = null,
    @SerializedName("sections"      ) var sections     : ArrayList<Sections> = arrayListOf()

)


data class Sections (

    @SerializedName("title"         ) var title        : String?           = null,
    @SerializedName("body_elements" ) var bodyElements : ArrayList<String> = arrayListOf()

)


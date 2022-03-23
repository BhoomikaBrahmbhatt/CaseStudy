package com.android.demo.casestudy.data

import com.android.demo.casestudy.response.CaseStudies

object FakeCaseStudy {

    const val FAKE_NETWORK_DELAY = 1000L

    val caseStudy = arrayOf(
        CaseStudies(
            1,
            "TfL",
            "Testing Tube brakes, with TfL Decelerator",
            "Public Sector",
            true,
            "A World-First For Apple iPad",
            "https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/images/decelerator_header-image-2x.jpg",
        ),
        CaseStudies(
            2,
            "Apple",
            "Testing Apple, with Apple Decelerator",
            "Public Sector",
            true,
            "A World-First For Apple iPad",
            "https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/images/decelerator_header-image-2x.jpg",
        ),
        CaseStudies(
            3,
            "Android",
            "Testing Apple, with Apple Decelerator",
            "Public Sector",
            true,
            "A World-First For Apple iPad",
            "https://raw.githubusercontent.com/theappbusiness/engineering-challenge/main/endpoints/v1/images/decelerator_header-image-2x.jpg",
        )
    )
}
package com.emircivelek.redpay.feature.ui.onboarding

import com.emircivelek.redpay.R

sealed class OnBoardingPages(val headerText:Int,val contentText:Int) {
    object FirstOnBoardingScreen:OnBoardingPages(R.string.onboarding_first_header_text,R.string.onboarding_first_content_text)
    object SecondOnBoardingScreen:OnBoardingPages(R.string.onboarding_second_header_text,R.string.onboarding_second_content_text)
    object ThirdOnBoardingScreen:OnBoardingPages(R.string.onboarding_third_header_text,R.string.onboarding_third_content_text)
}
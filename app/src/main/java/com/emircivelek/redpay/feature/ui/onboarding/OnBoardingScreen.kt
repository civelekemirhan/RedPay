package com.emircivelek.redpay.feature.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.emircivelek.redpay.R
import com.emircivelek.redpay.common.components.AuthButton
import com.emircivelek.redpay.common.components.AuthColumn

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavController) {

    val pages = listOf(
        OnBoardingPages.FirstOnBoardingScreen,
        OnBoardingPages.SecondOnBoardingScreen,
        OnBoardingPages.ThirdOnBoardingScreen
    )
    val pagerState = rememberPagerState(
        pageCount = { pages.size },
    )

    AuthColumn {

        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(0.8f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier
                .weight(1f), verticalAlignment = Alignment.CenterVertically) {
                HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { page ->

                    OnBoardingItem(
                        headerText = stringResource(id = pages[page].headerText),
                        contentText = stringResource(id = pages[page].contentText)
                    )

                }
            }
            Row(
                modifier = Modifier.weight(0.1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .background(color)
                            .width(50.dp)
                            .height(3.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier
                    .height(100.dp), horizontalArrangement = Arrangement.Center
            ) {
                if (pagerState.currentPage == pages.size - 1) {
                    AuthButton(onClick = {  }, text = "Devam Et")
                }
            }


            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontSize = 40.sp,
                    letterSpacing = 10.sp,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }

    }


}
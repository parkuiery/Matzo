package com.uiel.matzo.feature.check

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.uiel.matzo.R
import com.uiel.matzo.model.CheckModel
import com.uiel.matzo.ui.theme.pretendard
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun CheckScreen(
    modifier: Modifier = Modifier,
    name: String,
    isSuccess: Boolean,
    onNavigateHome: () -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val lottie = if(isSuccess) R.raw.check_status else R.raw.error_status
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottie))
        val progress by animateLottieCompositionAsState(composition)

        LaunchedEffect(Unit) {
            delay(1000)
            onNavigateHome()
        }

        LottieAnimation(
            composition = composition,
            progress = { progress },
        )
        if(isSuccess) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = name,
                    style = TextStyle(
                        fontFamily = pretendard,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Bold,
                    ),
                    color = Color(0xFF9944EF),
                )
                Text(
                    text = "님 신청되었습니다",
                    style = TextStyle(
                        fontFamily = pretendard,
                        fontSize = 34.sp,
                        fontWeight = FontWeight.SemiBold,
                    ),
                    color = Color.Black,
                )
            }
        }
    }
}
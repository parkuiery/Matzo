package com.uiel.matzo.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uiel.matzo.R
import com.uiel.matzo.feature.home.component.ApplicationListContent
import com.uiel.matzo.feature.home.component.WorkingButton

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateSearch: () -> Unit,
) {
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
        ) {
            Image(
                modifier = Modifier.align(Alignment.CenterStart),
                painter = painterResource(R.drawable.ic_app_text_color),
                contentDescription = null,
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "4월 1일 화요일",
                fontWeight = FontWeight.Bold,
                fontSize = 38.sp,
            )
        }
        Row {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                WorkingButton(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    text = "초과 근무",
                    onClick = onNavigateSearch,
                )
                WorkingButton(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    text = "일반 근무",
                    onClick = { },
                )
                WorkingButton(
                    modifier = Modifier.fillMaxSize().weight(1f),
                    text = "외부인",
                    onClick = { },
                )
            }
            Row (
                modifier = Modifier
                    .weight(3f)
                    .padding(horizontal = 80.dp)
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                ApplicationListContent(
                    modifier = Modifier.weight(1f),
                    title = "초과 근무",
                    applications = listOf("박의엘","바그렐","바그엥","바구링","바구렝")
                )
                ApplicationListContent(
                    modifier = Modifier.weight(1f),
                    title = "일반 근무",
                    applications = listOf("박의엘","바그렐","바그엥","바구링","바구렝")
                )
                ApplicationListContent(
                    modifier = Modifier.weight(1f),
                    title = "외부인",
                    applications = listOf("박의엘","바그렐","바그엥","바구링","바구렝")
                )
            }
        }
    }
}
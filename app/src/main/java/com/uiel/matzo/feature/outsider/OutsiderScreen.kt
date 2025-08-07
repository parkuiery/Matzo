package com.uiel.matzo.feature.outsider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uiel.matzo.MatzoTopAppBar
import com.uiel.matzo.feature.outsider.component.InputContent
import com.uiel.matzo.ui.theme.pretendard
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun OutsiderScreen(
    modifier: Modifier = Modifier,
    viewModel: OutsiderViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val state by viewModel.collectAsState()
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MatzoTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = "외부인 등록",
                onBackClick = onBackClick,
            )
        },
        containerColor = Color(0xFFF7F7F7),
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(
                    horizontal = 36.dp,
                    vertical = 20.dp,
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(30.dp),
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(60.dp, Alignment.CenterVertically),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.4f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                InputContent(
                    title = "⦁ 성함",
                    value = state.name,
                    onValueChange = viewModel::setName,
                )
                InputContent(
                    title = "⦁ 전화번호",
                    value = state.number,
                    onValueChange = viewModel::setNumber,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
                )
                InputContent(
                    title = "⦁ 소속",
                    value = state.department,
                    onValueChange = viewModel::setDepartment,
                )
            }
            Button(
                onClick = viewModel::application,
                enabled = state.isEnabled,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9944EF),
                )
            ) {
                Text(
                    text = "신청하기",
                    style = TextStyle(
                        fontFamily = pretendard,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                )
            }
        }
    }
}

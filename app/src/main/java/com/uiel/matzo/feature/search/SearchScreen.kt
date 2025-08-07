package com.uiel.matzo.feature.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uiel.matzo.MatzoTopAppBar
import com.uiel.matzo.feature.search.component.UserItemButton
import com.uiel.matzo.ui.theme.Gray0
import com.uiel.matzo.ui.theme.Gray20
import com.uiel.matzo.ui.theme.Gray30
import com.uiel.matzo.ui.theme.Gray40
import com.uiel.matzo.ui.theme.Gray50
import com.uiel.matzo.ui.theme.Gray70
import com.uiel.matzo.ui.theme.Gray90
import com.uiel.matzo.ui.theme.pretendard
import com.uiel.matzo.util.formatKoreanDate
import com.uiel.matzo.util.toIcon
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import java.time.LocalDate

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onNavigateToCheck: (String, Boolean) -> Unit,
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            is SearchSideEffect.ApplicationSuccess -> onNavigateToCheck(it.name, true)
            SearchSideEffect.ApplicationFail -> onNavigateToCheck("", false)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MatzoTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = "이름 검색",
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        Text(
                            text = formatKoreanDate(LocalDate.now()),
                            style = TextStyle(
                                fontFamily = pretendard,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 26.sp,
                            ),
                            color = Color(0xFF9944EF),
                        )
                        Image(
                            painter = painterResource(state.mealType.toIcon()),
                            contentDescription = null,
                        )
                    }
                },
                onBackClick = onBackClick,
            )
        },
        containerColor = Color(0xFFF7F7F7),
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(
                    horizontal = 36.dp,
                    vertical = 20.dp,
                )
                .background(color = Color.White, shape = RoundedCornerShape(28.dp))
                .padding(horizontal = 34.dp, vertical = 26.dp),
        ) {
            val initials =
                listOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
            var selectedInitial by remember { mutableStateOf<String?>(null) }

            val filteredUsers = remember(selectedInitial, state.users) {
                if (selectedInitial == null) {
                    state.users
                } else {
                    state.users.filter { getInitial(it.name) == selectedInitial }
                }
            }

            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(4),
            ) {
                items(initials) { initial ->
                    val (buttonColor, textColor) = if (selectedInitial == initial) Color(0xFF9944EF) to
                            Color.White else Color(0xFF9944EF).copy(alpha = 0.08f) to Color.Black
                    Button(
                        onClick = { selectedInitial = initial },
                        modifier = Modifier.padding(4.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = buttonColor,
                        )
                    ) {
                        Text(
                            text = initial,
                            style = TextStyle(
                                fontFamily = pretendard,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 20.sp,
                            ),
                            color = textColor,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(
                    horizontal = 12.dp, vertical = 8.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(filteredUsers) { user ->
                    UserItemButton(
                        name = user.name,
                        department = user.departmentName,
                        onClick = { viewModel.application(user) }
                    )
//                    Column(
//                        modifier = Modifier
//                            .clip(RoundedCornerShape(8.dp))
//                            .clickable { viewModel.application(user) }
//                            .background(color = Gray20, shape = RoundedCornerShape(8.dp))
//                            .padding(horizontal = 12.dp, vertical = 8.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Center,
//                    ) {
//                        Text(
//                            text = user.name,
//                            style = MaterialTheme.typography.headlineLarge,
//                            fontWeight = FontWeight.SemiBold,
//                            textAlign = TextAlign.Center,
//                            color = Gray90,
//                        )
//                        Text(
//                            text = user.departmentName,
//                            color = Gray70,
//                            fontWeight = FontWeight.SemiBold,
//                        )
//                    }
                }
            }
        }
    }
}

fun getInitial(name: String): String {
    val firstChar = name.firstOrNull() ?: return ""
    return when (firstChar) {
        in '가'..'깋' -> "ㄱ"
        in '나'..'닣' -> "ㄴ"
        in '다'..'딯' -> "ㄷ"
        in '라'..'맇' -> "ㄹ"
        in '마'..'밓' -> "ㅁ"
        in '바'..'빟' -> "ㅂ"
        in '사'..'싷' -> "ㅅ"
        in '아'..'잏' -> "ㅇ"
        in '자'..'짛' -> "ㅈ"
        in '차'..'칳' -> "ㅊ"
        in '카'..'킿' -> "ㅋ"
        in '타'..'팋' -> "ㅌ"
        in '파'..'핗' -> "ㅍ"
        in '하'..'힣' -> "ㅎ"
        else -> ""
    }
}

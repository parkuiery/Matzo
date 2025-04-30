package com.uiel.matzo.feature.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uiel.matzo.MatzoTopAppBar

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onNavigateToCheck: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            MatzoTopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = "이름 검색",
                onBackClick = onBackClick,
            )
        },
        containerColor = Color.DarkGray,
    ) { paddingValues ->
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(
                    horizontal = 48.dp,
                    vertical = 24.dp,
                )
                .background(Color.Gray)
                .padding(12.dp),
        ) {
            val names = listOf(
                "김철수", "박영희", "이민수", "최다은", "정하늘",
                "한지우", "오예진", "윤도윤", "장하은", "임수현",
                "강민준", "조예슬", "서연우", "신하림", "권채은",
                "황준영", "안나현", "송태훈", "류성민", "홍은지",
                "이현우", "박지민", "김하윤", "최서윤", "정도현",
                "한예림", "오지훈", "윤슬기", "장태현", "임은채",
                "강도현", "조하진", "서민재", "신유정", "권지후",
                "황하율", "안예준", "송민하", "류도현", "홍예빈",
                "김윤서", "박준호", "이슬아", "최하민", "정세린",
                "한지훈", "오채연", "윤하늘", "장유리", "임시우"
            )
            val initials = listOf("ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ")
            var selectedInitial by remember { mutableStateOf<String?>(null) }

            val filteredNames = remember(selectedInitial) {
                if (selectedInitial == null) {
                    names
                } else {
                    names.filter { getInitial(it) == selectedInitial }
                }
            }

            LazyVerticalGrid(
                modifier = Modifier.weight(1f),
                columns = GridCells.Fixed(4),
            ) {
                items(initials) { initial ->
                    Button(
                        onClick = { selectedInitial = initial },
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Text(initial)
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
                items(filteredNames) { name ->
                    Text(
                        modifier = Modifier
                            .clickable { onNavigateToCheck() }
                            .background(Color.White)
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        text = name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                    )
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

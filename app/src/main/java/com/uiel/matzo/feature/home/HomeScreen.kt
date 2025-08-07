package com.uiel.matzo.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uiel.matzo.R
import com.uiel.matzo.dropShadow
import com.uiel.matzo.feature.home.component.ApplicationListContent
import com.uiel.matzo.feature.home.component.MealDropDownContent
import com.uiel.matzo.feature.home.component.WorkingButton
import com.uiel.matzo.model.MealType
import com.uiel.matzo.model.WorkingType
import com.uiel.matzo.ui.theme.Gray0
import com.uiel.matzo.ui.theme.Gray10
import com.uiel.matzo.ui.theme.Gray20
import com.uiel.matzo.ui.theme.Gray30
import com.uiel.matzo.ui.theme.pretendard
import com.uiel.matzo.util.formatKoreanDate
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onNavigateSearch: (WorkingType, MealType) -> Unit,
    onNavigateSetting: () -> Unit,
    onNavigateOutsider: (MealType) -> Unit,
) {
    val state by viewModel.collectAsState()
    Row(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .dropShadow(
                    shape = RectangleShape,
                    color = Color(0xFF000000).copy(0.08f),
                    blur = 10.dp,
                    offsetX = 4.dp,
                    offsetY = 0.dp,
                    spread = 0.dp,
                )
                .background(color = Color.White)
                .padding(vertical = 40.dp, horizontal = 52.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_app),
                contentDescription = null,
            )
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Column {
                    Text(
                        text = "근무 유형 선택",
                        style = TextStyle(
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                        ),
                        color = Color.Black,
                    )
                    Text(
                        text = "외부인의 경우엔 정보 입력이 필요합니다",
                        style = TextStyle(
                            fontFamily = pretendard,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,
                        ),
                        color = Color(0xFF787878),
                    )
                }
                WorkingButton(
                    text = "초과 근무",
                    color = Color(0xFF1C7CB7),
                    onClick = { onNavigateSearch(WorkingType.OVER, state.selectMealType) },
                )
                WorkingButton(
                    text = "일반 근무",
                    color = Color(0xFFDB8122),
                    onClick = { onNavigateSearch(WorkingType.REGULAR, state.selectMealType) },
                )
                WorkingButton(
                    text = "외부인",
                    color = Color(0xFFF06264),
                    onClick = { onNavigateOutsider(state.selectMealType) },
                )
            }
        }
        Column(
            modifier = Modifier
                .weight(3f),
            //horizontalArrangement = Arrangement.spacedBy(40.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(38.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = formatKoreanDate(state.date),
                    style = TextStyle(
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 26.sp,
                    ),
                    color = Color(0xFF9944EF),
                )
                MealDropDownContent(
                    selectMealType = state.selectMealType,
                    setSelectMealType = viewModel::setMealType,
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = onNavigateSetting,
                ) {
                    Icon(
                        painter = painterResource(R.drawable.round_menu_24),
                        contentDescription = null,
                        tint = Color(0xFF606060),
                    )
                }
            }
            ApplicationListContent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp, horizontal = 38.dp)
                    .border(width = 1.dp, color = Color(0xFFEAEAEA), shape = RoundedCornerShape(40.dp)),
                applications = state.applications.filter { it.mealType == state.selectMealType },
                onDeleteApplication = viewModel::deleteApplication,
            )
        }
    }
}

package com.uiel.matzo.feature.setting.component.history

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uiel.matzo.R
import com.uiel.matzo.database.entity.ApplicationEntity
import com.uiel.matzo.ui.theme.Gray20
import com.uiel.matzo.ui.theme.Gray50
import com.uiel.matzo.util.toKorean
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HistoryContent(
    modifier: Modifier = Modifier,
    viewModel: HistoryViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()
    val context = LocalContext.current
    var showDatePicker by remember { mutableStateOf(false) }

    if(showDatePicker) {
        DateRangePickerModal(
            onDateRangeSelected = viewModel::setSelectDateRange,
            onDismiss = { showDatePicker = false }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.exportApplicationsToCsv(context = context) }
            ) {
                Icon(
                    painter = painterResource(R.drawable.rounded_download_24),
                    contentDescription = null,
                )
            }
        },
        containerColor = Color.White,
    ) { paddingValues ->
       Column(
           modifier = modifier
               .fillMaxSize()
               .padding(paddingValues)
       ) {
           Button(
               onClick = { showDatePicker = true },
               colors = ButtonDefaults.buttonColors(
                   containerColor = Color(0xFF9944EF),
               )
           ) {
               Text(
                   text = if(state.endDay == null) "전체" else "${state.startDay} ~ ${state.endDay}"
               )
           }
           Spacer(modifier = Modifier.height(20.dp))
           HistoryGraph(
               applications = state.applications
           )
       }
    }
}

@Composable
private fun HistoryGraph(
    applications: List<ApplicationEntity>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, shape = RoundedCornerShape(0.dp), color = Gray50),
    ) {
        val titles = listOf("날짜", "신청 시간", "식사", "근무 형태", "이름", "전화번호", "부서")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Gray20),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(0.5f),
                text = "Id",
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
            )
            titles.forEach {
                Text(
                    modifier = Modifier.weight(1f),
                    text = it,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                )
            }
        }
        HorizontalDivider()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
        ) {
            items(applications) {
                HistoryItem(applicationEntity = it)
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun HistoryItem(
    modifier: Modifier = Modifier,
    applicationEntity: ApplicationEntity,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HistoryItemText(text = applicationEntity.id.toString(), modifier = Modifier.weight(0.5f))
        HistoryItemText(text = applicationEntity.date.toString(), modifier = Modifier.weight(1f))
        HistoryItemText(
            text = applicationEntity.applicationTime.toString().substring(0, 5),
            modifier = Modifier.weight(1f)
        )
        HistoryItemText(
            text = applicationEntity.mealType.toKorean(),
            modifier = Modifier.weight(1f)
        )
        HistoryItemText(
            text = applicationEntity.workingType.toKorean(),
            modifier = Modifier.weight(1f)
        )
        HistoryItemText(text = applicationEntity.user.name, modifier = Modifier.weight(1f))
        HistoryItemText(
            text = applicationEntity.user.phoneNumber ?: "-",
            modifier = Modifier.weight(1f)
        )
        HistoryItemText(
            text = applicationEntity.user.departmentName,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun HistoryItemText(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center,
    )
}
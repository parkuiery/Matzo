package com.uiel.matzo.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uiel.matzo.database.entity.ApplicationEntity
import com.uiel.matzo.database.entity.UserEntity
import com.uiel.matzo.model.WorkingType
import com.uiel.matzo.ui.theme.pretendard
import com.uiel.matzo.util.toKorean
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun ApplicationListContent(
    modifier: Modifier = Modifier,
    applications: List<ApplicationEntity>,
    onDeleteApplication: (Long) -> Unit,
) {
    var dialogTargetId by remember { mutableStateOf<Long?>(null) }

    if (dialogTargetId != null) {
        AlertDialog(
            onDismissRequest = {
                dialogTargetId = null
            },
            title = { Text(text = "정말 신청을 취소하시겠습니까?") },
            confirmButton = {
                TextButton(onClick = {
                    onDeleteApplication(dialogTargetId!!)
                    dialogTargetId = null
                }) {
                    Text(text = "확인")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogTargetId = null
                }) {
                    Text(text = "취소")
                }
            },
        )
    }

    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (applications.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "아직 아무도 신청하지 않았어요. 첫 번째로 신청해보세요!",
                    style = TextStyle(
                        fontFamily = pretendard,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                    ),
                    color = Color.Gray,
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.padding(top = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(applications) { application ->
                    ApplicationItem(
                        user = application.user,
                        workingType = application.workingType,
                        applicationTime = application.applicationTime,
                        onCancel = {
                            dialogTargetId = application.id
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun ApplicationItem(
    modifier: Modifier = Modifier,
    user: UserEntity,
    workingType: WorkingType,
    applicationTime: LocalTime,
    onCancel: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 40.dp,
                vertical = 24.dp,
            ),
        //horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val formattedTime = applicationTime.format(DateTimeFormatter.ofPattern("HH:mm"))
        Text(
            modifier = Modifier.weight(1f),
            text = user.name,
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            ),
            color = Color(0xFF9944EF),
        )
        Text(
            modifier = Modifier.weight(1f),
            text = user.departmentName,
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            ),
            color = Color.Black,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = formattedTime,
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            ),
            color = Color.Black,
        )
        Text(
            modifier = Modifier.weight(1f),
            text = workingType.toKorean(),
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            ),
            color = Color.Black,
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.weight(1f),
            onClick = onCancel,
            shape = RoundedCornerShape(4.dp),
            contentPadding = PaddingValues(horizontal = 28.dp, vertical = 6.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF46254),
            )
        ) {
            Text(
                text = "취소",
                style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                ),
                color = Color.White,
            )
        }
    }
}
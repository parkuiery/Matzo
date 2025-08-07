package com.uiel.matzo.feature.setting.component.user

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uiel.matzo.ui.theme.pretendard

@Composable
internal fun UserRegisterContent(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit,
    onSave: (name: String, department: String) -> Unit,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
    ) {
        var name by remember { mutableStateOf("") }
        var department by remember { mutableStateOf("") }
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "선생님 등록",
                style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                ),
                color = Color(0xFF9944EF),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "이름",
                style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                )
            )
            TextField(
                value = name,
                onValueChange = { name = it },
                placeholder = {
                    Text(
                        text = "이름을 입력해주세요",
                        style = TextStyle(
                            fontFamily = pretendard,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                        ),
                        color = Color.Gray,
                    )
                },
                textStyle = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Black,
                ),
            )
            Text(
                text = "부서",
                style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                )
            )
            TextField(
                value = department,
                onValueChange = { department = it },
                placeholder = {
                    Text(
                        text = "부서를 입력해주세요",
                        style = TextStyle(
                            fontFamily = pretendard,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp,
                        ),
                        color = Color.Gray,
                    )
                },
                textStyle = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp,
                    color = Color.Black,
                ),
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                OutlinedButton(
                    onClick = onCancel,
                ) {
                    Text(text = "취소")
                }
                Button(
                    onClick = { onSave(name, department) },
                    enabled = name.isNotEmpty() && department.isNotEmpty(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF9944EF),
                    )
                ) {
                    Text(
                        text = "저장"
                    )
                }
            }
        }
    }
}
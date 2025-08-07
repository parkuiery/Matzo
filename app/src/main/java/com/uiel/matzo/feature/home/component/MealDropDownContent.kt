package com.uiel.matzo.feature.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uiel.matzo.R
import com.uiel.matzo.model.MealType
import com.uiel.matzo.ui.theme.pretendard
import com.uiel.matzo.util.toIcon
import com.uiel.matzo.util.toKorean

@Composable
fun MealDropDownContent(
    modifier: Modifier = Modifier,
    selectMealType: MealType,
    setSelectMealType: (MealType) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(selectMealType.toIcon()),
                contentDescription = null,
            )
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    modifier = Modifier
                        .size(36.dp)
                        .rotate(if (expanded) 180f else 0f),
                    tint = Color.LightGray,
                )
            }
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            shadowElevation = 4.dp,
            containerColor = Color.White,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(width = 1.dp, color = Color(0xFFEAEAEA))
        ) {
            MealType.entries.forEachIndexed { index, mealType ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = mealType.toKorean(),
                            style = TextStyle(
                                fontFamily = pretendard,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 14.sp,
                            ),
                            color = Color.Black,
                        )
                    },
                    onClick = {
                        setSelectMealType(mealType)
                        expanded = false
                    }
                )
                if (index < MealType.entries.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 10.dp),
                        color = Color(0xFFEAEAEA)
                    )
                }
            }
        }
    }
}

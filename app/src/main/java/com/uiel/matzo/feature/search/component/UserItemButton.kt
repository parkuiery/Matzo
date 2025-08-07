package com.uiel.matzo.feature.search.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uiel.matzo.dropShadow
import com.uiel.matzo.ui.theme.pretendard

@Composable
internal fun UserItemButton(
    modifier: Modifier = Modifier,
    name: String,
    department: String,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    val (backgroundColor, borderColor) = if (isPressed.value) Color(0xFFF7F0FE) to
            Color(0xFF9944EF) else Color.White to Color(0xFF9944EF).copy(alpha = 0.08f)

    Box(
        modifier = modifier
            .fillMaxWidth()
            .dropShadow(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF000000).copy(alpha = 0.06f),
                blur = 5.4.dp,
                offsetX = 0.dp,
                offsetY = 3.6.dp,
                spread = 0.dp,
            )
            .clip(RoundedCornerShape(12.dp))
            .background(color = borderColor, shape = RoundedCornerShape(12.dp))
            .padding(top = 3.6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 9.dp,
                        topEnd = 9.dp,
                        bottomStart = 12.dp,
                        bottomEnd = 12.dp
                    )
                )
                .background(color = backgroundColor)
                .clickable(
                    interactionSource = interactionSource,
                    indication = ripple(
                        color = backgroundColor,
                    ),
                    onClick = onClick,
                )
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = name,
                color = Color.Black,
                style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                )
            )
            Text(
                text = department,
                color = Color(0xFF707070),
                style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                )
            )
        }
    }
}
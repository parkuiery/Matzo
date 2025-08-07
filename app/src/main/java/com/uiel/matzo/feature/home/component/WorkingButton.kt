package com.uiel.matzo.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
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
fun WorkingButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color,
    onClick: () -> Unit,
) {
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
            .background(color = color, shape = RoundedCornerShape(12.dp))
            .padding(top = 3.6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
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
                .background(color = Color.White)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple(
                        color = color.copy(alpha = 0.08f),
                    ),
                    onClick = onClick,
                )
                .padding(vertical = 38.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = text,
                color = color,
                style = TextStyle(
                    fontFamily = pretendard,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                )
            )
        }
    }
}
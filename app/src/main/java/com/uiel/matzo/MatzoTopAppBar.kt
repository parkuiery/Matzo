package com.uiel.matzo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MatzoTopAppBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .background(color = Color(0xFF8977AD))
            .padding(
                horizontal = 24.dp,
                vertical = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp,
        )
        Image(
            painter = painterResource(R.drawable.app_text_icon),
            contentDescription = null,
        )
        IconButton(
            onClick = onBackClick,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = Color.White,
            )
        }
    }
}
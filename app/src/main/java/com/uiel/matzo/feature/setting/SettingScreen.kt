package com.uiel.matzo.feature.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.uiel.matzo.MatzoTopAppBar
import com.uiel.matzo.feature.setting.component.SettingDetailContent
import com.uiel.matzo.feature.setting.component.SettingItem
import com.uiel.matzo.feature.setting.component.SettingListContent

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    var selectSettingItem: SettingItem? by remember { mutableStateOf(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        MatzoTopAppBar(
            modifier = Modifier.fillMaxWidth(),
            title = "설정",
            onBackClick = onBackClick,
        )
        Row {
            SettingListContent(
                modifier = Modifier.weight(1f),
                onSelectItem = { selectSettingItem = it }
            )
            SettingDetailContent(
                modifier = Modifier.weight(2f),
                settingItem = selectSettingItem
            )
        }
    }
}

package com.uiel.matzo.feature.setting.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uiel.matzo.feature.setting.component.history.HistoryContent
import com.uiel.matzo.feature.setting.component.user.UserManagementContent

@Composable
internal fun SettingDetailContent(
    modifier: Modifier = Modifier,
    settingItem: SettingItem?,
) {
    Column(modifier = modifier.padding(16.dp)) {
        when (settingItem) {
            is SettingItem.User -> UserManagementContent()
            is SettingItem.History -> HistoryContent()
            else -> { }
        }
    }
}
package com.uiel.matzo.feature.setting.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uiel.matzo.R

@Composable
internal fun SettingListContent(
    modifier: Modifier = Modifier,
    onSelectItem: (SettingItem) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(SettingItem.all) {
            SettingItemContent(
                settingItem = it,
                onClick = onSelectItem,
            )
        }
    }
}

sealed class SettingItem(
    @DrawableRes val icon: Int,
    val title: String
) {
    data object User : SettingItem(R.drawable.ic_person, "사용자 관리")
    data object History : SettingItem(R.drawable.ic_calendar, "기록보기")

    companion object {
        val all = listOf(User, History)
        val none = SettingItem
    }
}

@Composable
private fun SettingItemContent(
    modifier: Modifier = Modifier,
    settingItem: SettingItem,
    onClick: (SettingItem) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(settingItem) }
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Image(
            painter = painterResource(id = settingItem.icon),
            contentDescription = null,
        )
        Text(
            text = settingItem.title,
        )
    }
}
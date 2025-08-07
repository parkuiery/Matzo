package com.uiel.matzo.feature.setting.component.user

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.uiel.matzo.R
import com.uiel.matzo.database.entity.UserEntity
import com.uiel.matzo.feature.setting.UserManagementSideEffect
import com.uiel.matzo.feature.setting.UserManagementViewModel
import com.uiel.matzo.ui.theme.pretendard
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun UserManagementContent(
    modifier: Modifier = Modifier,
    viewModel: UserManagementViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()
    val context = LocalContext.current
    var dialogTargetId by remember { mutableStateOf<Long?>(null) }
    var editTargetUser by remember { mutableStateOf<UserEntity?>(null) }

    viewModel.collectSideEffect {
        when (it) {
            is UserManagementSideEffect.UserRegisterSuccess -> {
                Toast.makeText(
                    context,
                    "사용자가 등록되었습니다",
                    Toast.LENGTH_SHORT,
                ).show()
            }

            is UserManagementSideEffect.UserRegisterFail -> {
                Toast.makeText(
                    context,
                    "사용자를 등록하지 못했어요",
                    Toast.LENGTH_SHORT,
                ).show()
            }

            is UserManagementSideEffect.UserEditSuccess -> {
                Toast.makeText(
                    context,"사용자 정보가 수정되었습니다",Toast.LENGTH_SHORT
                ).show()
            }

            is UserManagementSideEffect.UserEditFail -> {
                Toast.makeText(
                    context,"사용자 정보를 수정하지 못했어요",Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    if (dialogTargetId != null) {
        AlertDialog(
            onDismissRequest = {
                dialogTargetId = null
            },
            title = { Text(text = "정말 사용자를 삭제하시겠습니까?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteUser(dialogTargetId!!)
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

    if (editTargetUser != null) {
        Dialog(
            onDismissRequest = { editTargetUser = null }
        ) {
            UserEditContent(
                user = editTargetUser!!,
                onCancel = { editTargetUser = null },
                onEdit = { updatedUser ->
                    viewModel.editUser(updatedUser)
                    editTargetUser = null
                }
            )
        }
    }

    if (state.showRegisterDialog) {
        Dialog(
            onDismissRequest = viewModel::hideUserRegisterDialog,
        ) {
            UserRegisterContent(
                onCancel = viewModel::hideUserRegisterDialog,
                onSave = viewModel::registerUser,
            )
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            IconButton(
                modifier = Modifier.size(64.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color(0xFF9944EF),
                ),
                onClick = viewModel::showUserRegisterDialog
            ) {
                Icon(
                    painter = painterResource(R.drawable.round_person_add_24),
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        },
        containerColor = Color.White,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 36.dp, vertical = 16.dp)
                .border(width = 2.dp, color = Color(0xFFEAEAEA), shape = RoundedCornerShape(40.dp))
                .padding(horizontal = 16.dp, vertical = 20.dp),
        ) {
            LazyColumn {
                items(state.users) {
                    UserItem(
                        name = it.name,
                        department = it.departmentName,
                        onEditClick = { editTargetUser = it },
                        onDeleteClick = { dialogTargetId = it.id },
                    )
                }
            }
        }
    }
}

@Composable
private fun UserItem(
    modifier: Modifier = Modifier,
    name: String,
    department: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = name,
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            ),
            color = Color(0xFF9944EF),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.weight(1f),
            text = department,
            style = TextStyle(
                fontFamily = pretendard,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
            ),
            color = Color.Black,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(3f))
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = onEditClick,
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_edit_square_24),
                contentDescription = null,
                tint = Color(0xFF3180F3),
            )
        }
        IconButton(
            modifier = Modifier.weight(1f),
            onClick = onDeleteClick,
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_delete_24),
                contentDescription = null,
                tint = Color(0xFFF46254),
            )
        }
    }
}
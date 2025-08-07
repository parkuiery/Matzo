package com.uiel.matzo.feature.setting

import androidx.lifecycle.ViewModel
import com.uiel.matzo.database.dao.UserDao
import com.uiel.matzo.database.entity.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class UserManagementViewModel @Inject constructor(
    private val userDao: UserDao,
) : ContainerHost<UserManagementUiState, UserManagementSideEffect>, ViewModel() {

    override val container: Container<UserManagementUiState, UserManagementSideEffect> = container(
        UserManagementUiState()
    )

    init {
        fetchUsers()
    }

    private fun fetchUsers() = intent {
        val users = userDao.fetchAll()
        reduce { state.copy(users = users) }
    }

    internal fun registerUser(name: String, departmentName: String) = intent {
        val registerUserEntity = UserEntity(
            name = name,
            departmentName = departmentName,
            phoneNumber = null,
        )
        runCatching {
            userDao.insert(userEntity = registerUserEntity)
        }.onSuccess {
            postSideEffect(UserManagementSideEffect.UserRegisterSuccess)
            reduce { state.copy(users = state.users.plus(registerUserEntity)) }
            hideUserRegisterDialog()
        }.onFailure {
            postSideEffect(UserManagementSideEffect.UserRegisterFail)
        }
    }

    internal fun editUser(userEntity: UserEntity) = intent {
        kotlin.runCatching {
            userDao.update(userEntity = userEntity)
        }.onSuccess {
            val updatedUsers = state.users.map {
                if (it.id == userEntity.id) userEntity else it
            }
            reduce { state.copy(users = updatedUsers) }
            postSideEffect(UserManagementSideEffect.UserEditSuccess)
        }.onFailure {
            postSideEffect(UserManagementSideEffect.UserEditFail)
        }
    }

    internal fun hideUserRegisterDialog() = intent {
        reduce { state.copy(showRegisterDialog = false) }
    }

    internal fun showUserRegisterDialog() = intent {
        reduce { state.copy(showRegisterDialog = true) }
    }

    internal fun deleteUser(id: Long) = intent {
        kotlin.runCatching {
            userDao.deleteById(id)
        }.onSuccess {
            val userEntity = state.users.first { id == it.id }
            reduce { state.copy(users = state.users.minus(userEntity)) }
        }
    }
}

data class UserManagementUiState(
    val showRegisterDialog: Boolean = false,
    val users: List<UserEntity> = emptyList(),
)

sealed class UserManagementSideEffect {
    data object UserRegisterSuccess : UserManagementSideEffect()
    data object UserRegisterFail : UserManagementSideEffect()
    data object UserEditSuccess : UserManagementSideEffect()
    data object UserEditFail : UserManagementSideEffect()
}
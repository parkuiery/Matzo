package com.uiel.matzo.feature.outsider

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.uiel.matzo.database.dao.ApplicationDao
import com.uiel.matzo.database.entity.ApplicationEntity
import com.uiel.matzo.database.entity.UserEntity
import com.uiel.matzo.model.MealType
import com.uiel.matzo.model.WorkingType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class OutsiderViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val applicationDao: ApplicationDao,
) : ContainerHost<OutsiderUiState, OutsiderSideEffect>, ViewModel() {

    private val route = savedStateHandle.toRoute<OutsiderRoute>()
    override val container: Container<OutsiderUiState, OutsiderSideEffect> =
        container(OutsiderUiState())

    internal fun setName(name: String) = intent {
        reduce { state.copy(name = name) }
        setIsEnabled()
    }

    internal fun setNumber(number: String) = intent {
        reduce { state.copy(number = number) }
        setIsEnabled()
    }

    internal fun setDepartment(department: String) = intent {
        reduce { state.copy(department = department) }
        setIsEnabled()
    }

    private fun setIsEnabled() = intent {
        val isNameFilled = state.name.isNotEmpty()
        val isNumberFilled = state.number.isNotEmpty()
        val isDepartmentFilled = state.department.isNotEmpty()

        val isEnabled = isNameFilled && isNumberFilled && isDepartmentFilled
        reduce { state.copy(isEnabled = isEnabled) }
    }

    internal fun application() = intent {
        runCatching {
            applicationDao.insert(
                applicationEntity = ApplicationEntity(
                    date = LocalDate.now(),
                    applicationTime = LocalTime.now(),
                    mealType = MealType.DINNER,
                    workingType = WorkingType.OUTSIDER,
                    user = UserEntity(
                        name = state.name,
                        departmentName = state.department,
                        phoneNumber = state.number,
                    )
                )
            )
        }
    }
}

data class OutsiderUiState(
    val name: String = "",
    val number: String = "",
    val department: String = "",
    val isEnabled: Boolean = false,
)

sealed class OutsiderSideEffect
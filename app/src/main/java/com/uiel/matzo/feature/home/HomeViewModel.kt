package com.uiel.matzo.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.uiel.matzo.database.dao.ApplicationDao
import com.uiel.matzo.database.entity.ApplicationEntity
import com.uiel.matzo.model.MealType
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val applicationDao: ApplicationDao,
) : ContainerHost<HomeUiState, HomeSideEffect>, ViewModel() {

    override val container: Container<HomeUiState, HomeSideEffect> = container(HomeUiState())

    init {
        fetchApplications()
        initMealType()
    }

    private fun fetchApplications() = intent {
        val application = applicationDao.findByDate(date = state.date)
        Log.d("TEST",application.toString())
        reduce { state.copy(applications = application ?: emptyList()) }
    }

    private fun initMealType() = intent {
        val now = LocalTime.now()
        val mealType =  when {
            now.isBefore(LocalTime.of(10, 0)) -> MealType.BREAKFAST
            now.isBefore(LocalTime.of(15, 0)) -> MealType.LUNCH
            else -> MealType.DINNER
        }
        reduce { state.copy(selectMealType = mealType) }
    }

    internal fun setMealType(mealType: MealType) = intent {
        reduce { state.copy(selectMealType = mealType) }
    }

    internal fun deleteApplication(id: Long) = intent {
        runCatching {
            applicationDao.deleteById(id)
        }.onSuccess {
            val application = state.applications.find { it.id == id }
            application?.let {
                reduce { state.copy(applications = state.applications.minus(it)) }
            }
        }
    }
}

data class HomeUiState(
    val date: LocalDate = LocalDate.now(),
    val applications: List<ApplicationEntity> = emptyList(),
    val selectMealType: MealType = MealType.LUNCH,
)

sealed class HomeSideEffect {
}
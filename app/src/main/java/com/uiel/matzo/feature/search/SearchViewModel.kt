package com.uiel.matzo.feature.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.uiel.matzo.database.dao.ApplicationDao
import com.uiel.matzo.database.dao.UserDao
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
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val userDao: UserDao,
    private val applicationDao: ApplicationDao,
) : ContainerHost<SearchUiState, SearchSideEffect>, ViewModel() {

    private val route = savedStateHandle.toRoute<SearchRoute>()
    override val container: Container<SearchUiState, SearchSideEffect> = container(SearchUiState(
        mealType = route.mealType,
    ))

    init {
        fetchUsers()
    }

    private fun fetchUsers() = intent {
        kotlin.runCatching {
            userDao.fetchAll()
        }.onSuccess {
            reduce { state.copy(users = it) }
        }
    }

    internal fun application(userEntity: UserEntity) = intent {
        kotlin.runCatching {
            applicationDao.insert(
                applicationEntity = ApplicationEntity(
                    date = LocalDate.now(),
                    applicationTime = LocalTime.now(),
                    mealType = route.mealType,
                    workingType = route.workingType,
                    user = userEntity,
                )
            )
        }.onSuccess {
            postSideEffect(SearchSideEffect.ApplicationSuccess(userEntity.name))
        }.onFailure {
            postSideEffect(SearchSideEffect.ApplicationFail)
        }
    }
}

data class SearchUiState(
    val users: List<UserEntity> = emptyList(),
    val mealType: MealType = MealType.LUNCH,
)

sealed class SearchSideEffect {
    data class ApplicationSuccess(val name: String): SearchSideEffect()
    data object ApplicationFail: SearchSideEffect()
}
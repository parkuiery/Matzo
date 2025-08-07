package com.uiel.matzo.feature.setting.component.history

import android.content.Context
import android.util.Log
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import com.uiel.matzo.database.dao.ApplicationDao
import com.uiel.matzo.database.entity.ApplicationEntity
import com.uiel.matzo.util.toKorean
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val applicationDao: ApplicationDao,
) : ContainerHost<HistoryUiState, HistorySideEffect>, ViewModel() {

    override val container: Container<HistoryUiState, HistorySideEffect> =
        container(HistoryUiState())

    init {
        fetchApplications()
    }

    internal fun setSelectDateRange(
        selectDateRange: Pair<Long?, Long?>
    ) = intent {
        val (startMillis, endMillis) = selectDateRange

        val startDate = startMillis?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
        } ?: LocalDate.now()

        val endDate = endMillis?.let {
            Instant.ofEpochMilli(it).atZone(ZoneId.systemDefault()).toLocalDate()
        } ?: LocalDate.now()

        reduce {
            state.copy(
                startDay = startDate,
                endDay = endDate
            )
        }
        fetchRangeApplication()
    }

    internal fun exportApplicationsToCsv(context: Context) = intent {
        runCatching {
            val applications = applicationDao.fetchAll()
            val csv = applications.toCsvString()
            val fileName = if (state.startDay == null) {
                "전체 급식 명단"
            } else {
                "${state.startDay} ~ ${state.endDay} 급식 명단"
            }
            saveCsvToFile(context, "${fileName}.csv", csv)
        }.onSuccess { sharedFile ->
            shareFile(context, sharedFile)
        }.onFailure {
            Log.d("TEST", it.toString())
        }
    }

    private fun saveCsvToFile(context: Context, fileName: String, content: String): File {
        val file = File(context.filesDir, fileName)
        file.writeText(content)
        return file
    }

    private fun List<ApplicationEntity>.toCsvString(): String {
        val header = "ID,날짜,신청 시간,식사,근무 형태,이름,전화번호,부서"
        val rows = this.joinToString("\n") { entity ->
            val userName = entity.user.name.replace("\"", "\"\"") // 쉼표나 따옴표 처리
            val phoneNumber = entity.user.phoneNumber?.replace("\"", "\"\"")
            val department = entity.user.departmentName.replace("\"", "\"\"")
            "${entity.id},${entity.date},${
                entity.applicationTime.toString().substring(0, 5)
            },${entity.mealType.toKorean()},${entity.workingType.toKorean()},\"$userName\",\"$phoneNumber\",\"$department\""
        }
        return "$header\n$rows"
    }

    private fun shareFile(context: Context, file: File) = intent {
        kotlin.runCatching {
            val uri =
                FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/csv"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            val chooser = Intent.createChooser(shareIntent, "CSV 파일 공유하기")
            chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(chooser)
        }.onFailure {
            Log.d("HistoryViewModel", it.toString())
        }
    }

    private fun fetchApplications() = intent {
        runCatching {
            applicationDao.fetchAll()
        }.onSuccess {
            reduce { state.copy(applications = it) }
        }
    }

    private fun fetchRangeApplication() = intent {
        kotlin.runCatching {
            applicationDao.findByDateRange(startDate = state.startDay!!, endDate = state.endDay!!)
        }.onSuccess {
            reduce { state.copy(applications = it) }
        }
    }
}

data class HistoryUiState(
    val applications: List<ApplicationEntity> = emptyList(),
    val startDay: LocalDate? = null,
    val endDay: LocalDate? = null,
)

sealed class HistorySideEffect
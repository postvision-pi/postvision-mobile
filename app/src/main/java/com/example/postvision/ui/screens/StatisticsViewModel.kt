package com.example.postvision.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.postvision.ui.components.DayPerformance
import com.example.postvision.ui.components.SquatRecord
import com.example.postvision.ui.model.ExerciseReport
import com.example.postvision.ui.repository.UserRepository
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class StatisticsViewModel(private val repository: UserRepository) : ViewModel() {

    var isLoading by mutableStateOf(false)
    private var allReportsList = listOf<ExerciseReport>()

    var selectedFilter by mutableStateOf("Todos")
    var lastTrainCorrect by mutableStateOf(0)
    var lastTrainIncorrect by mutableStateOf(0)
    var lastTrainAccuracy by mutableStateOf("0%")
    var weeklyPerformanceList by mutableStateOf<List<DayPerformance>>(getDefaultWeeklyData())
    var monthlyRecordList by mutableStateOf<List<SquatRecord>>(emptyList())

    fun loadUserStatistics(userId: String, token: String) {
        Log.d("STATS_DEBUG", "Iniciando busca. UserId: $userId | Token vazio? ${token.isEmpty()}")
        if (userId.isEmpty() || token.isEmpty()) return

        viewModelScope.launch {
            isLoading = true
            try {
                // Faz a chamada na API
                val response = repository.getUserReports(userId, token)
                Log.d("STATS_DEBUG", "Código HTTP da API: ${response.code()}")

                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("STATS_DEBUG", "Sucesso! Corpo da resposta: $body")

                    if (body != null) {
                        allReportsList = body
                        applyFilter(selectedFilter)
                    } else {
                        Log.e("STATS_DEBUG", "A API retornou 200, mas o corpo (body) veio NULO. Verifique o formato do JSON!")
                    }
                } else {
                    Log.e("STATS_DEBUG", "Erro da API: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("STATS_DEBUG", "Exceção (Crash na conversão do Gson ou Rede): ${e.message}")
                e.printStackTrace()
            } finally {
                isLoading = false
            }
        }
    }

    fun applyFilter(filterName: String) {
        selectedFilter = filterName
        val filteredList = when (filterName) {
            "Agachamento" -> allReportsList
            "Flexão" -> emptyList()
            else -> allReportsList
        }
        processStatistics(filteredList)
    }

    private fun processStatistics(reports: List<ExerciseReport>) {
        if (reports.isEmpty()) {
            Log.d("STATS_DEBUG", "Lista de relatórios vazia após o filtro.")
            lastTrainCorrect = 0
            lastTrainIncorrect = 0
            lastTrainAccuracy = "0%"
            weeklyPerformanceList = getDefaultWeeklyData()
            monthlyRecordList = emptyList()
            return
        }

        val lastReport = reports.lastOrNull { it.report != null }
        if (lastReport?.report != null) {
            val correct = lastReport.report.correctReps
            val incorrect = lastReport.report.incorrectReps
            val total = (correct + incorrect).coerceAtLeast(1)

            lastTrainCorrect = correct
            lastTrainIncorrect = incorrect

            val calculatedAccuracy = (correct.toFloat() / total * 100).toInt()
            val accuracyVal = if (lastReport.report.averageAccuracy > 0) {
                lastReport.report.averageAccuracy.toInt()
            } else {
                calculatedAccuracy
            }
            lastTrainAccuracy = "$accuracyVal%"
        }

        val daysOfWeekMap = mutableMapOf(
            "Dom" to Pair(0, 0), "Seg" to Pair(0, 0), "Ter" to Pair(0, 0),
            "Qua" to Pair(0, 0), "Qui" to Pair(0, 0), "Sex" to Pair(0, 0), "Sab" to Pair(0, 0)
        )
        val dayNameFormat = SimpleDateFormat("EEE", Locale("pt", "BR"))

        val dayOfMonthMap = mutableMapOf<Int, Int>()
        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())

        // Instancia o calendário fora do loop
        val cal = Calendar.getInstance()

        reports.forEach { item ->
            val reportData = item.report ?: return@forEach
            val dateObj = parseDateSeguro(item.date)

            if (dateObj != null) {
                cal.time = dateObj
                val dayKey = when (cal.get(Calendar.DAY_OF_WEEK)) {
                    Calendar.SUNDAY -> "Dom"
                    Calendar.MONDAY -> "Seg"
                    Calendar.TUESDAY -> "Ter"
                    Calendar.WEDNESDAY -> "Qua"
                    Calendar.THURSDAY -> "Qui"
                    Calendar.FRIDAY -> "Sex"
                    Calendar.SATURDAY -> "Sab"
                    else -> "Dom"
                }

                val currentWeek = daysOfWeekMap[dayKey] ?: Pair(0, 0)
                daysOfWeekMap[dayKey] = Pair(
                    currentWeek.first + reportData.correctReps,
                    currentWeek.second + reportData.correctReps + reportData.incorrectReps
                )

                // Mensal
                val dayNum = cal.get(Calendar.DAY_OF_MONTH)
                val currentMonthTotal = dayOfMonthMap[dayNum] ?: 0
                dayOfMonthMap[dayNum] = currentMonthTotal + reportData.correctReps + reportData.incorrectReps
            } else {
                Log.e("STATS_DEBUG", "Falha ao ler a data do registo: ${item.date}")
            }
        }

        weeklyPerformanceList = daysOfWeekMap.map { DayPerformance(it.key, it.value.first, it.value.second) }
        monthlyRecordList = dayOfMonthMap.entries.sortedBy { it.key }.map { SquatRecord(it.key, it.value) }
    }

    private fun parseDateSeguro(dateStr: String?): Date? {
        if (dateStr == null) return null

        val formats = listOf(
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).apply { timeZone = TimeZone.getTimeZone("UTC") },
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.getDefault()),
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        )

        for (format in formats) {
            try {
                return format.parse(dateStr)
            } catch (e: Exception) {
                // Ignora o erro e tenta o próximo formato
            }
        }
        return null
    }

    private fun getDefaultWeeklyData(): List<DayPerformance> {
        return listOf("Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab").map { DayPerformance(it, 0, 0) }
    }
}
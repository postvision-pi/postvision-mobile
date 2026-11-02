package com.example.postvision.ui.model

import com.google.gson.annotations.SerializedName

data class ExerciseReport(
    @SerializedName("_id") val id: String? = null,
    val userId: String? = null,
    val exerciseId: ExerciseInfo?,
    val date: String? = null,
    val durationInSeconds: Int? = null,
    val report: ReportData? = null
)

data class ExerciseInfo(
    val _id: String,
    val name: String? = null
)

data class ReportData(
    val correctReps: Int = 0,
    val incorrectReps: Int = 0,
    val averageAccuracy: Float = 0f
)
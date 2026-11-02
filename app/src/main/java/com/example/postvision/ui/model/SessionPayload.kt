package com.example.postvision.ui.model

data class SessionComment(
    val text: String
)

data class SessionReport(
    val correctReps: Int,
    val incorrectReps: Int,
    val averageAccuracy: Int,
    val comment: SessionComment
)

data class SessionPayload(
    val exerciseId: String,
    val date: String,
    val durationInSeconds: Int,
    val report: SessionReport,
    val landmarksPath: String? = null,
    val userId: String? = null
)
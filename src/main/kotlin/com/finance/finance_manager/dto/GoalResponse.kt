package com.finance.finance_manager.dto

data class GoalResponse(
    val id: Long,
    val goalName: String,
    val targetAmount: Double,
    val targetDate: java.time.LocalDate,
    val startDate: java.time.LocalDate,
    val currentProgress: Double,
    val progressPercentage: Double,
    val remainingAmount: Double
)
package com.finance.finance_manager.dto

import java.time.LocalDate

data class GoalRequest(
    val goalName: String,
    val targetAmount: Double,
    val targetDate: LocalDate
)
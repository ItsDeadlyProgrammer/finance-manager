package com.finance.finance_manager.dto

data class YearlyReportResponse(
    val year: Int,
    val totalIncome: Map<String, Double>,
    val totalExpenses: Map<String, Double>,
    val netSavings: Double
)
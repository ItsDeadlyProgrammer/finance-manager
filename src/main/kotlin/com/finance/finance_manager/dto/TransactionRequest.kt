package com.finance.finance_manager.dto

data class TransactionRequest(

    val amount: Double,

    val category: String,

    val description: String = "",

    val type: String = "EXPENSE"
)
package com.finance.finance_manager.dto

data class TransactionRequest(

    val amount: Double,

    val category: String,

    val description: String? = null,

    val type: String = "EXPENSE"
)
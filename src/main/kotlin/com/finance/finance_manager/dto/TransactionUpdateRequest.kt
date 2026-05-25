package com.finance.finance_manager.dto

data class TransactionUpdateRequest(

    val amount: Double? = null,

    val category: String? = null,

    val description: String? = null,

    val type: String? = null
)
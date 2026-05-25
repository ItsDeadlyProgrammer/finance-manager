package com.finance.finance_manager.dto

data class RegisterRequest(
    val username: String,
    val password: String,
    val fullName: String,
    val phoneNumber: String
)
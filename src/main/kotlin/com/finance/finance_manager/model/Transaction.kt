package com.finance.finance_manager.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "transactions")
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val amount: Double = 0.0,

    val category: String = "",

    val description: String = "",

    val type: String = "EXPENSE",

    val date: LocalDate = LocalDate.now(),

    val userId: Long = 0
)
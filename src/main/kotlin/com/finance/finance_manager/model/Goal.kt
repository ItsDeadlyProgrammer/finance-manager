package com.finance.finance_manager.model

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "goals")
data class Goal(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val goalName: String = "",

    val targetAmount: Double = 0.0,

    val targetDate: LocalDate = LocalDate.now(),

    val startDate: LocalDate = LocalDate.now(),

    val userId: Long = 0
)
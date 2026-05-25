package com.finance.finance_manager.model

import jakarta.persistence.*

@Entity
@Table(name = "categories")
data class Category(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String = "",

    val type: String = "", // INCOME / EXPENSE

    val isCustom: Boolean = true,

    val userId: Long = 0
)
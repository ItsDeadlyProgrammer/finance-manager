package com.finance.finance_manager.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val username: String,

    val password: String,

    val fullName: String,

    val phoneNumber: String
)
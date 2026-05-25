package com.finance.finance_manager.repository

import com.finance.finance_manager.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {

    fun findByUserId(userId: Long): List<Category>

    fun findByNameAndUserId(name: String, userId: Long): Category?
}
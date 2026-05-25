package com.finance.finance_manager.repository

import com.finance.finance_manager.model.Goal
import org.springframework.data.jpa.repository.JpaRepository

interface GoalRepository : JpaRepository<Goal, Long> {

    fun findByUserId(userId: Long): List<Goal>
}
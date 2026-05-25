package com.finance.finance_manager.service

import com.finance.finance_manager.dto.GoalRequest
import com.finance.finance_manager.model.Goal
import com.finance.finance_manager.repository.GoalRepository
import com.finance.finance_manager.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class GoalService(
    private val goalRepository: GoalRepository,
    private val transactionRepository: TransactionRepository
) {

    fun createGoal(req: GoalRequest, userId: Long): Goal {

        val goal = Goal(
            goalName = req.goalName,
            targetAmount = req.targetAmount,
            targetDate = req.targetDate,
            startDate = java.time.LocalDate.now(),
            userId = userId
        )

        return goalRepository.save(goal)
    }

    fun getAllGoals(userId: Long): List<Goal> {
        return goalRepository.findByUserId(userId)
    }

    fun getGoalById(id: Long, userId: Long): Goal {

        val goal = goalRepository.findById(id)
            .orElseThrow { RuntimeException("Goal not found") }

        if (goal.userId != userId) {
            throw RuntimeException("Unauthorized")
        }

        return goal
    }

    fun updateGoal(id: Long, req: GoalRequest, userId: Long): Goal {

        val goal = goalRepository.findById(id)
            .orElseThrow { RuntimeException("Goal not found") }

        if (goal.userId != userId) {
            throw RuntimeException("Unauthorized")
        }

        val updated = goal.copy(
            goalName = req.goalName,
            targetAmount = req.targetAmount,
            targetDate = req.targetDate
        )

        return goalRepository.save(updated)
    }

    fun deleteGoal(id: Long, userId: Long) {

        val goal = goalRepository.findById(id)
            .orElseThrow { RuntimeException("Goal not found") }

        if (goal.userId != userId) {
            throw RuntimeException("Unauthorized")
        }

        goalRepository.deleteById(id)
    }
}
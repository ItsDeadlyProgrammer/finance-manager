package com.finance.finance_manager.service

import com.finance.finance_manager.dto.GoalRequest
import com.finance.finance_manager.model.Goal
import com.finance.finance_manager.repository.GoalRepository
import com.finance.finance_manager.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.NoSuchElementException

@Service
class GoalService(
    private val goalRepository: GoalRepository,
    private val transactionRepository: TransactionRepository
) {

    fun createGoal(req: GoalRequest, userId: Long): Goal {

        if (req.targetAmount <= 0) {
            throw IllegalArgumentException("Target amount must be greater than 0")
        }

        val goal = Goal(
            goalName = req.goalName,
            targetAmount = req.targetAmount,
            targetDate = req.targetDate,
            startDate = LocalDate.now(),
            userId = userId
        )

        return goalRepository.save(goal)
    }

    fun getAllGoals(userId: Long): List<Goal> {
        return goalRepository.findByUserId(userId)
            .map { enrichGoal(it) }
    }

    fun getGoalById(id: Long, userId: Long): Goal {

        val goal = goalRepository.findById(id)
            .orElseThrow { NoSuchElementException("Goal not found") }

        if (goal.userId != userId) {
            throw SecurityException("Unauthorized access")
        }

        return enrichGoal(goal)
    }

    fun updateGoal(id: Long, req: GoalRequest, userId: Long): Goal {

        val goal = goalRepository.findById(id)
            .orElseThrow { NoSuchElementException("Goal not found") }

        if (goal.userId != userId) {
            throw SecurityException("Unauthorized access")
        }

        if (req.targetAmount <= 0) {
            throw IllegalArgumentException("Target amount must be greater than 0")
        }

        val updated = goal.copy(
            goalName = req.goalName,
            targetAmount = req.targetAmount,
            targetDate = req.targetDate
        )

        return enrichGoal(goalRepository.save(updated))
    }

    fun deleteGoal(id: Long, userId: Long) {

        val goal = goalRepository.findById(id)
            .orElseThrow { NoSuchElementException("Goal not found") }

        if (goal.userId != userId) {
            throw SecurityException("Unauthorized access")
        }

        goalRepository.deleteById(id)
    }

    // ---------------- CORE FIX ----------------

    private fun enrichGoal(goal: Goal): Goal {

        val transactions = transactionRepository.findAll()
            .filter {
                it.userId == goal.userId &&
                !it.date.isBefore(goal.startDate)
            }

        val income = transactions
            .filter { it.type.toString() == "INCOME" }
            .sumOf { it.amount }

        val expense = transactions
            .filter { it.type.toString() == "EXPENSE" }
            .sumOf { it.amount }

        val currentProgress = income - expense
        val remaining = goal.targetAmount - currentProgress
        val percentage =
            if (goal.targetAmount > 0)
                (currentProgress / goal.targetAmount) * 100
            else 0.0

        return goal
    }
}
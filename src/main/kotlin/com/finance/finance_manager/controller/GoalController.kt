package com.finance.finance_manager.controller

import com.finance.finance_manager.dto.GoalRequest
import com.finance.finance_manager.model.Goal
import com.finance.finance_manager.service.GoalService
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/goals")
class GoalController(
    private val goalService: GoalService
) {

    @PostMapping
    fun createGoal(
        @RequestBody req: GoalRequest,
        session: HttpSession
    ): Goal {

        val userId = session.getAttribute("userId") as Long
        return goalService.createGoal(req, userId)
    }

    @GetMapping
    fun getAllGoals(session: HttpSession): List<Goal> {

        val userId = session.getAttribute("userId") as Long
        return goalService.getAllGoals(userId)
    }

    @GetMapping("/{id}")
    fun getGoalById(
        @PathVariable id: Long,
        session: HttpSession
    ): Goal {

        val userId = session.getAttribute("userId") as Long
        return goalService.getGoalById(id, userId)
    }

    @PutMapping("/{id}")
    fun updateGoal(
        @PathVariable id: Long,
        @RequestBody req: GoalRequest,
        session: HttpSession
    ): Goal {

        val userId = session.getAttribute("userId") as Long
        return goalService.updateGoal(id, req, userId)
    }

    @DeleteMapping("/{id}")
    fun deleteGoal(
        @PathVariable id: Long,
        session: HttpSession
    ) {

        val userId = session.getAttribute("userId") as Long
        goalService.deleteGoal(id, userId)
    }
}
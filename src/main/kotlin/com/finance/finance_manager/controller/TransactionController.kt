package com.finance.finance_manager.controller

import com.finance.finance_manager.dto.TransactionRequest
import com.finance.finance_manager.dto.TransactionUpdateRequest
import com.finance.finance_manager.model.Transaction
import com.finance.finance_manager.service.TransactionService
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transactions")
class TransactionController(
    private val transactionService: TransactionService
) {

    @PostMapping
    fun addTransaction(
        @RequestBody request: TransactionRequest,
        session: HttpSession
    ): Transaction {

        val userId = session.getAttribute("userId") as? Long
            ?: throw RuntimeException("User not logged in")

        return transactionService.addTransaction(request, userId)
    }

    @GetMapping
    fun getAllTransactions(session: HttpSession): List<Transaction> {

        val userId = session.getAttribute("userId") as? Long
            ?: throw RuntimeException("User not logged in")

        return transactionService.getAllTransactions(userId)
    }

    @PutMapping("/{id}")
    fun updateTransaction(
        @PathVariable id: Long,
        @RequestBody request: TransactionUpdateRequest,
        session: HttpSession
    ): Transaction {

        val userId = session.getAttribute("userId") as? Long
            ?: throw RuntimeException("User not logged in")

        return transactionService.updateTransaction(id, request, userId)
    }

    @DeleteMapping("/{id}")
    fun deleteTransaction(
        @PathVariable id: Long,
        session: HttpSession
    ) {

        val userId = session.getAttribute("userId") as? Long
            ?: throw RuntimeException("User not logged in")

        transactionService.deleteTransaction(id, userId)
    }
}
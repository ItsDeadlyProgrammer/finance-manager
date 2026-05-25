package com.finance.finance_manager.service

import com.finance.finance_manager.dto.TransactionRequest
import com.finance.finance_manager.dto.TransactionUpdateRequest
import com.finance.finance_manager.model.Transaction
import com.finance.finance_manager.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {

    fun addTransaction(request: TransactionRequest, userId: Long): Transaction {

        val transaction = Transaction(
            amount = request.amount,
            category = request.category,
            description = request.description,
            type = request.type,
            userId = userId
        )

        return transactionRepository.save(transaction)
    }

    fun getAllTransactions(userId: Long): List<Transaction> {
        return transactionRepository.findAll()
            .filter { it.userId == userId }
    }

    fun updateTransaction(
        id: Long,
        request: TransactionUpdateRequest,
        userId: Long
    ): Transaction {

        val existing = transactionRepository.findById(id)
            .orElseThrow { RuntimeException("Transaction not found") }

        if (existing.userId != userId) {
            throw RuntimeException("Unauthorized")
        }

        val updated = existing.copy(
            amount = request.amount ?: existing.amount,
            category = request.category ?: existing.category,
            description = request.description ?: existing.description,
            type = request.type ?: existing.type
        )

        return transactionRepository.save(updated)
    }

    fun deleteTransaction(id: Long, userId: Long) {

        val tx = transactionRepository.findById(id)
            .orElseThrow { RuntimeException("Not found") }

        if (tx.userId != userId) {
            throw RuntimeException("Unauthorized")
        }

        transactionRepository.deleteById(id)
    }
}
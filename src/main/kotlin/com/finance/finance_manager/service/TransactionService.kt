package com.finance.finance_manager.service

import com.finance.finance_manager.dto.TransactionRequest
import com.finance.finance_manager.dto.TransactionUpdateRequest
import com.finance.finance_manager.model.Transaction
import com.finance.finance_manager.repository.TransactionRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.NoSuchElementException

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {

    fun addTransaction(request: TransactionRequest, userId: Long): Transaction {

        if (request.amount <= 0) {
            throw IllegalArgumentException("Amount must be greater than 0")
        }

        val transaction = Transaction(
            amount = request.amount,
            category = request.category,
            description = request.description ?: "",
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
            .orElseThrow { NoSuchElementException("Transaction not found") }

        if (existing.userId != userId) {
            throw SecurityException("Unauthorized access")
        }

        if (request.amount != null && request.amount <= 0) {
            throw IllegalArgumentException("Amount must be greater than 0")
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
            .orElseThrow { NoSuchElementException("Transaction not found") }

        if (tx.userId != userId) {
            throw SecurityException("Unauthorized access")
        }

        transactionRepository.deleteById(id)
    }
}
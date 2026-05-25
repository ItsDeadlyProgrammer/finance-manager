package com.finance.finance_manager.service

import com.finance.finance_manager.dto.MonthlyReportResponse
import com.finance.finance_manager.dto.YearlyReportResponse
import com.finance.finance_manager.model.Transaction
import com.finance.finance_manager.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class ReportService(
    private val transactionRepository: TransactionRepository
) {

    fun monthlyReport(year: Int, month: Int, userId: Long): MonthlyReportResponse {

        val transactions = getFilteredTransactions(year, month, userId)

        val income = groupByCategory(
            transactions.filter { it.type == "INCOME" }
        )

        val expense = groupByCategory(
            transactions.filter { it.type == "EXPENSE" }
        )

        val totalIncome = income.values.sum()
        val totalExpense = expense.values.sum()

        return MonthlyReportResponse(
            month = month,
            year = year,
            totalIncome = income,
            totalExpenses = expense,
            netSavings = totalIncome - totalExpense
        )
    }

    fun yearlyReport(year: Int, userId: Long): YearlyReportResponse {

        val transactions = transactionRepository.findAll()
            .filter { it.userId == userId }
            .filter { it.date.year == year }

        val income = groupByCategory(
            transactions.filter { it.type == "INCOME" }
        )

        val expense = groupByCategory(
            transactions.filter { it.type == "EXPENSE" }
        )

        val totalIncome = income.values.sum()
        val totalExpense = expense.values.sum()

        return YearlyReportResponse(
            year = year,
            totalIncome = income,
            totalExpenses = expense,
            netSavings = totalIncome - totalExpense
        )
    }

    private fun getFilteredTransactions(
        year: Int,
        month: Int,
        userId: Long
    ): List<Transaction> {
        return transactionRepository.findAll()
            .filter { it.userId == userId }
            .filter { it.date.year == year && it.date.monthValue == month }
    }

    private fun groupByCategory(
        transactions: List<Transaction>
    ): Map<String, Double> {
        return transactions
            .groupBy { it.category }
            .mapValues { entry ->
                entry.value.sumOf { it.amount }
            }
    }
}
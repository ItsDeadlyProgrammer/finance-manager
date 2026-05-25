package com.finance.finance_manager.service

import com.finance.finance_manager.dto.MonthlyReportResponse
import com.finance.finance_manager.dto.YearlyReportResponse
import com.finance.finance_manager.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class ReportService(
    private val transactionRepository: TransactionRepository
) {

    fun monthlyReport(year: Int, month: Int, userId: Long): MonthlyReportResponse {

        val transactions = transactionRepository.findAll()
            .filter { it.userId == userId }
            .filter {
                it.date.year == year && it.date.monthValue == month
            }

        val income = transactions
            .filter { it.type == "INCOME" }
            .groupBy { it.category }
            .mapValues { it.value.sumOf { t -> t.amount } }

        val expense = transactions
            .filter { it.type == "EXPENSE" }
            .groupBy { it.category }
            .mapValues { it.value.sumOf { t -> t.amount } }

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

        val income = transactions
            .filter { it.type == "INCOME" }
            .groupBy { it.category }
            .mapValues { it.value.sumOf { t -> t.amount } }

        val expense = transactions
            .filter { it.type == "EXPENSE" }
            .groupBy { it.category }
            .mapValues { it.value.sumOf { t -> t.amount } }

        val totalIncome = income.values.sum()
        val totalExpense = expense.values.sum()

        return YearlyReportResponse(
            year = year,
            totalIncome = income,
            totalExpenses = expense,
            netSavings = totalIncome - totalExpense
        )
    }
}
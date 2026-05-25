package com.finance.finance_manager.repository

import com.finance.finance_manager.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long>
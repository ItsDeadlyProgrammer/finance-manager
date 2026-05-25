package com.finance.finance_manager.service

import com.finance.finance_manager.dto.RegisterRequest
import com.finance.finance_manager.dto.LoginRequest
import com.finance.finance_manager.model.User
import com.finance.finance_manager.repository.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException
import java.util.NoSuchElementException

@Service
class AuthService(
    private val userRepository: UserRepository
) {

    fun register(req: RegisterRequest): User {

        if (req.username.isBlank() || req.password.isBlank()) {
            throw IllegalArgumentException("Username and password cannot be empty")
        }

        val existing = userRepository.findByUsername(req.username)
        if (existing != null) {
            throw DataIntegrityViolationException("User already exists")
        }

        val user = User(
            username = req.username,
            password = req.password,
            fullName = req.fullName,
            phoneNumber = req.phoneNumber
        )

        return userRepository.save(user)
    }

    fun login(req: LoginRequest): User {

        if (req.username.isBlank() || req.password.isBlank()) {
            throw IllegalArgumentException("Invalid input")
        }

        val user = userRepository.findByUsername(req.username)
            ?: throw NoSuchElementException("User not found")

        if (user.password != req.password) {
            throw SecurityException("Invalid credentials")
        }

        return user
    }
}
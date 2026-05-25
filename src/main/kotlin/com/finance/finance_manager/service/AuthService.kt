package com.finance.finance_manager.service

import com.finance.finance_manager.dto.RegisterRequest
import com.finance.finance_manager.dto.LoginRequest
import com.finance.finance_manager.model.User
import com.finance.finance_manager.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository
) {

    fun register(req: RegisterRequest): User {

        val existing = userRepository.findByUsername(req.username)
        if (existing != null) {
            throw RuntimeException("User already exists")
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

        val user = userRepository.findByUsername(req.username)
            ?: throw RuntimeException("User not found")

        if (user.password != req.password) {
            throw RuntimeException("Invalid credentials")
        }

        return user
    }
}
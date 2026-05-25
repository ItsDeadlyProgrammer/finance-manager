package com.finance.finance_manager.controller

import com.finance.finance_manager.dto.LoginRequest
import com.finance.finance_manager.dto.RegisterRequest
import com.finance.finance_manager.service.AuthService
import jakarta.servlet.http.HttpSession
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@RequestBody req: RegisterRequest): ResponseEntity<Any> {

        val user = authService.register(req)

        return ResponseEntity.status(201).body(
            mapOf(
                "message" to "User registered successfully",
                "userId" to user.id
            )
        )
    }

    @PostMapping("/login")
    fun login(@RequestBody req: LoginRequest, session: HttpSession): ResponseEntity<Any> {

        val user = authService.login(req)

        session.setAttribute("userId", user.id)

        return ResponseEntity.ok(
            mapOf("message" to "Login successful")
        )
    }

    @PostMapping("/logout")
    fun logout(session: HttpSession): ResponseEntity<Any> {

        session.invalidate()

        return ResponseEntity.ok(
            mapOf("message" to "Logout successful")
        )
    }
}
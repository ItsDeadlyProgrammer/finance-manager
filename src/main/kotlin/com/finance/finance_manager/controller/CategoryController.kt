package com.finance.finance_manager.controller

import com.finance.finance_manager.dto.CategoryRequest
import com.finance.finance_manager.model.Category
import com.finance.finance_manager.service.CategoryService
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoryController(
    private val categoryService: CategoryService
) {

    @PostMapping
    fun addCategory(
        @RequestBody req: CategoryRequest,
        session: HttpSession
    ): Category {

        val userId = session.getAttribute("userId") as Long
        return categoryService.addCategory(req, userId)
    }

    @GetMapping
    fun getCategories(session: HttpSession): List<Category> {

        val userId = session.getAttribute("userId") as Long
        return categoryService.getCategories(userId)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(
        @PathVariable id: Long,
        session: HttpSession
    ) {

        val userId = session.getAttribute("userId") as Long
        categoryService.deleteCategory(id, userId)
    }
}
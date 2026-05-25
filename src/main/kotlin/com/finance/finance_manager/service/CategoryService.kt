package com.finance.finance_manager.service

import com.finance.finance_manager.dto.CategoryRequest
import com.finance.finance_manager.model.Category
import com.finance.finance_manager.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun addCategory(req: CategoryRequest, userId: Long): Category {

        val existing = categoryRepository.findByNameAndUserId(req.name, userId)
        if (existing != null) {
            throw RuntimeException("Category already exists")
        }

        val category = Category(
            name = req.name,
            type = req.type,
            isCustom = true,
            userId = userId
        )

        return categoryRepository.save(category)
    }

    fun getCategories(userId: Long): List<Category> {
        return categoryRepository.findByUserId(userId)
    }

    fun deleteCategory(id: Long, userId: Long) {

        val cat = categoryRepository.findById(id)
            .orElseThrow { RuntimeException("Not found") }

        if (cat.userId != userId) {
            throw RuntimeException("Unauthorized")
        }

        if (!cat.isCustom) {
            throw RuntimeException("Cannot delete default category")
        }

        categoryRepository.deleteById(id)
    }
}
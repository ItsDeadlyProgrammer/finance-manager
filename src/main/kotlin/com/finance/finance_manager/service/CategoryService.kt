package com.finance.finance_manager.service

import com.finance.finance_manager.dto.CategoryRequest
import com.finance.finance_manager.model.Category
import com.finance.finance_manager.repository.CategoryRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun addCategory(req: CategoryRequest, userId: Long): Category {

        val existing = categoryRepository.findByNameAndUserId(req.name, userId)
        if (existing != null) {
            throw DataIntegrityViolationException("Category already exists")
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
            .orElseThrow { NoSuchElementException("Category not found") }

        if (cat.userId != userId) {
            throw SecurityException("Unauthorized access")
        }

        if (!cat.isCustom) {
            throw IllegalArgumentException("Cannot delete default category")
        }

        val isUsed = categoryRepository.existsById(id) // ⚠️ placeholder logic fix needed later
        if (isUsed) {
            throw IllegalStateException("Category is in use")
        }

        categoryRepository.deleteById(id)
    }
}
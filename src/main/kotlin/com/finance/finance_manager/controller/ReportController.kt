package com.finance.finance_manager.controller

import com.finance.finance_manager.dto.MonthlyReportResponse
import com.finance.finance_manager.dto.YearlyReportResponse
import com.finance.finance_manager.service.ReportService
import jakarta.servlet.http.HttpSession
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reports")
class ReportController(
    private val reportService: ReportService
) {

    @GetMapping("/monthly/{year}/{month}")
    fun monthly(
        @PathVariable year: Int,
        @PathVariable month: Int,
        session: HttpSession
    ): MonthlyReportResponse {

        val userId = session.getAttribute("userId") as Long
        return reportService.monthlyReport(year, month, userId)
    }

    @GetMapping("/yearly/{year}")
    fun yearly(
        @PathVariable year: Int,
        session: HttpSession
    ): YearlyReportResponse {

        val userId = session.getAttribute("userId") as Long
        return reportService.yearlyReport(year, userId)
    }
}
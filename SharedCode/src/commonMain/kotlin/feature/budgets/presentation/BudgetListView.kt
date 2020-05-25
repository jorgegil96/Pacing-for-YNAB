package com.farsidelabs.pacingforynab.feature.budgets.presentation

import com.farsidelabs.pacingforynab.feature.budgets.model.budget.Budget

interface BudgetListView {
    fun setUserId(id: String)

    fun setBudgets(budgets: List<Budget>)

    fun showError(message: String)
}
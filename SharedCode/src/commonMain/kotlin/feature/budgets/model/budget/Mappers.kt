package com.farsidelabs.pacingforynab.feature.budgets.model.budget

import com.farsidelabs.pacingforynab.api.entities.budget.BudgetEntity

fun BudgetEntity.toBudget(): Budget {
    return Budget(
        id = id,
        name = name
    )
}
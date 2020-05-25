package com.farsidelabs.pacingforynab.api.entities.budget

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BudgetsResponseEntity(
    val data: BudgetsDataEntity
)

@Serializable
data class BudgetsDataEntity(
    val budgets: List<BudgetEntity>,
    @SerialName("default_budget") val defaultBudget: BudgetEntity?
)
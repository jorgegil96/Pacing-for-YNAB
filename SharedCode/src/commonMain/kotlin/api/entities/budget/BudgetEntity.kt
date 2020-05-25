package com.farsidelabs.pacingforynab.api.entities.budget

import kotlinx.serialization.Serializable

@Serializable
data class BudgetEntity(
    val id: String,
    val name: String
)
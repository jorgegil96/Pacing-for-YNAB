package com.farsidelabs.pacingforynab.api.service

import com.farsidelabs.pacingforynab.api.base.Response
import com.farsidelabs.pacingforynab.api.entities.budget.BudgetEntity
import com.farsidelabs.pacingforynab.api.entities.user.UserEntity

interface YNABService {
    suspend fun listBudgets(): Response<List<BudgetEntity>>

    suspend fun getUser(): Response<UserEntity>
}
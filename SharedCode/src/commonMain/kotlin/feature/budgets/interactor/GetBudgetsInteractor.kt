package com.farsidelabs.pacingforynab.feature.budgets.interactor

import com.farsidelabs.pacingforynab.api.service.YNABService
import com.farsidelabs.pacingforynab.api.base.Response
import com.farsidelabs.pacingforynab.api.entities.budget.BudgetEntity
import com.farsidelabs.pacingforynab.feature.base.Result
import com.farsidelabs.pacingforynab.feature.budgets.model.budget.Budget
import com.farsidelabs.pacingforynab.feature.budgets.model.budget.toBudget
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetBudgetsInteractor(
    private val ynabService: YNABService
) {

    fun getBudgetsFlow(): Flow<Result<List<Budget>>> = flow {
        when (val budgetsResponse = ynabService.listBudgets()) {
            is Response.Success -> {
                val budgets = budgetsResponse.data.toBudgets()
                emit(Result.Success(budgets))
            }
            is Response.Error -> {
                emit(Result.Error(budgetsResponse.throwable, budgetsResponse.code))
            }
        }
    }

    private fun List<BudgetEntity>.toBudgets(): List<Budget> {
        return this.map { budgetEntity ->
            budgetEntity.toBudget()
        }
    }
}
package com.farsidelabs.pacingforynab.feature.budgets.presentation

import com.farsidelabs.pacingforynab.feature.base.Result
import com.farsidelabs.pacingforynab.feature.budgets.interactor.GetBudgetsInteractor
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class BudgetListPresenter(
    private val view: BudgetListView,
    private val getBudgetsInteractor: GetBudgetsInteractor
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val job = Job()

    fun onViewAttached() {
        launch(Dispatchers.Main) {
            getBudgetsInteractor.getBudgetsFlow()
                .flowOn(Dispatchers.Default)
                .collect { result ->
                    when (result) {
                        is Result.Success -> view.setBudgets(result.data)
                        is Result.Error -> view.showError("Something went wrong [${result.code}]")
                    }
                }
        }
    }

    fun onViewDetached() {
        job.cancelChildren()
    }
}

package com.farsidelabs.pacingforynab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.farsidelabs.pacingforynab.api.service.YNABServiceImpl
import com.farsidelabs.pacingforynab.databinding.ActivityMainBinding
import com.farsidelabs.pacingforynab.feature.budgets.interactor.GetBudgetsInteractor
import com.farsidelabs.pacingforynab.feature.budgets.model.budget.Budget
import com.farsidelabs.pacingforynab.feature.budgets.presentation.BudgetListPresenter
import com.farsidelabs.pacingforynab.feature.budgets.presentation.BudgetListView

class MainActivity : AppCompatActivity(), BudgetListView {

  private lateinit var bindings: ActivityMainBinding
  private lateinit var presenter: BudgetListPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    bindings = ActivityMainBinding.inflate(layoutInflater)
    setContentView(bindings.root)

    presenter = BudgetListPresenter(this, GetBudgetsInteractor(YNABServiceImpl()))
  }

  override fun onStart() {
    super.onStart()
    presenter.onViewAttached()
  }

  override fun onStop() {
    presenter.onViewDetached()
    super.onStop()
  }

  override fun setUserId(id: String) {
    bindings.title.text = id
  }

  override fun setBudgets(budgets: List<Budget>) {
    bindings.title.text = budgets.joinToString(separator = ", ") { it.name }
  }

  override fun showError(message: String) {
    bindings.title.text = message
  }
}

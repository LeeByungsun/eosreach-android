package com.memtrip.eosreach.app.accountlist

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP
import android.os.Bundle
import com.memtrip.eosreach.R
import com.memtrip.eosreach.app.MviActivity
import com.memtrip.eosreach.app.ViewModelFactory
import com.memtrip.eosreach.app.account.AccountActivity.Companion.accountIntent
import com.memtrip.eosreach.app.account.AccountBundle

import com.memtrip.eosreach.db.account.AccountEntity

import com.memtrip.eosreach.uikit.Interaction
import com.memtrip.eosreach.uikit.gone
import com.memtrip.eosreach.uikit.visible
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.accounts_list_activity.*
import javax.inject.Inject

internal class AccountListActivity
    : MviActivity<AccountListIntent, AccountListRenderAction, AccountListViewState, AccountListViewLayout>(), AccountListViewLayout {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var render: AccountListViewRenderer

    private lateinit var adapter: AccountListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accounts_list_activity)

        val adapterInteraction: PublishSubject<Interaction<AccountEntity>> = PublishSubject.create()
        adapter = AccountListAdapter(this, adapterInteraction)
        account_list_recyclerview.adapter = adapter
    }

    override fun inject() {
        AndroidInjection.inject(this)
    }

    override fun intents(): Observable<AccountListIntent> = Observable.merge(
        Observable.just(AccountListIntent.Init),
        adapter.interaction.map {
            AccountListIntent.AccountSelected(it.data)
        }
    )

    override fun layout(): AccountListViewLayout = this

    override fun model(): AccountListViewModel = getViewModel(viewModelFactory)

    override fun render(): AccountListViewRenderer = render

    override fun showProgress() {
        account_list_progressbar.visible()
        account_list_recyclerview.gone()
        account_list_toolbar.gone()
    }

    override fun showError() {
    }

    override fun populate(accounts: List<AccountEntity>) {
        account_list_progressbar.gone()
        account_list_recyclerview.visible()
        account_list_toolbar.visible()
        adapter.clear()
        adapter.populate(accounts)
    }

    override fun navigateToAccount(accountBundle: AccountBundle) {
        model().publish(AccountListIntent.Idle)
        val intent = accountIntent(accountBundle, this)
        startActivity(intent)
    }

    companion object {

        fun accountListIntent(context: Context): Intent {
            return with (Intent(context, AccountListActivity::class.java)) {
                flags = FLAG_ACTIVITY_SINGLE_TOP or FLAG_ACTIVITY_CLEAR_TOP
                this
            }
        }
    }
}
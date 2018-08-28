package com.memtrip.eosreach.app.welcome.createaccount

import android.app.Application
import com.memtrip.eosreach.storage.EosReachSharedPreferences
import com.memtrip.mxandroid.MxViewModel
import io.reactivex.Observable
import javax.inject.Inject

class CreateAccountViewModel @Inject internal constructor(
    private val eosReachSharedPreferences: EosReachSharedPreferences,
    application: Application
) : MxViewModel<CreateAccountIntent, CreateAccountRenderAction, CreateAccountViewState>(
    CreateAccountViewState(view = CreateAccountViewState.View.Idle),
    application
) {

    override fun dispatcher(intent: CreateAccountIntent): Observable<CreateAccountRenderAction> = when (intent) {
        is CreateAccountIntent.Init -> Observable.just(CreateAccountRenderAction.Idle)
        is CreateAccountIntent.CreateAccount -> saveAccount()
    }

    override fun reducer(previousState: CreateAccountViewState, renderAction: CreateAccountRenderAction): CreateAccountViewState = when (renderAction) {
        CreateAccountRenderAction.Idle -> previousState.copy(view = CreateAccountViewState.View.Idle)
        CreateAccountRenderAction.OnProgress -> previousState.copy(view = CreateAccountViewState.View.OnProgress)
        CreateAccountRenderAction.OnSuccess -> previousState.copy(view = CreateAccountViewState.View.OnSuccess)
        CreateAccountRenderAction.OnError -> previousState.copy(view = CreateAccountViewState.View.OnError)
    }

    override fun filterIntents(intents: Observable<CreateAccountIntent>): Observable<CreateAccountIntent> = Observable.merge(
        intents.ofType(CreateAccountIntent.Init::class.java).take(1),
        intents.filter {
            !CreateAccountIntent.Init::class.java.isInstance(it)
        }
    )

    private fun saveAccount(): Observable<CreateAccountRenderAction> {
        return eosReachSharedPreferences
            .saveAccountCreated()
            .toSingleDefault<CreateAccountRenderAction>(CreateAccountRenderAction.OnSuccess)
            .onErrorReturn { CreateAccountRenderAction.OnError }
            .toObservable()
            .startWith(CreateAccountRenderAction.OnProgress)
    }
}
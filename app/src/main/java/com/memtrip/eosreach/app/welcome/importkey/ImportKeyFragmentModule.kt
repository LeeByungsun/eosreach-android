package com.memtrip.eosreach.app.welcome.importkey

import androidx.lifecycle.ViewModel

import com.memtrip.eosreach.app.ViewModelKey

import dagger.Binds
import dagger.Module

import dagger.multibindings.IntoMap

@Module
abstract class ImportKeyFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(ImportKeyViewModel::class)
    internal abstract fun contributesImportKeyViewModel(viewModel: ImportKeyViewModel): ViewModel
}
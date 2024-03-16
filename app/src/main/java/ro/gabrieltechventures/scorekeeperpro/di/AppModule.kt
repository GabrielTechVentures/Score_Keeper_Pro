package ro.gabrieltechventures.scorekeeperpro.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ro.gabrieltechventures.scorekeeperpro.player_list.PlayerListViewModel

val appModule= module{
    viewModel {
        PlayerListViewModel()
    }
}
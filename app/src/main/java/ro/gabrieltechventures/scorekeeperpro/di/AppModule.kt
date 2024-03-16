package ro.gabrieltechventures.scorekeeperpro.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ro.gabrieltechventures.scorekeeperpro.PlayerRepositoryImpl
import ro.gabrieltechventures.scorekeeperpro.data.PlayerDatabase
import ro.gabrieltechventures.scorekeeperpro.data.PlayerRepository
import ro.gabrieltechventures.scorekeeperpro.player_list.PlayerListViewModel

val appModule= module{
    single {
        Room.databaseBuilder(get(), PlayerDatabase::class.java, "player_db").build().dao
    }
    single<PlayerRepository> {
        PlayerRepositoryImpl(get())
    }

    viewModel {
        PlayerListViewModel(get())
    }
}
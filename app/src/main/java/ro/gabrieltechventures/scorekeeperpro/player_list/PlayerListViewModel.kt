package ro.gabrieltechventures.scorekeeperpro.player_list

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ro.gabrieltechventures.scorekeeperpro.data.Player
import ro.gabrieltechventures.scorekeeperpro.data.PlayerRepository
import ro.gabrieltechventures.scorekeeperpro.ui.theme.UiEvent

class PlayerListViewModel(
    private val repository: PlayerRepository
):ViewModel() {


    var showAddPlayerDialog by mutableStateOf(false)
        private set

    var name by mutableStateOf("")
        private set

    val players=repository.getAllPlayers()

    private val _uiEvent= Channel<UiEvent>()
    val uiEvent=_uiEvent.receiveAsFlow()

    fun onEvent(event: PlayerListEvent)
    {
        when(event)
        {
            is PlayerListEvent.onAddPlayerBtnClick->{
                    showAddPlayerDialog=true
            }
            is PlayerListEvent.addPlayerDismiss->{
                showAddPlayerDialog=false
            }
            is PlayerListEvent.onNameChange->{
                name=event.name
            }
            is PlayerListEvent.onAddPlayerDoneBtnPressed->{
                if (name.isNotBlank())
                {
                    viewModelScope.launch {
                        repository.addPlayer(Player(name))
                    }
                  name=""
                    showAddPlayerDialog=false
                }
                else{
                    sendUiEvent(UiEvent.showToast("Add Player's name field cannot be empty"))
                    //TODO afiseaza toast ca nu tb sa fie numele gol
                }
            }
        }
    }
    private fun sendUiEvent(event: UiEvent)
    {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
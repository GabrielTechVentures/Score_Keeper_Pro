package ro.gabrieltechventures.scorekeeperpro.player_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class PlayerListViewModel:ViewModel() {
    var showAddPlayerDialog by mutableStateOf(false)
        private set

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
        }
    }
}
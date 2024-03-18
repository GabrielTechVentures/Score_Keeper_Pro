    package ro.gabrieltechventures.scorekeeperpro.player_list


    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.setValue
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
        var animationPlayed by mutableStateOf(value = false)
            private set
    var showCustomizeScoreDialog by mutableStateOf(false)
        private set
        var name by mutableStateOf("")
            private set


        val players=repository.getAllPlayers()
        private var allPlayers:List<Player> = emptyList()//pt update toata lista


        var maximumScore by mutableStateOf("0")
            private set
        private val _uiEvent= Channel<UiEvent>()
        val uiEvent=_uiEvent.receiveAsFlow()

        init {
            viewModelScope.launch {
                repository.getAllPlayers().collect{
                    players->//List<Player>
                    allPlayers=players
                    maximumScore= allPlayers.firstOrNull()?.maximumScore ?: "0"
                }
            }
        }


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
                            repository.addPlayer(Player(name = name, maximumScore = maximumScore))
                        }
                      name=""
                        showAddPlayerDialog=false
                    }
                    else{
                        sendUiEvent(UiEvent.showToast("Add Player's name field cannot be empty"))

                    }
                }
                is PlayerListEvent.onCustomizeScoreBtnPressed->{
                    showCustomizeScoreDialog=true
                }
                is PlayerListEvent.onCustomizeScoreDialogDismiss->{
                    showCustomizeScoreDialog=false
                }
                is PlayerListEvent.onInfoButtonClick->{
                    sendUiEvent(UiEvent.showToast("The first player to reach this score will win the game"))
                }

                is PlayerListEvent.onCustomizeScoreChange->{
                    maximumScore=event.score
                }
                is PlayerListEvent.onCustomizeDoneBtnClick->{
                    if (maximumScore.isNotBlank())
                    {

                        viewModelScope.launch {

                        repository.updateAllPlayers(allPlayers, maximumScore)

                        }

                        showCustomizeScoreDialog=false
                    }
                    else{
                        sendUiEvent(UiEvent.showToast("Set maximum score field cannot be empty"))
                    }
                }
                is PlayerListEvent.animationChange->{
                    animationPlayed=true
                }
            }//when
        }
        private fun sendUiEvent(event: UiEvent)
        {
            viewModelScope.launch {
                _uiEvent.send(event)
            }
        }
    }
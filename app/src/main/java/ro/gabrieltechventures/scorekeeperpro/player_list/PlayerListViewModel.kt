    package ro.gabrieltechventures.scorekeeperpro.player_list


    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.setValue
    import androidx.compose.ui.text.capitalize
    import androidx.core.text.isDigitsOnly
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import androidx.lifecycle.viewmodel.compose.viewModel
    import kotlinx.coroutines.channels.Channel

    import kotlinx.coroutines.flow.receiveAsFlow
    import kotlinx.coroutines.launch
    import ro.gabrieltechventures.scorekeeperpro.data.Player
    import ro.gabrieltechventures.scorekeeperpro.data.PlayerRepository
    import ro.gabrieltechventures.scorekeeperpro.ui.theme.UiEvent

    class PlayerListViewModel(
        private val repository: PlayerRepository
    ):ViewModel() {
        var winnerPlayer by mutableStateOf(Player())
            private set
        private var auxPlayer:Player?=null
            private var highestScore by mutableStateOf(0)
         var gamesWon:String by mutableStateOf("")
            private set
        var showUpdatePlayerDialog by mutableStateOf(false)
            private set
        var showDeletePlayerDialog by mutableStateOf(false)
            private set
        var showResetScoreDialog by mutableStateOf(false)
            private set
        var showAddScoreDialog by mutableStateOf(false)
        private set
        var showAddPlayerDialog by mutableStateOf(false)
            private set
        var showFinishGameDialog by mutableStateOf(false)
            private set
        var animationPlayed by mutableStateOf(value = false)
            private set
    var showCustomizeScoreDialog by mutableStateOf(false)
        private set
        var name by mutableStateOf("")
            private set
        var pointsEarned by mutableStateOf("")
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
                        if(maximumScore.matches(Regex("^-?\\d+(\${2,})?\$")))
                        {
                            viewModelScope.launch {

                                repository.updateAllPlayers(allPlayers, maximumScore)

                            }
                            highestScore=maximumScore.toInt()

                            showCustomizeScoreDialog=false
                        }
                        else{
                            sendUiEvent(UiEvent.showToast("Only digits allowed!"))
                        }
                        }
                    else
                    {
                        sendUiEvent(UiEvent.showToast("Set maximum score field cannot be empty"))
                    }




                }
                is PlayerListEvent.animationChange->{
                    animationPlayed=true
                }

                is PlayerListEvent.onDeletePlayerClick->{
                    auxPlayer=event.player
                    showDeletePlayerDialog=true

                }
                is PlayerListEvent.onAddScoreBtnClick->{
                    if(event.player.maximumScore=="0")
                    {
                        sendUiEvent(UiEvent.showToast("Please customize the score first"))
                    }
                    else{
                        auxPlayer=event.player
                        showAddScoreDialog=true
                    }

            }
                is PlayerListEvent.onEarnedPointsChange->{
                    pointsEarned=event.points
                }
                is PlayerListEvent.onAddScoreDoneBtnClick->{
                    if (pointsEarned.isNotBlank())
                    {
                        if(pointsEarned.matches(Regex("^-?\\d+(\${2,})?\$")))
                        {val total=pointsEarned.toInt()+auxPlayer!!.currentScore.toInt()
                        auxPlayer!!.currentScore=total.toString()
                        auxPlayer!!.percentage=1f/auxPlayer!!.maximumScore.toFloat()*auxPlayer!!.currentScore.toFloat()
                        viewModelScope.launch {
                            repository.updatePlayer(auxPlayer!!)
                        }
                        highestScore=maximumScore.toInt()
                        allPlayers.forEach{
                            Player->
                            if (Player.currentScore.toInt()>=highestScore)
                            {
                                highestScore=Player.currentScore.toInt()

                            }
                        }
                        if (auxPlayer!!.currentScore.toInt()>=highestScore) {

                            updateWinStatus()
                            auxPlayer!!.hasWon=true

                            viewModelScope.launch { repository.updatePlayer(auxPlayer!!) }

                        }
                        else{
                            auxPlayer!!.hasWon=false
                            viewModelScope.launch { repository.updatePlayer(auxPlayer!!)}
                        }







                        showAddScoreDialog=false
                        auxPlayer=null
                        pointsEarned=""
                    }  else{
                            sendUiEvent(UiEvent.showToast("Only digits allowed!"))
                        }

                    }
                    else{
                        sendUiEvent(UiEvent.showToast("Earned points field cannot be empty"))
                    }
                }
                is PlayerListEvent.onAddScoreDialogDismiss->{
                    showAddScoreDialog=false
                    auxPlayer=null
                    pointsEarned=""
                }
                is PlayerListEvent.onResetScoreButtonClick->{
                    showResetScoreDialog=true
                }
                is PlayerListEvent.onResetScoreDialogDismiss->{
                    showResetScoreDialog=false
                }
                is PlayerListEvent.onResetScoreConfirmClick->{
                    viewModelScope.launch {
                        repository.updateAllPlayersScore(allPlayers,0,"0")
                        updateWinStatus()
                    }
                    showResetScoreDialog=false
                }
                is PlayerListEvent.onDeletePlayerDialogDismiss->{
                    showDeletePlayerDialog=false
                    auxPlayer=null
                }
                is PlayerListEvent.onDeletePlayerConfirmClick->{
                    viewModelScope.launch {
                        repository.deletePlayer(auxPlayer!!)
                        auxPlayer=null
                        showDeletePlayerDialog=false
                    }

                }
                is PlayerListEvent.onUpdatePlayerClick->{
                    auxPlayer=event.player
                    showUpdatePlayerDialog=true
                }
                is PlayerListEvent.onUpdatePlayerDialogDismiss->{
                    showUpdatePlayerDialog=false
                    auxPlayer=null
                }
                is PlayerListEvent.onGamesWonChange->{
                    gamesWon=event.games
                }
                is PlayerListEvent.onEditDoneBtnClick->{
                    if(name.isNotBlank())
                    {
                        auxPlayer!!.name=name
                        viewModelScope.launch {
                            repository.updatePlayer(auxPlayer!!)
                        }
                        name=""
                    }
                    if(gamesWon.isNotBlank())
                    {
                        if(gamesWon.matches(Regex("^-?\\d+(\${2,})?\$")))
                        {
                            auxPlayer!!.gamesWon=gamesWon.toInt()
                            viewModelScope.launch{
                                repository.updatePlayer(auxPlayer!!)
                            }

                            gamesWon=""
                            auxPlayer=null

                        } else{
                            sendUiEvent(UiEvent.showToast("Only digits allowed!"))
                        }

                    }

                    showUpdatePlayerDialog=false
                }
                is PlayerListEvent.onWinnerBtnPressed->{
                    showFinishGameDialog=true
                    winnerPlayer=event.player
                }
                is PlayerListEvent.onFinishGameDialogDimiss->{
                    showFinishGameDialog=false

                }
                is PlayerListEvent.onFinishGameConfirmBtnPressed->
                {   winnerPlayer.gamesWon++

                    viewModelScope.launch {
                        repository.updatePlayer(winnerPlayer)

                    }
                    allPlayers.forEach {
                        player->
                        if(player.hasWon)
                        {
                            player.gamesWon++
                        }
                        player.hasWon=false
                        player.currentScore="0"
                        player.percentage=0f

                        viewModelScope.launch {
                            repository.updatePlayer(player)
                        }
                    }
                    highestScore=maximumScore.toInt()

                    showFinishGameDialog=false

                }
            }//when
        }


        private fun sendUiEvent(event: UiEvent)
        {
            viewModelScope.launch {
                _uiEvent.send(event)
            }
        }
        private fun updateWinStatus(){
            allPlayers.forEach {
                    player ->
                val updatedPlayer=player.copy(hasWon = false)
                viewModelScope.launch{
                    repository.updatePlayer(updatedPlayer)

                }

            }
        }
    }
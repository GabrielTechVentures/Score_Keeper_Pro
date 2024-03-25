package ro.gabrieltechventures.scorekeeperpro.data


import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    suspend fun addPlayer(player: Player)

    suspend fun updatePlayer(player: Player)

    suspend fun deletePlayer(player: Player)

    fun getAllPlayers(): Flow<List<Player>>

    suspend fun getPlayerById(id:Int):Player?
    suspend fun updateAllPlayers(players:List<Player>, maximumScore:String)
    suspend fun updateAllPlayersScore(players: List<Player>, gamesWon:Int, currentScore:String)
    suspend fun updatePlayersWinStatus(players:List<Player>)
    suspend fun  updateAfterFinishedGame(
        players: List<Player>,
        currentScore: String,

        )
}
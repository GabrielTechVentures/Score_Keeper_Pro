package ro.gabrieltechventures.scorekeeperpro

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.forEach
import ro.gabrieltechventures.scorekeeperpro.data.Player
import ro.gabrieltechventures.scorekeeperpro.data.PlayerDao
import ro.gabrieltechventures.scorekeeperpro.data.PlayerRepository

class PlayerRepositoryImpl(
    private val dao: PlayerDao
)
    :PlayerRepository {
    override suspend fun addPlayer(player: Player) {
        dao.addPlayer(player)
    }

    override suspend fun updatePlayer(player: Player) {
        dao.updatePlayer(player)
    }

    override suspend fun deletePlayer(player: Player) {
        dao.deletePlayer(player)
    }

    override suspend fun updateAllPlayers(players:List<Player>, maximumScore:String) {
        players.forEach()
        {player->
            val updatedPlayer=player.copy(maximumScore = maximumScore)
            dao.updatePlayer(updatedPlayer)
        }

    }

    override fun getAllPlayers(): Flow<List<Player>> {
        return dao.getAllPlayers()
    }

    override suspend fun getPlayerById(id: Int): Player? {
        return dao.getPlayerById(id)
    }

}
package ro.gabrieltechventures.scorekeeperpro.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlayer(player: Player)

    @Update
    suspend fun updatePlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Query("Select * from `player-table`")
    fun getAllPlayers():Flow<List<Player>>

    @Query("Select * from `player-table` where id=:id")
    suspend fun getPlayerById(id:Int):Player?
}
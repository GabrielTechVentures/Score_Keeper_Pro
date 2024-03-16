package ro.gabrieltechventures.scorekeeperpro.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Player::class],
    version = 1
)
abstract class PlayerDatabase :RoomDatabase(){
    abstract val dao:PlayerDao
}
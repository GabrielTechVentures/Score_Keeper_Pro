package ro.gabrieltechventures.scorekeeperpro.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player-table")
data class Player(
    @ColumnInfo(name="games-won")
    var gamesWon:Int=0,
    @ColumnInfo(name = "is-winner")
    var hasWon:Boolean=false,
    @ColumnInfo(name = "player-name")
    var name:String="",
    @ColumnInfo(name = "maximum-score")
    var maximumScore:String="0",
    @ColumnInfo(name = "current-score")
    var currentScore:String="0",
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
)

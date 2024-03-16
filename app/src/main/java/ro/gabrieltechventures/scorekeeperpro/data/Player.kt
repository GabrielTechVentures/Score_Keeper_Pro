package ro.gabrieltechventures.scorekeeperpro.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player-table")
data class Player(

    @ColumnInfo(name = "player-name")
    var name:String="",

    @PrimaryKey(autoGenerate = true)
    val id:Int?=null
)

package ro.gabrieltechventures.scorekeeperpro.player_list

sealed class PlayerListEvent {
    object onAddPlayerBtnClick:PlayerListEvent()
    object addPlayerDismiss:PlayerListEvent()

    object onAddPlayerDoneBtnPressed:PlayerListEvent()
    data class onNameChange(val name:String):PlayerListEvent()

}
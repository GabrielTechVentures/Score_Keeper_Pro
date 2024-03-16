package ro.gabrieltechventures.scorekeeperpro.player_list

sealed class PlayerListEvent {
    object onAddPlayerBtnClick:PlayerListEvent()
    object addPlayerDismiss:PlayerListEvent()
}
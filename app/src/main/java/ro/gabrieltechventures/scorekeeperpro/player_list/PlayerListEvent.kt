package ro.gabrieltechventures.scorekeeperpro.player_list

import ro.gabrieltechventures.scorekeeperpro.data.Player

sealed class PlayerListEvent {
    object onAddPlayerBtnClick:PlayerListEvent()
    object addPlayerDismiss:PlayerListEvent()
    object onCustomizeScoreBtnPressed:PlayerListEvent()
    object onCustomizeScoreDialogDismiss:PlayerListEvent()
    object onInfoButtonClick:PlayerListEvent()

    object onAddPlayerDoneBtnPressed:PlayerListEvent()
    data class onNameChange(val name:String):PlayerListEvent()

    data class onCustomizeScoreChange(val score:String):PlayerListEvent()
    object onCustomizeDoneBtnClick:PlayerListEvent()
    object animationChange:PlayerListEvent()

}
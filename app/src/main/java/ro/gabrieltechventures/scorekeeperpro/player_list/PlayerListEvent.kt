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
    data class onEarnedPointsChange(val points:String):PlayerListEvent()

    data class onCustomizeScoreChange(val score:String):PlayerListEvent()
    object onCustomizeDoneBtnClick:PlayerListEvent()
    object animationChange:PlayerListEvent()
    data class onDeletePlayerClick(val player: Player):PlayerListEvent()
    data class onAddScoreBtnClick(val player: Player):PlayerListEvent()
    object onAddScoreDoneBtnClick:PlayerListEvent()
    object onAddScoreDialogDismiss:PlayerListEvent()
    object onResetScoreButtonClick:PlayerListEvent()
    object onResetScoreDialogDismiss:PlayerListEvent()
    object onResetScoreConfirmClick:PlayerListEvent()
    object onDeletePlayerDialogDismiss:PlayerListEvent()
    object onDeletePlayerConfirmClick:PlayerListEvent()

    data class onUpdatePlayerClick(val player: Player):PlayerListEvent()
    object onUpdatePlayerDialogDismiss:PlayerListEvent()
    data class onGamesWonChange(val games:String):PlayerListEvent()
    object onEditDoneBtnClick:PlayerListEvent()
    data class onWinnerBtnPressed(val player: Player):PlayerListEvent()
    object onFinishGameDialogDimiss:PlayerListEvent()
    object onFinishGameConfirmBtnPressed:PlayerListEvent()

}
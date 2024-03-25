package ro.gabrieltechventures.scorekeeperpro.ui.theme

import ro.gabrieltechventures.scorekeeperpro.data.Player

sealed class UiEvent {
    data class showToast(val message:String):UiEvent()

    }
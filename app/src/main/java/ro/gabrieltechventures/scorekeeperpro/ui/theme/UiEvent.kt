package ro.gabrieltechventures.scorekeeperpro.ui.theme

sealed class UiEvent {
    data class showToast(val message:String):UiEvent()
}
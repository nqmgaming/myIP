package app.pwhs.myip.presentation

sealed class HomeUIAction {
    data object OnRefresh : HomeUIAction()
}
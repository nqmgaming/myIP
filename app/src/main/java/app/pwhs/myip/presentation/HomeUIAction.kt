package app.pwhs.myip.presentation

sealed class HomeUIAction {
    data object OnRefresh : HomeUIAction()
    data class OnSearchIp(val query: String) : HomeUIAction()
}
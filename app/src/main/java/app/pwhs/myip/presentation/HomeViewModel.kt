package app.pwhs.myip.presentation

import androidx.lifecycle.ViewModel
import app.pwhs.myip.domain.repository.InternetProtocolRepository

class HomeViewModel(
    private val internetProtocolRepository: InternetProtocolRepository
) : ViewModel() {
}
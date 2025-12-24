package app.pwhs.myip.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.pwhs.myip.domain.entities.DeviceIPInfoEntity

@Composable
fun LocalIPCard(deviceIP: DeviceIPInfoEntity?) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            SectionTitle("Local IP")
            InfoRow("IPv4", deviceIP?.localIPv4)
            InfoRow("IPv6", deviceIP?.localIPv6)
        }
    }
}
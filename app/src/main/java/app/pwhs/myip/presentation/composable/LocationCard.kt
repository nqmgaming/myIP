package app.pwhs.myip.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.pwhs.myip.domain.entities.LocationEntity

@Composable
fun LocationCard(location: LocationEntity?) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            SectionTitle("Location")

            InfoRow("City", location?.city)
            InfoRow("Region", location?.region)
            InfoRow("Country", location?.country)
            InfoRow("Timezone", location?.timezone)
        }
    }
}

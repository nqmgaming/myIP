package app.pwhs.myip.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.pwhs.myip.domain.entities.LocationEntity

@Composable
fun LocationCard(location: LocationEntity?) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            SectionTitle("Location")

            InfoRow("City", location?.city)
            InfoRow("Latitude", location?.latitude.toString())
            InfoRow("Longitude", location?.longitude.toString())
            InfoRow("Region", location?.region)
            InfoRow("Country", location?.country)
            InfoRow("Timezone", location?.timezone)
        }
    }
}

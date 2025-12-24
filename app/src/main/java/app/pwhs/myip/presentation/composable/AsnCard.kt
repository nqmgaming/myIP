package app.pwhs.myip.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.pwhs.myip.domain.entities.AsnEntity

@Composable
fun AsnCard(asn: AsnEntity?) {
    Card {
        Column(modifier = Modifier.padding(16.dp)) {
            SectionTitle("Network")

            InfoRow("ASN", asn?.number?.toString())
            InfoRow("Organization", asn?.organization)
            InfoRow("ISP", asn?.isp)
        }
    }
}
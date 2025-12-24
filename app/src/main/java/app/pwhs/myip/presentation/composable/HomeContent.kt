package app.pwhs.myip.presentation.composable

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.pwhs.myip.presentation.HomeUIState

@Composable
fun HomeContent(
    state: HomeUIState,
    context: Context
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            PublicIPCard(state, context)
        }

        item {
            LocationCard(state.ipInfo?.location)
        }

        item {
            AsnCard(state.ipInfo?.asn)
        }

        item {
            LocalIPCard(state.deviceIP)
        }
    }
}
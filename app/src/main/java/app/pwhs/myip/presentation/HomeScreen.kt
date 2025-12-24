package app.pwhs.myip.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.pwhs.myip.R
import app.pwhs.myip.core.extension.getSVGAsset
import app.pwhs.myip.ui.theme.MyIPTheme
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.svg.SvgDecoder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.compose.viewmodel.koinViewModel
import androidx.core.net.toUri

@Destination<RootGraph>(
    start = true
)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    HomeUI(modifier = modifier, homeUIState = uiState, homeUIAction = viewModel::onAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUI(
    modifier: Modifier = Modifier,
    homeUIState: HomeUIState,
    homeUIAction: (HomeUIAction) -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My IP",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 20.sp
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                "https://github.com/nqmgaming/myIP.git".toUri()
                            )
                            context.startActivity(intent)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_github),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    homeUIAction(HomeUIAction.OnRefresh)
                },
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            if (homeUIState.isLoading) {
                CircularProgressIndicator()
            } else if (homeUIState.error != null) {
                Text(
                    text = "Error: ${homeUIState.error}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error,
                )
            } else {
                Text(
                    text = homeUIState.ipInfo?.ip?.ipAddress ?: "N/A",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 32.sp
                )
                Text(
                    text = homeUIState.ipInfo?.location?.city ?: "Unknown Location",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = homeUIState.ipInfo?.location?.country ?: "Unknown Country",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                val flag = context.getSVGAsset(
                    homeUIState.ipInfo?.location?.countryCode ?: "unknown.svg"
                )
                val imageLoader = ImageLoader.Builder(context)
                    .components {
                        add(SvgDecoder.Factory())
                    }
                    .build()
                AsyncImage(
                    model = flag,
                    imageLoader = imageLoader,
                    modifier = Modifier.size(72.dp),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    text = "Local IP",
                    fontWeight = FontWeight.SemiBold
                )

                Text(
                    text = "IPv4: ${homeUIState.deviceIP?.localIPv4 ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "IPv6: ${homeUIState.deviceIP?.localIPv6 ?: "N/A"}",
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    MyIPTheme {
        HomeUI(
            homeUIState = HomeUIState(),
            homeUIAction = {}
        )
    }
}
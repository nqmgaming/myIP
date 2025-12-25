package app.pwhs.myip.presentation

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.net.toUri
import app.pwhs.myip.R
import app.pwhs.myip.presentation.composable.HomeContent
import app.pwhs.myip.ui.theme.MyIPTheme
import com.mapbox.geojson.Point
import com.mapbox.maps.Style
import com.mapbox.maps.extension.compose.MapboxMap
import com.mapbox.maps.extension.compose.animation.viewport.rememberMapViewportState
import com.mapbox.maps.extension.compose.annotation.IconImage
import com.mapbox.maps.extension.compose.annotation.generated.PointAnnotation
import com.mapbox.maps.extension.compose.style.MapStyle
import com.mapbox.maps.extension.style.layers.properties.generated.IconAnchor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.compose.viewmodel.koinViewModel


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
    val scaffoldState = rememberBottomSheetScaffoldState()
    val context = LocalContext.current
    BottomSheetScaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                ),
                title = {
                    Text(
                        text = "My IP",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                actions = {
                    IconButton(
                        onClick = {
                            homeUIAction(HomeUIAction.OnRefresh)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh",
                            modifier = Modifier.size(24.dp)
                        )
                    }
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
        modifier = modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        sheetPeekHeight = 280.dp,
        sheetContent = {
            HomeContent(
                state = homeUIState,
                context = context,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 500.dp)
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val bitmap = ContextCompat
                .getDrawable(context, R.drawable.ic_map_marker)
                ?.toBitmap()
            if (homeUIState.isLoading) {
                CircularProgressIndicator()
            } else {
                MapboxMap(
                    modifier = Modifier
                        .fillMaxSize(),
                    mapViewportState = rememberMapViewportState {
                        setCameraOptions {
                            center(
                                Point.fromLngLat(
                                    homeUIState.ipInfo?.location?.longitude ?: 0.0,
                                    homeUIState.ipInfo?.location?.latitude ?: 0.0
                                )
                            )
                            zoom(16.0)
                        }
                    },
                    style = {
                        MapStyle(style = Style.SATELLITE_STREETS)
                    }
                ) {
                   bitmap?.let { bitmap ->
                       PointAnnotation(
                           point = Point.fromLngLat(
                               homeUIState.ipInfo?.location?.longitude ?: 0.0,
                               homeUIState.ipInfo?.location?.latitude ?: 0.0
                           )
                       ) {
                           iconSize = 1.2
                           iconAnchor = IconAnchor.BOTTOM
                           iconImage = IconImage(bitmap = bitmap)
                       }
                   }
                }
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
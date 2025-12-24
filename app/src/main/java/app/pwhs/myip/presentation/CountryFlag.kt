package app.pwhs.myip.presentation

import android.content.Context
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.pwhs.myip.core.extension.getSVGAsset
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.svg.SvgDecoder

@Composable
fun CountryFlag(
    context: Context,
    countryCode: String?
) {
    val flag = context.getSVGAsset(countryCode ?: "unknown.svg")

    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components { add(SvgDecoder.Factory()) }
            .build()
    }

    AsyncImage(
        model = flag,
        imageLoader = imageLoader,
        contentDescription = null,
        modifier = Modifier.size(56.dp)
    )
}

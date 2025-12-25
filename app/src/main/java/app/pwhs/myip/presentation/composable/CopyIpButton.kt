package app.pwhs.myip.presentation.composable

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun CopyIpButton(
    ip: String,
) {
    val clipboardManager = LocalClipboard.current
    val context = LocalContext.current
    OutlinedButton(
        onClick = {
            val clipData = ClipData.newPlainText("plain text", ip)
            clipboardManager.nativeClipboard.setPrimaryClip(clipData)
            Toast
                .makeText(context, "IP copied to clipboard", Toast.LENGTH_SHORT)
                .show()
        }
    ) {
        Icon(
            imageVector = Icons.Outlined.ContentCopy,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.width(8.dp))
        Text("Copy")
    }
}

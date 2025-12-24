package app.pwhs.myip.presentation.composable

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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp

@Composable
fun CopyIpButton(
    ip: String,
    clipboardManager: ClipboardManager,
    context: Context
) {
    OutlinedButton(
        onClick = {
            clipboardManager.setText(AnnotatedString(ip))
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

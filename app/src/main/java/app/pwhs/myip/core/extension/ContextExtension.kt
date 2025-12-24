package app.pwhs.myip.core.extension

import android.content.Context

fun Context.getSVGAsset(fileName: String): String {
    return "file:///android_asset/svg/${fileName.lowercase()}.svg"
}


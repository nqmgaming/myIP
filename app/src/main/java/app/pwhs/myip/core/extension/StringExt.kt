package app.pwhs.myip.core.extension

fun String.isValidIPv4(): Boolean {
    val parts = this.split(".")
    if (parts.size != 4) return false

    return parts.all { part ->
        if (part.isEmpty()) return false
        if (part.length > 1 && part.startsWith("0")) return false

        part.toIntOrNull()?.let { value ->
            value in 0..255
        } ?: false
    }
}

fun String.isPublicIPv4(): Boolean {
    if (!this.isValidIPv4()) return false

    val p = this.split(".").map { it.toInt() }
    val a = p[0]
    val b = p[1]

    return when {
        a == 0 -> false                     // 0.0.0.0/8
        a == 10 -> false                    // 10.0.0.0/8
        a == 127 -> false                   // loopback
        a == 169 && b == 254 -> false       // link-local
        a == 192 && b == 168 -> false       // private
        a == 172 && b in 16..31 -> false    // private
        a == 255 -> false                   // broadcast
        else -> true
    }
}

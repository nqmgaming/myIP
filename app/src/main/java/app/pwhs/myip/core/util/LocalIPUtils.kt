package app.pwhs.myip.core.util

import app.pwhs.myip.domain.entities.DeviceIPInfoEntity
import java.net.Inet4Address
import java.net.Inet6Address
import java.net.NetworkInterface

object LocalIPUtils {

    fun getLocalIPs(): DeviceIPInfoEntity {
        var ipv4: String? = null
        var ipv6: String? = null

        NetworkInterface.getNetworkInterfaces().toList().forEach { iface ->
            iface.inetAddresses.toList().forEach { addr ->
                if (!addr.isLoopbackAddress) {
                    when (addr) {
                        is Inet4Address if ipv4 == null ->
                            ipv4 = addr.hostAddress

                        is Inet6Address if ipv6 == null ->
                            ipv6 = null ?: addr.hostAddress?.substringBefore("%")
                    }
                }
            }
        }
        return DeviceIPInfoEntity(ipv4, ipv6)
    }
}

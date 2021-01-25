package com.narc.alibaba.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @author : Narcssus
 * @date : 2021/1/25 18:02
 */
@Slf4j
public class IpUtils {
    /**
     * 单网卡名称
     */
    private static final String NETWORK_CARD = "eth0";

    /**
     * 绑定网卡名称
     */
    private static final String NETWORK_CARD_BAND = "bond0";

    /**
     * Description: linux下获得本机IPv4 IP<br>
     */
    public static String getLocalIP() {
        String ip = "";
        try {
            Enumeration<NetworkInterface> e1 = NetworkInterface.getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = e1.nextElement();
                //单网卡或者绑定双网卡
                if ((NETWORK_CARD.equals(ni.getName()))
                        || (NETWORK_CARD_BAND.equals(ni.getName()))) {
                    Enumeration<InetAddress> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()) {
                        InetAddress ia = e2.nextElement();
                        if (ia instanceof Inet6Address) {
                            continue;
                        }
                        ip = ia.getHostAddress();
                    }
                    break;
                }
            }
        } catch (Exception e) {
            log.error("获取本机IP异常", e);
        }
        return ip;
    }
}

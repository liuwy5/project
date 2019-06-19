package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/*
127.xxx.xxx.xxx 属于"loopback" 地址，即只能你自己的本机可见，就是本机地址，比较常见的有127.0.0.1；
192.168.xxx.xxx 属于private 私有地址(site local address)，属于本地组织内部访问，只能在本地局域网可见。同样10.xxx.xxx.xxx、从172.16.xxx.xxx 到 172.31.xxx.xxx都是私有地址，也是属于组织内部访问；
169.254.xxx.xxx 属于连接本地地址（link local IP），在单独网段可用
从224.xxx.xxx.xxx 到 239.xxx.xxx.xxx 属于组播地址
比较特殊的255.255.255.255 属于广播地址
除此之外的地址就是点对点的可用的公开IPv4地址
 */
public class IpUtil {

    private final static Logger logger = LoggerFactory.getLogger(IpUtil.class);

    /**
     * 复杂网络环境不对 linux为127.0.1.1
     */
    public static String obtainIpSimple () {
        String ip = null;
        try {
            // // 不一定准确的IP拿法
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        logger.info("ip is " + ip);
        return ip;
    }

    public static String obtainIp() {
        try {
            InetAddress spareAddress = null;
            Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = (NetworkInterface) allNetInterfaces.nextElement();
                Enumeration inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (inetAddress != null && !inetAddress.isLoopbackAddress()) {
                        if (inetAddress.isSiteLocalAddress()) {
                            return inetAddress.getHostAddress();
                        } else if (spareAddress == null) {
                            spareAddress = inetAddress;
                        }
                    }
                }
            }

            if (spareAddress != null) {
                return spareAddress.getHostAddress();
            }
            // 如果没有发现 non-loopback地址.只能用最次选的方案
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }

            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

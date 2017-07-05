package com.sunder.xie.whats.sso.config;

import com.sunder.xie.whats.sso.log.WhatsLogger;

/**
 * Created by xieshengrong on 2017/5/24.
 */
public class WhatsIpConfig
        private static final transient WhatsLogger dbLogger = LoggerUtils.getLogger(GewaIpConfig.class);
        private static final String serverIp;
        private static final String hostname;
        private static final List<String> searchList = new ArrayList();
        private static final List<String> innerLocalIp;
        private static final List<String> officeIp = Arrays.asList(new String[] { "101.95.157.134", "124.74.105.54" });
        private static final List<String> nhIpList = Arrays
                .asList(new String[] { "180.153.135.116", "180.153.135.117", "180.153.135.118", "180.153.135.119",
                        "180.153.135.120", "180.153.135.121", "180.153.135.122", "180.153.135.123", "180.153.135.124",
                        "180.153.135.125", "180.153.135.126", "114.80.171.245", "114.80.171.247", "114.80.171.248",
                        "114.80.171.250", "114.80.171.251", "114.80.171.252", "114.80.171.253" });
        private static final List<String> aliyunIpList = new ArrayList();

        public static boolean isNanhuiIp(String ip) {
            return nhIpList.contains(ip);
        }

        public static boolean isAliyunIp(String ip) {
            return aliyunIpList.contains(ip);
        }

        public static boolean isGewaInnerIp(String ip) {
            Iterator arg0 = innerLocalIp.iterator();

            String inner;
            do {
                if (!arg0.hasNext()) {
                    return false;
                }

                inner = (String) arg0.next();
            } while (!StringUtils.startsWith(ip, inner));

            return true;
        }

        public static boolean isOfficeIp(String ip) {
            return officeIp.contains(ip);
        }

        public static final boolean isLocalIp(String ip) {
            return ip.contains("192.168.") || ip.equals("127.0.0.1") || ip.endsWith(":1");
        }

        public static final boolean isDevServer() {
            return serverIp.contains("192.168.") || serverIp.equals("127.0.0.1") || serverIp.endsWith(":1");
        }

        public static void filterIp(String ip) {
            if (!isGewaInnerIp(ip) && !isLocalIp(ip)) {
                throw new IllegalArgumentException("invalid ip");
            }
        }

        public static boolean allowOffice(String ip) {
            return isGewaInnerIp(ip) || isLocalIp(ip) || isOfficeIp(ip);
        }

        public static void filterOfficeIp(String ip) {
            if (!isGewaInnerIp(ip) && !isLocalIp(ip) && !isOfficeIp(ip)) {
                throw new IllegalArgumentException("invalid ip");
            }
        }

        public static boolean isGewaServerIp(String ip) {
            Iterator arg0 = searchList.iterator();

            String search;
            do {
                if (!arg0.hasNext()) {
                    return false;
                }

                search = (String) arg0.next();
            } while (!StringUtils.startsWith(ip, search));

            return true;
        }

        public static String[] getServerAddr() {
            Map hostMap = getServerAddrMap();
            Iterator arg0 = searchList.iterator();

            while (arg0.hasNext()) {
                String search = (String) arg0.next();
                Iterator arg2 = hostMap.keySet().iterator();

                while (arg2.hasNext()) {
                    String addr = (String) arg2.next();
                    if (addr.startsWith(search)) {
                        return new String[] { addr, (String) hostMap.get(addr) };
                    }
                }
            }

            return new String[] { "127.0.0.1", "localhost" };
        }

        private static Map<String, String> getServerAddrMap() {
            TreeMap hostMap = new TreeMap();

            try {
                Enumeration niList = NetworkInterface.getNetworkInterfaces();

                while (niList.hasMoreElements()) {
                    NetworkInterface ni = (NetworkInterface) niList.nextElement();
                    Enumeration addrList = ni.getInetAddresses();

                    while (addrList.hasMoreElements()) {
                        InetAddress addr = (InetAddress) addrList.nextElement();
                        if (addr instanceof Inet4Address) {
                            hostMap.put(addr.getHostAddress(), addr.getHostName());
                        }
                    }
                }
            } catch (Exception arg4) {
                ;
            }

            dbLogger.warn("SERVER-IP:" + hostMap);
            return hostMap;
        }

        public static String getServerip() {
            return serverIp;
        }

        public static String getHostname() {
            return hostname;
        }

static {
        searchList
        .addAll(Arrays.asList(new String[] { "192.168.2.", "192.168.8.", "192.168.3.", "172.28.", "172.22." }));
        searchList.add("10.47.");
        searchList.add("10.147.");
        innerLocalIp = new ArrayList();
        innerLocalIp.addAll(Arrays.asList(new String[] { "172.22.1.", "172.28." }));
        innerLocalIp.add("10.47.");
        innerLocalIp.add("10.147.");

        InputStream host;
        Throwable h;
        List e;
        try {
        host = GewaIpConfig.class.getClassLoader().getResourceAsStream("searchip.txt");
        h = null;

        try {
        if (host != null) {
        e = IOUtils.readLines(host);
        searchList.addAll(e);
        aliyunIpList.addAll(e);
        }
        } catch (Throwable arg31) {
        h = arg31;
        throw arg31;
        } finally {
        if (host != null) {
        if (h != null) {
        try {
        host.close();
        } catch (Throwable arg30) {
        h.addSuppressed(arg30);
        }
        } else {
        host.close();
        }
        }

        }
        } catch (Exception arg35) {
        ;
        }

        try {
        host = GewaIpConfig.class.getClassLoader().getResourceAsStream("inner_local_ip.txt");
        h = null;

        try {
        if (host != null) {
        e = IOUtils.readLines(host);
        innerLocalIp.addAll(e);
        }
        } catch (Throwable arg29) {
        h = arg29;
        throw arg29;
        } finally {
        if (host != null) {
        if (h != null) {
        try {
        host.close();
        } catch (Throwable arg28) {
        h.addSuppressed(arg28);
        }
        } else {
        host.close();
        }
        }

        }
        } catch (Exception arg33) {
        ;
        }

        String[] host1 = getServerAddr();
        serverIp = host1[0];
        dbLogger.warn("GetServerIP:" + serverIp);
        String h1 = "";

        try {
        h1 = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException arg27) {
        arg27.printStackTrace();
        }

        if (StringUtils.isBlank(h1)) {
        h1 = host1[1];
        }

        hostname = h1;
        }

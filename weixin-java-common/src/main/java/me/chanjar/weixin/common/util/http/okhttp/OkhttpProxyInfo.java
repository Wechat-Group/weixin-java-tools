package me.chanjar.weixin.common.util.http.okhttp;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by ecoolper on 2017/4/26.
 * Proxy information.
 */
public class OkhttpProxyInfo {
  /**
   * Proxy types.
   */
  public enum ProxyType {
    NONE, HTTP, SOCKS4, SOCKS5
  }

  private final String proxyAddress;
  private final int proxyPort;
  private final String proxyUsername;
  private final String proxyPassword;
  private final ProxyType proxyType;

  public OkhttpProxyInfo(ProxyType proxyType, String proxyHost, int proxyPort, String proxyUser, String proxyPassword) {
    this.proxyType = proxyType;
    this.proxyAddress = proxyHost;
    this.proxyPort = proxyPort;
    this.proxyUsername = proxyUser;
    this.proxyPassword = proxyPassword;
  }

  // ---------------------------------------------------------------- factory

  /**
   * Creates directProxy.
   */
  public static OkhttpProxyInfo directProxy() {
    return new OkhttpProxyInfo(ProxyType.NONE, null, 0, null, null);
  }

  /**
   * Creates SOCKS4 proxy.
   */
  public static OkhttpProxyInfo socks4Proxy(String proxyAddress, int proxyPort, String proxyUser) {
    return new OkhttpProxyInfo(ProxyType.SOCKS4, proxyAddress, proxyPort, proxyUser, null);
  }

  /**
   * Creates SOCKS5 proxy.
   */
  public static OkhttpProxyInfo socks5Proxy(String proxyAddress, int proxyPort, String proxyUser, String proxyPassword) {
    return new OkhttpProxyInfo(ProxyType.SOCKS5, proxyAddress, proxyPort, proxyUser, proxyPassword);
  }

  /**
   * Creates HTTP proxy.
   */
  public static OkhttpProxyInfo httpProxy(String proxyAddress, int proxyPort, String proxyUser, String proxyPassword) {
    return new OkhttpProxyInfo(ProxyType.HTTP, proxyAddress, proxyPort, proxyUser, proxyPassword);
  }

  // ---------------------------------------------------------------- getter

  /**
   * Returns proxy type.
   */
  public ProxyType getProxyType() {
    return proxyType;
  }

  /**
   * Returns proxy address.
   */
  public String getProxyAddress() {
    return proxyAddress;
  }

  /**
   * Returns proxy port.
   */
  public int getProxyPort() {
    return proxyPort;
  }

  /**
   * Returns proxy user name or <code>null</code> if
   * no authentication required.
   */
  public String getProxyUsername() {
    return proxyUsername;
  }

  /**
   * Returns proxy password or <code>null</code>.
   */
  public String getProxyPassword() {
    return proxyPassword;
  }

  /**
   * 返回 java.net.Proxy
   * @return
   */
  public Proxy getProxy() {
    Proxy proxy = null;
    if (getProxyType().equals(ProxyType.SOCKS5)) {
      proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(getProxyAddress(), getProxyPort()));
    } else if (getProxyType().equals(ProxyType.SOCKS4)) {
      proxy = new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(getProxyAddress(), getProxyPort()));
    } else if (getProxyType().equals(ProxyType.HTTP)) {
      proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(getProxyAddress(), getProxyPort()));
    } else if (getProxyType().equals(ProxyType.NONE)) {
      proxy = new Proxy(Proxy.Type.DIRECT, new InetSocketAddress(getProxyAddress(), getProxyPort()));
    }
    return proxy;
  }
}

package me.chanjar.weixin.common.util.http.okhttp;

import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by ecoolper on 2017/4/26.
 * Proxy information.
 */
public class OkHttpProxyInfo {
  private final String proxyAddress;
  private final int proxyPort;
  private final String proxyUsername;
  private final String proxyPassword;
  private final ProxyType proxyType;

  public OkHttpProxyInfo(ProxyType proxyType, String proxyHost, int proxyPort, String proxyUser, String proxyPassword) {
    this.proxyType = proxyType;
    this.proxyAddress = proxyHost;
    this.proxyPort = proxyPort;
    this.proxyUsername = proxyUser;
    this.proxyPassword = proxyPassword;
  }

  /**
   * Creates directProxy.
   */
  public static OkHttpProxyInfo directProxy() {
    return new OkHttpProxyInfo(ProxyType.NONE, null, 0, null, null);
  }

  // ---------------------------------------------------------------- factory

  /**
   * Creates SOCKS4 proxy.
   */
  public static OkHttpProxyInfo socks4Proxy(String proxyAddress, int proxyPort, String proxyUser) {
    return new OkHttpProxyInfo(ProxyType.SOCKS4, proxyAddress, proxyPort, proxyUser, null);
  }

  /**
   * Creates SOCKS5 proxy.
   */
  public static OkHttpProxyInfo socks5Proxy(String proxyAddress, int proxyPort, String proxyUser, String proxyPassword) {
    return new OkHttpProxyInfo(ProxyType.SOCKS5, proxyAddress, proxyPort, proxyUser, proxyPassword);
  }

  /**
   * Creates HTTP proxy.
   */
  public static OkHttpProxyInfo httpProxy(String proxyAddress, int proxyPort, String proxyUser, String proxyPassword) {
    return new OkHttpProxyInfo(ProxyType.HTTP, proxyAddress, proxyPort, proxyUser, proxyPassword);
  }

  /**
   * Returns proxy type.
   */
  public ProxyType getProxyType() {
    return proxyType;
  }

  // ---------------------------------------------------------------- getter

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

  /**
   * Proxy types.
   */
  public enum ProxyType {
    NONE, HTTP, SOCKS4, SOCKS5
  }
}

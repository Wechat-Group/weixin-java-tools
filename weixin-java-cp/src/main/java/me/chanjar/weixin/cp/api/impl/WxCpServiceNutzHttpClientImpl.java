package me.chanjar.weixin.cp.api.impl;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;

import org.nutz.http.Http;
import org.nutz.http.ProxySwitcher;
import org.nutz.http.Request;
import org.nutz.http.Sender;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;

import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;

public class WxCpServiceNutzHttpClientImpl extends BaseWxCpServiceImpl<Sender, ProxySwitcher> {

    private ProxySwitcher proxySwitcher;

    @Override
    public String getAccessToken(boolean forceRefresh) throws WxErrorException {
        if (!this.configStorage.isAccessTokenExpired() && !forceRefresh) {
            return this.configStorage.getAccessToken();
        }

        synchronized (this.globalAccessTokenRefreshLock) {
            String url = String.format(this.configStorage.getApiUrl(WxCpApiPathConsts.GET_TOKEN),
                                       this.configStorage.getCorpId(),
                                       this.configStorage.getCorpSecret());

            if (this.proxySwitcher != null) {
                Http.setProxySwitcher(proxySwitcher);
            }
            String resultContent;
            try {
                resultContent = Http.get(url).getContent();

            }
            catch (Exception e) {
                throw Lang.wrapThrow(e);
            }
            WxError error = WxError.fromJson(resultContent, WxType.CP);
            if (error.getErrorCode() != 0) {
                throw new WxErrorException(error);
            }

            WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
            this.configStorage.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
        }
        return this.configStorage.getAccessToken();
    }

    @Override
    public void initHttp() {
        if (this.configStorage.getHttpProxyHost() != null && this.configStorage.getHttpProxyPort() > 0) {
            String host = this.configStorage.getHttpProxyHost();
            int port = this.configStorage.getHttpProxyPort();
            this.proxySwitcher = new ProxySwitcher() {

                @Override
                public Proxy getProxy(Request req) {
                    return getProxy(req.getUrl());
                }

                @Override
                public Proxy getProxy(URL url) {
                    if (Strings.equalsIgnoreCase(url.getHost(), "qyapi.weixin.qq.com")) {
                        return new Proxy(Type.HTTP, new InetSocketAddress(host, port));
                    }
                    return null;
                }
            };
        }
    }

    @Override
    public WxCpConfigStorage getWxCpConfigStorage() {
        return this.configStorage;
    }

    @Override
    public Sender getRequestHttpClient() {
        return null;
    }

    @Override
    public ProxySwitcher getRequestHttpProxy() {
        return this.proxySwitcher;
    }

    @Override
    public HttpType getRequestType() {
        return HttpType.NUTZ_HTTP;
    }

}

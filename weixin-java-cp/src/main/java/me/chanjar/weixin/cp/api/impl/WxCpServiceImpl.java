package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.constant.WxCpApiPathConsts;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.concurrent.locks.Lock;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.GET_AGENT_CONFIG_TICKET;
import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.GET_JSAPI_TICKET;

/**
 * <pre>
 *  默认接口实现类，使用apache httpclient实现
 * Created by Binary Wang on 2017-5-27.
 * </pre>
 * <pre>
 * 增加分布式锁（基于WxCpConfigStorage实现）的支持
 * Updated by yuanqixun on 2020-05-13
 * </pre>
 *
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxCpServiceImpl extends WxCpServiceApacheHttpClientImpl {
  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!getWxCpConfigStorage().isAccessTokenExpired() && !forceRefresh) {
      return getWxCpConfigStorage().getAccessToken();
    }
    Lock lock = getWxCpConfigStorage().getAccessTokenLock();
    lock.lock();
    try {
      // 拿到锁之后，再次判断一下最新的token是否过期，避免重刷
      if (!getWxCpConfigStorage().isAccessTokenExpired() && !forceRefresh) {
        return getWxCpConfigStorage().getAccessToken();
      }
      String url = String.format(getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.GET_TOKEN), this.configStorage.getCorpId(), this.configStorage.getCorpSecret());
      try {
        HttpGet httpGet = new HttpGet(url);
        if (getRequestHttpProxy() != null) {
          RequestConfig config = RequestConfig.custom()
            .setProxy(getRequestHttpProxy()).build();
          httpGet.setConfig(config);
        }
        String resultContent;
        try (CloseableHttpClient httpClient = getRequestHttpClient();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {
          resultContent = new BasicResponseHandler().handleResponse(response);
        } finally {
          httpGet.releaseConnection();
        }
        WxError error = WxError.fromJson(resultContent, WxType.CP);
        if (error.getErrorCode() != 0) {
          throw new WxErrorException(error);
        }

        WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
        getWxCpConfigStorage().updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    } finally {
      lock.unlock();
    }
    return getWxCpConfigStorage().getAccessToken();
  }

  @Override
  public String getAgentJsapiTicket(boolean forceRefresh) throws WxErrorException {
    if (forceRefresh) {
      getWxCpConfigStorage().expireAgentJsapiTicket();
    }
    if (getWxCpConfigStorage().isAgentJsapiTicketExpired()) {
      Lock lock = getWxCpConfigStorage().getAgentJsapiTicketLock();
      lock.lock();
      try {
        // 拿到锁之后，再次判断一下最新的token是否过期，避免重刷
        if (getWxCpConfigStorage().isAgentJsapiTicketExpired()) {
          String responseContent = this.get(getWxCpConfigStorage().getApiUrl(GET_AGENT_CONFIG_TICKET), null);
          JsonObject jsonObject = GsonParser.parse(responseContent);
          getWxCpConfigStorage().updateAgentJsapiTicket(jsonObject.get("ticket").getAsString(),
            jsonObject.get("expires_in").getAsInt());
        }
      } finally {
        lock.unlock();
      }
    }
    return getWxCpConfigStorage().getAgentJsapiTicket();
  }

  @Override
  public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
    if (forceRefresh) {
      getWxCpConfigStorage().expireJsapiTicket();
    }

    if (getWxCpConfigStorage().isJsapiTicketExpired()) {
      Lock lock = getWxCpConfigStorage().getJsapiTicketLock();
      lock.lock();
      try {
        // 拿到锁之后，再次判断一下最新的token是否过期，避免重刷
        if (getWxCpConfigStorage().isJsapiTicketExpired()) {
          String responseContent = this.get(getWxCpConfigStorage().getApiUrl(GET_JSAPI_TICKET), null);
          JsonObject tmpJsonObject = GsonParser.parse(responseContent);
          getWxCpConfigStorage().updateJsapiTicket(tmpJsonObject.get("ticket").getAsString(),
            tmpJsonObject.get("expires_in").getAsInt());
        }
      } finally {
        lock.unlock();
      }
    }
    return getWxCpConfigStorage().getJsapiTicket();
  }
}

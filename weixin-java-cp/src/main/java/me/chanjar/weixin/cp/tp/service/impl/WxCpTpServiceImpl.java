package me.chanjar.weixin.cp.tp.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpProviderToken;

import java.util.concurrent.locks.Lock;

/**
 * <pre>
 *  默认接口实现类，使用apache httpclient实现
 * Created by zhenjun cai.
 * </pre>
 * <pre>
 *   实现分布式锁（基于WxCpTpRedissonConfigImpl存储引擎实现类）版本；
 *   主要封装了suiteAccessToken，corpAccessToken，suiteJsapiTicket，corpJsapiTicket等的获取方法
 *   Updated by zhangq <zhangq002@gmail.com> on 2021-02-13
 * </pre>
 *
 * @author zhenjun cai
 * @author zhangq
 */
@Slf4j
public class WxCpTpServiceImpl extends WxCpTpServiceApacheHttpClientImpl {

  @Override
  public WxAccessToken getSuiteAccessTokenEntity() throws WxErrorException {
    return this.getSuiteAccessTokenEntity(false);
  }

  @Override
  public WxAccessToken getSuiteAccessTokenEntity(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isSuiteAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getSuiteAccessTokenEntity();
    }

    // 此处configStorage推荐使用WxCpTpRedissonConfigImpl实现类，
    // 它底层采用了redisson提供的并发锁，会自动续期，无需担心异常中断导致的死锁问题，以及锁提前释放导致的并发问题
    Lock lock = this.configStorage.getSuiteAccessTokenLock();
    lock.lock();
    try {
      if (!this.configStorage.isSuiteAccessTokenExpired() && !forceRefresh) {
        return this.configStorage.getSuiteAccessTokenEntity();
      }

      super.getSuiteAccessToken(forceRefresh);
      return this.configStorage.getSuiteAccessTokenEntity();
    } finally {
      lock.unlock();
    }
  }

  /**
   * 复写父类方法，使其支持并发锁模式
   * @param forceRefresh
   * @return
   * @throws WxErrorException
   */
  @Override
  public String getSuiteAccessToken(boolean forceRefresh) throws WxErrorException {
    WxAccessToken suiteToken = this.getSuiteAccessTokenEntity(forceRefresh);
    return suiteToken.getAccessToken();
  }

  @Override
  public WxCpProviderToken getWxCpProviderTokenEntity() throws WxErrorException {
    return this.getWxCpProviderTokenEntity(false);
  }

  @Override
  public WxCpProviderToken getWxCpProviderTokenEntity(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isProviderTokenExpired() && !forceRefresh) {
      return this.configStorage.getProviderTokenEntity();
    }

    Lock lock = this.configStorage.getProviderAccessTokenLock();
    lock.lock();
    try {
      if (!this.configStorage.isProviderTokenExpired() && !forceRefresh) {
        return this.configStorage.getProviderTokenEntity();
      }

      return super.getWxCpProviderTokenEntity(forceRefresh);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public WxAccessToken getCorpToken(String authCorpId, String permanentCode) throws WxErrorException {
    return this.getCorpToken(authCorpId, permanentCode, false);
  }

  @Override
  public WxAccessToken getCorpToken(String authCorpId, String permanentCode, boolean forceRefresh)
    throws WxErrorException {
    if (!this.configStorage.isAccessTokenExpired(authCorpId) && !forceRefresh) {
      return this.configStorage.getAccessTokenEntity(authCorpId);
    }

    Lock lock = this.configStorage.getAccessTokenLock(authCorpId);
    lock.lock();
    try {
      if (!this.configStorage.isAccessTokenExpired(authCorpId) && !forceRefresh) {
        return this.configStorage.getAccessTokenEntity(authCorpId);
      }

      WxAccessToken accessToken = super.getCorpToken(authCorpId, permanentCode);
      this.configStorage.updateAccessToken(authCorpId, accessToken.getAccessToken(), accessToken.getExpiresIn());
      return accessToken;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public String getAuthCorpJsApiTicket(String authCorpId) throws WxErrorException {
    return this.getAuthCorpJsApiTicket(authCorpId, false);
  }

  @Override
  public String getAuthCorpJsApiTicket(String authCorpId, boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isAuthCorpJsApiTicketExpired(authCorpId) && !forceRefresh) {
      return this.configStorage.getAuthCorpJsApiTicket(authCorpId);
    }

    Lock lock = this.configStorage.getAuthCorpJsapiTicketLock(authCorpId);
    lock.lock();
    try {
      if (!this.configStorage.isAuthCorpJsApiTicketExpired(authCorpId) && !forceRefresh) {
        return this.configStorage.getAuthCorpJsApiTicket(authCorpId);
      }
      if (forceRefresh) {
        this.configStorage.expireAuthCorpJsApiTicket(authCorpId);
      }
      return super.getAuthCorpJsApiTicket(authCorpId);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public String getSuiteJsApiTicket(String authCorpId) throws WxErrorException {
    return this.getSuiteJsApiTicket(authCorpId, false);
  }

  @Override
  public String getSuiteJsApiTicket(String authCorpId, boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isAuthSuiteJsApiTicketExpired(authCorpId) && !forceRefresh) {
      return this.configStorage.getAuthSuiteJsApiTicket(authCorpId);
    }

    Lock lock = this.configStorage.getSuiteJsapiTicketLock(authCorpId);
    lock.lock();
    try {
      if (!this.configStorage.isAuthSuiteJsApiTicketExpired(authCorpId) && !forceRefresh) {
        return this.configStorage.getAuthSuiteJsApiTicket(authCorpId);
      }
      if (forceRefresh) {
        this.configStorage.expireAuthSuiteJsApiTicket(authCorpId);
      }
      return super.getSuiteJsApiTicket(authCorpId);
    } finally {
      lock.unlock();
    }
  }

}

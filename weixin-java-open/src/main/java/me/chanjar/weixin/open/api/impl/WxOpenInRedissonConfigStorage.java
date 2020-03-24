package me.chanjar.weixin.open.api.impl;

import java.util.concurrent.TimeUnit;
import org.redisson.api.RedissonClient;

/**
 * @author yangyidian
 * @date 2020/01/06
 **/
public class WxOpenInRedissonConfigStorage extends AbstractWxOpenInRedisConfigStorage{

    private RedissonClient redissonClient;

    public WxOpenInRedissonConfigStorage(RedissonClient redissonClient, String keyPrefix) {
        this.keyPrefix = keyPrefix;
        this.redissonClient = redissonClient;
    }

    public WxOpenInRedissonConfigStorage(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public String getComponentVerifyTicket() {
        Object value = redissonClient.getBucket(this.componentVerifyTicketKey).get();
        return value == null ? null : value.toString();
    }

    @Override
    public void setComponentVerifyTicket(String componentVerifyTicket) {
        redissonClient.getBucket(this.componentVerifyTicketKey).set(componentVerifyTicket);
    }

    @Override
    public String getComponentAccessToken() {
        Object value = redissonClient.getBucket(this.componentAccessTokenKey).get();
        return value == null ? null : value.toString();
    }

    @Override
    public boolean isComponentAccessTokenExpired() {
        return redissonClient.getBucket(this.componentAccessTokenKey).remainTimeToLive() < 2;
    }

    @Override
    public void expireComponentAccessToken() {
        redissonClient.getBucket(this.componentAccessTokenKey).expire(0, TimeUnit.SECONDS);
    }

    @Override
    public void updateComponentAccessToken(String componentAccessToken, int expiresInSeconds) {
        redissonClient.getBucket(this.componentAccessTokenKey).set(componentAccessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public String getAuthorizerRefreshToken(String appId) {
        Object value = redissonClient.getBucket(this.getKey(this.authorizerRefreshTokenKey, appId)).get();
        return value == null ? null : value.toString();
    }

    @Override
    public void setAuthorizerRefreshToken(String appId, String authorizerRefreshToken) {
        redissonClient.getBucket(this.getKey(this.authorizerRefreshTokenKey, appId)).set(authorizerRefreshToken);
    }

    @Override
    public String getAuthorizerAccessToken(String appId) {
        Object value = redissonClient.getBucket(this.getKey(this.authorizerAccessTokenKey, appId)).get();
        return value == null ? null : value.toString();
    }

    @Override
    public boolean isAuthorizerAccessTokenExpired(String appId) {
        return redissonClient.getBucket(this.getKey(this.authorizerAccessTokenKey, appId)).remainTimeToLive() < 2;
    }

    @Override
    public void expireAuthorizerAccessToken(String appId) {
        redissonClient.getBucket(this.getKey(this.authorizerAccessTokenKey, appId)).expire(0, TimeUnit.SECONDS);
    }

    @Override
    public void updateAuthorizerAccessToken(String appId, String authorizerAccessToken, int expiresInSeconds) {
        redissonClient.getBucket(this.getKey(this.authorizerAccessTokenKey, appId)).set(authorizerAccessToken, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public String getJsapiTicket(String appId) {
        Object value = redissonClient.getBucket(this.getKey(this.jsapiTicketKey, appId)).get();
        return value == null ? null : value.toString();
    }

    @Override
    public boolean isJsapiTicketExpired(String appId) {
        return redissonClient.getBucket(this.getKey(this.jsapiTicketKey, appId)).remainTimeToLive() < 2;
    }

    @Override
    public void expireJsapiTicket(String appId) {
        redissonClient.getBucket(this.getKey(this.jsapiTicketKey, appId)).expire(0, TimeUnit.SECONDS);
    }

    @Override
    public void updateJsapiTicket(String appId, String jsapiTicket, int expiresInSeconds) {
        redissonClient.getBucket(this.getKey(this.jsapiTicketKey, appId)).set(jsapiTicket, expiresInSeconds - 200, TimeUnit.SECONDS);
    }

    @Override
    public String getCardApiTicket(String appId) {
        Object value = redissonClient.getBucket(this.getKey(this.cardApiTicket, appId)).get();
        return value == null ? null : value.toString();
    }

    @Override
    public boolean isCardApiTicketExpired(String appId) {
        return redissonClient.getBucket(this.getKey(this.cardApiTicket, appId)).remainTimeToLive() < 2;
    }

    @Override
    public void expireCardApiTicket(String appId) {
        redissonClient.getBucket(this.getKey(this.cardApiTicket, appId)).expire(0 ,TimeUnit.SECONDS);
    }

    @Override
    public void updateCardApiTicket(String appId, String cardApiTicket, int expiresInSeconds) {
        redissonClient.getBucket(this.getKey(this.cardApiTicket, appId)).set(cardApiTicket, expiresInSeconds - 200, TimeUnit.SECONDS);
    }
}

package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

import java.util.Map;

/**
 * 微信消息拦截器，可以用来做验证
 *
 * @author Daniel Qian
 */
public interface WxMpMessageInterceptor {

    /**
     * 拦截微信消息
     *
     * @param wxMessage      the wx message
     * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
     * @param wxMpService    the wx mp service
     * @param sessionManager the session manager
     * @return true代表OK ，false代表不OK
     * @throws WxErrorException the wx error exception
     */
    boolean intercept(WxMpXmlMessage wxMessage,
                    Map<String, Object> context,
                    WxMpService wxMpService,
                    WxSessionManager sessionManager) throws WxErrorException;

}

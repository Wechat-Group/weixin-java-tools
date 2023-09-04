package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.result.WxMpUserBlacklistGetResult;

import java.util.List;

/**
 * The interface Wx mp user blacklist service.
 *
 * @author miller
 */
public interface WxMpUserBlacklistService {
    /**
     * <pre>
     * 获取公众号的黑名单列表
     * 详情请见http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1471422259_pJMWA&token=&lang=zh_CN
     * </pre>
     *
     * @param nextOpenid the next openid
     * @return the blacklist
     * @throws WxErrorException the wx error exception
     */
    WxMpUserBlacklistGetResult getBlacklist(String nextOpenid) throws WxErrorException;

    /**
     * <pre>
     *   拉黑用户
     *   详情请见http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1471422259_pJMWA&token=&lang=zh_CN
     * </pre>
     *
     * @param openidList the openid list
     * @throws WxErrorException the wx error exception
     */
    void pushToBlacklist(List<String> openidList) throws WxErrorException;

    /**
     * <pre>
     *   取消拉黑用户
     *   详情请见http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1471422259_pJMWA&token=&lang=zh_CN
     * </pre>
     *
     * @param openidList the openid list
     * @throws WxErrorException the wx error exception
     */
    void pullFromBlacklist(List<String> openidList) throws WxErrorException;
}

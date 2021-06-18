package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpContactSearch;
import me.chanjar.weixin.cp.bean.WxCpTpContactSearchResp;

/**
 * @author uianz
 * @description
 * @since 2020/12/23 下午 02:39
 */
public interface WxCpTpContactService {

    /**
     * https://work.weixin.qq.com/api/doc/90001/90143/91844
     * 通讯录单个搜索
     * @param wxCpTpContactSearch
     * @return
     * @throws WxErrorException
     */
    WxCpTpContactSearchResp contactSearch(WxCpTpContactSearch wxCpTpContactSearch) throws WxErrorException;
}

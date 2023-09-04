package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTpContactSearch;
import me.chanjar.weixin.cp.bean.WxCpTpContactSearchResp;

/**
 * The interface Wx cp tp contact service.
 *
 * @author uianz
 * @since 2020 /12/23 下午 02:39
 */
public interface WxCpTpContactService {

  /**
   * https://work.weixin.qq.com/api/doc/90001/90143/91844
   * 通讯录单个搜索
   *
   * @param wxCpTpContactSearch the wx cp tp contact search
   * @return wx cp tp contact search resp
   * @throws WxErrorException the wx error exception
   */
  WxCpTpContactSearchResp contactSearch(WxCpTpContactSearch wxCpTpContactSearch) throws WxErrorException;
}

package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpCorpGroupCorp;

import java.util.List;

/**
 * 企业互联相关接口
 *
 * @author libo <422423229@qq.com>
 * Created on 27/2/2023 9:57 PM
 */
public interface WxCpCorpGroupService {
  /**
   * List app share info list.
   *
   * @param agentId      the agent id
   * @param businessType the business type
   * @param corpId       the corp id
   * @param limit        the limit
   * @param cursor       the cursor
   * @return the list
   * @throws WxErrorException the wx error exception
   */
  List<WxCpCorpGroupCorp> listAppShareInfo(Integer agentId, Integer businessType, String corpId, Integer limit, String cursor) throws WxErrorException;

  /**
   * <pre>
   * 上下游 - 基础接口 - 获取下级/下游企业的access_token.
   * 详情请见: <a href="https://developer.work.weixin.qq.com/document/path/95816">...</a>
   * </pre>
   *
   * @param corpId 已授权的下级/下游企业corpid
   * @param agentId 已授权的下级/下游企业应用ID
   * @param businessType 填0则为企业互联/局校互联，填1则表示上下游企业
   * @return 获取到的下级/下游企业调用凭证，最长为512字节
   * @throws WxErrorException
   */
  String getToken(String corpId, Integer agentId, Integer businessType) throws WxErrorException;
}

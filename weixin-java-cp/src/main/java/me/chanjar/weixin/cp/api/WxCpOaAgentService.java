package me.chanjar.weixin.cp.api;

import lombok.NonNull;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.oa.selfagent.WxCpOpenApprovalData;

/**
 * 企业微信自建应用接口.
 * https://developer.work.weixin.qq.com/document/path/90269
 *
 * @author <a href="https://gitee.com/Wang_Wong/">Wang_Wong</a> created on  2022-04-06
 */
public interface WxCpOaAgentService {

  /**
   * 查询第三方应用审批申请当前状态
   * 开发者也可主动查询审批单的当前审批状态。
   * <p>
   * 请求方式： POST（HTTPS）
   * 请求地址： https://qyapi.weixin.qq.com/cgi-bin/corp/getopenapprovaldata?access_token=ACCESS_TOKEN
   *
   * @param thirdNo the third no
   * @return open approval data
   * @throws WxErrorException the wx error exception
   */
  WxCpOpenApprovalData getOpenApprovalData(@NonNull String thirdNo) throws WxErrorException;

}

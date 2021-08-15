package me.chanjar.weixin.cp.tp.service;

import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * <pre>
 *  任务卡片管理接口.
 *  Created by Jeff on 2019-05-16.
 * </pre>
 *
 * @author <a href="https://github.com/domainname">Jeff</a>
 * @date 2019 -05-16
 */
public interface WxCpTpTaskCardService {

  /**
   * <pre>
   * 更新任务卡片消息状态
   * 详情请见: https://work.weixin.qq.com/api/doc#90000/90135/91579
   *
   * 注意: 这个方法使用WxCpConfigStorage里的agentId
   * </pre>
   *
   * @param corpId      the corp id
   * @param userIds     企业的成员ID列表
   * @param taskId      任务卡片ID
   * @param replaceName 替换文案
   * @throws WxErrorException the wx error exception
   */
  void update(String corpId, List<String> userIds, String taskId, String replaceName) throws WxErrorException;
}

package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;

import java.util.List;

/**
 * <pre>
 *  任务卡片管理接口.
 *  Created by Jeff on 2019-05-16.
 *  Updted by HeXiao on 2022-03-09.
 * </pre>
 *
 * @author <a href="https://github.com/domainname">Jeff</a>
 * @date 2019-05-16
 */
public interface WxCpTaskCardService {

  /**
   * <pre>
   * 更新任务卡片消息状态
   * 详情请见: https://work.weixin.qq.com/api/doc#90000/90135/91579
   *
   * 注意: 这个方法使用WxCpConfigStorage里的agentId
   * </pre>
   *
   * @param userIds     企业的成员ID列表
   * @param taskId      任务卡片ID
   * @param replaceName 替换文案
   */
  void update(List<String> userIds, String taskId, String replaceName) throws WxErrorException;


  /**
   * 更新按钮为不可点击状态
   * 详情请见https://developer.work.weixin.qq.com/document/path/94888#%E6%9B%B4%E6%96%B0%E6%8C%89%E9%92%AE%E4%B8%BA%E4%B8%8D%E5%8F%AF%E7%82%B9%E5%87%BB%E7%8A%B6%E6%80%81
   * @param userIds    企业的成员ID列表
   * @param partyIds   企业的部门ID列表
   * @param tagIds    企业的标签ID列表
   * @param atAll     更新整个任务接收人员
   * @param responseCode  更新卡片所需要消费的code，可通过发消息接口和回调接口返回值获取，一个code只能调用一次该接口，且只能在24小时内调用
   * @param replaceName   需要更新的按钮的文案
   * @throws WxErrorException
   */
  void updateTemplateCardButton(List<String> userIds, List<Integer> partyIds,
                          List<Integer> tagIds, Integer atAll, String responseCode,
                          String replaceName) throws WxErrorException;

}

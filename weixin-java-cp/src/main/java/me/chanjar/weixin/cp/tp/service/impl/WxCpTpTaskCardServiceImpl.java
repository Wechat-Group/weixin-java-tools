package me.chanjar.weixin.cp.tp.service.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.tp.service.WxCpTpService;
import me.chanjar.weixin.cp.tp.service.WxCpTpTaskCardService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.TaskCard.UPDATE_TASK_CARD;

/**
 * <pre>
 *  任务卡片管理接口.
 *  Created by Jeff on 2019-05-16.
 * </pre>
 *
 * @author <a href="https://github.com/domainname">Jeff</a>
 * @date 2019-05-16
 */
@RequiredArgsConstructor
public class WxCpTpTaskCardServiceImpl implements WxCpTpTaskCardService {
  private final WxCpTpService mainService;

  @Override
  public void update(String corpId, List<String> userIds, String taskId, String replaceName) throws WxErrorException {
    Integer agentId = this.mainService.getWxCpTpConfigStorage().getAgentId(corpId);

    Map<String, Object> data = new HashMap<>(4);
    data.put("userids", userIds);
    data.put("agentid", agentId);
    data.put("task_id", taskId);
    data.put("replace_name", replaceName);

    String url = this.mainService.getCorpApiUrl(UPDATE_TASK_CARD, corpId);
    this.mainService.post(url, WxGsonBuilder.create().toJson(data));
  }
}

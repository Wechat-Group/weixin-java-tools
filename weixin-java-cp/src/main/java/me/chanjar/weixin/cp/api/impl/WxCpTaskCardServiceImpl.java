package me.chanjar.weixin.cp.api.impl;

import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.WxCpConsts;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.WxCpTaskCardService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class WxCpTaskCardServiceImpl implements WxCpTaskCardService {
  private final WxCpService mainService;

  @Override
  public void update(List<String> userIds, String taskId, String clickedKey) throws WxErrorException {
    Integer agentId = this.mainService.getWxCpConfigStorage().getAgentId();

    Map<String, Object> data = new HashMap<>(4);
    data.put("userids", userIds);
    data.put("agentid", agentId);
    data.put("task_id", taskId);
    data.put("clicked_key", clickedKey);

    String url = this.mainService.getWxCpConfigStorage().getApiUrl("/cgi-bin/message/update_taskcard");
    this.mainService.post(url, WxGsonBuilder.create().toJson(data));
  }
}

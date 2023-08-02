package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpOaAgentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.oa.selfagent.WxCpOpenApprovalData;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Oa.GET_OPEN_APPROVAL_DATA;

/**
 * 企业微信自建应用接口实现类.
 *
 * @author <a href="https://gitee.com/Wang_Wong/">Wang_Wong</a> created on  2022-04-06
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpOaAgentServiceImpl implements WxCpOaAgentService {
  private final WxCpService cpService;

  @Override
  public WxCpOpenApprovalData getOpenApprovalData(@NonNull String thirdNo) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("thirdNo", thirdNo);
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(GET_OPEN_APPROVAL_DATA);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpGsonBuilder.create()
      .fromJson(GsonParser.parse(responseContent).get("data"),
        new TypeToken<WxCpOpenApprovalData>() {
        }.getType()
      );
  }

}

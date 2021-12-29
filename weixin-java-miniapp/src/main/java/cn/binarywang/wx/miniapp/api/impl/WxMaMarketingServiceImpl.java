package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaMarketingService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.marketing.WxMaUserAction;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;

import java.util.List;

/**
 * @author <a href="https://github.com/184759547">184759547</a>
 * @Description :微信营销接口
 * @since : 2021/12/28
 */
@Slf4j
@RequiredArgsConstructor
public class WxMaMarketingServiceImpl implements WxMaMarketingService {
  private final WxMaService wxMaService;
  private final String USER_ACTION_SETS_ADD = "https://api.weixin.qq.com/marketing/user_action_sets/add?version=v1.0";
  private final String USER_ACTIONS_ADD = "https://api.weixin.qq.com/marketing/user_actions/add?version=v1.0";

  @Override
  public long addUserActionSets(String type, String name, String description) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("type", type);
    json.addProperty("name", name);
    json.addProperty("description", description);
    String responseContent = wxMaService.post(USER_ACTION_SETS_ADD, json.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("data").getAsJsonObject().get("user_action_set_id").getAsLong();
  }

  @Override
  public String addUserAction(List<WxMaUserAction> actions, Long userActionSetId) throws WxErrorException {
    return wxMaService.post(USER_ACTIONS_ADD, WxMaUserAction.listToJson(actions, userActionSetId));
  }
}

package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaLiveMemberService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Broadcast.Role;
import com.google.common.base.Joiner;
import com.google.gson.JsonArray;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.common.util.json.GsonParser;

import java.util.HashMap;
import java.util.Map;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Broadcast.Role.LIST_BY_ROLE;

/**
 * .
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2021-02-15
 */
@RequiredArgsConstructor
public class WxMaLiveMemberServiceImpl implements WxMaLiveMemberService {
  private final WxMaService service;

  @Override
  public String addRole(String username, int role) throws WxErrorException {
    return this.service.post(Role.ADD_ROLE,
      GsonHelper.buildJsonObject("username", username, "role", role));
  }

  @Override
  public String deleteRole(String username, int role) throws WxErrorException {
    return this.service.post(Role.DELETE_ROLE,
      GsonHelper.buildJsonObject("username", username, "role", role));
  }

  @Override
  public JsonArray listByRole(Integer role, Integer offset, Integer limit, String keyword) throws WxErrorException {
    Map<String, Object> params = new HashMap<>(8);
    params.put("role", role);
    params.put("offset", offset);
    params.put("limit", limit);
    params.put("keyword", keyword);
    final String response = this.service.get(LIST_BY_ROLE, Joiner.on("&").withKeyValueSeparator("=").join(params));
    return GsonParser.parse(response).getAsJsonArray("list");
  }
}

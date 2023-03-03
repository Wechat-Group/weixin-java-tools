package me.chanjar.weixin.cp.corpgroup.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpCorpGroupCorpGetTokenReq;
import me.chanjar.weixin.cp.corpgroup.service.WxCpCgService;
import me.chanjar.weixin.cp.corpgroup.service.WxCpLinkedCorpService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpAgentPerm;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpDepartment;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpUser;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.LinkedCorp.*;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.api.impl
 * @Description: 互联企业相关接口实现类
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 27/2/2023 10:02 PM
 */
@RequiredArgsConstructor
public class WxCpLinkedCorpServiceImpl implements WxCpLinkedCorpService {
  private final WxCpCgService cpCgService;

  @Override
  public WxCpLinkedCorpAgentPerm getLinkedCorpAgentPerm(WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    final String url = this.cpCgService.getWxCpCorpGroupConfigStorage().getApiUrl(GET_PERM_LIST);
    JsonObject jsonObject = new JsonObject();
    String responseContent = this.cpCgService.post(url, jsonObject.toString(), req);
    return WxCpGsonBuilder.create().fromJson(responseContent, WxCpLinkedCorpAgentPerm.class);
  }

  @Override
  public WxCpLinkedCorpUser getLinkedCorpUser(String userId, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    final String url = this.cpCgService.getWxCpCorpGroupConfigStorage().getApiUrl(GET_USER);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("userid", userId);
    String responseContent = this.cpCgService.post(url, jsonObject.toString(), req);
    JsonObject tmpJson = GsonParser.parse(responseContent);

    return WxCpGsonBuilder.create().fromJson(tmpJson.get("user_info"),
      new TypeToken<WxCpLinkedCorpUser>() {
      }.getType()
    );
  }

  @Override
  public List<WxCpLinkedCorpUser> getLinkedCorpSimpleUserList(String departmentId, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    final String url = this.cpCgService.getWxCpCorpGroupConfigStorage().getApiUrl(GET_USER_SIMPLELIST);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("department_id", departmentId);
    String responseContent = this.cpCgService.post(url, jsonObject.toString(),req);
    JsonObject tmpJson = GsonParser.parse(responseContent);

    return WxCpGsonBuilder.create().fromJson(tmpJson.get("userlist"),
      new TypeToken<List<WxCpLinkedCorpUser>>() {
      }.getType()
    );
  }

  @Override
  public List<WxCpLinkedCorpUser> getLinkedCorpUserList(String departmentId, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    final String url = this.cpCgService.getWxCpCorpGroupConfigStorage().getApiUrl(GET_USER_LIST);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("department_id", departmentId);
    String responseContent = this.cpCgService.post(url, jsonObject.toString(),req);
    JsonObject tmpJson = GsonParser.parse(responseContent);

    return WxCpGsonBuilder.create().fromJson(tmpJson.get("userlist"),
      new TypeToken<List<WxCpLinkedCorpUser>>() {
      }.getType()
    );
  }

  @Override
  public List<WxCpLinkedCorpDepartment> getLinkedCorpDepartmentList(String departmentId, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException {
    final String url = this.cpCgService.getWxCpCorpGroupConfigStorage().getApiUrl(GET_DEPARTMENT_LIST);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("department_id", departmentId);
    String responseContent = this.cpCgService.post(url, jsonObject.toString(),req);
    JsonObject tmpJson = GsonParser.parse(responseContent);

    return WxCpGsonBuilder.create().fromJson(tmpJson.get("department_list"),
      new TypeToken<List<WxCpLinkedCorpDepartment>>() {
      }.getType()
    );
  }
}

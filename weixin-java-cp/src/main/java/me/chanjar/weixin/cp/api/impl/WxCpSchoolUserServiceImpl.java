package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpSchoolUserService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;
import me.chanjar.weixin.cp.bean.school.user.WxCpCreateParentRequest;
import me.chanjar.weixin.cp.bean.school.user.WxCpUpdateParentRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.School.*;

/**
 * 企业微信家校沟通相关接口.
 * https://developer.work.weixin.qq.com/document/path/91638
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 * @date: 2022/6/18 9:10
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpSchoolUserServiceImpl implements WxCpSchoolUserService {

  private final WxCpService cpService;

  @Override
  public WxCpBaseResp createStudent(@NonNull String studentUserId, @NonNull String name, @NonNull List<Integer> departments) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(CREATE_STUDENT);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("student_userid", studentUserId);
    jsonObject.addProperty("name", name);
    JsonArray jsonArray = new JsonArray();
    for (Integer depart : departments) {
      jsonArray.add(new JsonPrimitive(depart));
    }
    jsonObject.add("department", jsonArray);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp deleteStudent(@NonNull String studentUserId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(DELETE_STUDENT) + studentUserId;
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp updateStudent(@NonNull String studentUserId, String newStudentUserId, String name, List<Integer> departments) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(UPDATE_STUDENT);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("student_userid", studentUserId);
    if (StringUtils.isNotEmpty(newStudentUserId)) {
      jsonObject.addProperty("new_student_userid", newStudentUserId);
    }
    if (StringUtils.isNotEmpty(name)) {
      jsonObject.addProperty("name", name);
    }
    if (departments != null && departments.size() > 0) {
      JsonArray jsonArray = new JsonArray();
      for (Integer depart : departments) {
        jsonArray.add(new JsonPrimitive(depart));
      }
      jsonObject.add("department", jsonArray);
    }
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp createParent(@NonNull WxCpCreateParentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(CREATE_PARENT);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp updateParent(@NonNull WxCpUpdateParentRequest request) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(UPDATE_PARENT);
    String responseContent = this.cpService.post(apiUrl, request.toJson());
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp deleteParent(@NonNull String userId) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(DELETE_PARENT) + userId;
    String responseContent = this.cpService.get(apiUrl, null);
    return WxCpBaseResp.fromJson(responseContent);
  }

  @Override
  public WxCpBaseResp setArchSyncMode(@NonNull Integer archSyncMode) throws WxErrorException {
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(SET_ARCH_SYNC_MODE);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("arch_sync_mode", archSyncMode);
    String responseContent = this.cpService.post(apiUrl, jsonObject.toString());
    return WxCpBaseResp.fromJson(responseContent);
  }

}

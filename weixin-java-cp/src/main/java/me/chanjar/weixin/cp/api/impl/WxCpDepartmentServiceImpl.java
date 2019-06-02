package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.cp.api.WxCpDepartmentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import java.util.List;

/**
 * <pre>
 *  部门管理接口
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxCpDepartmentServiceImpl implements WxCpDepartmentService {
  private WxCpService mainService;

  public WxCpDepartmentServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public Long create(WxCpDepart depart) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(WxCpDepartmentService.DEPARTMENT_CREATE);
    String responseContent = this.mainService.post(url, depart.toJson());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return GsonHelper.getAsLong(tmpJsonElement.getAsJsonObject().get("id"));
  }

  @Override
  public void update(WxCpDepart group) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(WxCpDepartmentService.DEPARTMENT_UPDATE);
    this.mainService.post(url, group.toJson());
  }

  @Override
  public void delete(Long departId) throws WxErrorException {
    String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpDepartmentService.DEPARTMENT_DELETE), departId);
    this.mainService.get(url, null);
  }

  @Override
  public List<WxCpDepart> list(Long id) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(WxCpDepartmentService.DEPARTMENT_LIST);
    if (id != null) {
      url += "?id=" + id;
    }

    String responseContent = this.mainService.get(url, null);
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(tmpJsonElement.getAsJsonObject().get("department"),
        new TypeToken<List<WxCpDepart>>() {
        }.getType()
      );
  }
}

package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpMenuService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

/**
 * <pre>
 * 菜单管理相关接口
 * Created by Binary Wang on 2017-6-25.
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * </pre>
 */
public class WxCpMenuServiceImpl implements WxCpMenuService {
  private WxCpService mainService;

  public WxCpMenuServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public void create(WxMenu menu) throws WxErrorException {
    this.create(this.mainService.getWxCpConfigStorage().getAgentId(), menu);
  }

  @Override
  public void create(Integer agentId, WxMenu menu) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?agentid=" + agentId;
    this.mainService.post(url, menu.toJson());
  }

  @Override
  public void delete() throws WxErrorException {
    this.delete(this.mainService.getWxCpConfigStorage().getAgentId());
  }

  @Override
  public void delete(Integer agentId) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/menu/delete?agentid=" + agentId;
    this.mainService.get(url, null);
  }

  @Override
  public WxMenu get() throws WxErrorException {
    return this.get(this.mainService.getWxCpConfigStorage().getAgentId());
  }

  @Override
  public WxMenu get(Integer agentId) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/menu/get?agentid=" + agentId;
    try {
      String resultContent = this.mainService.get(url, null);
      return WxCpGsonBuilder.create().fromJson(resultContent, WxMenu.class);
    } catch (WxErrorException e) {
      // 46003 不存在的菜单数据
      if (e.getError().getErrorCode() == 46003) {
        return null;
      }
      throw e;
    }
  }
}

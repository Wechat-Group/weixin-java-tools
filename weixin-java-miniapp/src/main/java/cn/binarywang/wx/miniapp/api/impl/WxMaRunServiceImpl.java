package cn.binarywang.wx.miniapp.api.impl;

import java.util.List;

import cn.binarywang.wx.miniapp.api.WxMaRunService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaRunStepInfo;
import cn.binarywang.wx.miniapp.util.crypt.WxMaCryptUtils;
import lombok.AllArgsConstructor;

/**
 * <pre>
 *
 * Created by Binary Wang on 2018/11/4.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@AllArgsConstructor
public class WxMaRunServiceImpl implements WxMaRunService {
  private WxMaService service;

  @Override
  public List<WxMaRunStepInfo> getRunStepInfo(String sessionKey, String encryptedData, String ivStr) {
    return WxMaRunStepInfo.fromJson(WxMaCryptUtils.decrypt(sessionKey, encryptedData, ivStr));
  }
}

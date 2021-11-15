package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenMaPrivacyService;
import me.chanjar.weixin.open.bean.ma.privacy.GetPrivacySettingResult;
import me.chanjar.weixin.open.bean.ma.privacy.SetPrivacySetting;
import me.chanjar.weixin.open.bean.ma.privacy.UploadPrivacyFileResult;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信第三方平台 小程序用户隐私保护指引接口
 * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/privacy_config/set_privacy_setting.html
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@AllArgsConstructor
public class WxOpenMaPrivacyServiceImpl implements WxOpenMaPrivacyService {

  private final WxMaService wxMaService;


  @Override
  public GetPrivacySettingResult getPrivacySetting(@Nullable Integer privacyVer) throws WxErrorException {
    Map<String, Object> params = new HashMap<>();
    if (privacyVer != null) {
      params.put("privacy_ver", privacyVer);
    }
    String json = wxMaService.post(OPEN_GET_PRIVACY_SETTING, params);
    return WxOpenGsonBuilder.create().fromJson(json, GetPrivacySettingResult.class);
  }

  @Override
  public void setPrivacySetting(SetPrivacySetting dto) throws WxErrorException {
    wxMaService.post(OPEN_SET_PRIVACY_SETTING, dto);
  }

  @SneakyThrows
  @Override
  public UploadPrivacyFileResult uploadPrivacyFile(String content) throws WxErrorException {
    // TODO 应实现通过InputStream上传的功能，一下代码暂时无法正常运行
//    ByteArrayInputStream data = new ByteArrayInputStream(content.getBytes("GBK"));
//    GenericUploadRequestExecutor executor = new GenericUploadRequestExecutor(wxMaService.getRequestHttp(), "POST", "file", "/temp.txt");
//    String json = wxMaService.execute(executor, OPEN_UPLOAD_PRIVACY_FILE, data);
//    return WxOpenGsonBuilder.create().fromJson(json, UploadPrivacyFileResult.class);
    throw new WxErrorException(new WxError(5003, "暂未实现用户隐私指引内容上传"));
  }
}

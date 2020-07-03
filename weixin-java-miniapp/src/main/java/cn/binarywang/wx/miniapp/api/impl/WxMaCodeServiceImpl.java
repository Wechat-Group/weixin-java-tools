package cn.binarywang.wx.miniapp.api.impl;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.util.json.GsonParser;
import org.apache.commons.lang3.StringUtils;

import cn.binarywang.wx.miniapp.api.WxMaCodeService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.code.WxMaCategory;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeAuditStatus;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeCommitRequest;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeSubmitAuditRequest;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeVersionDistribution;
import cn.binarywang.wx.miniapp.util.json.WxMaGsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.BaseMediaDownloadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.json.GsonHelper;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 20:00
 */
@AllArgsConstructor
public class WxMaCodeServiceImpl implements WxMaCodeService {

  private WxMaService wxMaService;

  @Override
  public void commit(WxMaCodeCommitRequest commitRequest) throws WxErrorException {
    this.wxMaService.post(COMMIT_URL, commitRequest.toJson());
  }

  @Override
  public byte[] getQrCode(String path) throws WxErrorException {
    String appId = this.wxMaService.getWxMaConfig().getAppid();
    Path qrCodeFilePath = null;
    try {
      RequestExecutor<File, String> executor = BaseMediaDownloadRequestExecutor
        .create(this.wxMaService.getRequestHttp(), Files.createTempDirectory("wxjava-ma-" + appId).toFile());

      final StringBuilder url = new StringBuilder(GET_QRCODE_URL);
      if (StringUtils.isNotBlank(path)) {
        url.append("?path=").append(URLEncoder.encode(path, StandardCharsets.UTF_8.name()));
      }

      qrCodeFilePath = this.wxMaService.execute(executor, url.toString(), null).toPath();
      return Files.readAllBytes(qrCodeFilePath);
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorMsg(e.getMessage()).build(), e);
    } finally {
      if (qrCodeFilePath != null) {
        try {
          // 及时删除二维码文件，避免积压过多缓存文件
          Files.delete(qrCodeFilePath);
        } catch (Exception ignored) {
        }
      }
    }
  }

  @Override
  public List<WxMaCategory> getCategory() throws WxErrorException {
    String responseContent = this.wxMaService.get(GET_CATEGORY_URL, null);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    boolean hasCategoryList = jsonObject.has("category_list");
    if (hasCategoryList) {
      return WxMaGsonBuilder.create().fromJson(jsonObject.getAsJsonArray("category_list"),
        new TypeToken<List<WxMaCategory>>() {
        }.getType());
    } else {
      return null;
    }
  }

  @Override
  public List<String> getPage() throws WxErrorException {
    String responseContent = this.wxMaService.get(GET_PAGE_URL, null);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    boolean hasPageList = jsonObject.has("page_list");
    if (hasPageList) {
      return WxMaGsonBuilder.create().fromJson(jsonObject.getAsJsonArray("page_list"),
        new TypeToken<List<String>>() {
        }.getType());
    } else {
      return null;
    }
  }

  @Override
  public long submitAudit(WxMaCodeSubmitAuditRequest auditRequest) throws WxErrorException {
    String responseContent = this.wxMaService.post(SUBMIT_AUDIT_URL, auditRequest.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    return GsonHelper.getLong(jsonObject, "auditid");
  }

  @Override
  public WxMaCodeAuditStatus getAuditStatus(long auditId) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("auditid", auditId);
    String responseContent = this.wxMaService.post(GET_AUDIT_STATUS_URL, param.toString());
    return WxMaCodeAuditStatus.fromJson(responseContent);
  }

  @Override
  public WxMaCodeAuditStatus getLatestAuditStatus() throws WxErrorException {
    String responseContent = this.wxMaService.get(GET_LATEST_AUDIT_STATUS_URL, null);
    return WxMaCodeAuditStatus.fromJson(responseContent);
  }

  @Override
  public void release() throws WxErrorException {
    this.wxMaService.post(RELEASE_URL, "{}");
  }

  @Override
  public void changeVisitStatus(String action) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("action", action);
    this.wxMaService.post(CHANGE_VISIT_STATUS_URL, param.toString());
  }

  @Override
  public void revertCodeRelease() throws WxErrorException {
    this.wxMaService.get(REVERT_CODE_RELEASE_URL, null);
  }

  @Override
  public WxMaCodeVersionDistribution getSupportVersion() throws WxErrorException {
    String responseContent = this.wxMaService.post(GET_SUPPORT_VERSION_URL, "{}");
    return WxMaCodeVersionDistribution.fromJson(responseContent);
  }

  @Override
  public void setSupportVersion(String version) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("version", version);
    this.wxMaService.post(SET_SUPPORT_VERSION_URL, param.toString());
  }

  @Override
  public void undoCodeAudit() throws WxErrorException {
    this.wxMaService.get(UNDO_CODE_AUDIT_URL, null);
  }
}

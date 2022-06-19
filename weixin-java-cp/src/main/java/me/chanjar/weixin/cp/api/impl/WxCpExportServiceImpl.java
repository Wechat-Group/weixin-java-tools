package me.chanjar.weixin.cp.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.cp.api.WxCpExportService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.export.WxCpExportRequest;
import me.chanjar.weixin.cp.bean.export.WxCpExportResult;
import me.chanjar.weixin.cp.util.json.WxCpGsonBuilder;

import static me.chanjar.weixin.cp.constant.WxCpApiPathConsts.Export.*;

/**
 * 异步导出接口
 *
 * @author <a href="https://github.com/zhongjun96">zhongjun</a>
 * @date 2022/4/21
 **/
@RequiredArgsConstructor
public class WxCpExportServiceImpl implements WxCpExportService {

  private final WxCpService mainService;

  @Override
  public String simpleUser(WxCpExportRequest params) throws WxErrorException {
    return export(SIMPLE_USER, params);
  }

  @Override
  public String user(WxCpExportRequest params) throws WxErrorException {
    return export(USER, params);
  }

  @Override
  public String department(WxCpExportRequest params) throws WxErrorException {
    return export(DEPARTMENT, params);
  }

  @Override
  public String tagUser(WxCpExportRequest params) throws WxErrorException {
    return export(TAG_USER, params);
  }

  @Override
  public WxCpExportResult getResult(String jobId) throws WxErrorException {
    String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(GET_RESULT), jobId);
    String responseContent = this.mainService.get(url, null);
    return WxCpGsonBuilder.create().fromJson(responseContent, WxCpExportResult.class);
  }

  private String export(String path, WxCpExportRequest params) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(path);
    String responseContent = this.mainService.post(url, params.toJson());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("jobid").getAsString();
  }
}

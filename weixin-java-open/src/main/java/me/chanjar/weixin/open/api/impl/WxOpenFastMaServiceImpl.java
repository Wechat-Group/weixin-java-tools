package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.json.GsonHelper;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenFastMaService;
import me.chanjar.weixin.open.bean.fastma.WxAccountBasicInfo;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.List;

/**
 * @author Hipple
 * @description
 * @since 2019/1/23 15:27
 */
public class WxOpenFastMaServiceImpl extends WxMaServiceImpl implements WxOpenFastMaService {

  private WxOpenComponentService wxOpenComponentService;
  private WxMaConfig wxMaConfig;
  private String appId;

  public WxOpenFastMaServiceImpl (WxOpenComponentService wxOpenComponentService, String appId, WxMaConfig wxMaConfig) {
    this.wxOpenComponentService = wxOpenComponentService;
    this.appId = appId;
    this.wxMaConfig = wxMaConfig;
    initHttp ();
  }

  @Override
  public WxMaConfig getWxMaConfig () {
    return wxMaConfig;
  }

  @Override
  public String getAccessToken (boolean forceRefresh) throws WxErrorException {
    return wxOpenComponentService.getAuthorizerAccessToken (appId, forceRefresh);
  }

  /**
   * 1.获取小程序的信息,GET请求
   * <pre>
   *     注意：这里不能直接用小程序的access_token
   * </pre>
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxAccountBasicInfo getAccountBasicInfo () throws WxErrorException {
    String response = get (OPEN_GET_ACCOUNT_BASIC_INFO, "");
    return WxOpenGsonBuilder.create ().fromJson (response, WxAccountBasicInfo.class);
  }

  /**
   * 2.小程序名称设置及改名
   *  TODO
   * @param nickname          昵称
   * @param idCard            身份证照片–临时素材mediaid(个人号必填)
   * @param license           组织机构代码证或营业执照–临时素材mediaid(组织号必填)
   * @param namingOtherStuff1 其他证明材料---临时素材 mediaid
   * @param namingOtherStuff2 其他证明材料---临时素材 mediaid
   * @throws WxErrorException
   */
  @Override
  public void setNickname (String nickname, String idCard, String license, String namingOtherStuff1, String namingOtherStuff2) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("nick_name", nickname);
    params.addProperty ("id_card", idCard);
    params.addProperty ("license", license);
    params.addProperty ("naming_other_stuff_1", namingOtherStuff1);
    params.addProperty ("naming_other_stuff_2", namingOtherStuff2);
    String response = post (OPEN_SET_NICKNAME, GSON.toJson (params));
  }

  /**
   * 4. 微信认证名称检测
   *  <pre>
   *      命中关键字策略时返回命中关键字的说明描述
   *  </pre>
   * @param nickname 名称
   * @throws WxErrorException
   */
  @Override
  public String checkWxVerifyNickname (String nickname) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("nick_name", nickname);
    String response = post (OPEN_CHECK_WX_VERIFY_NICKNAME, GSON.toJson (params));
    JsonObject resObj = new JsonParser ().parse(response).getAsJsonObject();
    return GsonHelper.getString (resObj, "wording");
  }

  /**
   * 5.修改头像
   * <pre>
   *     图片格式只支持：BMP、JPEG、JPG、GIF、PNG，大小不超过2M
   *      注：实际头像始终为正方形
   * </pre>
   *
   * @param headImgMediaId 头像素材media_id
   * @param x1             裁剪框左上角x坐标（取值范围：[0, 1]）
   * @param y1             裁剪框左上角y坐标（取值范围：[0, 1]）
   * @param x2             裁剪框右下角x坐标（取值范围：[0, 1]）
   * @param y2             裁剪框右下角y坐标（取值范围：[0, 1]）
   * @throws WxErrorException
   */
  @Override
  public void modifyHeadImage (String headImgMediaId, float x1, float y1, float x2, float y2) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("head_img_media_id", headImgMediaId);
    params.addProperty ("x1", x1);
    params.addProperty ("y1", y1);
    params.addProperty ("x2", x2);
    params.addProperty ("y2", y2);
    post (OPEN_MODIFY_HEADIMAGE, GSON.toJson (params));
  }

  /**
   * 6.修改功能介绍
   *
   * @param signature 简介：4-120字
   * @throws WxErrorException
   */
  @Override
  public void modifySignature (String signature) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("signature", signature);
    post (OPEN_MODIFY_SIGNATURE, GSON.toJson (params));
  }

  /**
   * 将字符串对象转化为GsonArray对象
   *
   * @param strList
   * @return
   */
  private JsonArray toJsonArray (List<String> strList) {
    JsonArray jsonArray = new JsonArray ();
    if (strList != null && ! strList.isEmpty ()) {
      for (String str : strList) {
        jsonArray.add (str);
      }
    }
    return jsonArray;
  }
}

package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenComponentService;
import me.chanjar.weixin.open.api.WxOpenFastMaService;
import me.chanjar.weixin.open.bean.fastma.WxFastMaCategory;
import me.chanjar.weixin.open.bean.result.*;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hipple
 * @description
 * @since 2019/1/23 15:27
 */
public class WxOpenFastMaServiceImpl extends WxMaServiceImpl implements WxOpenFastMaService {

  protected final Logger log = LoggerFactory.getLogger (this.getClass ());

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
  public WxFastMaAccountBasicInfoResult getAccountBasicInfo () throws WxErrorException {
    String response = get (OPEN_GET_ACCOUNT_BASIC_INFO, "");
    return WxOpenGsonBuilder.create ().fromJson (response, WxFastMaAccountBasicInfoResult.class);
  }

  /**
   * 2.小程序名称设置及改名
   *
   * @param nickname          昵称
   * @param idCard            身份证照片–临时素材mediaid(个人号必填)
   * @param license           组织机构代码证或营业执照–临时素材mediaid(组织号必填)
   * @param namingOtherStuff1 其他证明材料---临时素材 mediaid
   * @param namingOtherStuff2 其他证明材料---临时素材 mediaid
   * @throws WxErrorException
   */
  @Override
  public WxFastMaSetNickameResult setNickname (String nickname, String idCard, String license, String namingOtherStuff1, String namingOtherStuff2) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("nick_name", nickname);
    params.addProperty ("id_card", idCard);
    params.addProperty ("license", license);
    params.addProperty ("naming_other_stuff_1", namingOtherStuff1);
    params.addProperty ("naming_other_stuff_2", namingOtherStuff2);
    String response = post (OPEN_SET_NICKNAME, GSON.toJson (params));
    return WxOpenGsonBuilder.create ().fromJson (response, WxFastMaSetNickameResult.class);
  }

  /**
   * 3 小程序改名审核状态查询
   *
   * @param auditId 审核单id
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxFastMaQueryNicknameStatusResult querySetNicknameStatus (String auditId) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("audit_id", auditId);
    String response = post (OPEN_API_WXA_QUERYNICKNAME, GSON.toJson (params));
    return WxOpenGsonBuilder.create ().fromJson (response, WxFastMaQueryNicknameStatusResult.class);
  }

  /**
   * 4. 微信认证名称检测
   * <pre>
   *      命中关键字策略时返回命中关键字的说明描述
   *  </pre>
   *
   * @param nickname 名称
   * @throws WxErrorException
   */
  @Override
  public WxFastMaCheckNickameResult checkWxVerifyNickname (String nickname) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("nick_name", nickname);
    String response = post (OPEN_CHECK_WX_VERIFY_NICKNAME, GSON.toJson (params));
    return WxOpenGsonBuilder.create ().fromJson (response, WxFastMaCheckNickameResult.class);
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
  public WxOpenResult modifyHeadImage (String headImgMediaId, float x1, float y1, float x2, float y2) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("head_img_media_id", headImgMediaId);
    params.addProperty ("x1", x1);
    params.addProperty ("y1", y1);
    params.addProperty ("x2", x2);
    params.addProperty ("y2", y2);
    String response = post (OPEN_MODIFY_HEADIMAGE, GSON.toJson (params));
    return WxOpenGsonBuilder.create ().fromJson (response, WxOpenResult.class);
  }

  /**
   * 6.修改功能介绍
   *
   * @param signature 简介：4-120字
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult modifySignature (String signature) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("signature", signature);
    String response = post (OPEN_MODIFY_SIGNATURE, GSON.toJson (params));
    return WxOpenGsonBuilder.create ().fromJson (response, WxOpenResult.class);
  }

  /**
   * 7.3 管理员换绑
   *
   * @param taskid 换绑管理员任务序列号(公众平台最终点击提交回跳到第三方平台时携带)
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult componentRebindAdmin (String taskid) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("taskid", taskid);
    String response = post (OPEN_COMPONENT_REBIND_ADMIN, GSON.toJson (params));
    return WxOpenGsonBuilder.create ().fromJson (response, WxOpenResult.class);
  }

  /**
   * 8.1 获取账号可以设置的所有类目
   *
   * @return
   */
  @Override
  public WxFastMaCanSetCategoryResult getAllCategories () throws WxErrorException {
    String response = get (OPEN_GET_ALL_CATEGORIES, "");
    return WxOpenGsonBuilder.create ().fromJson (response, WxFastMaCanSetCategoryResult.class);
  }

  /**
   * 8.2添加类目
   *
   * @param categoryList
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult addCategory (List<WxFastMaCategory> categoryList) throws WxErrorException {
    Map<String, Object> map = new HashMap<> ();
    map.put ("categories", categoryList);
    String response = post (OPEN_ADD_CATEGORY, WxOpenGsonBuilder.create ().toJson (map));
    return WxOpenGsonBuilder.create ().fromJson (response, WxOpenResult.class);
  }

  /**
   * 8.3删除类目
   *
   * @param first  一级类目ID
   * @param second 二级类目ID
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult deleteCategory (int first, int second) throws WxErrorException {
    JsonObject params = new JsonObject ();
    params.addProperty ("first", first);
    params.addProperty ("Second", second);
    String response = post (OPEN_DELETE_CATEGORY, GSON.toJson (params));
    return WxOpenGsonBuilder.create ().fromJson (response, WxOpenResult.class);
  }

  /**
   * 8.4获取账号已经设置的所有类目
   *
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxFastMaBeenSetCategoryResult getCategory () throws WxErrorException {
    String response = get (OPEN_GET_CATEGORY, "");
    return WxOpenGsonBuilder.create ().fromJson (response, WxFastMaBeenSetCategoryResult.class);
  }

  /**
   * 8.5修改类目
   *
   * @param category 实体
   * @return
   * @throws WxErrorException
   */
  @Override
  public WxOpenResult modifyCategory (WxFastMaCategory category) throws WxErrorException {
    String response = post (OPEN_MODIFY_CATEGORY, GSON.toJson (category));
    return WxOpenGsonBuilder.create ().fromJson (response, WxOpenResult.class);
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

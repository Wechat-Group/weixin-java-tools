package me.chanjar.weixin.open.api;

import me.chanjar.weixin.common.bean.CommonUploadData;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.bean.auth.*;

/**
 * 微信第三方平台 小程序认证接口 (年审)
 * https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/product/weapp_wxverify.html
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on 2024/01/11
 */
public interface WxOpenMaAuthService {

  /**
   * 1 小程程序认证
   */
  String OPEN_MA_AUTH_SUBMIT = "https://api.weixin.qq.com/wxa/sec/wxaauth";

  /**
   * 2 小程程序认证任务进度查询.
   */
  String OPEN_MA_AUTH_QUERY = "https://api.weixin.qq.com/wxa/sec/queryauth";

  /**
   * 3 小程序认证上传补充材料.
   */
  String OPEN_MA_AUTH_UPLOAD = "https://api.weixin.qq.com/wxa/sec/uploadauthmaterial";

  /**
   * 4 小程序认证重新提审.
   */
  String OPEN_MA_AUTH_RESUBMIT = "https://api.weixin.qq.com/wxa/sec/reauth";

  /**
   * 5 查询个人认证身份选项列表.
   */
  String OPEN_MA_AUTH_IDENTITY = "https://api.weixin.qq.com/wxa/sec/authidentitytree";


  /**
   * 小程序认证（提审）
   *
   * @param param 参数
   * @return 提交结果，须保存任务ID 和 授权链接
   */
  MaAuthSubmitResult submit(MaAuthSubmitParam param) throws WxErrorException;


  /**
   * 进度查询
   *
   * @param taskId 任务ID，提交任务时返回
   */
  MaAuthQueryResult query(String taskId) throws WxErrorException;


  /**
   * 上传补充材料
   *
   * @param data 上传数据，仅支持png\jpeg\jpg\gif格式，文件后缀名如果填写不对会导致上传失败，建议写死1.jpg
   */
  MaAuthUploadResult upload(CommonUploadData data) throws WxErrorException;


  /**
   * 重新提审
   *
   * @param param 参数
   * @return 提交结果
   */
  MaAuthSubmitResult resubmit(MaAuthResubmitParam param) throws WxErrorException;


  /**
   * 查询个人认证身份选项列表
   *
   * @return 职业身份认证树
   */
  MaAuthQueryIdentityTreeResult queryIdentityTree() throws WxErrorException;
}

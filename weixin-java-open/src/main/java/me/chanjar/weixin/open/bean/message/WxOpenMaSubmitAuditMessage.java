package me.chanjar.weixin.open.bean.message;

import cn.binarywang.wx.miniapp.bean.code.WxMaCodeSubmitAuditItem;
import cn.binarywang.wx.miniapp.bean.code.WxMaCodeSubmitAuditPreviewInfo;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 微信小程序代码包提交审核(仅供第三方开发者代小程序调用)
 *
 * @author yqx
 * created on  2018/9/13
 */
@Data
public class WxOpenMaSubmitAuditMessage implements Serializable {
  private static final long serialVersionUID = 8881103449144288927L;

  /**
   * 提交审核项的一个列表（至少填写1项，至多填写5项）
   */
  @SerializedName("item_list")
  private List<WxMaCodeSubmitAuditItem> itemList;

  /**
   * 预览信息（小程序页面截图和操作录屏）
   */
  @SerializedName("preview_info")
  private WxMaCodeSubmitAuditPreviewInfo previewInfo;

  /**
   * 小程序版本说明和功能解释
   */
  @SerializedName("version_desc")
  private String versionDesc;

  /**
   * 反馈内容，不超过200字
   */
  @SerializedName("feedback_info")
  private String feedbackInfo;

  /**
   * 图片media_id列表，中间用“丨”分割，xx丨yy丨zz，不超过5张图片, 其中 media_id 可以通过新增临时素材接口上传而得到
   */
  @SerializedName("feedback_stuff")
  private String feedbackStuff;

  /**
   * 用于声明是否不使用“代码中检测出但是未配置的隐私相关接口”
   */
  @SerializedName("privacy_api_not_use")
  private Boolean privacyApiNotUse;

  /**
   * 订单中心path
   */
  @SerializedName("order_path")
  private String orderPath;
}

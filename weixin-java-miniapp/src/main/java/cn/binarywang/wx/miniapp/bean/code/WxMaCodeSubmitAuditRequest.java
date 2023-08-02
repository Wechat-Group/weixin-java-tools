package cn.binarywang.wx.miniapp.bean.code;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 提交审核的请求
 *
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-26 19:45
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxMaCodeSubmitAuditRequest implements Serializable {
  private static final long serialVersionUID = 8854979405505241314L;

  /**
   * 提交审核项的一个列表（至少填写1项，至多填写5项）
   */
  @SerializedName("item_list")
  private List<WxMaCodeSubmitAuditItem> itemList;

  /**
   * feedback_info	String	否	反馈内容，至多 200 字
   */
  @SerializedName("feedback_info")
  private String feedbackInfo;

  /**
   * feedback_stuff	String	否	用 | 分割的 media_id 列表，至多 5 张图片, 可以通过新增临时素材接口上传而得到
   */
  @SerializedName("feedback_stuff")
  private String feedbackStuff;

  /**
   * preview_info	Object	否	预览信息（小程序页面截图和操作录屏）
   */
  @SerializedName("preview_info")
  private WxMaCodeSubmitAuditPreviewInfo previewInfo;

  /**
   * version_desc
   * String
   * 否
   * 小程序版本说明和功能解释
   */
  @SerializedName("version_desc")
  private String versionDesc;

  /**
   * ugc_declare
   * Object
   * 否
   * 用户生成内容场景（UGC）信息安全声明
   */
  @SerializedName("ugc_declare")
  private WxMaCodeSubmitAuditUgcDeclare ugcDeclare;

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

  public String toJson() {
    return WxMaGsonBuilder.create().toJson(this);
  }

}

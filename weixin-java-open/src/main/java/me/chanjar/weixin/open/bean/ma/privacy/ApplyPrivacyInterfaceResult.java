package me.chanjar.weixin.open.bean.ma.privacy;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

/**
 * 获取接口列表 响应
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Getter
@Setter
public class ApplyPrivacyInterfaceResult extends WxOpenResult {

  /**
   * 审核ID
   */
  @SerializedName("audit_id")
  private Long auditId;

}

package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 获取隐私接口检测返回结果
 *
 * @author Yuan
 * @version 1.0.0
 * @date 2024-02-01 12:49:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaGetCodePrivacyInfoResult extends WxOpenResult {

  private static final long serialVersionUID = -2660090947103046607L;
  
  /**
   * 没权限的隐私接口的api英文名
   */
  @SerializedName("without_auth_list")
  private List<String> withoutAuthList;

  /**
   * 没配置的隐私接口的api英文名
   */
  @SerializedName("without_conf_list")
  private List<String> withoutConfList;

}

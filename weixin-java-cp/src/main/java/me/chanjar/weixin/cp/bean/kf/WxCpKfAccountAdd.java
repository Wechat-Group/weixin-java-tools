package me.chanjar.weixin.cp.bean.kf;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 添加客服帐号-请求参数
 *
 * @author Fu  created on  2022/1/19 18:59
 */
@NoArgsConstructor
@Data
public class WxCpKfAccountAdd implements Serializable {

  private static final long serialVersionUID = 3565729481246537411L;

  /**
   * 客服名称；不多于16个字符
   */
  @SerializedName("name")
  private String name;

  /**
   * 客服头像临时素材。可以调用上传临时素材接口获取。
   * 不多于128个字节
   */
  @SerializedName("media_id")
  private String mediaId;
}

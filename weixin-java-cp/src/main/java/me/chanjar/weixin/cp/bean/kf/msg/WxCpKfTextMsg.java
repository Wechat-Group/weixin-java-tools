package me.chanjar.weixin.cp.bean.kf.msg;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leiin
 * @date 2022/1/26 5:30 下午
 */
@NoArgsConstructor
@Data
public class WxCpKfTextMsg {

  /**
   * <pre>
   *   参数：content
   *   是否必须：是
   *   类型：string
   *   说明：消息内容，最长不超过2048个字节
   * </pre>
   */
  private String content;
  @SerializedName("menu_id")
  private String menuId;
}
